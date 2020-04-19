package match;

import java.util.Calendar;

import database.DatabaseHelper;

public class MatchGenerator {
	private MatchParser matchParser;
	private MatchDBInterator matchDBInterator;
	private Match match;
	
	public MatchGenerator(DatabaseHelper dbHelper) {
		matchDBInterator = new MatchDBInterator(dbHelper);
	}
	
	public void setMatch(String leagueID, String matchID) {
		matchParser = new MatchParser(leagueID, matchID, matchDBInterator);
		match = new Match(matchParser.getLeagueID(), matchParser.getMatchID(), matchParser.getHomeTeamID(),
				matchParser.getAwayTeamID(), matchParser.getHomeScore(), matchParser.getAwayScore(), matchParser.getDate());
	}
	
	public Match getMatch() {
		return match;
	}
	
	public MatchDBInterator getMatchDBInterator()
	{
		return this.matchDBInterator;
	}
	
	public MatchParser getMatchParser()
	{
		return this.matchParser;
	}
	
	public void createMatch(String leagueID, String homeTeamID, String awayTeamID, String date)
	{
		matchDBInterator.createMatch(leagueID, homeTeamID, awayTeamID, date);
	}
	
	public void updateMatch(String leagueID, String matchID, String homeTeamID, String awayTeamID, String date, String homeScore, String awayScore)
	{
		matchDBInterator.updateMatch(leagueID, matchID, homeTeamID, awayTeamID, date, homeScore, awayScore);
	}
	
	public void deleteMatch(String leagueID, String matchID)
	{
		matchDBInterator.deleteMatch(leagueID, matchID);
	}

}
