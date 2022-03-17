package um.vum.input;

import um.vum.pojo.WerkzoekendeProfiel;

public interface Importer {
	
	public WerkzoekendeProfiel[] read(String filename);

}
