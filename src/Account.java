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
	private JSONObject accountData;

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
		getAccountDetails();
		
		this.firstName = (String) accountData.get("firstName");
		this.lastName = (String) accountData.get("lastName");
		
		this.ownedLeagueIDs = (ArrayList<String>) accountData.get("ownedLeagueIDs");
		this.leagueCastedIDs = (ArrayList<String>) accountData.get("leagueCastedIDs");
		this.followedLeagueIDs = (ArrayList<String>) accountData.get("followedLeagueIDs");
		this.ownedTeamIDs = (ArrayList<String>) accountData.get("ownedTeamIDs");
		this.managedTeamIDs = (ArrayList<String>) accountData.get("managedTeamIDs");
		this.followedTeamIDs = (ArrayList<String>) accountData.get("followedTeamIDs");
	}
	
	void getAccountDetails()
	{
		Document accountDocument = dbHelper.getDocument("Users", _ID); 	
				
				try
				{
					Object obj = parser.parse(accountDocument.toJson());
					accountData = (JSONObject) obj;
					
					System.out.println(accountData.toString());
				}
				catch (Exception e) { e.printStackTrace(); }
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
	
	ArrayList<String> getOwnedLeagueIDs() 
	{
		return ownedLeagueIDs;
	}

	ArrayList<String> getLeagueCastedIDs() 
	{
		return leagueCastedIDs;
	}

	ArrayList<String> getFollowedLeagueIDs() 
	{
		return followedLeagueIDs;
	}

	ArrayList<String> getOwnedTeamIDs() 
	{
		return ownedTeamIDs;
	}

	ArrayList<String> getManagedTeamIDs() 
	{
		return managedTeamIDs;
	}

	ArrayList<String> getFollowedTeamIDs() 
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
	
	void deleteLeague(String league_ID)
	{
		if (ownedLeagueIDs.contains(league_ID))
		{
			ownedLeagueIDs.remove(league_ID);
			dbHelper.removeOwnedLeagueID(this._ID, league_ID);
			if (leagueCastedIDs.contains(league_ID))
			{
				leagueCastedIDs.remove(league_ID);
				dbHelper.removeLeagueCastedID(this._ID, league_ID);
			}

			if (followedLeagueIDs.contains(league_ID))
			{
				followedLeagueIDs.remove(league_ID);
				dbHelper.removeFollowedLeagueID(this._ID, league_ID);
			}
		}	
	}
	
	void deleteTeam(String team_ID)
	{
		if (ownedTeamIDs.contains(team_ID))
		{
			ownedTeamIDs.remove(team_ID);
			dbHelper.removeOwnedTeamID(this._ID, team_ID);
			if (managedTeamIDs.contains(team_ID))
			{
				managedTeamIDs.remove(team_ID);
				dbHelper.removeManagedTeamID(this._ID, team_ID);
			}

			if (followedTeamIDs.contains(team_ID))
			{
				followedTeamIDs.remove(team_ID);
				dbHelper.removeFollowedTeamID(this._ID, team_ID);
			}
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
	
	boolean logOut()
	{
		dbHelper.getClient().close();
		return true;
	}
}
