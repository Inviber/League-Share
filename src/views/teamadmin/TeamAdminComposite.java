package views.teamadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import team.Team;
import team.TeamGenerator;
import user.AccountGenerator;
import views.GUIShell;
import views.landing.LandingComposite;
import views.leagueadmin.EditTeamsComposite;
import views.leagueadmin.UpdateInfoComposite;

import org.eclipse.wb.swt.SWTResourceManager;

import league.League;
import league.LeagueGenerator;
import match.MatchGenerator;
import player.PlayerGenerator;

public class TeamAdminComposite extends Composite {

	private Composite displayedComposite = null;
	private TeamAdminComposite self = this;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TeamAdminComposite(Composite parent, int style, GUIShell shell, String leagueId, String teamId) {
		super(parent, style);
		
		AccountGenerator accountGenerator = shell.getAccountGenerator();
		LeagueGenerator leagueGenerator = shell.getLeagueGenerator();
		MatchGenerator matchGenerator = shell.getMatchGenerator();
		TeamGenerator teamGenerator = shell.getTeamGenerator();
		PlayerGenerator playerGenerator = shell.getPlayerGenerator();
		
		
		
		Team team = teamGenerator.generateTeam(leagueId, teamId);
		
		
		
		ModifyRosterComposite modifyRoster = new ModifyRosterComposite(self, SWT.NONE);
		FormData fd_modifyRoster = new FormData();
		fd_modifyRoster.right = new FormAttachment(100, -23);
		modifyRoster.setLayoutData(fd_modifyRoster);
		//editTeams.setLocation(0, 0);
		displayedComposite = modifyRoster;
		//displayedComposite.update();
		displayedComposite.setSize(863, 521);
		
		
		
		
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setText(team.getTeamName());
		
		lblNewLabel.setAlignment(SWT.CENTER);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 23);
		fd_lblNewLabel.right = new FormAttachment(100, -491);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		// lblNewLabel.setText(team.getTeamName());
		
		Group grpTeamOptions = new Group(this, SWT.NONE);
		fd_modifyRoster.left = new FormAttachment(grpTeamOptions, 24);
		fd_modifyRoster.bottom = new FormAttachment(grpTeamOptions, 0, SWT.BOTTOM);
		fd_modifyRoster.top = new FormAttachment(grpTeamOptions, 0, SWT.TOP);
		grpTeamOptions.setText("Team Options");
		FormData fd_grpTeamOptions = new FormData();
		fd_grpTeamOptions.bottom = new FormAttachment(100, -181);
		fd_grpTeamOptions.left = new FormAttachment(0, 73);
		grpTeamOptions.setLayoutData(fd_grpTeamOptions);
		
		Button btnModifyRoster = new Button(grpTeamOptions, SWT.NONE);
		btnModifyRoster.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Modify Roster");
				displayedComposite.dispose();
				ModifyRosterComposite modifyRoster = new ModifyRosterComposite(self, SWT.NONE);
				modifyRoster.setLocation(350, 150);
				displayedComposite = modifyRoster;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnModifyRoster.setBounds(25, 35, 192, 48);
		btnModifyRoster.setText("Modify Roster");
		
		Button btnAddPlayer = new Button(grpTeamOptions, SWT.NONE);
		btnAddPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Add Player");
				displayedComposite.dispose();
				AddPlayerComposite addPlayer = new AddPlayerComposite(self, SWT.NONE);
				addPlayer.setLocation(350, 150);
				displayedComposite = addPlayer;
				displayedComposite.update();
				displayedComposite.setSize(863, 521);
			}
		});
		btnModifyRoster.setBounds(25, 35, 192, 48);
		btnModifyRoster.setText("Add Player");
		
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
		fd_grpTeamOptions.top = new FormAttachment(btnBack, 24);
		FormData fd_btnBack = new FormData();
		fd_btnBack.top = new FormAttachment(0, 34);
		fd_btnBack.bottom = new FormAttachment(100, -645);
		fd_btnBack.right = new FormAttachment(0, 138);
		fd_btnBack.left = new FormAttachment(0, 50);
		btnBack.setLayoutData(fd_btnBack);
		btnBack.setText("Back");
		
		
		fd_grpTeamOptions.right = new FormAttachment(100, -962);
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
