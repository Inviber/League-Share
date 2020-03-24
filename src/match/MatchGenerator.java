package match;

import java.util.Calendar;

import database.DatabaseHelper;

public class MatchGenerator {
	private MatchParser matchParser;
	private Match match;
	
	public MatchGenerator(String leagueID, String matchID, DatabaseHelper dbHelper) {
		matchParser = new MatchParser(leagueID, matchID, dbHelper);
		setMatch( matchParser.getHomeTeamID(), matchParser.getAwayTeamID(), matchParser.getHomeScore(), matchParser.getAwayScore(), matchParser.getDate() );
	}
	
	public void setMatch(String homeTeamID, String awayTeamID, int homeScore, int awayScore, Calendar date) {
		match = new Match( homeTeamID, awayTeamID, homeScore, awayScore, date);
	}
	
	public Match getMatch() {
		return match;
	}

}
