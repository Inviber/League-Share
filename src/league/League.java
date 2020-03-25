package league;

import java.util.ArrayList;

public class League {

	private String leagueID;
	private String leagueName;
	private String ownerID;
	private String sport;
	private String description;
	private ArrayList<String> casterIDs = new ArrayList<String>();
	private ArrayList<String> teamIDs = new ArrayList<String>();
	private ArrayList<String> matchIDs = new ArrayList<String>();
	private LeagueUpdater leagueUpdater;
	
	public League(String leagueID, String leagueName, String ownerID, String sport, String description, 
			ArrayList<String> casterIDs, ArrayList<String> teamIDs, ArrayList<String> matchIDs, LeagueUpdater leagueUpdater) 
	{
		this.leagueID = leagueID;
		this.leagueName = leagueName;
		this.ownerID = ownerID;
		this.sport = sport;
		this.description = description;
		this.casterIDs = casterIDs;
		this.teamIDs = teamIDs;
		this.matchIDs = matchIDs;
		this.leagueUpdater = leagueUpdater;
	}
	
	public String getLeagueID() 
	{
		return leagueID;
	}
	
	public String getLeagueName() 
	{
		return leagueName;
	}

	public String getSport() 
	{
		return sport;
	}
	
	public String getOwnerID() 
	{
		return ownerID;
	}
	
	public String getLeagueDescription() 
	{
		return description;
	}

	public ArrayList<String> getTeamIDs() 
	{
		return teamIDs;
	}
	
	public ArrayList<String> getMatchIDs() 
	{
		return matchIDs;
	}

	public ArrayList<String> getCasterIDs() 
	{
		return casterIDs;
	}
	

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public void setOwnerID(String ownerID) 
	{
		this.ownerID = ownerID;
	}

	public void addCasterIDs(String casterID) 
	{
		if (!this.casterIDs.contains(casterID)) 
		{
			this.casterIDs.add(casterID);
			leagueUpdater.addCasterIDs(this.leagueID, casterID);
		}
	}

	public void removeCasterIDs(String casterID) 
	{
		if (this.casterIDs.contains(casterID)) 
		{
			this.casterIDs.remove(casterID);
			leagueUpdater.removeCasterIDs(this.leagueID, casterID);
		} 
	}
	
	/*
	// Creating team code, removed due to class turning into a Parser.
	String createTeam(String teamName, String zipcode) 
	{
		if (dbHelper.getTeamIDByTeamName(leagueID, teamName) == "")
		{
			String teamID = dbHelper.createTeam(leagueID, teamName, zipcode);
			teamIDs.add(teamID);
			new TeamParser(leagueID, teamID);
			return teamID;
		}
		else return "";
	}
	
	void deleteTeam(String teamName) 
	{
		String teamID = dbHelper.getTeamIDByTeamName(leagueID, teamName);
		if (teamID == "")
		{
			return; // not in database.
		}
		dbHelper.deleteTeam(leagueID, teamID);
		teamIDs.remove(teamID);
	}

	void removeTeam(String teamID) 
	{
		if (teamIDs.contains(teamID)) 
		{
			teamIDs.remove(teamID);
		}
	}
	
	*/
	
	/*
	// Creating match code, removed due to class turning into a Parser.
	String createMatch(String homeTeamID, String awayTeamID, String date) 
	{
		String matchID = dbHelper.createMatch(leagueID, homeTeamID, awayTeamID, date);
		matchIDs.add(matchID);
		new MatchParser(leagueID, matchID);
		return matchID;
	}
	
	void deleteMatch(String matchID) 
	{
		if (matchIDs.contains(matchID))
		{
			dbHelper.deleteMatch(leagueID, matchID);
			matchIDs.remove(matchID);
		}
		else
		{
			return; // not in database.
		}
	}
	
	*/
}
