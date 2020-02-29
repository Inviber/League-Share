import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AccountTest {

	private static Account account;
	
	@BeforeAll
	static void createAccount()
	{
		account = new Account("WhiteWolf");
	}
	
	@Test
	void accountPopulatedSuccessfully() {
		assertEquals(account.getUsername(), "WhiteWolf");
		assertEquals(account.getFirstName(), "Geralt");
		assertEquals(account.getLastName(), "Of Rivia");
		assertNotEquals(account.getID(), "");
	}
	
	@Test
	void accountAddsLeagueAndPersists() {
		account.addLeague("ownedLeague", true, false);
		
		assertEquals(account.getOwnedLeagueIDs().contains("ownedLeague"), true);
		
		account.deleteLeague("ownedLeague");
		
		account.addLeague("castedLeague", false, true);
		
		assertEquals(account.getLeagueCastedIDs().contains("castedLeague"), true);
		
		account.demoteLeagueCaster("castedLeague");
		account.unfollowLeague("castedLeague");
		
		account.addLeague("followedLeague", false, false);

		assertEquals(account.getFollowedLeagueIDs().contains("followedLeague"), true);
		
		account.unfollowLeague("followedLeague");
	}
	
	@Test
	void accountAddsTeamAndPersists() {
		account.addTeam("ownedTeam", true, false);
		
		assertEquals(account.getOwnedTeamIDs().contains("ownedTeam"), true);
		
		account.deleteTeam("ownedTeam");
		
		account.addTeam("managedTeam", false, true);
		
		assertEquals(account.getManagedTeamIDs().contains("managedTeam"), true);
		
		account.demoteTeamManager("managedTeam");
		account.unfollowTeam("managedTeam");


		account.addTeam("followedTeam", false, false);
		
		assertEquals(account.getFollowedTeamIDs().contains("followedTeam"), true);
		
		account.unfollowTeam("followedTeam");
	}
	
	@Test
	void accountAddsLeagueAndUnfollows() {
		account.addLeague("followedLeague", false, false);
		
		assertEquals(account.getFollowedLeagueIDs().contains("followedLeague"), true);
		
		account.unfollowLeague("followedLeague");

		assertEquals(account.getFollowedLeagueIDs().contains("followedLeague"), false);
	}
	
	@Test
	void accountAddsLeagueAndPromotes() {
		account.addLeague("promotedLeague", false, true);
		
		assertEquals(account.getLeagueCastedIDs().contains("promotedLeague"), true);
		
		account.promoteLeagueCaster("promotedLeague");

		assertEquals(account.getLeagueCastedIDs().contains("promotedLeague"), true);

		assertEquals(account.getFollowedLeagueIDs().contains("promotedLeague"), true);
		
		account.demoteLeagueCaster("promotedLeague");
		account.unfollowLeague("promotedLeague");
	}
	
	@Test
	void accountAddsLeagueAndDemotes() {
		account.addLeague("demotedLeague", false, true);
		
		assertEquals(account.getLeagueCastedIDs().contains("demotedLeague"), true);
		
		account.demoteLeagueCaster("demotedLeague");

		assertEquals(account.getLeagueCastedIDs().contains("demotedLeague"), false);

		assertEquals(account.getFollowedLeagueIDs().contains("demotedLeague"), true);
		
		account.unfollowLeague("demotedLeague");
	}
	
	@Test
	void accountAddsTeamAndUnfollows() {
		account.addTeam("followedTeam", false, false);
		
		assertEquals(account.getFollowedTeamIDs().contains("followedTeam"), true);
		
		account.unfollowTeam("followedTeam");

		assertEquals(account.getFollowedTeamIDs().contains("followedTeam"), false);
	}
	
	@Test
	void accountAddsTeamAndPromotes() {
		account.addTeam("promotedTeam", false, true);
		
		assertEquals(account.getManagedTeamIDs().contains("promotedTeam"), true);
		
		account.promoteTeamManager("promotedTeam");

		assertEquals(account.getManagedTeamIDs().contains("promotedTeam"), true);

		assertEquals(account.getFollowedTeamIDs().contains("promotedTeam"), true);
		
		account.demoteTeamManager("promotedTeam");
		account.unfollowTeam("promotedTeam");
	}
	
	@Test
	void accountAddsTeamAndDemotes() {
		account.addTeam("demotedTeam", false, true);
		
		assertEquals(account.getManagedTeamIDs().contains("demotedTeam"), true);
		
		account.demoteTeamManager("demotedTeam");

		assertEquals(account.getManagedTeamIDs().contains("demotedTeam"), false);

		assertEquals(account.getFollowedTeamIDs().contains("demotedTeam"), true);
		
		account.unfollowTeam("demotedTeam");
	}
	
	@AfterAll
	static void accountTestCleansupAndLogsOut()
	{
		account.getAccountDetails();
		
		account.closeDatabase();
	}
	
}
