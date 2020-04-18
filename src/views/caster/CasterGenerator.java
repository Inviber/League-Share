package views.caster;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import match.Match;
import match.MatchGenerator;
import player.Player;
import player.PlayerGenerator;
import team.Team;
import views.GUIShell;

public class CasterGenerator {
	CasterComposite casterComposite;
	private Match match;
	private Team homeTeam;
	private Team awayTeam;
	private ArrayList<Player> homePlayers;
	private ArrayList<Player> awayPlayers;
	
	public CasterGenerator(Composite parent, int style, Match match, Team homeTeam, Team awayTeam)
	{
		this.match = match;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		PlayerGenerator playerGenerator = ((GUIShell)parent).getPlayerGenerator();
		MatchGenerator matchGenerator = ((GUIShell)parent).getMatchGenerator();
		
		populateTeamPlayerLists(playerGenerator);
		createComposite(parent, style);
		casterComposite.setPlayerDBInterator(playerGenerator.getPlayerDBInterator());
		casterComposite.setMatchDBInterator(matchGenerator.getMatchDBInterator());
	}
	
	public void populateTeamPlayerLists(PlayerGenerator playerGenerator)
	{
		// Variables to use for generating players (helps with readability)
		ArrayList<String> homePlayerIDs = homeTeam.getPlayerIDs();
		ArrayList<String> awayPlayerIDs = awayTeam.getPlayerIDs();
		String team1ID = homeTeam.getTeamID();
		String team2ID = awayTeam.getTeamID();
		String leagueID = homeTeam.getLeagueID();
		
		homePlayers = new ArrayList<Player>(); // Clear list before populating it again.
		awayPlayers = new ArrayList<Player>(); // Clear list before populating it again.
		
		// Verify both teams are in the same league before generating players otherwise don't generate players.
		if ( homeTeam.getLeagueID().equals(awayTeam.getLeagueID()) )
		{
			// Generate all players from homeTeam and add them to the homeTeam array list
			for(int i = 0; i < homePlayerIDs.size(); i++)
			{
				homePlayers.add( playerGenerator.generatePlayer(leagueID, team1ID, homePlayerIDs.get(i)) );
			}
			
			// Generate all players from awayTeam and add them to the awayTeam array list
			for(int i = 0; i < awayPlayerIDs.size(); i++)
			{
				awayPlayers.add( playerGenerator.generatePlayer(leagueID, team2ID, awayPlayerIDs.get(i)) );
			}
		}
		else
		{
			System.out.println("Error: The provided teams do not belong to the same league. Unable to getAllPlayers().");

		}

	}
	
	public ArrayList<Player> getHomePlayers()
	{
		return homePlayers;
	}
	
	public ArrayList<Player> getAwayPlayers()
	{
		return awayPlayers;
	}
	
	public void createComposite(Composite parent, int style)
	{
		casterComposite = new CasterComposite(parent, style);
		casterComposite.setMatch(match);
		casterComposite.setHomeTeam(homeTeam);
		casterComposite.setAwayTeam(awayTeam);
		casterComposite.setHomePlayers(homePlayers);
		casterComposite.setAwayPlayers(awayPlayers);
		casterComposite.fillComposite(parent);
	}
	
	public CasterComposite getCasterComposite()
	{
		return casterComposite;
	}
}
