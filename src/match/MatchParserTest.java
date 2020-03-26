package match;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.DatabaseHelper;

class MatchParserTest {
	
	private static MatchDBInterator matchDBInterator;
	private static MatchParser matchParser;
	private static String leagueID = "5e7129f4b0f12336fb6ad648"; // Speed Finger Painting League
	private static String matchID = "5e7129f4b0f12336fb6ad654"; // first match

	@BeforeAll
	static void createMatchParser() {
		matchDBInterator = new MatchDBInterator( new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare") );
		matchParser = new MatchParser( leagueID, matchID, matchDBInterator);
	}

	@Test
	void testGetMatchID() {
		assertEquals(matchParser.getMatchID(), matchID);
	}
	
	@Test
	void testGetHomeTeamID() {
		String homeTeamID = "5e7129f4b0f12336fb6ad649";
		assertEquals(matchParser.getHomeTeamID(), homeTeamID);
	}
	
	@Test
	void testGetAwayTeamID() {
		String awayTeamID = "5e7129f4b0f12336fb6ad64a";
		assertEquals(matchParser.getAwayTeamID(), awayTeamID);
	}
	
	@Test
	void testGetDate() {		
		Calendar date = Calendar.getInstance();
		date.clear();
		date.set(2020, 3, 9);
		assertEquals(matchParser.getDate(), date);
	}
	
	@Test
	void testGetHomeScore() {
		int homeScore = 7;
		assertEquals(matchParser.getHomeScore(), homeScore);
	}
	
	@Test
	void testGetAwayScore() {
		int awayScore = 10;
		assertEquals(matchParser.getAwayScore(), awayScore);
	}
}
