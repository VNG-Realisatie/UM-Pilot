package um.vum.pojo;

public class xxBeroepOngecodeerd  {
    private String naamBeroepOngecodeerd;

    public String getNaamBeroepOngecodeerd() { return naamBeroepOngecodeerd; }
    /**
     * @param value maxLength: 120
     * De naam van het BEROEP
     */
    public void setNaamBeroepOngecodeerd(String value) {
	    if (value.length()<121 ) {
	    	naamBeroepOngecodeerd = value;
	    } else {
    		System.out.println("NaamBeroepOngecodeerd is langer dan 120 characters");			
    	}
    }

}
