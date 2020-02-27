import java.util.ArrayList;

public class League {
	private String leagueID;
	private String ownerID;
	private ArrayList<String> teams;
	private ArrayList<String> admins;
	private String leagueDescription;
	
	League()
	{
		this.leagueID = "";
		this.ownerID = "";
		this.teams = new ArrayList<String>();
		this.setAdmins(new ArrayList<String>());
	}
	
	League(String leagueID, String ownerID)
	{
		this.leagueID = leagueID;
		this.ownerID = ownerID;
		this.teams = new ArrayList<String>();
		this.setAdmins(new ArrayList<String>());
	}
	
	String getLeagueID()
	{
		return leagueID;
	}
	
	String getLeagueDescription()
	{
		return leagueDescription;
	}
	
	String getOwnerID()
	{
		return ownerID;
	}
	
	ArrayList<String> getTeams()
	{
		return teams;
	}
	
	ArrayList<String> getAdmins() 
	{
		return admins;
	}
	
	boolean addTeam(String teamID)
	{
		if(!teams.contains(teamID))
		{
			teams.add(teamID);
			if(teams.contains(teamID))
				return true;
		}
		
		return false;
			
	}
	
	boolean removeTeam(String teamID)
	{
		int indexOfTeam;
		if(teams.contains(teamID))
		{
			indexOfTeam = teams.indexOf(teamID);
			teams.remove(indexOfTeam);
			if(!teams.contains(teamID))
				return true;
		}
		
		return false;
	}
	
	void setLeagueDescription(String leagueDescription)
	{
		this.leagueDescription = leagueDescription;
	}
	
	void setLeagueID(String leagueID)
	{
		this.leagueID = leagueID;
	}
	
	void setOwnerID(String ownerID)
	{
		this.ownerID = ownerID;
	}

	void setAdmins(ArrayList<String> admins) 
	{
		this.admins = admins;
	}
	
}
