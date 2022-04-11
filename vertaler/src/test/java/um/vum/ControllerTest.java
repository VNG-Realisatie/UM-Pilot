/**
 * 
 */
package um.vum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import um.vum.pojo.WerkzoekendeProfiel;

/**
 * @author ronny
 *
 */
class ControllerTest {

	WerkzoekendeProfiel wp;
	WerkzoekendeProfiel[] wpArray;

	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link um.vum.Controller#CheckWP(um.vum.pojo.WerkzoekendeProfiel[])}.
	 * Test of twee minimale en unieke profielen kunnen worden toegevoegd. 
	 */
	@Test
	void testCheckToevoegenMinimaalUniekeWerkzoekendeProfielenWP() {
		wpArray = new WerkzoekendeProfiel[2];

		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("1");
		wp.setToestemmingTotDelenProfiel(true);
		wpArray[0] = wp;
		
		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("2");	
		wp.setToestemmingTotDelenProfiel(true);
		wpArray[1] = wp;
		
		int expected = 2;
		int actual = Controller.CheckWP(wpArray).length;
		assertEquals(expected, actual,"Twee unieke profielen met minimale gegevens kunnen worden toegevoegd.");
	}

	/**
	 * Test method for {@link um.vum.Controller#CheckWP(um.vum.pojo.WerkzoekendeProfiel[])}.
	 * Test profielen met duplicate ID worden geweigerd. 
	 */
	@Test
	void testCheckToevoegenNietUniekeWerkzoekendeProfielenWP() {
		wpArray = new WerkzoekendeProfiel[2];

		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("1");
		wp.setToestemmingTotDelenProfiel(true);
		wpArray[0] = wp;
		
		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("1");	
		wp.setToestemmingTotDelenProfiel(true);
		wpArray[1] = wp;
		
		int expected = 1;
		int actual = Controller.CheckWP(wpArray).length;
		assertEquals(expected, actual,"Profielen met reeds aanwezig ID kunnen niet worden toegevoegd.");
	}

	/**
	 * Test method for {@link um.vum.Controller#CheckWP(um.vum.pojo.WerkzoekendeProfiel[])}.
	 * Test profielen zonder expliciete toestemming worden geweigerd. 
	 */
	@Test
	void testCheckWerkzoekendeProfielenZonderToestemming() {
		wpArray = new WerkzoekendeProfiel[3];

		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("1");
		wp.setToestemmingTotDelenProfiel(true);
		wpArray[0] = wp;
		
		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("2");	
		//wp.setToestemmingTotDelenProfiel(true);
		wpArray[1] = wp;

		wp = new WerkzoekendeProfiel();
		wp.setIdWerkzoekende("3");	
		wp.setToestemmingTotDelenProfiel(false);
		wpArray[2] = wp;

		int expected = 1;
		int actual = Controller.CheckWP(wpArray).length;
		assertEquals(expected, actual,"Een profiel zonder toestemming kan niet worden toegevoegd.");
	}

}
