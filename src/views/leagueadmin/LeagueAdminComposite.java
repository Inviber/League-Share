package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;

import team.Team;
import team.TeamGenerator;
import user.AccountGenerator;
import views.GUIShell;
import views.landing.LandingComposite;
import views.login.LoginComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import league.League;
import league.LeagueGenerator;
import match.MatchGenerator;
import player.PlayerGenerator;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LeagueAdminComposite extends Composite {

	private Composite displayedComposite = null;
	private LeagueAdminComposite self = this;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LeagueAdminComposite(Composite parent, int style, GUIShell shell, String leagueID) {
		super(parent, style);
		
		AccountGenerator accountGenerator = shell.getAccountGenerator();
		LeagueGenerator leagueGenerator = shell.getLeagueGenerator();
		MatchGenerator matchGenerator = shell.getMatchGenerator();
		TeamGenerator teamGenerator = shell.getTeamGenerator();
		PlayerGenerator playerGenerator = shell.getPlayerGenerator();
		
		
		League league = leagueGenerator.generateLeague(leagueID);
		
		
		
		EditTeamsComposite editTeams = new EditTeamsComposite(self, SWT.NONE);
		FormData fd_editTeams = new FormData();
		fd_editTeams.right = new FormAttachment(100, -23);
		editTeams.setLayoutData(fd_editTeams);
		//editTeams.setLocation(0, 0);
		displayedComposite = editTeams;
		//displayedComposite.update();
		displayedComposite.setSize(863, 521);
		
		
		
		
		
		
		
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setText(league.getLeagueName());
		
		lblNewLabel.setAlignment(SWT.CENTER);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 23);
		fd_lblNewLabel.right = new FormAttachment(100, -491);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		// lblNewLabel.setText(team.getTeamName());
		
		Group grpLeagueOptions = new Group(this, SWT.NONE);
		fd_editTeams.left = new FormAttachment(grpLeagueOptions, 24);
		fd_editTeams.bottom = new FormAttachment(grpLeagueOptions, 0, SWT.BOTTOM);
		fd_editTeams.top = new FormAttachment(grpLeagueOptions, 0, SWT.TOP);
		grpLeagueOptions.setText("League Options");
		FormData fd_grpLeagueOptions = new FormData();
		fd_grpLeagueOptions.bottom = new FormAttachment(100, -181);
		fd_grpLeagueOptions.left = new FormAttachment(0, 73);
		grpLeagueOptions.setLayoutData(fd_grpLeagueOptions);
		
		Button btnUpdateLeagueInfo = new Button(grpLeagueOptions, SWT.NONE);
		btnUpdateLeagueInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Update League Information");
				displayedComposite.dispose();
				UpdateInfoComposite updateInfo = new UpdateInfoComposite(self, SWT.NONE);
				updateInfo.setLocation(350, 150);
				displayedComposite = updateInfo;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnUpdateLeagueInfo.setBounds(25, 35, 192, 48);
		btnUpdateLeagueInfo.setText("Update League Information");
		
		Button btnEditTeams = new Button(grpLeagueOptions, SWT.NONE);
		btnEditTeams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Edit Teams");
//				if(displayedComposite == null)
//				{
//					displayedComposite.dispose();
//				}
				displayedComposite.dispose();
				EditTeamsComposite editTeams = new EditTeamsComposite(self, SWT.NONE);
				editTeams.setLocation(350, 100);
				displayedComposite = editTeams;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
				//displayedComposite.setParent(displayedComposite);
			}
		});
		btnEditTeams.setText("Edit Teams");
		btnEditTeams.setBounds(25, 100, 192, 48);
		
		Button btnCreateNewTeam = new Button(grpLeagueOptions, SWT.NONE);
		btnCreateNewTeam.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCreateNewTeam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Create New Team");
				
				displayedComposite.dispose();
				CreateNewTeamComposite createTeam = new CreateNewTeamComposite(self, SWT.NONE, leagueID, teamGenerator);
				createTeam.setLocation(350, 125);
				displayedComposite = createTeam;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnCreateNewTeam.setText("Create New Team");
		btnCreateNewTeam.setBounds(25, 165, 192, 48);
		
		Button btnNewPlayerStatistic = new Button(grpLeagueOptions, SWT.NONE);
		btnNewPlayerStatistic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Track New Player Statistic");
				
				displayedComposite.dispose();
				TrackNewPlayerStatsComposite newStat = new TrackNewPlayerStatsComposite(self, SWT.NONE, leagueID, leagueGenerator);
				newStat.setLocation(350, 150);
				displayedComposite = newStat;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnNewPlayerStatistic.setText("Track New Player Statistic");
		btnNewPlayerStatistic.setBounds(25, 230, 192, 48);
		
		Button btnCreateMatch = new Button(grpLeagueOptions, SWT.NONE);
		btnCreateMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Create Match");
				
				displayedComposite.dispose();
				CreateMatchComposite createMatch = new CreateMatchComposite(self, SWT.NONE);
				createMatch.setLocation(350, 100);
				displayedComposite = createMatch;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnCreateMatch.setText("Create Match");
		btnCreateMatch.setBounds(25, 295, 192, 48);
		
		Button btnAppointCastor = new Button(grpLeagueOptions, SWT.NONE);
		btnAppointCastor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Appoint Caster");
				
				displayedComposite.dispose();
				AppointCasterComposite appointCastor = new AppointCasterComposite(self, SWT.NONE, leagueID, accountGenerator, leagueGenerator);
				appointCastor.setLocation(375, 100);
				displayedComposite = appointCastor;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnAppointCastor.setText("Appoint Caster");
		btnAppointCastor.setBounds(25, 360, 192, 48);
		
		Button btnBack = new Button(this, SWT.NONE);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Back button pressed");
				shell.disposeDisplayedComposite();
				LandingComposite landingComposite = new LandingComposite(shell, SWT.NONE, shell);
				shell.setDisplayedComposite(landingComposite);
			}
		});
		
		
		fd_lblNewLabel.bottom = new FormAttachment(btnBack, 0, SWT.BOTTOM);
		fd_lblNewLabel.left = new FormAttachment(btnBack, 308);
		fd_grpLeagueOptions.top = new FormAttachment(btnBack, 24);
		FormData fd_btnBack = new FormData();
		fd_btnBack.top = new FormAttachment(0, 34);
		fd_btnBack.bottom = new FormAttachment(100, -645);
		fd_btnBack.right = new FormAttachment(0, 138);
		fd_btnBack.left = new FormAttachment(0, 50);
		btnBack.setLayoutData(fd_btnBack);
		btnBack.setText("Back");
		
		
		
		
		fd_grpLeagueOptions.right = new FormAttachment(100, -962);
		
		/*
		EditTeamsComposite editTeams = new EditTeamsComposite(displayedComposite, SWT.NONE);
		editTeams.setLocation(0, 0);
		displayedComposite = editTeams;
		displayedComposite.setSize(863, 521);
		*/
		
		
		
		
		
	}
	
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
