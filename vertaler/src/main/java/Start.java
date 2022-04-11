/*
 * Copyright (c) 2022, VNG and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the European Union Public License EUPL 1.2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public License
 * 1.2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the European Union Public License version
 * 1.2 along with this work; if not, find it a the website 
 * https://ec.europa.eu/info/european-union-public-licence_en.
 *
 * Please contact Vereniging van Nederlandse Gemeenten VNG, 
 * Nassaulaan 12, 2514JS Den Haag
 * or visit www.vng.nl if you need additional information or have any
 * questions.
 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import um.vum.Arguments;
import um.vum.Controller;
import um.vum.input.*;
import um.vum.output.*;
import um.vum.pojo.*;
import um.vum.vertalers.*;

public class Start {
	
/**
 * Eerste stap
 *   het inputbestand wordt gelezen en geplaatsen in het VUM objectmodel
 * Tweede stap
 *   Mogelijk komt de inhoud van de objecten niet overeen met de waarde van VUM daarom
 *   wordt waar nodig de objecten vertaald naar VUM waarden
 * Derde stap
 *   Controleer of ID's over de dataset uniek zijn en alle verplichte elementen gevuld zijn.
 *   Profielen zonder toestemming tot delen worden uit de set gefilterd (ivm AVG en Wbp)
 * Vierde stap
 *   Als laatste stap wordt het objectmodel vertaald naar Json en verstuurd naar UM 
 *   of als bestand weggeschreven
 * @param args
 * 
 * 
 * Exit status. Details staan in de foutmelding.
 *  0 = programma is succesvol afgerond
 *  1 = Fout met het opgegeven argumenten.
    2 - Fout met het verwerken van het inputformaat.
    4 - Fout met het wegschrijven van het outputformaat.
    
    
     * TODO in Opleidingnaam: opleiding ongecodeerd / gecodeerd is anders verwerkt
     * idem bij Beroep
 */
	public static void main(String[] args) {
		// bevat de argumenten die met het opstarten worden meegegeven		
		Arguments arguments = new Arguments(args);
				
		// Zet de juiste functionaliteit klaar voor stap 1 en 2 en voer 
		// afhankelijk van het "format"-argument de juiste verwerking uit  
		Importer importer = null; // Voor stap 1
		Vertaler vertaler = null; // Voor stap 2

		switch (arguments.getFormat().toUpperCase()) {
		case "DUMMY":
		    importer = new DummyImport();
		    vertaler = new DummyTranslate(); 
			break;
		case "VOORBEELD":
		    importer = new FileImport();
		    vertaler = new DummyTranslate(); 
			break;
		case "SONAREXCELIMPORT":
		    importer = new SonarExcelImport();
		    vertaler = new SonarTranslate(); 
		    break;
		default:
			System.err.println("Onbekend import-type");
			// Sluit het programma af met status 1 als teken dat
			//het programma zijn taak niet succesvol heeft kunnen afgeronden
			System.exit(1); 				
		}
		
		// Controleer of het opgegeven bestand gevonden kan worden
	    String f = arguments.getFilename();
		Path path = Paths.get(f);
		if(!Files.exists(path)) {
			System.out.println("Bestand: "+f + " kan niet gevonden worden.");
			System.exit(1);
		}
 
        // Stap 1 - Lees bestand in 
		WerkzoekendeProfiel[] werkzoekendeProfielen = importer.read(arguments.getFilename());
        
        // Stap 2 - Vertaal de regels naar het WerkzoekendeObject
		vertaler.vertaal(werkzoekendeProfielen);

		// Stap 3 - Doe een final check op de dataset
		werkzoekendeProfielen = Controller.CheckWP(werkzoekendeProfielen);
		
		// Stap 4 - Schrijf de werkzoekendeObjecten weg als beastand in Json-formaat en verzend indien gewenst direct naar UM-API
	    JSONArray jsonArray = new JSONArray(werkzoekendeProfielen);

	    // Zend bestand naar UM-API als het OIN als parameter is meegegeven
        boolean succesvolVerzonden = false;
		if ( arguments.getOIN() != null) {

	        // Zend bestand naar de UM API 
		    UmConnector con = new UmConnector(arguments.getUMURL(),arguments.getOIN());
		    boolean succesfulAuthenticated = con.authenticate(arguments.getUMAuthURL(), arguments.getUMUsername(), arguments.getUMPassword());
		    if (succesfulAuthenticated) {
		    	succesvolVerzonden = con.send(jsonArray.toString());
		    }
	    }

		String now = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
		String fileName = "output/UM-Werkzoekenden-" + now + ".json";
		WriteJsonFile.write(fileName, jsonArray.toString());

		// Terugkoppeling over het proces
		if(succesvolVerzonden) {
			System.out.println("Het Werkzoekenden bestand \"" + fileName + "\" is aangemaakt en succesvol naar UM verzonden.");
		} else {
			if ( arguments.getOIN() != null) {			
				System.out.println("Het Werkzoekenden bestand \"" + fileName + "\" is aangemaakt maar kon NIET verzonden worden.");			
			} else {
				System.out.println("Het Werkzoekenden bestand \"" + fileName + "\" is aangemaakt.");			
			}
			System.out.println("U kunt dit bestand handmatig op het UM portal aanbieden.");	
		}
		
		System.out.println("Klaar met verwerking.");		
	}
	
}