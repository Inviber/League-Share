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
	public void createLeague(String leagueName, String sport, String leagueDescription)
	{
		// access db, this call will likely return an ID from the db.
		//String leage_ID = accountDBInterator.createLeague(leagueName, this.userID, sport, leagueDescription); 
		//new LeagueParser(leagueName, accountDBInterator); awaiting actual use of this within GUI for refactor.
		//addLeague(leage_ID, true, false);
		System.out.println("Doing nothing currently.");
	}
	
	public boolean updateLeague(String _ID, String leagueName, String leagueDescription) // returns boolean for if update was successful
	{
		if (ownedLeagueIDs.contains(_ID))
		{
			// access DB, this call will probably return a boolean on update success.
			//return accountDBInterator.updateLeague(this.userID, leagueName, leagueDescription);
			return true; // currently no update function for the database.
		}
		else
		{
			System.out.println("Not authorized to update this league.");
			return false;
		}
	}
	
	
	public void createTeam(String leagueID, String teamName, String zipcode)
	{
		
		if (ownedLeagueIDs.contains(this.userID))
		{
			String teamID = accountDBInterator.createTeam(leagueID, teamName, zipcode); 
			addTeam(teamID, true, false);
		}
		else
		{
			System.out.println("You don't have authority to create a team in this league.");
		}
			
	}
	
	public boolean updateTeam(String leagueID, String teamName) // returns boolean for if update was successful
	{
		if (ownedLeagueIDs.contains(this.userID) || ownedTeamIDs.contains(this.userID) || managedTeamIDs.contains(this.userID))
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
	
	*/
	public void addLeague(String leagueID, boolean owns, boolean casting)
	{
		// lookup league by _ID, pass it here, add to appropriate array
		if (owns)
		{
			if (ownedLeagueIDs.contains(leagueID))
			{
				return; // already within array.
			}
			else
			{
				ownedLeagueIDs.add(leagueID);
				accountDBInterator.addOwnedLeagueID(this.userID, leagueID);
			}
		}
		
		if (casting)
		{
			if (leagueCastedIDs.contains(leagueID))
			{
				return; // already within array.
			}
			else
			{
				leagueCastedIDs.add(leagueID);
				accountDBInterator.addLeagueCastedID(this.userID, leagueID);
			}
		}
		
		if (followedLeagueIDs.contains(leagueID))
		{
			return; // already within array.
		}
		else
		{
			followedLeagueIDs.add(leagueID);
			accountDBInterator.addFollowedLeagueID(this.userID, leagueID);
		}
		
	}
	/*
	public void addTeam(String teamID, boolean owns, boolean manages)
	{
		// lookup team by _ID, pass it here, add to appropriate array
		if (owns)
		{
			if (ownedTeamIDs.contains(teamID))
			{
				return; // already within array.
			}
			else
			{
				ownedTeamIDs.add(teamID);
				accountDBInterator.addOwnedTeamID(this.userID, teamID);
			}
		}
		
		if (manages)
		{
			if (managedTeamIDs.contains(teamID))
			{
				return; // already within array.
			}
			else
			{
				managedTeamIDs.add(teamID);
				accountDBInterator.addManagedTeamID(this.userID, teamID);
			}
		}
		
		if (followedTeamIDs.contains(teamID))
		{
			return; // already within array.
		}
		else
		{
			followedTeamIDs.add(teamID);
			accountDBInterator.addFollowedTeamID(this.userID, teamID);
		}
	}
	
	public void deleteLeague(String leagueID) // for league owners
	{
		if (ownedLeagueIDs.contains(leagueID))
		{
			ownedLeagueIDs.remove(leagueID);
			accountDBInterator.removeOwnedLeagueID(this.userID, leagueID);
			if (leagueCastedIDs.contains(leagueID))
			{
				leagueCastedIDs.remove(leagueID);
				accountDBInterator.removeLeagueCastedID(this.userID, leagueID);
			}

			if (followedLeagueIDs.contains(leagueID))
			{
				followedLeagueIDs.remove(leagueID);
				accountDBInterator.removeFollowedLeagueID(this.userID, leagueID);
			}
		}	
	}
	
	public void deleteTeam(String teamID) // for league owners
	{
		if (ownedTeamIDs.contains(teamID))
		{
			ownedTeamIDs.remove(teamID);
			accountDBInterator.removeOwnedTeamID(this.userID, teamID);
			if (managedTeamIDs.contains(teamID))
			{
				managedTeamIDs.remove(teamID);
				accountDBInterator.removeManagedTeamID(this.userID, teamID);
			}

			if (followedTeamIDs.contains(teamID))
			{
				followedTeamIDs.remove(teamID);
				accountDBInterator.removeFollowedTeamID(this.userID, teamID);
			}
		}	
	}
	*/
	
	public void unfollowLeague(String leagueID)
	{
		followedLeagueIDs.remove(leagueID);
		accountDBInterator.removeFollowedLeagueID(this.userID, leagueID);
	}
	
	/*
	public void promoteLeagueCaster(String leagueID)
	{
		leagueCastedIDs.add(leagueID);
		accountDBInterator.addLeagueCastedID(this.userID, leagueID);
	}
	
	public void demoteLeagueCaster(String leagueID)
	{
		leagueCastedIDs.remove(leagueID);
		accountDBInterator.removeLeagueCastedID(this.userID, leagueID);
	}
	
	public void unfollowTeam(String teamID)
	{
		followedTeamIDs.remove(teamID);
		accountDBInterator.removeFollowedTeamID(this.userID, teamID);
	}
	
	public void promoteTeamManager(String teamID)
	{
		managedTeamIDs.add(teamID);
		accountDBInterator.addManagedTeamID(this.userID, teamID);
	}
	
	public void demoteTeamManager(String teamID)
	{
		managedTeamIDs.remove(teamID);
		accountDBInterator.removeManagedTeamID(this.userID, teamID);
	}
	*/
}
