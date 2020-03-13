import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TeamParserTester {
	
	private static TeamParser team;

	@BeforeAll
	static void populateTeam()
	{
		team = new TeamParser("5e59763368ec36619a66bfdc", "5e5fdb13762e9912f7f22a1f");
		team.getTeamDetails(true);
	}
	
	@Test
	void teamPopulatedSuccessfully() {
		assertTrue(team.getZipcode().contains("41015"), "Team data populated successfully");
	}
	
	@Test
	void createPlayerInTeam() {
		String testAddPlayerID = team.createPlayer("Add", "Me");
				
		assertTrue(team.getPlayerIDs().contains(testAddPlayerID), "testAddPlayerID is pressent in team array");
	
		team.deletePlayer(testAddPlayerID);
	}
	
	@Test
	void deleteTeamFromLeague()
	{
		String testRemovePlayerID = team.createPlayer("Remove", "Me");
		
		assertTrue(team.getPlayerIDs().contains(testRemovePlayerID), "testRemovePlayerID is pressent in team array");

		team.deletePlayer(testRemovePlayerID);

		assertFalse(team.getPlayerIDs().contains(testRemovePlayerID), "testRemovePlayerID is no longer present in team array");
	}

	@AfterAll
	static void cleanUpAndClose()
	{
		team.getTeamDetails(true);
		
		team.closeDatabase();
	}

}
