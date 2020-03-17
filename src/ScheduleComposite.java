import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class ScheduleComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ScheduleComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, LeagueParser leagueParser) {
		super(parent, style);
		
		Color dark_gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
//		Date today = new Date();
		
		System.out.println("schedule made");
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 77);
		fd_lblNewLabel.right = new FormAttachment(0, 1270);
		fd_lblNewLabel.top = new FormAttachment(0, 60);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText(leagueParser.getLeagueName());
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(0, 50);
		fd_btnNewButton.right = new FormAttachment(0, 78);
		fd_btnNewButton.top = new FormAttachment(0, 10);
		fd_btnNewButton.left = new FormAttachment(0, 10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				shell.disposeDisplayedComposite();
				LandingComposite landingComposite = new LandingComposite(shell, SWT.NONE, shell, dbHelper);
				shell.setDisplayedComposite(landingComposite);
			}
		});
		btnNewButton.setText("Back");
		
		Label lblSchedule = new Label(this, SWT.NONE);
		FormData fd_lblSchedule = new FormData();
		fd_lblSchedule.bottom = new FormAttachment(0, 100);
		fd_lblSchedule.right = new FormAttachment(0, 1270);
		fd_lblSchedule.top = new FormAttachment(0, 83);
		fd_lblSchedule.left = new FormAttachment(0, 10);
		lblSchedule.setLayoutData(fd_lblSchedule);
		lblSchedule.setText("Schedule");
		lblSchedule.setAlignment(SWT.CENTER);
		
		Group grpMatches = new Group(this, SWT.NONE);
		grpMatches.setLayout(new GridLayout(1, false));
		grpMatches.setLayout(new GridLayout(3, false));
		grpMatches.setBackground(dark_gray);
		FormData fd_grpMatches = new FormData();
		fd_grpMatches.bottom = new FormAttachment(0, 710);
		fd_grpMatches.right = new FormAttachment(0, 1270);
		fd_grpMatches.top = new FormAttachment(0, 106);
		fd_grpMatches.left = new FormAttachment(0, 10);
		grpMatches.setLayoutData(fd_grpMatches);
		grpMatches.setText("MATCHES");
		new Label(grpMatches, SWT.NONE);
		
		Composite composite = new Composite(grpMatches, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 305;
		gd_composite.heightHint = 201;
		composite.setLayoutData(gd_composite);
		
		Label lblMatchDate = new Label(composite, SWT.NONE);
		lblMatchDate.setBounds(10, 23, 248, 15);
		lblMatchDate.setText("Match Date");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("<Home Team>");
		label_1.setBounds(10, 60, 248, 15);
		
		Label label_1_1 = new Label(composite, SWT.NONE);
		label_1_1.setText("<Away Team>");
		label_1_1.setBounds(10, 153, 248, 15);
		
		Label lblVs = new Label(composite, SWT.NONE);
		lblVs.setBounds(102, 104, 55, 15);
		lblVs.setText("V.S.");
		
		ArrayList<String> matchIDs = leagueParser.getMatchIDs();
		
		for(int i = 0; i < matchIDs.size(); i++)
		{
			MatchParser match1 = new MatchParser(leagueParser.getLeagueID(), matchIDs.get(i), dbHelper);
			TeamParser team1 = new TeamParser(leagueParser.getLeagueID(), match1.getHomeTeamID(), dbHelper);
			TeamParser team2 = new TeamParser(leagueParser.getLeagueID(), match1.getAwayTeamID(), dbHelper);
			
			Composite composite = new Composite(grpMatches, SWT.NONE);
			GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_composite.widthHint = 305;
			gd_composite.heightHint = 201;
			composite.setLayoutData(gd_composite);

			Label lblMatchDate = new Label(composite, SWT.NONE);
			lblMatchDate.setBounds(10, 23, 248, 15);
			lblMatchDate.setText(match1.getDate());
			lblMatchDate.setAlignment(SWT.CENTER);

			Label lblTeam1 = new Label(composite, SWT.NONE);
			lblTeam1.setText(team1.getTeamName());
			lblTeam1.setAlignment(SWT.CENTER);
			lblTeam1.setBounds(10, 60, 248, 15);

			Label lblTeam2 = new Label(composite, SWT.NONE);
			lblTeam2.setText(team2.getTeamName());
			lblTeam2.setAlignment(SWT.CENTER);
			lblTeam2.setBounds(10, 153, 248, 15);

			Label lblVs = new Label(composite, SWT.NONE);
			lblVs.setText("V.S.");
			lblVs.setAlignment(SWT.CENTER);
			lblVs.setBounds(102, 104, 55, 15);
			
			

		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
