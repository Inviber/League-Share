package team;

import java.util.ArrayList;

import database.DatabaseHelper;

public class TeamGenerator {
	
	private TeamParser teamParser;
	private TeamUpdater teamUpdater;
	
	public TeamGenerator(DatabaseHelper dbHelper)
	{
		this.teamParser = new TeamParser(teamUpdater);
		this.teamUpdater = new TeamUpdater(dbHelper);
	}
	
	public Team generateLeague(String leagueID, String teamID)
	{
		teamParser.parseTeam(leagueID, teamID);
		  
		Team team = new Team(leagueID, teamID, teamParser.getTeamName(), teamParser.getZipcode(),  teamParser.getPlayerIDs(), teamUpdater);
		  		  
		return team;
	}
}
