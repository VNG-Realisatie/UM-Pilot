package um.vum.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJsonFile {
	
	public static void write(String fileName, String content){
		
		System.out.println("Bezig met wegschrijven naar \"" + fileName +"\" (Stap3/3).");		
		
		BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(fileName));
			    writer.write(content);
			    writer.close();
			} catch (IOException e) {
				System.err.println("Fout met schrijven uitvoerbestand. Melding:"+e.getLocalizedMessage());
				// Sluit het programma af met status 2 als teken dat
				//het programma zijn taak niet succesvol heeft kunnen afgeronden
				System.exit(4); 				
			}
		}

}
