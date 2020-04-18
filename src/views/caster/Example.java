package views.caster;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import views.GUIShell;
import views.spectator.SpectatorGenerator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class Example extends Composite {
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Example(Composite parent, int style) {
		super(parent, style);
		
		Color dark_gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
		setLayout(new FormLayout());
		
		// Top score panel of view ----------------------------------------------------------->
		Group scoreGroup = new Group(this, SWT.NONE);
		FormData fd_scoreGroup = new FormData();
		fd_scoreGroup.bottom = new FormAttachment(0, 85);
		fd_scoreGroup.right = new FormAttachment(0, 1260);
		fd_scoreGroup.top = new FormAttachment(0, 10);
		fd_scoreGroup.left = new FormAttachment(0, 10);
		scoreGroup.setLayoutData(fd_scoreGroup);
		
		Label lblTeamname_1 = new Label(scoreGroup, SWT.NONE);
		lblTeamname_1.setAlignment(SWT.CENTER);
		lblTeamname_1.setBounds(165, 10, 375, 30);
		lblTeamname_1.setText(team1.getTeamName());
		
		Label lblTeamname_2 = new Label(scoreGroup, SWT.NONE);
		lblTeamname_2.setText(team2.getTeamName());
		lblTeamname_2.setAlignment(SWT.CENTER);
		lblTeamname_2.setBounds(596, 10, 375, 30);
		
		Label lblTeam1score = new Label(scoreGroup, SWT.NONE);
		lblTeam1score.setAlignment(SWT.CENTER);
		lblTeam1score.setBounds(315, 46, 75, 30);
		lblTeam1score.setText( "" + match.getHomeScore() );
		
		Label lblTeam2score = new Label(scoreGroup, SWT.NONE);
		lblTeam2score.setText( "" + match.getAwayScore() );
		lblTeam2score.setAlignment(SWT.CENTER);
		lblTeam2score.setBounds(745, 46, 75, 30);
		
		Button spectateButton = new Button(scoreGroup, SWT.NONE);
		spectateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				System.out.println("match: " + match);
//				System.out.println("team1: " + team1);
//				System.out.println("team2: " + team2);
				SpectatorGenerator spectatorGenerator = new SpectatorGenerator(parent, SWT.NONE, ((GUIShell)parent), match, team1, team2 /*((GUIShell)parent).getMatchGenerator().getMatch(), ((GUIShell)parent).getTeamGenerator().generateTeam(team1.getLeagueID(), team1.getTeamID()), ((GUIShell)parent).getTeamGenerator().generateTeam(team2.getLeagueID(), team2.getTeamID())*/);
				((GUIShell)parent).setDisplayedComposite(spectatorGenerator.getSpectatorComposite());
			}
		});
		spectateButton.setBounds(1131, 10, 119, 30);
		spectateButton.setText("Spectator View");
		
		Button team1ScoreIncrementButton = new Button(scoreGroup, SWT.NONE);
		team1ScoreIncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				match.setHomeScore( match.getHomeScore() + 1 );
				matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				System.out.println("Team1 score incremented.");
			}
		});
		team1ScoreIncrementButton.setBounds(175, 46, 40, 30);
		team1ScoreIncrementButton.setText("+");
		
		Button team1ScoreDecrementButton = new Button(scoreGroup, SWT.NONE);
		team1ScoreDecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ( match.getHomeScore() > 0 ) {
					match.setHomeScore( match.getHomeScore() - 1 );
					matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				}
				System.out.println("Team1 score decremented.");
			}
		});
		team1ScoreDecrementButton.setText("-");
		team1ScoreDecrementButton.setBounds(221, 46, 40, 30);
		
		Button team2ScoreIncrementButton = new Button(scoreGroup, SWT.NONE);
		team2ScoreIncrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				match.setAwayScore( match.getAwayScore() + 1 );
				matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				System.out.println("Team2 score incremented.");
			}
		});
		team2ScoreIncrementButton.setText("+");
		team2ScoreIncrementButton.setBounds(885, 46, 40, 30);
		
		Button team2ScoreDecrementButton = new Button(scoreGroup, SWT.NONE);
		team2ScoreDecrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ( match.getAwayScore() > 0 ) {
					match.setAwayScore( match.getAwayScore() - 1 );
					matchDBInterator.updateMatchData( match.getLeagueID(), match.getMatchID(), match.getHomeScore(), match.getAwayScore(), match.getDate() );
				}
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
		fd_btnSubmit.bottom = new FormAttachment(100, -65);
		fd_btnSubmit.right = new FormAttachment(100, -55);
		btnSubmit.setLayoutData(fd_btnSubmit);
		btnSubmit.setText("Submit");
		
		Text text = new Text(this, SWT.BORDER);
		fd_btnSubmit.top = new FormAttachment(text, -2, SWT.TOP);
		fd_btnSubmit.left = new FormAttachment(text, 6);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -136);
		text.setLayoutData(fd_text);
		
		Label lblPostAnnouncement = new Label(this, SWT.NONE);
		fd_text.top = new FormAttachment(lblPostAnnouncement, -3, SWT.TOP);
		fd_text.left = new FormAttachment(lblPostAnnouncement, 6);
		lblPostAnnouncement.setAlignment(SWT.CENTER);
		FormData fd_lblPostAnnouncement = new FormData();
		fd_lblPostAnnouncement.right = new FormAttachment(100, -1095);
		fd_lblPostAnnouncement.left = new FormAttachment(0, 55);
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
		fd_statsTeam1Group.left = new FormAttachment(scoreGroup, 0, SWT.LEFT);
		fd_statsTeam1Group.right = new FormAttachment(0, 630);
		statsTeam1Group.setLayoutData(fd_statsTeam1Group);
		
		// ScrolledComposite to scroll through all players
		ScrolledComposite scrolledComposite_team1 = new ScrolledComposite(statsTeam1Group, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_scrolledComposite.heightHint = 480;
		gd_scrolledComposite.widthHint = 580;
		scrolledComposite_team1.setLayoutData(gd_scrolledComposite);
		scrolledComposite_team1.setExpandHorizontal(true);
		scrolledComposite_team1.setExpandVertical(true);
		
		// Put playerBtnList onto Scrolled Composite
		Composite playerBtnList = new Composite(scrolledComposite_team1, SWT.NONE);

		// Add action listeners to player buttons for expanding
		// Create components for each player on the team.
		for(int i = 0; i < team1Players.size(); i++)
		{
			Button btnPlayername = new Button(playerBtnList, SWT.NONE);
			btnPlayername.setText( team1Players.get(i).getFirstName() + " " + team1Players.get(i).getLastName() );
			btnPlayername.setBounds(0, i * 36, 560, 30);
			btnPlayername.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("Player button clicked.");
				}
			});

		}
		
		scrolledComposite_team1.setContent(playerBtnList);
		scrolledComposite_team1.setMinSize(playerBtnList.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		// START EXPANDED COMPOSITE EXAMPLE----------------------------------------------------->
//		Composite statComposite_team1_expanded = new Composite(statsTeam1Group, SWT.NONE);
//		statComposite_team1_expanded.setBounds(10, 46, 565, 250);
//		
//		Label lblPlayername_1 = new Label(statComposite_team1_expanded, SWT.NONE);
//		lblPlayername_1.setText("player.name");
//		lblPlayername_1.setAlignment(SWT.CENTER);
//		lblPlayername_1.setBounds(10, 10, 75, 15);
//		
//		Label lblStat1 = new Label(statComposite_team1_expanded, SWT.NONE);
//		lblStat1.setAlignment(SWT.CENTER);
//		lblStat1.setBounds(100, 50, 275, 30);
//		lblStat1.setText("stat1");
//		
//		Button stat1DecrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
//		stat1DecrementButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("Stat1 decremented.");
//			}
//		});
//		stat1DecrementButton.setText("-");
//		stat1DecrementButton.setBounds(427, 50, 40, 30);
//		
//		Button stat1IncrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
//		stat1IncrementButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				System.out.println("Stat1 incremented.");
//			}
//		});
//		stat1IncrementButton.setBounds(381, 50, 40, 30);
//		stat1IncrementButton.setText("+");
//		
//		Label lblStat2 = new Label(statComposite_team1_expanded, SWT.NONE);
//		lblStat2.setText("stat2");
//		lblStat2.setAlignment(SWT.CENTER);
//		lblStat2.setBounds(100, 86, 275, 30);
//		
//		Button stat2IncrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
//		stat2IncrementButton.setText("+");
//		stat2IncrementButton.setBounds(381, 86, 40, 30);
//		
//		Button stat2DecrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
//		stat2DecrementButton.setText("-");
//		stat2DecrementButton.setBounds(427, 86, 40, 30);
		// END EXPANDED COMPOSITE EXAMPLE <-------------------------------------------------------
		
		// Team 2 player list and expandable stat menu group
		Group statsTeam2Group = new Group(this, SWT.NONE);
		statsTeam2Group.setLayout(new GridLayout(1, true));
		statsTeam2Group.setBackground(dark_gray);
		FormData fd_statsTeam2Group = new FormData();
		fd_statsTeam2Group.bottom = new FormAttachment(btnSubmit, -6);
		fd_statsTeam2Group.top = new FormAttachment(scoreGroup, 6);
		fd_statsTeam2Group.right = new FormAttachment(scoreGroup, 0, SWT.RIGHT);
		fd_statsTeam2Group.left = new FormAttachment(100, -630);
		statsTeam2Group.setLayoutData(fd_statsTeam2Group);
		
		// ScrolledComposite to scroll through all players
		ScrolledComposite scrolledComposite_team2 = new ScrolledComposite(statsTeam2Group, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite2 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_scrolledComposite2.heightHint = 480;
		gd_scrolledComposite2.widthHint = 580;
		scrolledComposite_team2.setLayoutData(gd_scrolledComposite2);
		scrolledComposite_team2.setExpandHorizontal(true);
		scrolledComposite_team2.setExpandVertical(true);
		
		// Put playerBtnList2 onto Scrolled Composite for team 2
		Composite playerBtnList2 = new Composite(scrolledComposite_team2, SWT.NONE);

		// Add action listeners to player buttons for expanding
		// Create components for each player on the team.
		for(int i = 0; i < team2Players.size(); i++)
		{
			Button btnPlayername2 = new Button(playerBtnList2, SWT.NONE);
			btnPlayername2.setText( team2Players.get(i).getFirstName() + " " + team2Players.get(i).getLastName() );
			btnPlayername2.setBounds(0, i * 36, 560, 30);
			btnPlayername2.addSelectionListener( new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					System.out.println("Player button clicked.");
				}
			});

		}
		
		scrolledComposite_team2.setContent(playerBtnList2);
		scrolledComposite_team2.setMinSize(playerBtnList2.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//		scrolledComposite_team2.setMinSize(new Point(560, 714));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
