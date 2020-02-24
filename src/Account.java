import java.util.ArrayList;

public class Account {
	String ID;		// _ID is the naming used by MongoDB.
	String username;
	
	ArrayList<String> leaguesOwnedIDs = new ArrayList<String>();
	ArrayList<String> leaguesCastedIDs = new ArrayList<String>();
	ArrayList<String> leaguesFollowedIDs = new ArrayList<String>();
	ArrayList<String> teamsOwnedIDs = new ArrayList<String>();
	ArrayList<String> teamsManagedIDs = new ArrayList<String>();
	ArrayList<String> teamsFollowedIDs = new ArrayList<String>();
	
	Account(String _ID, String username) 
	{
		this.ID = _ID;
		this.username = username;
	}
	
	void createLeague(String leagueName, String leagueDescription)
	{
		// access db, this call will likely return an ID from the db.
		String _ID = db.createLeague(this.ID, leagueName, leagueDescription); 
		addLeague(_ID, true);
	}
	
	boolean updateLeague(String _ID, String leagueName, String leagueDescription) // returns boolean for if update was successful
	{
		if (leaguesOwnedIDs.contains(_ID))
		{
			// access DB, this call will probably return a boolean on update success.
			return db.updateLeague(this.ID, leagueName, leagueDescription);
		}
		else
		{
			System.out.println("Not authorized to update this league.");
			return false;
		}
	}
	
	void addLeague(String _ID, boolean owns, boolean casting)
	{
		// Need logic to prevent adding one league to multiple lists
		// lookup league by _ID, pass it here, add to appropriate array
		if (owns)
		{
			leaguesOwnedIDs.add(_ID);
		}
		else if (casting)
		{
			leaguesCastedIDs.add(_ID);
		}
		
		leaguesFollowedIDs.add(_ID);
	}
	
	void addTeam(String _ID, boolean owns, boolean manages)
	{
		// lookup team by _ID, pass it here, add to appropriate array
		if (owns)
		{
			teamsOwnedIDs.add(_ID);
		}
		else if (manages)
		{
			teamsManagedIDs.add(_ID);
		}
		
		teamsFollowedIDs.add(_ID);
	}
	
	void unfollowLeague(String _ID)
	{
		leaguesFollowedIDs.remove(_ID);
	}
	
	void promoteLeagueCaster(String _ID)
	{
		leaguesCastedIDs.add(_ID);
	}
	
	void demoteLeagueCaster(String _ID)
	{
		leaguesCastedIDs.remove(_ID);
	}
	
	void unfollowTeam(String _ID)
	{
		teamsFollowedIDs.remove(_ID);
	}
	
	void promoteTeamManager(String _ID)
	{
		teamsManagedIDs.add(_ID);
	}
	
	void demoteTeamManager(String _ID)
	{
		teamsManagedIDs.remove(_ID);
	}
}
