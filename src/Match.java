
public class Match {
	private String matchID;
	private String homeTeam;
	private String awayTeam;
	private String date;
	private String finalScore;
	
	Match(String matchID, String homeTeam, String awayTeam, String date, String finalScore)
	{
		this.matchID = matchID;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
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

	String getHomeTeam() 
	{
		return homeTeam;
	}

	String getAwayTeam() 
	{
		return awayTeam;
	}

	String getDate() 
	{
		return date;
	}
	
	
}
