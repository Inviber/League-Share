package player;

import java.util.ArrayList;
import java.util.HashMap;

public interface PlayerParserInterface {

	public void parsePlayer(String leagueID, String teamID, String playerID);
	
	public String getPlayerID();
	public String getFirstName();
	public String getLastName();
	public ArrayList<String> getStatisticNames();
	public HashMap<String, String> getStatistics();
	public String getStatstic(String statisticName);
}
