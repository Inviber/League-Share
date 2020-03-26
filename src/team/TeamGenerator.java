package team;

import database.DatabaseHelper;

public class TeamGenerator {
	
	private TeamParser teamParser;
	private TeamDBInterator teamDBInterator;
	
	public TeamGenerator(DatabaseHelper dbHelper)
	{
		this.teamParser = new TeamParser(teamDBInterator);
		this.teamDBInterator = new TeamDBInterator(dbHelper);
	}
	
	public Team generateLeague(String leagueID, String teamID)
	{
		teamParser.parseTeam(leagueID, teamID);
		  
		Team team = new Team(leagueID, teamID, teamParser.getTeamName(), teamParser.getZipcode(),  teamParser.getPlayerIDs(), teamDBInterator);
		  		  
		return team;
	}
}
