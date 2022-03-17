package um.vum.pojo;

/**
 * @author ronny
 * Een onderwerp waar de werkzoekende belangstelling voor heeft.
 */
public class Interesse {

    private String naamInteresse;

    public String getNaamInteresse() { return naamInteresse; }
    /**
     * @param value
     * De naam van een interesse zoals deze is opgegeven door werkzoekende of werkgever.
     */
    public void setNaamInteresse(String value) {
	    if (value.length() < 121) {
	       	naamInteresse = value; 
	        	    } else {
	    	System.out.println("NaamInteresse \"" + value + "\" mag niet langer zijn dan 120 characters.");	    	
	    }

    }
}
