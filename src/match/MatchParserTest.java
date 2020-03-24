package match;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.DatabaseHelper;

class MatchParserTest {
	
	private static MatchParser matchParser;
	private static String leagueID = "";
	private static String matchID = "";

	@BeforeAll
	static void createMatchParser() {
		matchParser = new MatchParser( leagueID, matchID, new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare") );
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
