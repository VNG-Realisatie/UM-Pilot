package um.vum.input;

public class Arguments {

	private String filename;
	private String format;
	private String umURL;
	private String umAuthURL;
	private String oin;
	private String umPassword;
	private String umUsername;
	
	public Arguments(String[] args) {
		// Werken met een Configfile waar de parameters in staan
		if (args.length == 1 && args[0].substring(0,7).equalsIgnoreCase("-config")){
			String[] elementen = args[0].split("=");
				getConfigFile(elementen[1]);  					

		} else if (args.length > 0) {
			for (int i = 0; i< args.length; i++) {
				String[] elementen = args[i].split("=");
				switch (elementen[0].toUpperCase()) {
				case "-FILENAME":
					filename = elementen[1];						
					break;
				case "-FORMAT":
				    format = elementen[1];
				    break;
				case "-URL":
					umURL = (elementen[1]);
					break;
				case "-AUTHURL":
					umAuthURL = elementen[1];
					break;
				case "-OIN":
					oin = elementen[1];
					break;
				case "-PASSWORD":
					umPassword = elementen[1];
					break;
				case "-USERNAME":
					umUsername = elementen[1];
					break;
				default:
					System.out.println("Verkeerde parameters");
					showProgrammaArgumenten();							
				}
			}

		} else {
			showProgrammaArgumenten();
		}
		// controleer of de argumenten geldige waarden hebben 
		ckeckArguments();
	}

	public String getFilename() {
		return filename;
	}

	public String getFormat() {
		return format;
	}

	public String getUMAuthURL() {
		return umAuthURL; // "http://localhost:8080/auth/realms/poc-vng-realm/protocol/openid-connect/token";

	}

	public String getUMURL() {
		return umURL; // "http://localhost:8080/werkzoekende/lijst/";
	}

	public String getOIN() {
		return oin;
	}

	public String getUMUsername() {
		return umUsername;
	}

	public String getUMPassword() {
		return umPassword;
	}

	private static void showProgrammaArgumenten(){
		System.out.println("gebruik syntax: java -jar UM-VUMTranslator.jar [argumenten...]");
		System.out.println();
		System.out.println("mogelijke argumenten");
		//System.out.println(" -config   Een configfile die de parameters bevat");
		//System.out.println("           syntax -config=<bestandsnaam>");
	    //System.out.println("           De configfile bevat hierbij al de argumenten");
		//System.out.println("           Als de configfile niet bestaat, ");
		//System.out.println("           dan wordt een default configfile aangemaakt.");
		System.out.println(" -filename Het bestand wat u wilt aanbieden");
		System.out.println("           syntax -filename=<bestandsnaam>");
		System.out.println(" -format   Het format waarin het bestand wordt aangeboden");
		System.out.println("           syntax -format=<type>");
		System.out.println("           mogelijkse opties for type zijn \\\"Dummy\\\", \"Voorbeeld\" en \"SonarExcel\"");
		System.out.println(" -url      De URL voor de API werkzoekendeprofielen aanbieden");
		System.out.println("           syntax -url=<url naar de UM API>");
		System.out.println(" -oin      Het OIN van uw organisatie");
		System.out.println("           syntax -oin=<oin>");
		System.out.println(" -authurl  De URL voor de inloggen van UM");
		System.out.println("           syntax -authurl=<url naar de UM authenticatieserver>");
		System.out.println(" -username Gebruikersnaam voor inloggen op UM");
		System.out.println("           syntax -username=<gebruikersnaam>");
		System.out.println(" -password Wachtwoord voor inloggen op UM");
		System.out.println("           syntax -password=<wachtwoord>");
		
		System.exit(1);			
	}
	
	private void getConfigFile(String configfile) {
		System.out.println("Gevraagde configfile is "+configfile);
		// TODO Hier de ophalen van parameters uit een configfile nog uitwerken

		if (configfile.contains("dummy")) {
			filename = "BlancoDashboards.xlsx";
			format = "SONAREXCELIMPORT";
			umURL = "http://localhost:8081/werkzoekende/lijst/";
			umAuthURL = "http://localhost:8080/auth/realms/poc-vng-realm/protocol/openid-connect/token";
			oin = "12345";
			umUsername = "testdorp";
			umPassword = "password";
		}
	}
	
	private void ckeckArguments() {

		boolean argumentsSuccesful = true;

		// Controle of het invoer bestandsnaam wel is opgegeven
		if (filename == null) {
			System.err.println("Het te verwerken invoerbestand ontbreekt in de argumenten.");
			argumentsSuccesful = false;				
		} else {
			System.out.println("Verwerk bestand \""+ filename + "\" in formaat \""+ format +"\".");			
		}

			
		if(oin == null) {
		    System.out.println("Het Organisatie Indentificatie Nummer OIN() is niet opgegeven. Het resultaat wordt als bestand weggeschreven.");
		} else {
			// Controle op de URL's  
			if(umAuthURL == null || umURL == null) {
			    System.out.println("URL naar de Authenticatie server en/of de URL naar de API zijn niet opgegeven.");
				argumentsSuccesful = false;				
			} else {
				System.out.println("Authenticatie server \""+ umAuthURL + "\".");
			}
			
			// Controle op het gebruikersnaam en wachtwoord  
			if(umUsername == null | umPassword == null) {
				System.err.println("De username en/of password ontbreeken in de argumenten.");
				argumentsSuccesful = false;				
			} else {
				System.out.println("Authenticatie gebruiker \""+ umUsername + "\" met toegangscode \""+ "********" +"\".");
			}
		}
		
		if (!argumentsSuccesful) {
			// Sluit het programma af als een verplicht argument ontbreekt
			System.exit(1);						
		}

	}


}
