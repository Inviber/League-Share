import java.util.ArrayList;

public class Team {
	private String leagueID;
	private String teamID;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	Team(String leagueID, String teamID)
	{
		this.leagueID = leagueID;
		this.teamID = teamID;
	}
	
	ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	String getTeamID()
	{
		return teamID;
	}
	
	void setTeamID(String teamID)
	{
		this.teamID = teamID;
	}
}
