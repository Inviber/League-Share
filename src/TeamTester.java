import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TeamTester {
	
	private static Team team;

	@BeforeAll
	static void populateTeam()
	{
		team = new Team("5e59763368ec36619a66bfdc", "Boxer Bruisers");
	}
	
	@Test
	void teamPopulatedSuccessfully() {
		assertTrue(team.getTeamName().contains("Boxer Bruisers"));
	}

	@AfterAll
	static void cleanUpAndClose()
	{
		team.getTeamDetails();
		
		team.closeDatabase();
	}

}
