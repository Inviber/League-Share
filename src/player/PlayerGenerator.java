package player;

import database.DatabaseHelper;

public class PlayerGenerator {

	private PlayerParser playerParser;
	private PlayerDBInterator playerDBInterator;
	
	public PlayerGenerator(DatabaseHelper dbHelper)
	{
		this.playerDBInterator = new PlayerDBInterator(dbHelper);
		this.playerParser = new PlayerParser(playerDBInterator);
	}
	
	public Player generatePlayer(String leagueID, String teamID, String playerID)
	{
		//System.out.println(leagueID + " " + teamID + " " + playerID);
		playerParser.parsePlayer(leagueID, teamID, playerID);
		
		
		
		  Player player = new Player(leagueID, teamID, playerID, playerParser.getFirstName(), playerParser.getLastName(),
				  playerParser.getStatisticNames(), playerParser.getStatistics(), playerDBInterator);
		  
		  return player;
	}

	public void createPlayer(String leagueID, String teamID, String firstName, String lastName)
	{
		playerDBInterator.createPlayer(leagueID, teamID, firstName, lastName);
	}
	
	public void updatePlayer(String leagueID, String teamID, String playerID, String firstName, String lastName)
	{
		playerDBInterator.updatePlayer(leagueID, teamID, playerID, firstName, lastName);
	}
	
	public void deletePlayer(String leagueID, String teamID, String playerID)
	{
		playerDBInterator.deletePlayer(leagueID, teamID, playerID);
	}
	
}
