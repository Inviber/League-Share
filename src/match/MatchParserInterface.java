package match;

import java.util.Calendar;

public interface MatchParserInterface {
	
	public void getMatchDetails();
	
	public String getLeagueID();
	public String getMatchID();
	public String getHomeTeamID();
	public String getAwayTeamID();
	public Calendar getDate();
	public int getHomeScore();
	public int getAwayScore();
}
