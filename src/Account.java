import java.util.ArrayList;

public class Account {
	String _ID;		// _ID is the naming used by MongoDB.
	String username;
	
	ArrayList<String> leaguesOwnedIDs = new ArrayList<String>();
	ArrayList<String> leaguesFollowedIDs = new ArrayList<String>();
	ArrayList<String> teamsOwnedIDs = new ArrayList<String>();
	ArrayList<String> teamsManagedIDs = new ArrayList<String>();
	ArrayList<String> teamsFollowedIDs = new ArrayList<String>();
	
	Account(String _ID, String username) 
	{
		this._ID = _ID;
		this.username = username;
	}
	
	void addLeague(String _ID, boolean own)
	{
		// Need logic to prevent adding one league to multiple lists
		// lookup league by _ID, pass it here, add to appropriate array
		if (own)
		{
			leaguesOwnedIDs.add(_ID);
		}
		else // following
		{
			leaguesFollowedIDs.add(_ID);
		}
	}
	
	void addTeam(String _ID, boolean own, boolean manage)
	{
		// lookup team by _ID, pass it here, add to appropriate array
		if (own)
		{
			teamsOwnedIDs.add(_ID);
		}
		else if (manage)
		{
			teamsManagedIDs.add(_ID);
		}
		else
		{
			teamsFollowedIDs.add(_ID);
		}
	}
	
	void unfollowLeague(String _ID)
	{
		leaguesFollowedIDs.remove(_ID);
	}
	
	void unfollowTeam(String _ID)
	{
		teamsFollowedIDs.remove(_ID);
	}
	
	void promoteTeamManager(String _ID)
	{
		teamsManagedIDs.add(_ID);
		teamsFollowedIDs.remove(_ID);
	}
	
	void demoteTeamManager(String _ID)
	{
		teamsManagedIDs.remove(_ID);
	}
}
