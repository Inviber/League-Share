package views.teamadmin;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import league.LeagueGenerator;
import match.MatchDBInterator;
import match.MatchGenerator;
import player.Player;
import player.PlayerDBInterator;
import player.PlayerGenerator;
import team.Team;
import team.TeamGenerator;
import user.AccountGenerator;
import views.GUIShell;
import views.caster.CasterGenerator;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;

public class ModifyRosterComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	private Team team;
	private ArrayList<Player> players = new ArrayList<Player>();
	private PlayerDBInterator playerDBInterator;
	private MatchDBInterator matchDBInterator;
	
	
	public ModifyRosterComposite(Composite parent, int style, GUIShell shell, String leagueID, String teamID) {
		super(parent, style);
		
		setSize(863, 521);
		
		AccountGenerator accountGenerator = shell.getAccountGenerator();
		LeagueGenerator leagueGenerator = shell.getLeagueGenerator();
		MatchGenerator matchGenerator = shell.getMatchGenerator();
		TeamGenerator teamGenerator = shell.getTeamGenerator();
		PlayerGenerator playerGenerator = shell.getPlayerGenerator();
		
		Label lblPlayers = new Label(this, SWT.NONE);
		lblPlayers.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblPlayers.setAlignment(SWT.CENTER);
		lblPlayers.setBounds(380, 79, 101, 25);
		lblPlayers.setText("Players");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(184, 110, 506, 400);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Team team = teamGenerator.generateTeam(leagueID, teamID);
		
		
		ArrayList<String> playerIDs = team.getPlayerIDs();
		//System.out.println(playerIDs.toString());
		
		for(int i = 0; i < playerIDs.size(); i++)
		{
			
			Player player = playerGenerator.generatePlayer(leagueID, teamID, playerIDs.get(i));
			//System.out.println(player.getFirstName());
			players.add(player);
		}
		
		displayPlayers(team, players, shell, scrolledComposite);
		
		//playerName.setText( displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName() );
		

	}
	//private void displayPlayers(Team currentTeam, ArrayList<Player> players, Composite parent, Group groupToAddTo)
	private void displayPlayers(Team currentTeam, ArrayList<Player> players, Composite parent, Composite groupToAddTo) {
		Player displayedPlayer;
		
		ScrolledComposite scrollingPlayersComposite = new ScrolledComposite(groupToAddTo, SWT.BORDER | SWT.V_SCROLL);
		scrollingPlayersComposite.setAlwaysShowScrollBars(true);
		scrollingPlayersComposite.setExpandHorizontal(true);
		scrollingPlayersComposite.setExpandVertical(true);
		scrollingPlayersComposite.setBounds(0, 0, 606, 313);

		Composite playersComposite = new Composite(scrollingPlayersComposite, SWT.NONE);
		FillLayout fill = new FillLayout(SWT.VERTICAL);
		fill.spacing = 20;
		playersComposite.setLayout(fill);

		// to iterate through each player
		int playerIterator = 0;
		// Populating stats for each player
		while (playerIterator < players.size()) {
			
			// to access the player name and stats
			displayedPlayer = players.get(playerIterator);
			ArrayList<String> statisticNames = displayedPlayer.getStatisticNames();
			int statsPerPlayer = statisticNames.size();
			
			Composite playerInfo = new Composite(playersComposite, SWT.NONE);

			Label playerName = new Label(playerInfo, SWT.NONE);
			playerName.setText( displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName() );
			playerName.setBounds(0, 0, 200, 20);

			
			for( int statIterator = 0; statIterator < statsPerPlayer; statIterator++ )
			{
				// get all data for updating a specific stat
				String leagueID = currentTeam.getLeagueID();
				String teamID = currentTeam.getTeamID();
				String playerID = displayedPlayer.getPlayerID();
				String currentStat = statisticNames.get(statIterator);
				HashMap<String, String> statValues = displayedPlayer.getStatistics();
				int currentStatValue;
				try {
					currentStatValue = Integer.parseInt(statValues.get(currentStat));
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					currentStatValue = -1;
				}
				
				// integer for x value of setting player stat label bounds : 30 represents label height (20) plus spacing (10)
				int nextStatPosition = 25 + (statIterator * 25);
				
				Label statLbl = new Label(playerInfo, SWT.NONE);
				statLbl.setText( statisticNames.get(statIterator) + ": " + currentStatValue);
				statLbl.setBounds(10, nextStatPosition, 200, 20);
				
				// to use statIterator value inside button listener
				int nameLocation = statIterator;
				
				Button incrementStat = new Button(playerInfo, SWT.NONE);
				incrementStat.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						int currentStatValue;
						try {
							currentStatValue = Integer.parseInt(statValues.get(currentStat));
						} catch (NumberFormatException nfe) {
							nfe.printStackTrace();
							currentStatValue = -1;
						}

						playerDBInterator.updatePlayerStatistics(leagueID, teamID, playerID, currentStat, currentStatValue + 1 );
//						CasterGenerator cg = new CasterGenerator(parent, SWT.NONE, getNewMatch(parent), getNewHomeTeam(parent), getNewAwayTeam(parent) );
//						((GUIShell)parent).setDisplayedComposite(cg.getCasterComposite());

					}
				});
				incrementStat.setBounds(220, nextStatPosition, 30, 20);
				incrementStat.setText("+");
				
				Button decrementStat = new Button(playerInfo, SWT.NONE);
				decrementStat.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						int currentStatValue;
						try {
							currentStatValue = Integer.parseInt(statValues.get(currentStat));
						} catch (NumberFormatException nfe) {
							nfe.printStackTrace();
							currentStatValue = -1;
						}

						if (currentStatValue > 0)
						{
							playerDBInterator.updatePlayerStatistics(leagueID, teamID, playerID, currentStat, currentStatValue - 1 );
//							CasterGenerator cg = new CasterGenerator(parent, SWT.NONE, getNewMatch(parent), getNewHomeTeam(parent), getNewAwayTeam(parent) );
//							((GUIShell)parent).setDisplayedComposite(cg.getCasterComposite());

						}
						
					}
				});
				decrementStat.setBounds(260, nextStatPosition, 30, 20);
				decrementStat.setText("-");
			}
			
			playerIterator++;
		}
		scrollingPlayersComposite.setContent(playersComposite);
		scrollingPlayersComposite.setMinSize(playersComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
