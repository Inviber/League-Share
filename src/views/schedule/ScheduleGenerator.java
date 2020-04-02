package views.schedule;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import views.GUIShell;
import league.League;
import league.LeagueGenerator;
import match.MatchGenerator;
import match.Match;
import team.Team;
import team.TeamGenerator;

public class ScheduleGenerator {
	
	private ScheduleComposite scheduleComposite;
	private String leagueID;
	private LeagueGenerator leagueGenerator;
	private MatchGenerator matchGenerator;
	private TeamGenerator teamGenerator;
	
	public ScheduleGenerator(Composite parent, int style, String leagueID, LeagueGenerator leagueGenerator, MatchGenerator matchGenerator, TeamGenerator teamGenerator) {
		this.leagueID = leagueID;
		this.leagueGenerator = leagueGenerator;
		this.matchGenerator = matchGenerator;
		this.teamGenerator = teamGenerator;
		
		createComposite(parent, style);
		
//		((GUIShell)parent).setDisplayedComposite(scheduleComposite);
	}
	
	private void createComposite(Composite parent, int style)
	{
		scheduleComposite = new ScheduleComposite(parent, style);
		// Generate league with leagueID then set the league object in scheduleComposite to the created POJO
		League league = leagueGenerator.generateLeague(leagueID);
		scheduleComposite.setLeague(league);
		
		// ArrayLists to set in scheduleComposite
		ArrayList<Match> matches = new ArrayList<Match>();
		ArrayList<Team> team1List = new ArrayList<Team>();
		ArrayList<Team> team2List = new ArrayList<Team>();
		
		// Loop through league's matchIDs to generate match, team1, and team2 objects for the above lists.
		ArrayList<String> matchIDs = league.getMatchIDs();
		for(int i = 0; i < matchIDs.size(); i++)
		{
			matchGenerator.setMatch( leagueID, matchIDs.get(i) );
			Match match = matchGenerator.getMatch();
			matches.add(match);
			
			// Generate team1 with leagueID and teamID from match then set the team1 object in scheduleComposite to the created POJO
			Team team1 = teamGenerator.generateTeam(leagueID, match.getHomeTeamID());
			team1List.add(team1);
			
			// Generate team2 with leagueID and teamID from match then set the team2 object in scheduleComposite to the created POJO
			Team team2 = teamGenerator.generateTeam(leagueID, match.getAwayTeamID());
			team2List.add(team2);
		}
		
		scheduleComposite.setMatchList(matches);
		scheduleComposite.setTeam1List(team1List);
		scheduleComposite.setTeam2(team2List);
		scheduleComposite.fillComposite(parent);
	}
	
	public ScheduleComposite getScheduleComposite()
	{
		return scheduleComposite;
	}

}
