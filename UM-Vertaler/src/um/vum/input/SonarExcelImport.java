package um.vum.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import um.vum.pojo.Arbeidsmarktkwalificatie;
import um.vum.pojo.Bemiddelingsberoep;
import um.vum.pojo.Beroep;
import um.vum.pojo.ContactPersoon;
import um.vum.pojo.Mobiliteit;
import um.vum.pojo.Opleiding;
import um.vum.pojo.Opleidingsnaam;
import um.vum.pojo.Rijbewijs;
import um.vum.pojo.Sector;
import um.vum.pojo.Taalbeheersing;
import um.vum.pojo.Vervoermiddel;
import um.vum.pojo.Werkervaring;
import um.vum.pojo.Werktijden;
import um.vum.pojo.WerkzoekendeProfiel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SonarExcelImport implements Importer{

	private XSSFWorkbook workbook = null;

 	private ArrayList<WerkzoekendeProfiel> werkzoekendeProfielen = new ArrayList<WerkzoekendeProfiel>();
	//private WerkzoekendeProfiel wzp = null;
 	 
	public SonarExcelImport() {
	}

	@Override
	public WerkzoekendeProfiel[] read(String filename) {
		System.out.println("Bezig met inlezen van Excel bestand \"" + filename + "\" (Stap1/3).");

		// Lees Excel bestand in array
		FileInputStream inputStream = null;
		Sheet sheet = null;
		
		try {
			inputStream = new FileInputStream(new File(filename));
			//workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				
				// Skip eerste regel. Omdat dit een Label is.
				if (row.getRowNum() == 0) continue;
				
				// Skip lege regels
				if (checkIfRowIsEmpty(row)) continue; // Kijkt naar de eerste cell in de regel

				// Haal de data uit de Excel regel op
	            String sonarWerkzoekendID                  = readCell(row.getCell(2)); 
	            String sonarLdrRegistratie                 = readCell(row.getCell(24)); 
	            String sonarTelefoon                       = readCell(row.getCell(13)); 
	            String sonarEmail                          = readCell(row.getCell(16)); 
	            String sonarMaxAantalUren                  = readCell(row.getCell(4)); 
	            String sonarContactpersoonNaam             = readCell(row.getCell(29)) +"("+ readCell(row.getCell(30)) +")";
	            String sonarBemiddelingsberoep             = readCell(row.getCell(7)); 
	            String sonarWerkervaringberoep             = readCell(row.getCell(7)); 
	            String sonarSectorCode                     = readCell(row.getCell(6)); 
	            String sonarBemiddelingsPostcode           = readCell(row.getCell(19)); 
	            String sonarVervoermiddel                  = readCell(row.getCell(17)); 
		        String sonarRijbewijs                      = readCell(row.getCell(10)); 
	            String sonarCodeTaalbeheersing             = "nld";
	            String sonarCodeTaalbeheersingMondeling    = readCell(row.getCell(8)); 
	            String sonarCodeTaalbeheersingSchriftelijk = readCell(row.getCell(9)); 
	            String sonarCodeOpleiding                  = readCell(row.getCell(5)); 
	            //String sonarOIN =  readCell(row.getCell(18));             
	            //String sonarParticipatieLader=  readCell(row.getCell(32)); 
                
	            // Schrijf de data weg in het object
        		WerkzoekendeProfiel wp = null;
        		Mobiliteit mobiliteit = null;
        		Werktijden werktijden = null;
        		Arbeidsmarktkwalificatie arbeidsmarktkwalificatie = null;
        		ArrayList<Vervoermiddel> vervoermiddelen = null;
        		Vervoermiddel vervoermiddel = null;
        		ArrayList<ContactPersoon> contactpersonen = null;
        		ContactPersoon cp = null;
        		ArrayList<Sector> sectors = null;
        		Sector sector = null;
        		ArrayList<Bemiddelingsberoep> bemiddelingsberoepen = null;
        		Bemiddelingsberoep bemiddelingsberoep = null;

        		// Schrijf de elementen weg in het POJO object van werkzoekende 
        		wp = new WerkzoekendeProfiel();

        		/*
        		 * "idWerkzoekende":"12345",
        		 * "indicatieLdrRegistratie":2,
        		 * "indicatieBeschikbaarheidContactgegevens":1,
        		 * "telefoonnummer":["088-7777777"],
        		 * "emailadres":["Lian.Doe@testdorp.org"]}
        		 */
        		wp.setIdWerkzoekende(sonarWerkzoekendID); // JSON: "idWerkzoekende":"12345"        		
        		wp.setIndicatieLdrRegistratie( Integer.valueOf(sonarLdrRegistratie));
        		//wp.setIndicatieBeschikbaarheidContactgegevens(WerkzoekendeProfiel.Indicatie.JA.label);
        		String[] tel = new String[]{ sonarTelefoon }; 
        		wp.setTelefoonnummer(tel); 
        	    String[] ea = new String[]{ sonarEmail };
        		wp.setEmailadres(ea);
 
        		/*
        		 * "werktijden":{
        		 *    "aantalWerkurenPerWeekMaximaal":40,
        		 * }
                 */

        		werktijden = new Werktijden();
        		werktijden.setAantalWerkurenPerWeekMaximaal( Integer.valueOf(sonarMaxAantalUren) );
        		wp.setWerktijden(werktijden);

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
        		cp.setNaamContactpersoonAfdeling( sonarContactpersoonNaam );
        		contactpersonen.add(cp);
        		wp.setContactpersoon(contactpersonen.toArray(new ContactPersoon[0]));		
        		werkzoekendeProfielen.add(wp);

                /*
                 * "bemiddelingsberoep":[
                 *      { "naamBeroepOngecodeerd":"Metselaar"
                 *      }
                 *  ]
                 */	

        		bemiddelingsberoepen =new ArrayList<Bemiddelingsberoep>();
        		bemiddelingsberoep = new Bemiddelingsberoep();
        		bemiddelingsberoep.setNaamBeroepOngecodeerd( sonarBemiddelingsberoep );
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
        		sector.setCodeSbi( Integer.valueOf(sonarSectorCode) );
        		sectors.add(sector);
        		wp.setSector(sectors.toArray(new Sector[0]));
        	
        		/*
        		 * "mobiliteit":{
        		 *     "bemiddelingspostcode":"1234AA"
        		 *  }
        		 */

        		mobiliteit = new Mobiliteit();
        		mobiliteit.setBemiddelingspostcode( sonarBemiddelingsPostcode );
        		wp.setMobiliteit(mobiliteit);

        		/*
        		 * "vervoermiddel": [
        		 *     {
        		 *        "codeVervoermiddel":1
        		 *     }
        		 *  ]
        		 */

        		vervoermiddelen = new ArrayList<Vervoermiddel>();
        		vervoermiddel = new Vervoermiddel();
        		
        		switch (sonarVervoermiddel.toUpperCase()) {
        		case "BROMFIETS":
           		case "SCOOTER":
              		vervoermiddel.setCodeVervoermiddel(Vervoermiddel.TypeVervoermiddel.BROMFIETS.label);
            		vervoermiddelen.add(vervoermiddel);
            			break;
        		case "AUTO":
              		vervoermiddel.setCodeVervoermiddel(Vervoermiddel.TypeVervoermiddel.AUTO.label);
            		vervoermiddelen.add(vervoermiddel);
        			break;
        		case "MOTOR":
              		vervoermiddel.setCodeVervoermiddel(Vervoermiddel.TypeVervoermiddel.MOTOR.label);
            		vervoermiddelen.add(vervoermiddel);
        			break;
           		case "FIETS":
              		vervoermiddel.setCodeVervoermiddel(Vervoermiddel.TypeVervoermiddel.FIETS.label);
            		vervoermiddelen.add(vervoermiddel);
        			break;
        	    default:
        	    	System.out.println("Onbekend vervoermiddel: "+sonarVervoermiddel);
        		}
       	    	if (!vervoermiddelen.isEmpty()) {
       	    		wp.setVervoermiddel(vervoermiddelen.toArray(new Vervoermiddel[0]));		
       	    	}
        		
        		/*
        		 * "arbeidsmarktkwalificatie":{
        		 *    .....
        		 *  }
        		 */
        		arbeidsmarktkwalificatie = new Arbeidsmarktkwalificatie();


        		/*
        		 * "arbeidsmarktkwalificatie":{
        		 *    "werkervaring": [
        		 *        {
        		 *           "beroep":
        		 *              {
        		 *                 "naamBeroepOngecodeerd":"Kozijnenbouwer"
        		 *              },
        		 *        }
        		 *     ]
        		 *  }
        	     */
        		
        		ArrayList<Werkervaring> werkervaringen = new ArrayList<Werkervaring>();
        		Werkervaring werkervaring = new Werkervaring();
        		Beroep beroep = new Beroep();
        		beroep.setNaamBeroepOngecodeerd(sonarWerkervaringberoep);
        		werkervaring.setBeroep(beroep);
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
        		for (String rb : sonarRijbewijs.split(",")) {
            		Rijbewijs rijbewijs = new Rijbewijs();        		
            		switch (rb.toUpperCase()) {
               		case "A":
              		case "A1":
              		case "A2":
              		case "AM":
              		case "B":
              		case "B+":
              		case "C1":
              		case "C":
              		case "D1":
              		case "D":
             		case "BE":
             		case "C1E":
             		case "CE":
             		case "D1E":
             		case "DE":
               		case "T":
              			rijbewijs.setCodeSoortRijbewijs(rb);
                   		break;
                   	default:
                   		System.out.println("Rijbewijs is onbekend: "+rb);
            		}
            		if (!rijbewijs.getCodeSoortRijbewijs().isEmpty()) {
                  		rijbewijzen.add(rijbewijs);
            		}
        		}
        		if (!rijbewijzen.isEmpty()){
        			arbeidsmarktkwalificatie.setRijbewijs(rijbewijzen.toArray(new Rijbewijs[0]));		
        		}
        		
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
        		taalbeheersing.setCodeTaal( sonarCodeTaalbeheersing);
        		taalbeheersing.setCodeNiveauTaalbeheersingMondeling( Integer.valueOf(sonarCodeTaalbeheersingMondeling));
        		taalbeheersing.setCodeNiveauTaalbeheersingSchriftelijk(Integer.valueOf(sonarCodeTaalbeheersingSchriftelijk) );
        		taalbeheersingen.add(taalbeheersing);
        		arbeidsmarktkwalificatie.setTaalbeheersing(taalbeheersingen.toArray(new Taalbeheersing[0]));		
        		
        		/*
        	     * "arbeidsmarktkwalificatie": {
        	     *    "opleiding": [
        	     *        {
        	     *           "codeNiveauOpleiding": 4,
        	     *           "opleidingsnaam": {
        	     *              "omschrijvingOpleidingsnaam": "Code 95"
        	     *           },
        	     *           "codeStatusOpleiding": 1,
        	     *         }
        	     *      ],
                 *	}
                 */
        		ArrayList<Opleiding> opleidingen = new ArrayList<Opleiding>();
        		Opleiding opleiding = new Opleiding();
        		Opleidingsnaam opleidingsnaam = new Opleidingsnaam();
        		opleidingsnaam.setNaamOpleidingOngecodeerd(sonarCodeOpleiding);
        		opleiding.setOpleidingsnaam(opleidingsnaam);

        		switch(sonarCodeOpleiding.toUpperCase()) {
        		case "BASISONDERWIJS":
               		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.BASISONDERWIJS.label);
        			break;
           		case "VMBO":
              		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.VMBO.label);
              		        			break;
           		case "HAVO":
           		case "VWO":
              		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.HAVO_VWO.label);
              		
        			break;
           		case "MBO":
              		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.MBO.label);
              		 
        			break;
          		case "HBO":
           		case "BACHELOR":
              		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.HBO_BACHELOR.label);
              		 
        			break;
          		case "WO":
           		case "MASTER":
              		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.WO_MASTER.label);
        			break;
           		default:
              		opleiding.setCodeNiveauOpleiding(Opleiding.codeNiveau.ANDERS.label);
        			break;
        		}
        		opleiding.setCodeStatusOpleiding(Opleiding.stadiumOpleiding.SUCCESVOL_AFGEROND.label);
        		opleiding.setIndicatieDiploma(Opleiding.codeDiploma.JA.label);
        		opleidingen.add(opleiding);
        		arbeidsmarktkwalificatie.setOpleiding(opleidingen.toArray(new Opleiding[0]));		
        		
        		wp.setArbeidsmarktkwalificatie(arbeidsmarktkwalificatie);
 				
			}

			inputStream.close();

		} catch (IOException | EncryptedDocumentException e) {
			System.err.println("Fout met lezen invoerbestand. Melding:"+e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(2); 				
		}
		
		
		return werkzoekendeProfielen.toArray(new WerkzoekendeProfiel[0]);
	}
	
	
	private String readCell(Cell cell) {
		String returnvalue = null;
			switch (cell.getCellType()) {
			case STRING:
                returnvalue = cell.getStringCellValue();
				//System.out.println(cell.getRichStringCellValue().getString());
				break;
			case NUMERIC:
				// double afronden en naar string vertalen 
                returnvalue = String.valueOf(Math.round(cell.getNumericCellValue()));
                if (DateUtil.isCellDateFormatted(cell)) {
                        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                        Date date = cell.getDateCellValue();
                        returnvalue = df.format(date);
                     }
                break;
			case BOOLEAN:
                returnvalue = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA:
                System.out.println("Cell bevat formule");
				break;
			case BLANK:
                //System.out.println("Cell is leeg");
				break;
			default:
                System.out.println("Geen celltype gevonden");
			}
		return returnvalue;
	}

	private boolean checkIfRowIsEmpty(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return true;
        }
        Cell cell = row.getCell((int)row.getFirstCellNum());
        if (cell == null) {
            return true;
        }
        if (readCell(cell) == null) {
        	return true;
        }
        if ("".equals(readCell(cell))) {
        	return true;
        }
       	return false;
           }
}
