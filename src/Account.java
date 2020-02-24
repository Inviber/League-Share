import java.util.ArrayList;

class Account {
	private String ID;		// _ID is the naming used by MongoDB.
	private String username;
	//private database db;	// pseudo code for when we have the database working.
	
	private ArrayList<String> leaguesOwnedIDs = new ArrayList<String>();
	private ArrayList<String> leaguesCastedIDs = new ArrayList<String>();
	private ArrayList<String> leaguesFollowedIDs = new ArrayList<String>();
	private ArrayList<String> teamsOwnedIDs = new ArrayList<String>();
	private ArrayList<String> teamsManagedIDs = new ArrayList<String>();
	private ArrayList<String> teamsFollowedIDs = new ArrayList<String>();
	
	Account(String _ID, String username) 
	{
		this.ID = _ID;
		this.username = username;
	}
	
	/*
	void connectDatabase(database db)
	{
		this.db = db;
	}
	
	void createLeague(String leagueName, String leagueDescription)
	{
		// access db, this call will likely return an ID from the db.
		String _ID = db.createLeague(this.ID, leagueName, leagueDescription); 
		addLeague(_ID, true, false);
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
	*/ 
	
	/*
	void createTeam(String teamName)
	{
		// access db, this call will likely return an ID from the db.
		String _ID = db.createTeam(this.ID, teamName); 
		addTeam(_ID, true, false);
	}
	
	boolean updateTeam(String _ID, String teamName) // returns boolean for if update was successful
	{
		if (teamsOwnedIDs.contains(_ID) || teamsManagedIDs.contains(_ID))
		{
			// access DB, this call will probably return a boolean on update success.
			return db.updateTeam(this.ID, teamName);
		}
		else
		{
			System.out.println("Not authorized to update this Team.");
			return false;
		}
	}
	*/ 
	
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
	
	String getID()
	{
		return this.ID;
	}
	
	String getUsername()
	{
		return this.username;
	}
	
	ArrayList<String> getLeaguesOwnedIDs() 
	{
		return leaguesOwnedIDs;
	}

	ArrayList<String> getLeaguesCastedIDs() 
	{
		return leaguesCastedIDs;
	}

	ArrayList<String> getLeaguesFollowedIDs() 
	{
		return leaguesFollowedIDs;
	}

	ArrayList<String> getTeamsOwnedIDs() 
	{
		return teamsOwnedIDs;
	}

	ArrayList<String> getTeamsManagedIDs() 
	{
		return teamsManagedIDs;
	}

	ArrayList<String> getTeamsFollowedIDs() 
	{
		return teamsFollowedIDs;
	}
}
