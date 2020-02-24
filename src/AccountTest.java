import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AccountTest {

	private static Account account;
	
	@BeforeAll
	static void createAccount()
	{
		account = new Account("125A684", "Geralt");
	}
	
	@Test
	void accountCreatedSuccessfully() {
		assertEquals(account.getID(), "125A684");
		assertEquals(account.getUsername(), "Geralt");
	}
	
	@Test
	void accountAddsLeagueAndPersists() {
		account.addLeague("ownedLeague", true, false);
		
		assertEquals(account.getLeaguesOwnedIDs().contains("ownedLeague"), true);
		
		account.addLeague("castedLeague", false, true);
		
		assertEquals(account.getLeaguesCastedIDs().contains("castedLeague"), true);
		
		account.addLeague("followedLeague", false, false);
		
		assertEquals(account.getLeaguesFollowedIDs().contains("followedLeague"), true);
	}
	
	@Test
	void accountAddsTeamAndPersists() {
		account.addTeam("ownedTeam", true, false);
		
		assertEquals(account.getTeamsOwnedIDs().contains("ownedTeam"), true);
		
		account.addTeam("managedTeam", false, true);
		
		assertEquals(account.getTeamsManagedIDs().contains("managedTeam"), true);
		
		account.addTeam("followedTeam", false, false);
		
		assertEquals(account.getTeamsFollowedIDs().contains("followedTeam"), true);
	}
	
	@Test
	void accountAddsLeagueAndUnfollows() {
		account.addLeague("followedLeague", false, false);
		
		assertEquals(account.getLeaguesFollowedIDs().contains("followedLeague"), true);
		
		account.unfollowLeague("followedLeague");

		assertEquals(account.getLeaguesFollowedIDs().contains("followedLeague"), false);
	}
	
	@Test
	void accountAddsLeagueAndPromotes() {
		account.addLeague("promotedLeague", false, true);
		
		assertEquals(account.getLeaguesCastedIDs().contains("promotedLeague"), true);
		
		account.promoteLeagueCaster("promotedLeague");

		assertEquals(account.getLeaguesCastedIDs().contains("promotedLeague"), true);

		assertEquals(account.getLeaguesFollowedIDs().contains("promotedLeague"), true);
	}
	
	@Test
	void accountAddsLeagueAndDemotes() {
		account.addLeague("demotedLeague", false, true);
		
		assertEquals(account.getLeaguesCastedIDs().contains("demotedLeague"), true);
		
		account.demoteLeagueCaster("demotedLeague");

		assertEquals(account.getLeaguesCastedIDs().contains("demotedLeague"), false);

		assertEquals(account.getLeaguesFollowedIDs().contains("demotedLeague"), true);
	}
	
	@Test
	void accountAddsTeamAndUnfollows() {
		account.addTeam("followedTeam", false, false);
		
		assertEquals(account.getTeamsFollowedIDs().contains("followedTeam"), true);
		
		account.unfollowTeam("followedTeam");

		assertEquals(account.getTeamsFollowedIDs().contains("followedTeam"), false);
	}
	
	@Test
	void accountAddsTeamAndPromotes() {
		account.addTeam("promotedTeam", false, true);
		
		assertEquals(account.getTeamsManagedIDs().contains("promotedTeam"), true);
		
		account.promoteTeamManager("promotedTeam");

		assertEquals(account.getTeamsManagedIDs().contains("promotedTeam"), true);

		assertEquals(account.getTeamsFollowedIDs().contains("promotedTeam"), true);
	}
	
	@Test
	void accountAddsTeamAndDemotes() {
		account.addTeam("demotedTeam", false, true);
		
		assertEquals(account.getTeamsManagedIDs().contains("demotedTeam"), true);
		
		account.demoteTeamManager("demotedTeam");

		assertEquals(account.getTeamsManagedIDs().contains("demotedTeam"), false);

		assertEquals(account.getTeamsFollowedIDs().contains("demotedTeam"), true);
	}
	
	

}
