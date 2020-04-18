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
	
	public void unfollowLeague(String leagueID)
	{
		followedLeagueIDs.remove(leagueID);
		accountDBInterator.removeFollowedLeagueID(this.userID, leagueID);
	}
	
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
}
