package user;
import java.util.ArrayList;

public class Account {
	private String userID;
	private String username;
	private String firstName;
	private String lastName;	

	private ArrayList<String> ownedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> leagueCastedIDs = new ArrayList<String>();
	private ArrayList<String> followedLeagueIDs = new ArrayList<String>();
	private ArrayList<String> ownedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamIDs = new ArrayList<String>();
	private ArrayList<String> managedTeamLeagueIDs = new ArrayList<String>();
	private ArrayList<String> followedTeamIDs = new ArrayList<String>();
	
	private AccountDBInterator accountDBInterator;
	
	
	
	public Account(String userID, String username, String firstName, String lastName, ArrayList<String> ownedLeagueIDs,
			ArrayList<String> leagueCastedIDs, ArrayList<String> followedLeagueIDs, ArrayList<String> ownedTeamIDs,
			ArrayList<String> managedTeamIDs, ArrayList<String> managedTeamLeagueIDs,
			ArrayList<String> followedTeamIDs, AccountDBInterator accountDBInterator) 
	{
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ownedLeagueIDs = ownedLeagueIDs;
		this.leagueCastedIDs = leagueCastedIDs;
		this.followedLeagueIDs = followedLeagueIDs;
		this.ownedTeamIDs = ownedTeamIDs;
		this.managedTeamIDs = managedTeamIDs;
		this.managedTeamLeagueIDs = managedTeamLeagueIDs;
		this.followedTeamIDs = followedTeamIDs;
		this.accountDBInterator = accountDBInterator;
	}

	String getID()
	{
		return this.userID;
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

	public ArrayList<String> getFollowedTeamIDs() 
	{
		return followedTeamIDs;
	}
	
	/*
	void createLeague(String leagueName, String sport, String leagueDescription)
	{
		// access db, this call will likely return an ID from the db.
		//String leage_ID = accountDBInterator.createLeague(leagueName, this._ID, sport, leagueDescription); 
		//new LeagueParser(leagueName, accountDBInterator); awaiting actual use of this within GUI for refactor.
		//addLeague(leage_ID, true, false);
		System.out.println("Doing nothing currently.");
	}
	
	boolean updateLeague(String _ID, String leagueName, String leagueDescription) // returns boolean for if update was successful
	{
		if (ownedLeagueIDs.contains(_ID))
		{
			// access DB, this call will probably return a boolean on update success.
			//return accountDBInterator.updateLeague(this._ID, leagueName, leagueDescription);
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
			String teamID = accountDBInterator.createTeam(leagueID, teamName, zipcode); 
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
			String teamID = accountDBInterator.getTeamIDByTeamName(leagueID, teamName);
			//accountDBInterator.updateTeam(leagueID, teamID);
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
				accountDBInterator.addOwnedLeagueID(this._ID, league_ID);
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
				accountDBInterator.addLeagueCastedID(this._ID, league_ID);
			}
		}
		
		if (followedLeagueIDs.contains(league_ID))
		{
			return; // already within array.
		}
		else
		{
			followedLeagueIDs.add(league_ID);
			accountDBInterator.addFollowedLeagueID(this._ID, league_ID);
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
				accountDBInterator.addOwnedTeamID(this._ID, team_ID);
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
				accountDBInterator.addManagedTeamID(this._ID, team_ID);
			}
		}
		
		if (followedTeamIDs.contains(team_ID))
		{
			return; // already within array.
		}
		else
		{
			followedTeamIDs.add(team_ID);
			accountDBInterator.addFollowedTeamID(this._ID, team_ID);
		}
	}
	
	void deleteLeague(String league_ID)
	{
		if (ownedLeagueIDs.contains(league_ID))
		{
			ownedLeagueIDs.remove(league_ID);
			accountDBInterator.removeOwnedLeagueID(this._ID, league_ID);
			if (leagueCastedIDs.contains(league_ID))
			{
				leagueCastedIDs.remove(league_ID);
				accountDBInterator.removeLeagueCastedID(this._ID, league_ID);
			}

			if (followedLeagueIDs.contains(league_ID))
			{
				followedLeagueIDs.remove(league_ID);
				accountDBInterator.removeFollowedLeagueID(this._ID, league_ID);
			}
		}	
	}
	
	void deleteTeam(String team_ID)
	{
		if (ownedTeamIDs.contains(team_ID))
		{
			ownedTeamIDs.remove(team_ID);
			accountDBInterator.removeOwnedTeamID(this._ID, team_ID);
			if (managedTeamIDs.contains(team_ID))
			{
				managedTeamIDs.remove(team_ID);
				accountDBInterator.removeManagedTeamID(this._ID, team_ID);
			}

			if (followedTeamIDs.contains(team_ID))
			{
				followedTeamIDs.remove(team_ID);
				accountDBInterator.removeFollowedTeamID(this._ID, team_ID);
			}
		}	
	}
	
	void unfollowLeague(String league_ID)
	{
		followedLeagueIDs.remove(league_ID);
		accountDBInterator.removeFollowedLeagueID(this._ID, league_ID);
	}
	
	void promoteLeagueCaster(String league_ID)
	{
		leagueCastedIDs.add(league_ID);
		accountDBInterator.addLeagueCastedID(this._ID, league_ID);
	}
	
	void demoteLeagueCaster(String league_ID)
	{
		leagueCastedIDs.remove(league_ID);
		accountDBInterator.removeLeagueCastedID(this._ID, league_ID);
	}
	
	void unfollowTeam(String team_ID)
	{
		followedTeamIDs.remove(team_ID);
		accountDBInterator.removeFollowedTeamID(this._ID, team_ID);
	}
	
	void promoteTeamManager(String team_ID)
	{
		managedTeamIDs.add(team_ID);
		accountDBInterator.addManagedTeamID(this._ID, team_ID);
	}
	
	void demoteTeamManager(String team_ID)
	{
		managedTeamIDs.remove(team_ID);
		accountDBInterator.removeManagedTeamID(this._ID, team_ID);
	}
	*/
}
