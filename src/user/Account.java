package user;
import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import league.LeagueParser;
import database.DatabaseHelper;

public class Account {
	private String _ID;		// _ID is the naming used by MongoDB's unique ID system.
	private String username;
	private String firstName;
	private String lastName;
	
	private String passedPassword;
	private Boolean successfullyLoggedIn = false;
	
	// Database variables
	private JSONParser parser = new JSONParser();
	private DatabaseHelper dbHelper;
//	private DatabaseHelper dbHelper = 
//			new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");
	private JSONObject accountData;

	private ArrayList<String> ownedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> leagueCastedIDs = new ArrayList<String>();
	private ArrayList<String> followedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> ownedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamLeagueIDs = new ArrayList<String>();
	private ArrayList<String> followedTeamIDs = new ArrayList<String>();
	
	public Account(String username, String password, DatabaseHelper dbHelper)
	{
		this.dbHelper = dbHelper;
		this.username = username;
		passedPassword = password;
		this._ID = dbHelper.getUserIDByUsername(username);
		populateAccountDetails();
	}
	
	private void populateAccountDetails()
	{
		getAccountDetails(false);
		
		if (passedPassword.compareTo((String) accountData.get("password")) == 0)
		{
			this.firstName = (String) accountData.get("firstName");
			this.lastName = (String) accountData.get("lastName");
			
			this.ownedLeagueIDs = (ArrayList<String>) accountData.get("ownedLeagueIDs");
			this.leagueCastedIDs = (ArrayList<String>) accountData.get("leagueCastedIDs");
			this.followedLeagueIDs = (ArrayList<String>) accountData.get("followedLeagueIDs");
			this.ownedTeamIDs = (ArrayList<String>) accountData.get("ownedTeamIDs");
			this.managedTeamIDs = (ArrayList<String>) accountData.get("managedTeamIDs");
			
			this.managedTeamLeagueIDs = (ArrayList<String>) accountData.get("managedTeamLeagueIDs");
//			System.out.println(this.managedTeamLeagueIDs.toString());
			
			this.followedTeamIDs = (ArrayList<String>) accountData.get("followedTeamIDs");
			
			successfullyLoggedIn = true;
		}
	}
	
	public String getAccountDetails(boolean print)
	{
		Document accountDocument = dbHelper.getDocument("Users", _ID); 	
				
		try
		{
			Object obj = parser.parse(accountDocument.toJson());
			accountData = (JSONObject) obj;
		}
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}
		
		if (print)
		{
			System.out.println(accountData.toString());
		}
		
		return accountData.toString();
	}
	
	String getID()
	{
		return this._ID;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	String getFirstName() {
		return firstName;
	}

	String getLastName() {
		return lastName;
	}
	
	public ArrayList<String> getOwnedLeagueIDs() 
	{
		return ownedLeagueIDs;
	}

	public ArrayList<String> getLeagueCastedIDs() 
	{
		return leagueCastedIDs;
	}

	public ArrayList<String> getFollowedLeagueIDs() 
	{
		return followedLeagueIDs;
	}

	ArrayList<String> getOwnedTeamIDs() 
	{
		return ownedTeamIDs;
	}

	public ArrayList<String> getManagedTeamIDs() 
	{
		return managedTeamIDs;
	}
	
	public ArrayList<String> getManagedTeamLeagueIDs() 
	{
		return managedTeamLeagueIDs;
	}

	ArrayList<String> getFollowedTeamIDs() 
	{
		return followedTeamIDs;
	}
	
	
	Boolean getSuccessfullyLoggedIn() 
	{
		return successfullyLoggedIn;
	}

	void createLeague(String leagueName, String sport, String leagueDescription)
	{
		// access db, this call will likely return an ID from the db.
		//String leage_ID = dbHelper.createLeague(leagueName, this._ID, sport, leagueDescription); 
		//new LeagueParser(leagueName, dbHelper); awaiting actual use of this within GUI for refactor.
		//addLeague(leage_ID, true, false);
		System.out.println("Doing nothing currently.");
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
	
	
	void createTeam(String leagueID, String teamName, String zipcode)
	{
		
		if (ownedLeagueIDs.contains(this._ID))
		{
			String teamID = dbHelper.createTeam(leagueID, teamName, zipcode); 
			addTeam(teamID, true, false);
		}
		else
		{
			System.out.println("You don't have authority to create a team in this league.");
		}
			
	}
	
	boolean updateTeam(String leagueID, String teamName) // returns boolean for if update was successful
	{
		if (ownedLeagueIDs.contains(this._ID) || ownedTeamIDs.contains(this._ID) || managedTeamIDs.contains(this._ID))
		{
			// access DB, this call will probably return a boolean on update success.
			String teamID = dbHelper.getTeamIDByTeamName(leagueID, teamName);
			//dbHelper.updateTeam(leagueID, teamID);
			return true;
		}
		else
		{
			System.out.println("Not authorized to update this Team.");
			return false;
		}
	}
	
	
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
	
	void closeDatabase()
	{
		dbHelper.getClient().close();
	}
}
