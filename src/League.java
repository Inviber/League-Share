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
	
	ArrayList<String> getAdmins() 
	{
		return admins;
	}
	
	void addTeam(String teamID)
	{
		teams.add(teamID);
	}
	
	void removeTeam(String teamID)
	{
		int indexOfTeam;
		if(teams.contains(teamID))
		{
			indexOfTeam = teams.indexOf(teamID);
			teams.remove(indexOfTeam);
		}
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
