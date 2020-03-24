package player;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlayerParserTest {

	private static PlayerParser player;

	@BeforeAll
	static void populatePlayer()
	{
		player = new PlayerParser("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f", "5e5fddfa4dabc675c9788718");
		player.getPlayerDetails(true);
	}
	
	@Test
	void playerPopulatedSuccessfully() 
	{
		assertTrue(player.getFirstName().contains("Primp"), "Player name data populated successfully");
		assertTrue(player.getLastName().contains("Doge"), "Player name data populated successfully");
	}
	
	@Test
	void playerStatisticsReturnSuccessfully()
	{
		assertTrue(player.getStatstic("Puppers pwnd").contains("5"), "Player statistic data populated successfully");
	}

	@AfterAll
	static void cleanUpAndClose()
	{		
		player.closeDatabase();
	}


}
