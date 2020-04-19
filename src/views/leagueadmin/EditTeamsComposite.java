package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import league.League;
import league.LeagueGenerator;
import team.Team;
import team.TeamGenerator;
import views.GUIShell;
import views.teamadmin.TeamAdminComposite;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;


public class EditTeamsComposite extends Composite {

	private Composite teamComposite;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EditTeamsComposite(Composite parent, int style, GUIShell shell, String leagueID, LeagueGenerator leagueGenerator, TeamGenerator teamGenerator, Composite leagueAdminComposite) {
		super(parent, style);
		
		setSize(863, 521);
		
		Label lblTeams = new Label(this, SWT.NONE);
		lblTeams.setAlignment(SWT.CENTER);
		lblTeams.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTeams.setBounds(371, 107, 73, 25);
		lblTeams.setText("Teams");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(171, 139, 493, 231);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		League league = leagueGenerator.generateLeague(leagueID);
		ArrayList<String> teamIDs = league.getTeamIDs();
		//ArrayList<Team> teams = new ArrayList<Team>();
		
		teamComposite = new Composite(scrolledComposite, SWT.NONE);
		teamComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		for(int i = 0; i < teamIDs.size(); i++)
		{
			Team newTeam = teamGenerator.generateTeam(leagueID, teamIDs.get(i));
			String teamName = newTeam.getTeamName();
			
			//Composite singleTeamComposite = new Composite(teamComposite, SWT.NONE);
			//Label teamLabel = new Label(singleTeamComposite, SWT.NONE);
			Label teamLabel = new Label(teamComposite, SWT.NONE);
			teamLabel.setText(teamName);
			teamLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					System.out.println(teamName);
					
					TeamAdminComposite teamAdminComposite = new TeamAdminComposite(shell, SWT.NONE, shell, leagueID, newTeam.getTeamID(), leagueAdminComposite);
					shell.setDisplayedComposite(teamAdminComposite);
				}
			});
			
			//teams.add(newTeam);
		}
		
		scrolledComposite.setContent(teamComposite);
		scrolledComposite.setMinSize(teamComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		

		
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
