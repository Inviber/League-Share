package match;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import database.DatabaseHelper;

class MatchGeneratorTest {
	
	private static MatchGenerator matchGenerator;
	private static String leagueID = "";
	private static String matchID = "";

	@BeforeAll
	static void createMatchGenerator() {
		matchGenerator = new MatchGenerator( leagueID, matchID, new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare") );
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
