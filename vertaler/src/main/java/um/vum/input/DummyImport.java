package um.vum.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;

import um.vum.pojo.*;


 /**
 * @author ronny
 * DummyImport vult de array van werkzoekende POJO's zonder data te importeren
 * 
 *
 */
public class DummyImport implements Importer{
	
	private ArrayList<WerkzoekendeProfiel> werkzoekendeProfielen = new ArrayList<WerkzoekendeProfiel>();

	public DummyImport() {
	}

	@SuppressWarnings("unused")
	@Override
	public WerkzoekendeProfiel[] read(String filename) {
		
		System.out.println("Bezig met vullen van Werzoekende Objecten (Stap1/3).");
		
		WerkzoekendeProfiel wp = null;
		EisAanWerk eisAanWerk = null;
		Mobiliteit mobiliteit = null;
		Flexibiliteit flexibiliteit = null;
		Werktijden werktijden = null;
		Arbeidsmarktkwalificatie arbeidsmarktkwalificatie = null;
		ArrayList<Voorkeursland> voorkeurslanden = null;
		Voorkeursland voorkeursLand = null;
		ArrayList<Vervoermiddel> vervoermiddelen = null;
		Vervoermiddel vervoermiddel = null;
		ArrayList<Contractvorm> contractvormen = null;
		Contractvorm contractvorm = null;
		ArrayList<String> emailadressen = null;
		String emailadres = null;
		ArrayList<Webadres> webadressen = null;
		Webadres webadres = null;
		ArrayList<String> telefoonnummers = null;
		String telefoonnummer = null;
		ArrayList<ContactPersoon> contactpersonen = null;
		ContactPersoon cp = null;
		ArrayList<Sector> sectors = null;
		Sector sector = null;
		ArrayList<Bemiddelingsberoep> bemiddelingsberoepen = null;
		Bemiddelingsberoep bemiddelingsberoep = null;
		JSONObject jsonObject = null;

		// Schrijf de elementen weg in het POJO object van werkzoekende 
		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("12345"); // JSON: "idWerkzoekende":"12345"

		/*
		 * "toestemmingTotDelenProfiel:true",
		 * "idWerkzoekende":"12345",
		 * "indicatieLdrRegistratie":2,
		 * "indicatieBeschikbaarheidContactgegevens":1,
		 * "persoonlijkePresentatie":"Ik ben een dummygebruik op zoek naar werk.",
		 * "telefoonnummer":["088-7777777"],
		 * "emailadres":["Lian.Doe@testdorp.org"]}
		 */

		// Een profieleigenaar moet toestemming hebben gegeven voor het delen van het profiel 
		wp.setToestemmingTotDelenProfiel(true);
		wp.setIndicatieLdrRegistratie(WerkzoekendeProfiel.Indicatie.NEE.label);
		wp.setIndicatieBeschikbaarheidContactgegevens(WerkzoekendeProfiel.Indicatie.JA.label);
		wp.setPersoonlijkePresentatie("Ik ben een dummygebruik op zoek naar werk.");
		String[] tel = new String[]{"088-7777777"}; 
		wp.setTelefoonnummer(tel); 
	    String[] ea = new String[]{"Lian.Doe@testdorp.org"};
		wp.setEmailadres(ea);

		/*
		 * "werktijden":{
		 *    "aantalWerkurenPerWeekMaximaal":40,
		 *    "aantalWerkurenPerWeekMinimaal":24,
		 *    "indicatieKantoortijden":1
		 * }
         */

		werktijden = new Werktijden();
		werktijden.setIndicatieKantoortijden(Werktijden.IndicatieKantoortijden.JA.label);
		werktijden.setAantalWerkurenPerWeekMinimaal(24);
		werktijden.setAantalWerkurenPerWeekMaximaal(40);
		wp.setWerktijden(werktijden);

		/*
		 * "contractvorm":[
		 *    {"codeTypeArbeidscontract":"B",
		 *     "codeTypeOvereenkomst":"01"
		 *    },
		 *    {"codeTypeArbeidscontract":"O",
		 *     "codeTypeOvereenkomst":"01"
		 *    }
		 * ]
		 */
	
		contractvormen = new ArrayList<Contractvorm>();
		contractvorm = new Contractvorm();
		contractvorm.setCodeTypeArbeidscontract(Contractvorm.CodeArbeidscontract.BEPAALDE_TIJD.label);
		contractvorm.setCodeTypeOvereenkomst(Contractvorm.TypeOvereenkomst.REGULIERE_VACATURE.label);
		contractvormen.add(contractvorm);
		contractvorm = new Contractvorm();
		contractvorm.setCodeTypeArbeidscontract(Contractvorm.CodeArbeidscontract.ONBEPAALDE_TIJD.label);
		contractvorm.setCodeTypeOvereenkomst(Contractvorm.TypeOvereenkomst.REGULIERE_VACATURE.label);
		contractvormen.add(contractvorm);
		wp.setContractvorm(contractvormen.toArray(new Contractvorm[0]));	

		/* 
		 * "webadres":[
		 *     {"url":"www.testdorp.org/ProfielHenk",
		 *      "codeWebadres":2
		 *     }
		 * ]
		 */

		webadressen = new ArrayList<Webadres>();
		webadres = new Webadres();
		webadres.setCodeWebadres(Webadres.CodeWebadres.CV.label);
		webadres.setUrl("www.testdorp.org/ProfielHenk");
		webadressen.add(webadres);
		wp.setWebadres(webadressen.toArray(new Webadres[0])); 

		/*
		 * "contactPersoon":[
		 *    {"naamContactpersoonAfdeling":"Sociale Zaken",
		 *     "telefoonnummer":"088-1234567",
		 *     "emailadres":"Henk.Doe@testdorp.org"
		 *    }
		 *  ]
		 */

		contactpersonen = new ArrayList<ContactPersoon>();
		cp = new ContactPersoon();
		cp.setNaamContactpersoonAfdeling("Sociale Zaken");
		cp.setTelefoonnummer("088-1234567");
		cp.setEmailadres("Henk.Doe@testdorp.org");
		contactpersonen.add(cp);
		wp.setContactpersoon(contactpersonen.toArray(new ContactPersoon[0]));		
		werkzoekendeProfielen.add(wp);

        /*
         * "bemiddelingsberoep":[
         *      { "naamBeroepOngecodeerd":"Metselaar"
         *      },
         *      { "omschrijvingBeroepsnaam":"Timmerman",
         *        "codeBeroepsnaam":"A12355"
         *      }
         *  ]
         */	

		bemiddelingsberoepen =new ArrayList<Bemiddelingsberoep>();
		bemiddelingsberoep = new Bemiddelingsberoep();
		bemiddelingsberoep.setNaamBeroepOngecodeerd("Metselaar");
		bemiddelingsberoepen.add(bemiddelingsberoep);
		
		bemiddelingsberoep = new Bemiddelingsberoep();
		bemiddelingsberoep.setCodeBeroepsnaam("A12355");
		bemiddelingsberoep.setOmschrijvingBeroepsnaam("Timmerman");		
		bemiddelingsberoepen.add(bemiddelingsberoep);

		wp.setBemiddelingsberoep(bemiddelingsberoepen.toArray(new Bemiddelingsberoep[0]));		
		
		/*
		 * "sector":[
		 *    {
		 *      "codeSbi":99999
		 *    }
		 *  ]
		 */

		sectors =new ArrayList<Sector>();
		sector = new Sector();
		sector.setCodeSbi(99999);
		sectors.add(sector);
		wp.setSector(sectors.toArray(new Sector[0]));
	

		/*
		 * "voorkeursLand":[
		 *    {
		 *      "landencodeISO":"NL"
		 *    },
		 *    {
		 *      "landencodeISO":"BE"
		 *    }
		 * ]
		 */

		voorkeurslanden = new ArrayList<Voorkeursland>();
		voorkeursLand = new Voorkeursland();
		voorkeursLand.setLandencodeIso("NL");
		voorkeurslanden.add(voorkeursLand);
		voorkeursLand = new Voorkeursland();
		voorkeursLand.setLandencodeIso("BE");
		voorkeurslanden.add(voorkeursLand);
		wp.setVoorkeursland(voorkeurslanden.toArray(new Voorkeursland[0]));		

		/*
		 * "eisAanWerk":{
		 *    "indicatieWerkvariatie":0,
		 *    "indicatieAanpassingWerkomgeving":1,
		 *    "indicatieBegeleiding":2
		 * }
		 */
	
		eisAanWerk = new EisAanWerk();
		eisAanWerk.setIndicatieAanpassingWerkomgeving(1);
		eisAanWerk.setIndicatieBegeleiding(2);
		eisAanWerk.setIndicatieWerkvariatie(0);
		wp.setEisAanWerk(eisAanWerk);

		/*
		 * "mobiliteit":{
		 *     "maximaleReisafstand":15,
		 *     "maximaleReistijd":30,
		 *     "bemiddelingspostcode":"1234AA"
		 *  }
		 */

		mobiliteit = new Mobiliteit();
		mobiliteit.setBemiddelingspostcode("1234aa");
		mobiliteit.setMaximaleReisafstand(15);
		mobiliteit.setMaximaleReistijd(30);
		wp.setMobiliteit(mobiliteit);


		/*
		 * "flexibiliteit":{
		 *    "indicatieOnregelmatigWerkOfPloegendienst":2,
		 *    "codeRegiostraal":8,
		 *    "datumEindeBeschikbaarVoorWerk":"2023-02-28",
		 *    "datumAanvangBeschikbaarVoorWerk":"2022-01-01"
		 * }
		 */

 		flexibiliteit = new Flexibiliteit();
		flexibiliteit.setCodeRegiostraal(8);
		flexibiliteit.setIndicatieOnregelmatigWerkOfPloegendienst(2);
		try {
		flexibiliteit.setDatumAanvangBeschikbaarVoorWerk(LocalDate.of(2022,01,01));
		} catch (Exception e) {
			System.out.println("Ongeldige datum DatumAanvangBeschikbaarVoorWerk");
		}
		try {
		flexibiliteit.setDatumEindeBeschikbaarVoorWerk(LocalDate.of(2023,02,28));
		} catch (Exception e) {
			System.out.println("Ongeldige datum DatumEindeBeschikbaarVoorWerk");
		}
		wp.setFlexibiliteit(flexibiliteit);

		/*
		 * "vervoermiddel": [
		 *     {
		 *        "codeVervoermiddel":1,
		 *        "indicatieBeschikbaarVoorWoonWerkverkeer":1,
		 *        "indicatieBeschikbaarVoorUitvoeringWerk":2
		 *     }
		 *  ]
		 */

		vervoermiddelen = new ArrayList<Vervoermiddel>();
		vervoermiddel = new Vervoermiddel();
		vervoermiddel.setCodeVervoermiddel(1);
		vervoermiddel.setIndicatieBeschikbaarVoorUitvoeringWerk(2);
		vervoermiddel.setIndicatieBeschikbaarVoorWoonWerkverkeer(1);
		vervoermiddelen.add(vervoermiddel);
		wp.setVervoermiddel(vervoermiddelen.toArray(new Vervoermiddel[0]));		
		
		/*
		 * "arbeidsmarktkwalificatie":{
		 *    "codeWerkEnDenkniveauWerkzoekende":4,
		 *    .....
		 *  }
		 */
		arbeidsmarktkwalificatie = new Arbeidsmarktkwalificatie();

		arbeidsmarktkwalificatie.setCodeWerkEnDenkniveauWerkzoekende(4);

		/*
		 * "arbeidsmarktkwalificatie":{
		 *    "werkervaring": [
		 *        {
		 *           "naamOrganisatie":"Acme installaties BV",
		 *           "datumAanvangWerkzaamheden":"2002-05-01"
		 *           "datumEindeWerkzaamheden":"2021-12-31",
		 *           "beroep":
		 *              {
		 *                 "naamBeroepOngecodeerd":"Kozijnenbouwer"
		 *              },
		 *           "toelichtingWerkervaring":"Fijne baan. Jammer dat bedrijf beeindigd is",
		 *        }
		 *     ]
		 *  }
	     */
		
		ArrayList<Werkervaring> werkervaringen = new ArrayList<Werkervaring>();
		Werkervaring werkervaring = new Werkervaring();
		Beroep beroep = new Beroep();
		//beroep.setCodeBeroepsnaam("A12355");
		//beroep.setOmschrijvingBeroepsnaam("Timmerman");
		beroep.setNaamBeroepOngecodeerd("Kozijnenmaker");
		werkervaring.setBeroep(beroep);
		werkervaring.setDatumAanvangWerkzaamheden(LocalDate.of(2002,5,1));
		werkervaring.setDatumEindeWerkzaamheden(LocalDate.of(2021,12,31 ));
		werkervaring.setNaamOrganisatie("Acme installaties BV");
		werkervaring.setToelichtingWerkervaring("Fijne baan. Jammer dat bedrijf beeindigd is");
		werkervaringen.add(werkervaring);
		arbeidsmarktkwalificatie.setWerkervaring(werkervaringen.toArray(new Werkervaring[0]));		

	    /*
	     * "arbeidsmarktkwalificatie":{
	     *    "rijbewijs": [
	     *        {"codeSoortRijbewijs" : "B"},
	     *        {"codeSoortRijbewijs" : "BE"},
	     *        {"codeSoortRijbewijs" : "T"}
	     *     ]
	     *  }
	     */
		
		ArrayList<Rijbewijs> rijbewijzen = new ArrayList<Rijbewijs>();
		Rijbewijs rijbewijs = new Rijbewijs();
		rijbewijs.setCodeSoortRijbewijs("B");
		rijbewijzen.add(rijbewijs);
		rijbewijs = new Rijbewijs();
		rijbewijs.setCodeSoortRijbewijs("BE");
		rijbewijzen.add(rijbewijs);
		rijbewijs = new Rijbewijs();
		rijbewijs.setCodeSoortRijbewijs("T");
		rijbewijzen.add(rijbewijs);
		arbeidsmarktkwalificatie.setRijbewijs(rijbewijzen.toArray(new Rijbewijs[0]));		
		
		/*
		 * "arbeidsmarktkwalificatie":{
	     *   "cursus": [
	     *      {
	     *         "naamCursus":"Bedrijfshulpverlening BHV",
	     *         "naamOpleidingsinstituut":"ACME Opleidings Centrum",
	     *         "datumAanvangVolgenCursus":"2021-10-02",
	     *         "datumEindeVolgenCursus":"2021-10-07",
	     *         "datumCertificaat":"2021-10-07"
	     *       }
	     *    ]
	     * }  
	     */
	
		ArrayList<Cursus> cursussen = new ArrayList<Cursus>();
		Cursus cursus = new Cursus();
		cursus.setNaamCursus("Bedrijfshulpverlening BHV");
		cursus.setNaamOpleidingsinstituut("ACME Opleidings Centrum");
		cursus.setDatumAanvangVolgenCursus(LocalDate.of(2021,10,2));
		cursus.setDatumEindeVolgenCursus(LocalDate.of(2021,10,7));
		cursus.setDatumCertificaat(LocalDate.of(2021,10,7));
		cursussen.add(cursus);
		arbeidsmarktkwalificatie.setCursus(cursussen.toArray(new Cursus[0]));		

		/*
		 * "arbeidsmarktkwalificatie":{
		 *    "interesse": [
	     *       {
	     *          "naamInteresse": "Miniatuur Houtbouw"
	     *       }
	     *    ]
	     * }
         */

		ArrayList<Interesse> interreses = new ArrayList<Interesse>();
		Interesse interrese = new Interesse();
		interrese.setNaamInteresse("Miniatuur Houtbouw");
		interreses.add(interrese);
		arbeidsmarktkwalificatie.setInteresse(interreses.toArray(new Interesse[0]));		

		
		/*
		 * "arbeidsmarktkwalificatie":{
		 *    "gedragscompetentie":[
		 *       {
		 *          "codeBeheersingGedragscompetentie":1,
		 *          "codeGedragscompetentie":24105,
		 *          "omschrijvingGedragscompetentie":"Ik ben sociaal in mijn omgang"
		 *        }
		 *    ]
	     * }  
	     */

		ArrayList<Gedragscompetentie> gedragscompetenties = new ArrayList<Gedragscompetentie>();
		Gedragscompetentie gedragscompetentie = new Gedragscompetentie();
		gedragscompetentie.setCodeBeheersingGedragscompetentie(1);
		gedragscompetentie.setCodeGedragscompetentie(24105);
		gedragscompetentie.setOmschrijvingGedragscompetentie("Ik ben sociaal in mijn omgang");
		gedragscompetenties.add(gedragscompetentie);
		arbeidsmarktkwalificatie.setGedragscompetentie(gedragscompetenties.toArray(new Gedragscompetentie[0]));		

		
		/*
		 * "arbeidsmarktkwalificatie":{
	     *    "taalbeheersing":[
	     *       {
	     *          "codeTaal":"nld",
	     *          "codeNiveauTaalbeheersingSchriftelijk":4,
	     *          "codeNiveauTaalbeheersingLezen":4,
	     *          "codeNiveauTaalbeheersingMondeling":4,
	     *          "codeNiveauTaalbeheersingLuisteren":4
	     *       }
	     *    ]
	     * }
		 */

		ArrayList<Taalbeheersing> taalbeheersingen = new ArrayList<Taalbeheersing>();
		Taalbeheersing taalbeheersing = new Taalbeheersing();
		taalbeheersing.setCodeTaal("nld");
		taalbeheersing.setCodeNiveauTaalbeheersingLezen(4);
		taalbeheersing.setCodeNiveauTaalbeheersingLuisteren(4);
		taalbeheersing.setCodeNiveauTaalbeheersingMondeling(4);
		taalbeheersing.setCodeNiveauTaalbeheersingSchriftelijk(4);
		taalbeheersingen.add(taalbeheersing);
		arbeidsmarktkwalificatie.setTaalbeheersing(taalbeheersingen.toArray(new Taalbeheersing[0]));		

		/*
		 * "arbeidsmarktkwalificatie":{
	     *    "vakvaardigheid": [
	     *        {
	     *           "omschrijving": "Ik kan goed omgaan met houtbewerkingsmachines"
	     *        }
	     *     ]
	     *  }
	     */
		ArrayList<Vakvaardigheid> vakvaardigheden = new ArrayList<Vakvaardigheid>();
		Vakvaardigheid vakvaardigheid = new Vakvaardigheid();
		vakvaardigheid.setOmschrijving("Ik kan goed omgaan met houtbewerkingsmachines");
		vakvaardigheden.add(vakvaardigheid);
		arbeidsmarktkwalificatie.setVakvaardigheid(vakvaardigheden.toArray(new Vakvaardigheid[0]));		
		
		/*
	     * "arbeidsmarktkwalificatie": {
	     *    "opleiding": [
	     *        {
	     *           "codeNiveauOpleiding": 4,
	     *           "indicatieDiploma": 1,
	     *           "datumDiploma": "2022-01-01",
	     *           "opleidingsnaam": {
	     *              "codeOpleidingsnaam": 123123123,
	     *              "omschrijvingOpleidingsnaam": "Code 95"
	     *           },
	     *           "datumAanvangVolgenOpleiding": "2021-01-01",
	     *           "datumEindeVolgenOpleiding": "2022-01-01",
	     *           "codeStatusOpleiding": 1,
	     *           "naamOpleidingsinstituut": "ARBO opleidingen"
	     *         }
	     *      ],
         *	}
         */
		ArrayList<Opleiding> opleidingen = new ArrayList<Opleiding>();
		Opleiding opleiding = new Opleiding();
		Opleidingsnaam opleidingsnaam = new Opleidingsnaam();
		opleidingsnaam.setCodeOpleidingsnaam(123123123);
		opleidingsnaam.setOmschrijvingOpleidingsnaam("Code 95");
		opleiding.setOpleidingsnaam(opleidingsnaam);
		opleiding.setNaamOpleidingsinstituut("ARBO opleidingen");
		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.MBO.label);
		opleiding.setCodeStatusOpleiding(1);
		opleiding.setDatumAanvangVolgenOpleiding(LocalDate.of(2021,01,01));
		opleiding.setDatumEindeVolgenOpleiding(LocalDate.of(2022,01,01));
		opleiding.setDatumDiploma(LocalDate.of(2022,01,01));
		opleiding.setIndicatieDiploma(1);
		opleidingen.add(opleiding);
		arbeidsmarktkwalificatie.setOpleiding(opleidingen.toArray(new Opleiding[0]));		
		
		wp.setArbeidsmarktkwalificatie(arbeidsmarktkwalificatie);

		//jsonObject = new JSONObject(wp);
        //System.out.println(jsonObject.toString());

/** Persoon2		
		// Schrijf de elementen weg in het POJO object van werkzoekende 
		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("54321");
		wp.setIndicatieLdrRegistratie(2);
		wp.setIndicatieBeschikbaarheidContactgegevens(2);
		wp.setPersoonlijkePresentatie("Ik ben een John en ben erg sociaal aangelegd. Verder ben ik een redelijk onbekend persoon.");

		contactpersonen = new ArrayList<ContactPersoon>();
		cp = new ContactPersoon();
		cp.setNaamContactpersoonOfAfdeling("Sociale Zaken");
		cp.setTelefoonnummer("088-7777777");
		cp.setEmailadres("Lian.Doe@testdorp.org");
		contactpersonen.add(cp);
		wp.setContactPersonen(contactpersonen.toArray(new ContactPersoon[0]));		
		werkzoekendeProfielen.add(wp);

		jsonObject = new JSONObject(wp);
        //System.out.println(jsonObject.toString());
*/
        
		// Bestand is geheel verwerkt.
		return werkzoekendeProfielen.toArray(new WerkzoekendeProfiel[0]);
	}

}
