package team;

import database.DatabaseHelper;

public class TeamGenerator {
	
	private TeamParser teamParser;
	private TeamDBInterator teamUpdater;
	
	public TeamGenerator(DatabaseHelper dbHelper)
	{
		this.teamParser = new TeamParser(teamUpdater);
		this.teamUpdater = new TeamDBInterator(dbHelper);
	}
	
	public Team generateLeague(String leagueID, String teamID)
	{
		teamParser.parseTeam(leagueID, teamID);
		  
		Team team = new Team(leagueID, teamID, teamParser.getTeamName(), teamParser.getZipcode(),  teamParser.getPlayerIDs(), teamUpdater);
		  		  
		return team;
	}
}
