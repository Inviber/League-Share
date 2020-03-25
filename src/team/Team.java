package team;

import java.util.ArrayList;

public class Team {

	private String leagueID;
	private String teamID;
	private String teamName;
	private String zipcode;
	private ArrayList<String> playerIDs = new ArrayList<String>();
	private TeamDBInterator teamUpdater;
	
	public Team(String leagueID, String teamID, String teamName, String zipcode, ArrayList<String> playerIDs, TeamDBInterator teamUpdater) {
		this.leagueID = leagueID;
		this.teamID = teamID;
		this.teamName = teamName;
		this.zipcode = zipcode;
		this.playerIDs = playerIDs;
		this.teamUpdater = teamUpdater;
	}
	
	public String getLeagueID() 
	{
		return leagueID;
	}

	public String getTeamName() 
	{
		return teamName;
	}

	public String getZipcode() 
	{
		return zipcode;
	}

	public ArrayList<String> getPlayerIDs()
	{
		return playerIDs;
	}
	
	public String getTeamID()
	{
		return teamID;
	}
	
	public void setTeamID(String teamID)
	{
		this.teamID = teamID;
	}
	
	/*
	String createPlayer(String firstName, String lastName) 
	{
		String playerID = dbHelper.createPlayer(leagueID, teamID, firstName, lastName);
		playerIDs.add(playerID);
		new PlayerParser(leagueID, teamID, playerID);
		return playerID;
	}
	
	void deletePlayer(String playerID) 
	{
		if (playerIDs.contains(playerID))
		{
			dbHelper.deletePlayer(leagueID, teamID, playerID);
			playerIDs.remove(playerID);
		}
		else
		{
			return; // not in database.
		}
	}
	
	*/
	
}
