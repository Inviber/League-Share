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
			ownedLeagueIDs.add(_ID);
		}
		else if (casting)
		{
			leagueCastedIDs.add(_ID);
		}
		
		followedLeagueIDs.add(_ID);
	}
	
	void addTeam(String _ID, boolean owns, boolean manages)
	{
		// lookup team by _ID, pass it here, add to appropriate array
		if (owns)
		{
			ownedTeamIDs.add(_ID);
		}
		else if (manages)
		{
			managedTeamIDs.add(_ID);
		}
		
		followedTeamIDs.add(_ID);
	}
	
	void unfollowLeague(String _ID)
	{
		followedLeagueIDs.remove(_ID);
	}
	
	void promoteLeagueCaster(String _ID)
	{
		leagueCastedIDs.add(_ID);
	}
	
	void demoteLeagueCaster(String _ID)
	{
		leagueCastedIDs.remove(_ID);
	}
	
	void unfollowTeam(String _ID)
	{
		followedTeamIDs.remove(_ID);
	}
	
	void promoteTeamManager(String _ID)
	{
		managedTeamIDs.add(_ID);
	}
	
	void demoteTeamManager(String _ID)
	{
		managedTeamIDs.remove(_ID);
	}
}
