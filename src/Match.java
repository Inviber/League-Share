import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Match {
	private String matchID;
	private String homeTeamID;
	private String awayTeamID;
	private String date;
	private String finalScore;
	
	Match(String matchID, String homeTeamID, String awayTeamID, String date, String finalScore)
	{
		this.matchID = matchID;
		this.homeTeamID = homeTeamID;
		this.awayTeamID = awayTeamID;
		this.date = date;
		this.finalScore = finalScore;
	}

	String getFinalScore() 
	{
		return finalScore;
	}

	void setFinalScore(String finalScore) 
	{
		this.finalScore = finalScore;
	}

	String getMatchID() 
	{
		return matchID;
	}

	String getHomeTeamID() 
	{
		return homeTeamID;
	}

	String getAwayTeamID() 
	{
		return awayTeamID;
	}

	String getDate() 
	{
		return date;
	}
	
	
	/*
	@Test
	void createMatchInTeam() {
		String testAddMatchID = team.createMatch("homeTeamAdd", "awayTeam", "date", "finalScore");
				
		assertTrue(team.getMatchIDs().contains(testAddMatchID), "testAddMatchID is pressent in team array");
	
		team.deleteMatch(testAddMatchID);
	}
	
	@Test
	void deleteMatchFromLeague()
	{
		String testRemoveMatchID = team.createMatch("homeTeamRemove", "awayTeam", "date", "finalScore");
		
		assertTrue(team.getMatchIDs().contains(testRemoveMatchID), "testRemoveMatchID is pressent in team array");

		team.deleteMatch(testRemoveMatchID);

		assertFalse(team.getMatchIDs().contains(testRemoveMatchID), "testRemoveMatchID is no longer present in team array");
	}
	 */
	
	
}
