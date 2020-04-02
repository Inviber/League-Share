package match;

import java.util.Calendar;

public class Match {
	// private String leagueID;
	// private String matchID;
	private String homeTeamID;
	private String awayTeamID;
	private int homeScore;
	private int awayScore;
    private Calendar date;
    
    public Match() {}

    public Match(String homeTeamID, String awayTeamID, int homeScore, int awayScore, Calendar date) {
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.date = date;
    }

    public String getHomeTeamID() {
        return homeTeamID;
    }

    public String getAwayTeamID() {
        return awayTeamID;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Calendar getDate() {
        return date;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setDate(Calendar date) {
    	System.out.println(date.toString());
        this.date = date;
    }

}
