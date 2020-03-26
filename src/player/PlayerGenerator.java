package player;

import database.DatabaseHelper;

public class PlayerGenerator {

	private PlayerParser playerParser;
	private PlayerDBInterator playerDBInterator;
	
	public PlayerGenerator(DatabaseHelper dbHelper)
	{
		this.playerParser = new PlayerParser(playerDBInterator);
		this.playerDBInterator = new PlayerDBInterator(dbHelper);
	}
	
	public Player generatePlayer(String leagueID, String teamID, String playerID)
	{
		playerParser.parsePlayer(leagueID, teamID, playerID);
		  
		  Player player = new Player(leagueID, teamID, playerID, playerParser.getFirstName(), playerParser.getLastName(),
				  playerParser.getStatisticNames(), playerParser.getStatistics(), playerDBInterator);
		  
		  return player;
	}

	
}