package views.spectator;

import org.eclipse.swt.widgets.Composite;

import match.Match;
import team.Team;
import views.GUIShell;


public class SpectatorGenerator {
	
	
	public SpectatorGenerator(Composite parent, int style, GUIShell shell, Match match, Team homeTeam, Team awayTeam, Composite previousWindow)
	{
		
		shell.setDisplayedComposite(generateSpectator( parent,  style,  shell,  match,  homeTeam,  awayTeam,  previousWindow));
	
	}
	
	public SpectatorComposite  generateSpectator(Composite parent, int style, GUIShell shell, Match match, Team homeTeam, Team awayTeam, Composite previousWindow)
	
	{
		SpectatorComposite spectatorComposite = new SpectatorComposite(parent, style, shell, match, homeTeam, awayTeam, previousWindow );
		  return spectatorComposite;
	}
}
