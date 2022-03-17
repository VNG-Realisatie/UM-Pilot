package um.vum.output;

//import org.apache.http.impl.client.HttpClients
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import static java.net.http.HttpClient.Builder.NO_PROXY;

/**
 * @author ronny
 * De connector die nadat is geauthenticeerd een JSON bestand met werkzoekende profielen naar UM kan versturen
 */
public class UmConnector {
	
	private String urlString;
	private String accessToken;	

	/**
	 * @param urlString De URL waarop het vertaalde bestand aangeboden kan worden 
	 * @param oin Het OIN van de organisatie
	 */
	public UmConnector(String urlString, String oin) {
		this.urlString = urlString +"/"+oin;
	}
	
	/**
	 * haal access token op om te verbinden met het UM API Portal
	 * @param loginUrl de URL om in te loggen
	 * @param username Het gebruikersnaam
	 * @param password Het paswoord
	 * @return boolean true als de authenticatie succesvol is
	 */
	public boolean authenticate(String loginUrl, String username, String password ) {
		// Voorbeeld URL: http://localhost:8080/auth/realms/poc-vng-realm/protocol/openid-connect/token
			   
		Map<String, String> parameters = new HashMap<>();
		parameters.put("grant_type", "password");
		parameters.put("client_id", "poc-vng-frontend");
		parameters.put("username", username);
		parameters.put("password", password);

		String body = parameters.entrySet()
		    .stream()
		    .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
		    .collect(Collectors.joining("&"));
		
		try {
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI(loginUrl))
					  //.version(HttpClient.Version.HTTP_2)
					  .header("Content-Type","application/x-www-form-urlencoded")
					  .POST(HttpRequest.BodyPublishers.ofString(body))
					  .build();
			

			HttpResponse<String> response = HttpClient.newHttpClient()
					  .send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) { 
				// Haal acces_token uit de reponse body
				// Geen mooie oplossing om een json object te bewerken maar het voldoet voor nu 
				String output = response.body().substring(response.body().indexOf("\"access_token\"")); // haal de data voor access_token weg
				output = output.substring(0, output.indexOf(",")); // haal de data na de token weg

				// wat er overblijft is "access_token" : "gGhibXLW65Opv2bGbv4eQZ7F5Fj3"
				accessToken = output.split(":")[1].replaceAll("\"", ""); 

				return true;
			} else {
				System.out.println("Er is een fout opgetreden tijdens de authenticatie met UM.");
				System.out.println("De HTTP status is "+ response.statusCode());
				System.out.println("Details: "+ response.body());		
			}
		} catch (URISyntaxException e) {
			System.err.println("Geen verbinding met Authentication-API. Melding: "+e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("Geen verbinding met Authentication-API. Melding: "+e.getLocalizedMessage());
		} catch (InterruptedException e) {
			System.err.println("Geen verbinding met Authentication-API. Melding: "+e.getLocalizedMessage());
		}
		return false;
	}

	/**
	 * stuur het naar json vertaalde werkzoekendebestand naar UM
	 * @param body het JSON object
	 * @return boolean true als het bestand succesvol verzonden is
	 */
	public boolean send(String body) {
		// Voorbeeld URL: http://localhost:8081/werkzoekende/lijst/00000001002220647000
		try {
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI(urlString))
					  //.version(HttpClient.Version.HTTP_2)
					  .header("Authorization", "Bearer "+accessToken)
					  .header("Content-Type", "application/json")
					  .POST(HttpRequest.BodyPublishers.ofString(body))
					  .build();
			HttpResponse<String> response = HttpClient.newHttpClient()
					  .send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) { 
				return true;
			} else {
				System.out.println("Er is een fout opgetreden tijdens het verzenden naar UM.");
				System.out.println("De HTTP status is "+ response.statusCode());
				System.out.println("Details: "+ response.body());		
			}
		} catch (URISyntaxException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
		} catch (InterruptedException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
		}
		return false;
	}

	/**
	 * stuur het naar json vertaalde werkzoekendebestand naar UM met gebruik
	 * van een PKI client-certificaat voor authenticatie
	 * @param body het JSON object
	 * @param certificateName de filenaam waar het te gebruiken certificaat is staat
	 * @return boolean true als het bestand succesvol verzonden is
	 */
	public boolean send(String body, String certificateName) {
		// Voorbeeld URL: http://localhost:8081/werkzoekende/lijst/00000001002220647000
		
		/*
		 * TODO Work in progress
		 * https://www.tutorialspoint.com/apache_httpclient/apache_httpclient_custom_ssl_context.htm
		 */
		
		String keyPassphrase = "";

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream("cert-key-pair.pfx"), keyPassphrase.toCharArray());

		SSLContext sslContext = SSLContext
		        .loadKeyMaterial(keyStore, null)
		        .build();

		try {
			SSLContext ctx;
			SSLParameters params;  
	        ctx = SSLContext.getDefault();
	        params = ctx.getDefaultSSLParameters();
	        //params.setProtocols(new String[] { "TLSv1.2" });

			HttpClient client = HttpClient.setSSLContext(sslContext).build();

			/* HttpClient client = HttpClient.newBuilder()
		                .proxy(NO_PROXY)
		                .authenticator(ca)
		                .sslContext(ctx)
		                .sslParameters(params)
		                .build();
			*/ 
			HttpRequest request = HttpRequest.newBuilder()
					  .uri(new URI(urlString))
					  //.version(HttpClient.Version.HTTP_2)
					  .header("Authorization", "Bearer "+accessToken)
					  .header("Content-Type", "application/json")
					  .POST(HttpRequest.BodyPublishers.ofString(body))
					  .build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) { 
				return true;
			} else {
				System.out.println("Er is een fout opgetreden tijdens het verzenden naar UM.");
				System.out.println("De HTTP status is "+ response.statusCode());
				System.out.println("Details: "+ response.body());		
			}
		} catch (URISyntaxException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
		} catch (IOException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
		} catch (InterruptedException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Geen verbinding met UM-API. Melding: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return false;
	}
	
}
