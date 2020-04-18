package views.LeagueAdmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;

import team.Team;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.ScrolledComposite;

public class LeagueAdminComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LeagueAdminComposite(Composite parent, int style, Team team) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 31);
		fd_lblNewLabel.right = new FormAttachment(100, -454);
		fd_lblNewLabel.bottom = new FormAttachment(0, 83);
		fd_lblNewLabel.left = new FormAttachment(0, 483);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText(team.getTeamName());
		
		Group grpLeagueOptions = new Group(this, SWT.NONE);
		grpLeagueOptions.setText("League Options");
		FormData fd_grpLeagueOptions = new FormData();
		fd_grpLeagueOptions.left = new FormAttachment(0, 124);
		fd_grpLeagueOptions.top = new FormAttachment(0, 104);
		fd_grpLeagueOptions.bottom = new FormAttachment(100, -39);
		grpLeagueOptions.setLayoutData(fd_grpLeagueOptions);
		
		Button btnUpdateLeagueInfo = new Button(grpLeagueOptions, SWT.NONE);
		btnUpdateLeagueInfo.setBounds(25, 36, 192, 48);
		btnUpdateLeagueInfo.setText("Update League Information");
		
		Button btnEditTeams = new Button(grpLeagueOptions, SWT.NONE);
		btnEditTeams.setText("Edit Teams");
		btnEditTeams.setBounds(25, 107, 192, 48);
		
		Button btnCreateNewTeam = new Button(grpLeagueOptions, SWT.NONE);
		btnCreateNewTeam.setText("Create New Team");
		btnCreateNewTeam.setBounds(25, 180, 192, 48);
		
		Button btnNewPlayerStatistic = new Button(grpLeagueOptions, SWT.NONE);
		btnNewPlayerStatistic.setText("Track New Player Statistic");
		btnNewPlayerStatistic.setBounds(25, 257, 192, 48);
		
		Button btnCreateMatch = new Button(grpLeagueOptions, SWT.NONE);
		btnCreateMatch.setText("Create Match");
		btnCreateMatch.setBounds(25, 333, 192, 48);
		
		Button btnAppointCastor = new Button(grpLeagueOptions, SWT.NONE);
		btnAppointCastor.setText("Appoint Caster");
		btnAppointCastor.setBounds(25, 416, 192, 48);
		
		Composite composite = new Composite(this, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(lblNewLabel, 110);
		fd_composite.right = new FormAttachment(100, -78);
		fd_composite.left = new FormAttachment(100, -62);
		composite.setLayoutData(fd_composite);
		
		Button btnBack = new Button(this, SWT.NONE);
		FormData fd_btnBack = new FormData();
		fd_btnBack.bottom = new FormAttachment(lblNewLabel, -8, SWT.BOTTOM);
		fd_btnBack.right = new FormAttachment(0, 138);
		fd_btnBack.left = new FormAttachment(0, 50);
		fd_btnBack.top = new FormAttachment(lblNewLabel, 3, SWT.TOP);
		btnBack.setLayoutData(fd_btnBack);
		btnBack.setText("Back");
		
		Composite EditTeamComposite = new Composite(this, SWT.NONE);
		fd_grpLeagueOptions.right = new FormAttachment(EditTeamComposite, -62);
		FormData fd_EditTeamComposite = new FormData();
		fd_EditTeamComposite.bottom = new FormAttachment(100, -39);
		fd_EditTeamComposite.top = new FormAttachment(lblNewLabel, 21);
		fd_EditTeamComposite.right = new FormAttachment(composite, -6);
		fd_EditTeamComposite.left = new FormAttachment(composite, -785, SWT.LEFT);
		EditTeamComposite.setLayoutData(fd_EditTeamComposite);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(EditTeamComposite, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(171, 149, 443, 208);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Label lblTeams = new Label(EditTeamComposite, SWT.CENTER);
		lblTeams.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTeams.setBounds(302, 107, 155, 36);
		lblTeams.setText("Teams");
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
