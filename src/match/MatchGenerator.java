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
		match = new Match(matchParser.getHomeTeamID(), matchParser.getAwayTeamID(), matchParser.getHomeScore(), matchParser.getAwayScore(), matchParser.getDate());
	}
	
	public Match getMatch() {
		return match;
	}

}
