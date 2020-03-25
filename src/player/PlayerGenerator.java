package player;

import java.util.ArrayList;
import java.util.HashMap;

import database.DatabaseHelper;
import league.League;

public class PlayerGenerator {

	private PlayerParser playerParser;
	private PlayerUpdater playerUpdater;
	
	public PlayerGenerator(DatabaseHelper dbHelper)
	{
		this.playerParser = new PlayerParser(playerUpdater);
		this.playerUpdater = new PlayerUpdater(dbHelper);
	}
	
	public Player generatePlayer(String leagueID, String teamID, String playerID)
	{
		playerParser.parsePlayer(leagueID, teamID, playerID);
		  
		  Player player = new Player(leagueID, teamID, playerID, playerParser.getFirstName(), playerParser.getLastName(),
				  playerParser.getStatisticNames(), playerParser.getStatistics(), playerUpdater);
		  
		  return player;
	}

	
}
