import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Group;

public class ScheduleComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ScheduleComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, LeagueParser leagueParser) {
		super(parent, style);
		
		System.out.println("schedule made");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 60, 1260, 17);
		lblNewLabel.setText(leagueParser.getLeagueName());
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				shell.disposeDisplayedComposite();
				LandingComposite landingComposite = new LandingComposite(shell, SWT.NONE, shell, dbHelper);
				shell.setDisplayedComposite(landingComposite);
			}
		});
		btnNewButton.setBounds(10, 10, 68, 40);
		btnNewButton.setText("Back");
		
		Label lblSchedule = new Label(this, SWT.NONE);
		lblSchedule.setText("Schedule");
		lblSchedule.setAlignment(SWT.CENTER);
		lblSchedule.setBounds(10, 83, 1260, 17);
		
		Group grpMatches = new Group(this, SWT.NONE);
		grpMatches.setText("MATCHES");
		grpMatches.setBounds(10, 106, 1260, 604);
		
		Button btnNewButton_1 = new Button(grpMatches, SWT.NONE);
		btnNewButton_1.setBounds(966, 416, 93, 29);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				System.out.println("called spectate");
				
//				shell.disposeDisplayedComposite();
				SpectatorComposite spectatorComposite = new SpectatorComposite(shell, SWT.NONE, shell, dbHelper);
				shell.setDisplayedComposite(spectatorComposite);
				
				
				
				
				
			}
		});
		btnNewButton_1.setText("Spectate");
		
		Button btnNewButton_2 = new Button(grpMatches, SWT.NONE);
		btnNewButton_2.setBounds(916, 463, 252, 72);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
					
				System.out.println(leagueParser.getMatchIDs().toString());
				
				ArrayList<String> matchIDs = leagueParser.getMatchIDs();
				
				for(int i = 0; i < matchIDs.size(); i++)
				{
					MatchParser match1 = new MatchParser(leagueParser.getLeagueID(), matchIDs.get(i), dbHelper);
					TeamParser team1 = new TeamParser(leagueParser.getLeagueID(), match1.getHomeTeamID(), dbHelper);
					TeamParser team2 = new TeamParser(leagueParser.getLeagueID(), match1.getAwayTeamID(), dbHelper);
					
					System.out.println(match1.getDate());
					System.out.println(team1.getTeamName());
					System.out.println(team2.getTeamName() + "\n");
				}
				
				
			}
		});
		btnNewButton_2.setText("Print Match Data");
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
