import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class Account {
	private String _ID;		// _ID is the naming used by MongoDB's unique ID system.
	private String username;
	private String firstName;
	private String lastName;
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper = 
			new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");

	private ArrayList<String> ownedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> leagueCastedIDs = new ArrayList<String>();
	private ArrayList<String> followedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> ownedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamIDs = new ArrayList<String>();
	private ArrayList<String> followedTeamIDs = new ArrayList<String>();
	
	Account(String username)
	{
		this.username = username;
		this._ID = dbHelper.getUserIDByUsername(username);
		populateAccountDetails();
	}
	
	private void populateAccountDetails()
	{
		Document accountDocument = dbHelper.getDocument("Users", _ID); 	
		
		try
		{
			Object obj = parser.parse(accountDocument.toJson());
			JSONObject jsonData = (JSONObject) obj;
			
			this.firstName = (String) jsonData.get("firstName");
			this.lastName = (String) jsonData.get("lastName");
			
			this.ownedLeagueIDs = (ArrayList<String>) jsonData.get("ownedLeagueIDs");
			this.leagueCastedIDs = (ArrayList<String>) jsonData.get("leagueCastedIDs");
			this.followedLeagueIDs = (ArrayList<String>) jsonData.get("followedLeagueIDs");
			this.ownedTeamIDs = (ArrayList<String>) jsonData.get("ownedTeamIDs");
			this.managedTeamIDs = (ArrayList<String>) jsonData.get("managedTeamIDs");
			this.followedTeamIDs = (ArrayList<String>) jsonData.get("followedTeamIDs");
		}
		catch (Exception e) { e.printStackTrace(); }
		
		dbHelper.getClient().close();
	}
	
	String getID()
	{
		return this._ID;
	}
	
	String getUsername()
	{
		return this.username;
	}
	
	String getFirstName() {
		return firstName;
	}

	String getLastName() {
		return lastName;
	}
	
	ArrayList<String> getLeaguesOwnedIDs() 
	{
		return ownedLeagueIDs;
	}

	ArrayList<String> getLeaguesCastedIDs() 
	{
		return leagueCastedIDs;
	}

	ArrayList<String> getLeaguesFollowedIDs() 
	{
		return followedLeagueIDs;
	}

	ArrayList<String> getTeamsOwnedIDs() 
	{
		return ownedTeamIDs;
	}

	ArrayList<String> getTeamsManagedIDs() 
	{
		return managedTeamIDs;
	}

	ArrayList<String> getTeamsFollowedIDs() 
	{
		return followedTeamIDs;
	}
	
	
	void createLeague(String leagueName, String sport, String leagueDescription)
	{
		// access db, this call will likely return an ID from the db.
		String leage_ID = dbHelper.createLeague(leagueName, this._ID, sport, leagueDescription); 
		new League(leage_ID, this._ID);
		addLeague(leage_ID, true, false);
	}
	
	boolean updateLeague(String _ID, String leagueName, String leagueDescription) // returns boolean for if update was successful
	{
		if (ownedLeagueIDs.contains(_ID))
		{
			// access DB, this call will probably return a boolean on update success.
			//return dbHelper.updateLeague(this._ID, leagueName, leagueDescription);
			return true; // currently no update function for the database.
		}
		else
		{
			System.out.println("Not authorized to update this league.");
			return false;
		}
	}
	
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
	
	void addLeague(String league_ID, boolean owns, boolean casting)
	{
		// lookup league by _ID, pass it here, add to appropriate array
		if (owns)
		{
			if (ownedLeagueIDs.contains(league_ID))
			{
				return; // already within array.
			}
			else
			{
				ownedLeagueIDs.add(league_ID);
				dbHelper.addOwnedLeagueID(this._ID, league_ID);
			}
		}
		
		if (casting)
		{
			if (leagueCastedIDs.contains(league_ID))
			{
				return; // already within array.
			}
			else
			{
				leagueCastedIDs.add(league_ID);
				dbHelper.addLeagueCastedID(this._ID, league_ID);
			}
		}
		
		if (followedLeagueIDs.contains(league_ID))
		{
			return; // already within array.
		}
		else
		{
			followedLeagueIDs.add(league_ID);
			dbHelper.addFollowedLeagueID(this._ID, league_ID);
		}
		
	}
	
	void addTeam(String team_ID, boolean owns, boolean manages)
	{
		// lookup team by _ID, pass it here, add to appropriate array
		if (owns)
		{
			if (ownedTeamIDs.contains(team_ID))
			{
				return; // already within array.
			}
			else
			{
				ownedTeamIDs.add(team_ID);
				dbHelper.addOwnedTeamID(this._ID, team_ID);
			}
		}
		
		if (manages)
		{
			if (managedTeamIDs.contains(team_ID))
			{
				return; // already within array.
			}
			else
			{
				managedTeamIDs.add(team_ID);
				dbHelper.addManagedTeamID(this._ID, team_ID);
			}
		}
		
		if (followedTeamIDs.contains(team_ID))
		{
			return; // already within array.
		}
		else
		{
			followedTeamIDs.add(team_ID);
			dbHelper.addFollowedTeamID(this._ID, team_ID);
		}
	}
	
	void unfollowLeague(String league_ID)
	{
		followedLeagueIDs.remove(league_ID);
		dbHelper.removeFollowedLeagueID(this._ID, league_ID);
	}
	
	void promoteLeagueCaster(String league_ID)
	{
		leagueCastedIDs.add(league_ID);
		dbHelper.addLeagueCastedID(this._ID, league_ID);
	}
	
	void demoteLeagueCaster(String league_ID)
	{
		leagueCastedIDs.remove(league_ID);
		dbHelper.removeLeagueCastedID(this._ID, league_ID);
	}
	
	void unfollowTeam(String team_ID)
	{
		followedTeamIDs.remove(team_ID);
		dbHelper.removeFollowedTeamID(this._ID, team_ID);
	}
	
	void promoteTeamManager(String team_ID)
	{
		managedTeamIDs.add(team_ID);
		dbHelper.addManagedTeamID(this._ID, team_ID);
	}
	
	void demoteTeamManager(String team_ID)
	{
		managedTeamIDs.remove(team_ID);
		dbHelper.removeManagedTeamID(this._ID, team_ID);
	}
}
