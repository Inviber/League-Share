package player;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

	private String leagueID;
	private String teamID;
	private String playerID;
	private String firstName;
	private String lastName;
	private ArrayList<String> statisticNames = new ArrayList<String>();
	private HashMap<String, String> statistics = new HashMap<String, String>();
	private PlayerDBInterator playerUpdater;
	public Player(String leagueID, String teamID, String playerID, String firstName, String lastName,
			ArrayList<String> statisticNames, HashMap<String, String> statistics, PlayerDBInterator playerUpdater) 
	{
		this.leagueID = leagueID;
		this.teamID = teamID;
		this.playerID = playerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.statisticNames = statisticNames;
		this.statistics = statistics;
		this.playerUpdater = playerUpdater;
	}
	
	public String getLeagueID() 
	{
		return leagueID;
	}
	
	public String getTeamID() 
	{
		return teamID;
	}
	
	public String getPlayerID() 
	{
		return playerID;
	}
	
	public String getFirstName() 
	{
		return firstName;
	}
	
	public String getLastName() 
	{
		return lastName;
	}
	
	public ArrayList<String> getStatisticNames() 
	{
		return statisticNames;
	}
	
	public HashMap<String, String> getStatistics() 
	{
		return statistics;
	}
	
	
	
}
