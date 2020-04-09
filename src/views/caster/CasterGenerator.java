package views.caster;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import match.Match;
import player.Player;
import player.PlayerGenerator;
import team.Team;

public class CasterGenerator {
	CasterComposite casterComposite;
	private Match match;
	private Team team1;
	private Team team2;
	private ArrayList<Player> team1Players;
	private ArrayList<Player> team2Players;
	
	public CasterGenerator(Composite parent, int style, Match match, Team team1, Team team2, PlayerGenerator playerGenerator)
	{
		this.match = match;
		this.team1 = team1;
		this.team2 = team2;
		
		populateTeamPlayerLists(playerGenerator);
		createComposite(parent, style);
		casterComposite.setPlayerDBInterator(playerGenerator.getPlayerDBInterator());
	}
	
	public void populateTeamPlayerLists(PlayerGenerator playerGenerator)
	{
		// Variables to use for generating players (helps with readability)
		ArrayList<String> team1PlayerIDs = team1.getPlayerIDs();
		ArrayList<String> team2PlayerIDs = team2.getPlayerIDs();
		String team1ID = team1.getTeamID();
		String team2ID = team2.getTeamID();
		String leagueID = team1.getLeagueID();
		
		team1Players = new ArrayList<Player>(); // Clear list before populating it again.
		team2Players = new ArrayList<Player>(); // Clear list before populating it again.
		
		// Verify both teams are in the same league before generating players otherwise don't generate players.
		if ( team1.getLeagueID().equals(team2.getLeagueID()) )
		{
			// Generate all players from team1 and add them to the team1 array list
			for(int i = 0; i < team1PlayerIDs.size(); i++)
			{
				team1Players.add( playerGenerator.generatePlayer(leagueID, team1ID, team1PlayerIDs.get(i)) );
			}
			
			// Generate all players from team2 and add them to the team2 array list
			for(int i = 0; i < team2PlayerIDs.size(); i++)
			{
				team2Players.add( playerGenerator.generatePlayer(leagueID, team2ID, team2PlayerIDs.get(i)) );
			}
		}
		else
		{
			System.out.println("Error: The provided teams do not belong to the same league. Unable to getAllPlayers().");

		}
		
//		System.out.println(team1PlayerIDs);
//		System.out.println(team2PlayerIDs);
	}
	
	public ArrayList<Player> getTeam1Players()
	{
		return team1Players;
	}
	
	public ArrayList<Player> getTeam2Players()
	{
		return team2Players;
	}
	
	public void createComposite(Composite parent, int style)
	{
		casterComposite = new CasterComposite(parent, style);
		casterComposite.setMatch(match);
		casterComposite.setTeam1(team1);
		casterComposite.setTeam2(team2);
		casterComposite.setTeam1Players(team1Players);
		casterComposite.setTeam2Players(team2Players);
		casterComposite.fillComposite(parent);
	}
	
	public CasterComposite getCasterComposite()
	{
		return casterComposite;
	}
}
