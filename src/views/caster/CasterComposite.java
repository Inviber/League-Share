package views.caster;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

import match.Match;
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
	private Team team1;
	private Team team2;
	private ArrayList<Player> team1Players;
	private ArrayList<Player> team2Players;
	private PlayerDBInterator playerDBInterator;
	
	private Text text;

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
		Color dark_gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
		setLayout(new FormLayout());
		
		// Top score panel of view ----------------------------------------------------------->
		Group scoreGroup = new Group(this, SWT.NONE);
		FormData fd_scoreGroup = new FormData();
		fd_scoreGroup.bottom = new FormAttachment(0, 110);
		fd_scoreGroup.right = new FormAttachment(0, 1270);
		fd_scoreGroup.top = new FormAttachment(0, 10);
		fd_scoreGroup.left = new FormAttachment(0, 10);
		scoreGroup.setLayoutData(fd_scoreGroup);
		
		Label lblTeamname_1 = new Label(scoreGroup, SWT.NONE);
		lblTeamname_1.setAlignment(SWT.CENTER);
		lblTeamname_1.setBounds(165, 10, 375, 30);
		lblTeamname_1.setText("team1.name");
		
		Label lblTeamname_2 = new Label(scoreGroup, SWT.NONE);
		lblTeamname_2.setText("team2.name");
		lblTeamname_2.setAlignment(SWT.CENTER);
		lblTeamname_2.setBounds(596, 10, 375, 30);
		
		Label lblTeam1score = new Label(scoreGroup, SWT.NONE);
		lblTeam1score.setAlignment(SWT.CENTER);
		lblTeam1score.setBounds(315, 46, 75, 30);
		lblTeam1score.setText("team1.score");
		
		Label lblTeam2score = new Label(scoreGroup, SWT.NONE);
		lblTeam2score.setText("team2.score");
		lblTeam2score.setAlignment(SWT.CENTER);
		lblTeam2score.setBounds(745, 46, 75, 30);
		
		Button spectateButton = new Button(scoreGroup, SWT.NONE);
		spectateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SpectatorGenerator spectatorGenerator = new SpectatorGenerator(parent, SWT.NONE, ((GUIShell)parent), match, team1, team2);
				((GUIShell)parent).setDisplayedComposite(spectatorGenerator.getSpectatorComposite());
			}
		});
		spectateButton.setBounds(1131, 10, 119, 30);
		spectateButton.setText("Spectator View");
		
		Button team1ScoreIncrementButton = new Button(scoreGroup, SWT.NONE);
		team1ScoreIncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println("Team1 score incremented.");
			}
		});
		team1ScoreIncrementButton.setBounds(175, 46, 40, 30);
		team1ScoreIncrementButton.setText("+");
		
		Button team1ScoreDecrementButton = new Button(scoreGroup, SWT.NONE);
		team1ScoreDecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println("Team1 score decremented.");
			}
		});
		team1ScoreDecrementButton.setText("-");
		team1ScoreDecrementButton.setBounds(221, 46, 40, 30);
		
		Button team2ScoreIncrementButton = new Button(scoreGroup, SWT.NONE);
		team2ScoreIncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println("Team2 score incremented.");
			}
		});
		team2ScoreIncrementButton.setText("+");
		team2ScoreIncrementButton.setBounds(885, 46, 40, 30);
		
		Button team2ScoreDecrementButton = new Button(scoreGroup, SWT.NONE);
		team2ScoreDecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				System.out.println("Team2 score decremented.");
			}
		});
		team2ScoreDecrementButton.setText("-");
		team2ScoreDecrementButton.setBounds(931, 46, 40, 30);
		
		Label lblVs = new Label(scoreGroup, SWT.NONE);
		lblVs.setAlignment(SWT.CENTER);
		lblVs.setBounds(546, 10, 40, 30);
		lblVs.setText("V.S.");
		// end top score panel <-----------------------------------------------------
		
		// bottom text entry panel of view ----------------------------------------->
		Button btnSubmit = new Button(this, SWT.NONE);
		FormData fd_btnSubmit = new FormData();
		fd_btnSubmit.bottom = new FormAttachment(100, -25);
		fd_btnSubmit.right = new FormAttachment(100, -55);
		btnSubmit.setLayoutData(fd_btnSubmit);
		btnSubmit.setText("Submit");
		
		text = new Text(this, SWT.BORDER);
		fd_btnSubmit.top = new FormAttachment(text, -2, SWT.TOP);
		fd_btnSubmit.left = new FormAttachment(text, 6);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -136);
		text.setLayoutData(fd_text);
		
		Label lblPostAnnouncement = new Label(this, SWT.NONE);
		fd_text.left = new FormAttachment(lblPostAnnouncement, 6);
		fd_text.top = new FormAttachment(0, 672);
		lblPostAnnouncement.setAlignment(SWT.CENTER);
		FormData fd_lblPostAnnouncement = new FormData();
		fd_lblPostAnnouncement.bottom = new FormAttachment(100, -30);
		fd_lblPostAnnouncement.left = new FormAttachment(0, 55);
		fd_lblPostAnnouncement.right = new FormAttachment(100, -1095);
		lblPostAnnouncement.setLayoutData(fd_lblPostAnnouncement);
		lblPostAnnouncement.setText("Post Announcement");
		// end bottom text entry panel of view <--------------------------------------
		
		// Team 1 player list and expandable stat menu group
		Group statsTeam1Group = new Group(this, SWT.NONE);
		statsTeam1Group.setLayout(new GridLayout(1, true));
		statsTeam1Group.setBackground(dark_gray);
		fd_lblPostAnnouncement.top = new FormAttachment(0, 675);
		FormData fd_statsTeam1Group = new FormData();
		fd_statsTeam1Group.bottom = new FormAttachment(text, -6);
		fd_statsTeam1Group.top = new FormAttachment(scoreGroup, 6);
		fd_statsTeam1Group.left = new FormAttachment(0, 20);
		fd_statsTeam1Group.right = new FormAttachment(0, 605);
		statsTeam1Group.setLayoutData(fd_statsTeam1Group);
		
		// ADD ACTION LISTENER TO EXPAND COMPOSITE
		ScrolledComposite statComposite_team1 = new ScrolledComposite(statsTeam1Group, SWT.V_SCROLL);
		statComposite_team1.setExpandVertical(true);
		statComposite_team1.setMinSize( 550, 50 );
//		statComposite_team1.setBounds(10, 10, 565, 50);
		
		// Create components for each player on the team.
		for(int i = 0; i < team1Players.size(); i++)
		{
			Button btnPlayername = new Button(statComposite_team1, SWT.NONE);
			btnPlayername.setAlignment(SWT.CENTER);
			btnPlayername.setSize(565, 30);
			btnPlayername.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("Player button clicked.");
				}
			});
			btnPlayername.setText( team1Players.get(i).getFirstName() + " " + team1Players.get(i).getLastName() );
		}
		
		// START EXPANDED COMPOSITE EXAMPLE----------------------------------------------------->
		Composite statComposite_team1_expanded = new Composite(statsTeam1Group, SWT.NONE);
		statComposite_team1_expanded.setBounds(10, 46, 565, 250);
		
		Label lblPlayername_1 = new Label(statComposite_team1_expanded, SWT.NONE);
		lblPlayername_1.setText("player.name");
		lblPlayername_1.setAlignment(SWT.CENTER);
		lblPlayername_1.setBounds(10, 10, 75, 15);
		
		Label lblStat1 = new Label(statComposite_team1_expanded, SWT.NONE);
		lblStat1.setAlignment(SWT.CENTER);
		lblStat1.setBounds(100, 50, 275, 30);
		lblStat1.setText("stat1");
		
		Button stat1DecrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat1DecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Stat1 decremented.");
			}
		});
		stat1DecrementButton.setText("-");
		stat1DecrementButton.setBounds(427, 50, 40, 30);
		
		Button stat1IncrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat1IncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Stat1 incremented.");
			}
		});
		stat1IncrementButton.setBounds(381, 50, 40, 30);
		stat1IncrementButton.setText("+");
		
		Label lblStat2 = new Label(statComposite_team1_expanded, SWT.NONE);
		lblStat2.setText("stat2");
		lblStat2.setAlignment(SWT.CENTER);
		lblStat2.setBounds(100, 86, 275, 30);
		
		Button stat2IncrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat2IncrementButton.setText("+");
		stat2IncrementButton.setBounds(381, 86, 40, 30);
		
		Button stat2DecrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat2DecrementButton.setText("-");
		stat2DecrementButton.setBounds(427, 86, 40, 30);
		// END EXPANDED COMPOSITE EXAMPLE <-------------------------------------------------------
		
		// Team 2 player list and expandable stat menu group
		Group statsTeam2Group = new Group(this, SWT.NONE);
		statsTeam2Group.setLayout(new GridLayout(1, true));
		statsTeam2Group.setBackground(dark_gray);
		FormData fd_statsTeam2Group = new FormData();
		fd_statsTeam2Group.bottom = new FormAttachment(btnSubmit, -6);
		fd_statsTeam2Group.top = new FormAttachment(scoreGroup, 6);
		fd_statsTeam2Group.right = new FormAttachment(100, -10);
		fd_statsTeam2Group.left = new FormAttachment(100, -604);
		statsTeam2Group.setLayoutData(fd_statsTeam2Group);
		
		Composite statComposite_team2 = new Composite(statsTeam2Group, SWT.NONE);
		statComposite_team2.setBounds(10, 10, 565, 30);
		
		// Create components for each player on the team.
		for(int i = 0; i < team1Players.size(); i++)
		{
			Button btnPlayername = new Button(statComposite_team2, SWT.NONE);
			btnPlayername.setAlignment(SWT.CENTER);
			btnPlayername.setBounds(10, 10, 75, 15);
			btnPlayername.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
				}
			});
			btnPlayername.setText( team2Players.get(i).getFirstName() + " " + team2Players.get(i).getLastName() );
		}

	}
	
	public void setMatch(Match match)
	{
		this.match = match;
	}
	
	public void setTeam1(Team team1)
	{
		this.team1 = team1;
	}
	
	public void setTeam2(Team team2)
	{
		this.team2 = team2;
	}
	
	public void setPlayerDBInterator(PlayerDBInterator playerDBInterator)
	{
		this.playerDBInterator = playerDBInterator;
	}
	
	public void setTeam1Players(ArrayList<Player> team1Players)
	{
		this.team1Players = team1Players;
	}
	
	public void setTeam2Players(ArrayList<Player> team2Players)
	{
		this.team2Players = team2Players;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
