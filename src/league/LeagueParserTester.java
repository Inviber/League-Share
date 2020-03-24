package league;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.DatabaseHelper;

class LeagueParserTester {
	
	private static LeagueParser league;
	private static DatabaseHelper dbHelper = new DatabaseHelper(
	"mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority",
	"LeagueShare");

	@BeforeAll
	static void populateLeague()
	{
		league = new LeagueParser("5e59763368ec36619a66bfdc", dbHelper);
	}
	
	/*
	
	@Test
	void createTeamInLeague() {
		String testTeamID = league.createTeam("createTeamTest", "00000");
		
		System.out.println(league.getTeamIDs());
		
		assertTrue(league.getTeamIDs().contains(testTeamID), "createTeamTest is pressent in team array");
	
		league.deleteTeam("createTeamTest");
	}
	
	@Test
	void deleteTeamFromLeague()
	{
		String testTeamID = league.createTeam("removeTeamTest", "99999");
		
		assertTrue(league.getTeamIDs().contains(testTeamID), "removeTeamTest is pressent in team array");
		
		league.deleteTeam("removeTeamTest");

		assertFalse(league.getTeamIDs().contains(testTeamID), "removeTeamTest is no longer present in team array");
	}
	
	*/
	
	@Test
	void addCasterToLeague()
	{
		league.addCasterIDs("casterAddTest");
		
		assertTrue(league.getCasterIDs().contains("casterAddTest"), "casterAddTest must be successfully added to League");
	
		league.removeCasterIDs("casterAddTest");
	}
	
	@Test
	void removeCasterFromLeague()
	{
		league.addCasterIDs("casterRemoveTest");
		
		assertTrue(league.getCasterIDs().contains("casterRemoveTest"), "casterRemoveTest is present in League");
		
		league.removeCasterIDs("casterRemoveTest");

		assertFalse(league.getCasterIDs().contains("casterRemoveTest"), "casterRemoveTest is no longer present in League");
	}
	
	@AfterAll
	static void cleanUpAndClose()
	{
		league.getLeagueDetails(true);
		
		league.closeDatabase();
	}

}
