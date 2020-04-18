package views.caster;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import match.Match;
import match.MatchDBInterator;
import player.Player;
import player.PlayerDBInterator;
import team.Team;
import views.GUIShell;
import views.spectator.SpectatorGenerator;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CasterComposite extends Composite {
	
	// Match and Team objects for transitioning back to Spectator view.
	private Match match;
	private Team homeTeam;
	private Team awayTeam;
	private ArrayList<Player> homePlayers;
	private ArrayList<Player> awayPlayers;
	private PlayerDBInterator playerDBInterator;
	private MatchDBInterator matchDBInterator;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CasterComposite(Composite parent, int style) {
		super(parent, style);
	}
	
	public void fillComposite(Composite parent)
	{
		SpectatorGenerator spectatorGenerator = new SpectatorGenerator(parent, SWT.NONE, ((GUIShell)parent), match, homeTeam, awayTeam 
				/*((GUIShell)parent).getMatchGenerator().getMatch(), ((GUIShell)parent).getTeamGenerator().generateTeam(homeTeam.getLeagueID(), 
				 * homeTeam.getTeamID()), ((GUIShell)parent).getTeamGenerator().generateTeam(awayTeam.getLeagueID(), awayTeam.getTeamID())*/);
		Composite previousWindow = spectatorGenerator.getSpectatorComposite();
		
		CreateTopButtons(previousWindow, parent);
		CreateConstantLabels();
		CreateDynamicDataLabels(((GUIShell)parent));

	}
	
	private void CreateTopButtons(Composite previousWindow, Composite parent) {
		// button to go back to spectator view
		Button backButton = new Button(this, SWT.NONE);
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				((GUIShell) parent).setDisplayedComposite(previousWindow);
			
				System.out.println("Back button pressed.");
			}
		});
		backButton.setText("Back");
		backButton.setBounds(10, 10, 134, 45);
		
		Button homeScoreIncrementButton = new Button(this, SWT.NONE);
		homeScoreIncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				match.setHomeScore( match.getHomeScore() + 1 );
				matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				System.out.println("Home score incremented.");
			}
		});
		homeScoreIncrementButton.setBounds(355, 125, 30, 20);
		homeScoreIncrementButton.setText("+");
		
		Button homeScoreDecrementButton = new Button(this, SWT.NONE);
		homeScoreDecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ( match.getHomeScore() > 0 ) {
					match.setHomeScore( match.getHomeScore() - 1 );
					matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				}
				System.out.println("Home score decremented.");
			}
		});
		homeScoreDecrementButton.setText("-");
		homeScoreDecrementButton.setBounds(390, 125, 30, 20);
		
		Button awayScoreIncrementButton = new Button(this, SWT.NONE);
		awayScoreIncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				match.setAwayScore( match.getAwayScore() + 1 );
				matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				System.out.println("Away score incremented.");
			}
		});
		awayScoreIncrementButton.setText("+");
		awayScoreIncrementButton.setBounds(865, 125, 30, 20);
		
		Button awayScoreDecrementButton = new Button(this, SWT.NONE);
		awayScoreDecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ( match.getAwayScore() > 0 ) {
					match.setAwayScore( match.getAwayScore() - 1 );
					matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				}
				System.out.println("Away score decremented.");
			}
		});
		awayScoreDecrementButton.setText("-");
		awayScoreDecrementButton.setBounds(900, 125, 30, 20);
	}
	
	private void CreateConstantLabels() {
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(320, 10, 640, 36);
		lblNewLabel.setText("Caster");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblNewLabel_1.setBounds(628, 80, 24, 20);
		lblNewLabel_1.setText("vs.");
	}

	private void CreateDynamicDataLabels(GUIShell shell) {
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(320, 80, 306, 20);
		lblNewLabel_2.setText(homeTeam.getTeamName());

		Label lblNewLabel_2_1 = new Label(this, SWT.NONE);
		lblNewLabel_2_1.setAlignment(SWT.CENTER);
		lblNewLabel_2_1.setText(awayTeam.getTeamName());
		lblNewLabel_2_1.setBounds(670, 80, 306, 20);

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBounds(425, 125, 430, 20);
		lblNewLabel_3.setText(match.getHomeScore() + "   -   " + match.getAwayScore());

		Group grpHomeTeamInfo = new Group(this, SWT.NONE);
		grpHomeTeamInfo.setText("Home Team Info");
		grpHomeTeamInfo.setBounds(20, 151, 606, 313);

		Group grpAwayTeamInfo = new Group(this, SWT.NONE);
		grpAwayTeamInfo.setText("Away Team Info");
		grpAwayTeamInfo.setBounds(654, 151, 606, 313);

		displayPlayers(homeTeam, homePlayers, shell, grpHomeTeamInfo);
		displayPlayers(awayTeam, awayPlayers, shell, grpAwayTeamInfo);

	}

	private void displayPlayers(Team currentTeam, ArrayList<Player> players, GUIShell shell, Group groupToAddTo) {
		Player displayedPlayer;
		
		ScrolledComposite scrollingPlayersComposite = new ScrolledComposite(groupToAddTo, SWT.BORDER | SWT.V_SCROLL);
		scrollingPlayersComposite.setAlwaysShowScrollBars(true);
		scrollingPlayersComposite.setExpandHorizontal(true);
		scrollingPlayersComposite.setExpandVertical(true);
		scrollingPlayersComposite.setBounds(0, 0, 606, 313);

		Composite playersComposite = new Composite(scrollingPlayersComposite, SWT.NONE);
		FillLayout fill = new FillLayout(SWT.VERTICAL);
		fill.spacing = 5;
		playersComposite.setLayout(fill);

		// Generating the list of team mates
		while (!players.isEmpty()) { 
			Composite playerInfo = new Composite(playersComposite, SWT.NONE);
			fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			
			// making a player parser to access the players names
			displayedPlayer = players.remove(0);

			Label playerName = new Label(playerInfo, SWT.NONE);
			String playerStats = displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName();

			for(String statName : displayedPlayer.getStatisticNames())
				playerStats += "\n     " + statName + ": " + displayedPlayer.getStatistics().get(statName);
			
			playerName.setText(playerStats);
		}
		scrollingPlayersComposite.setContent(playersComposite);
		scrollingPlayersComposite.setMinSize(playersComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}
	
	public void setMatchDBInterator(MatchDBInterator matchDBInterator) {
		this.matchDBInterator = matchDBInterator;
	}
	
	public void setMatch(Match match)
	{
		this.match = match;
	}
	
	public void setHomeTeam(Team homeTeam)
	{
		this.homeTeam = homeTeam;
	}
	
	public void setAwayTeam(Team awayTeam)
	{
		this.awayTeam = awayTeam;
	}
	
	public void setPlayerDBInterator(PlayerDBInterator playerDBInterator)
	{
		this.playerDBInterator = playerDBInterator;
	}
	
	public void setHomePlayers(ArrayList<Player> homePlayers)
	{
		this.homePlayers = homePlayers;
	}
	
	public void setAwayPlayers(ArrayList<Player> awayPlayers)
	{
		this.awayPlayers = awayPlayers;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
