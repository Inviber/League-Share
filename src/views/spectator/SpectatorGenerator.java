package views.spectator;

import org.eclipse.swt.widgets.Composite;

import match.Match;
import team.Team;
import views.GUIShell;
import views.schedule.ScheduleGenerator;


public class SpectatorGenerator {
	
	
	public SpectatorGenerator(Composite parent, int style, GUIShell shell, Match match, Team homeTeam, Team awayTeam)
	{
		ScheduleGenerator scheduleGenerator = new ScheduleGenerator(parent, style, homeTeam.getLeagueID(), ((GUIShell)parent).getLeagueGenerator(), ((GUIShell)parent).getMatchGenerator(), ((GUIShell)parent).getTeamGenerator());
		shell.setDisplayedComposite(generateSpectator( parent,  style,  shell,  match,  homeTeam,  awayTeam,  scheduleGenerator.getScheduleComposite()));
	
	}
	
	public SpectatorComposite  generateSpectator(Composite parent, int style, GUIShell shell, Match match, Team homeTeam, Team awayTeam, Composite previousWindow)
	
	{
		SpectatorComposite spectatorComposite = new SpectatorComposite(parent, style, shell, match, homeTeam, awayTeam, previousWindow );

		return spectatorComposite;
		  
	}
}
