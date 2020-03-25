package views.schedule;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import database.DatabaseHelper;
import league.LeagueParser;
import match.MatchParser;
import team.TeamParser;
import views.GUIShell;
import views.landing.LandingComposite;
import views.spectator.SpectatorComposite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class ScheduleComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	@SuppressWarnings("deprecation")
	public ScheduleComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, LeagueParser leagueParser) {
		super(parent, style);
		
		Color dark_gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
		Calendar today = Calendar.getInstance();
		int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
		int month = today.get(Calendar.MONTH);
		Calendar matchCal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date matchDate = new Date();
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 77);
		fd_lblNewLabel.right = new FormAttachment(0, 1260);
		fd_lblNewLabel.top = new FormAttachment(0, 60);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText(leagueParser.getLeagueName());
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(0, 50);
		fd_btnNewButton.right = new FormAttachment(0, 100);
		fd_btnNewButton.top = new FormAttachment(0, 10);
		fd_btnNewButton.left = new FormAttachment(0, 10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
		grpMatches.setLayout(new GridLayout(3, true));
		grpMatches.setBackground(dark_gray);
		FormData fd_grpMatches = new FormData();
		fd_grpMatches.bottom = new FormAttachment(0, 680);
		fd_grpMatches.right = new FormAttachment(0, 1260);
		fd_grpMatches.top = new FormAttachment(0, 106);
		fd_grpMatches.left = new FormAttachment(0, 10);
		grpMatches.setLayoutData(fd_grpMatches);
		grpMatches.setText("MATCHES");
		
		ArrayList<String> matchIDs = leagueParser.getMatchIDs();
		
		for(int i = 0; i < matchIDs.size(); i++)
		{
			MatchParser match1 = new MatchParser(leagueParser.getLeagueID(), matchIDs.get(i), dbHelper);
			TeamParser team1 = new TeamParser(leagueParser.getLeagueID(), match1.getHomeTeamID(), dbHelper);
			TeamParser team2 = new TeamParser(leagueParser.getLeagueID(), match1.getAwayTeamID(), dbHelper);
			
			Composite matchComp = new Composite(grpMatches, SWT.NONE);
			GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_composite.widthHint = 405;
			gd_composite.heightHint = 270;
			matchComp.setLayoutData(gd_composite);

			Label lblMatchDate = new Label(matchComp, SWT.NONE);
			lblMatchDate.setBounds(10, 10, 390, 30);
//			lblMatchDate.setText(match1.getDate());
			lblMatchDate.setAlignment(SWT.CENTER);

			Label lblTeam1 = new Label(matchComp, SWT.NONE);
			lblTeam1.setText(team1.getTeamName());
			lblTeam1.setAlignment(SWT.CENTER);
			lblTeam1.setBounds(10, 60, 390, 30);
			
			Label lblVs = new Label(matchComp, SWT.NONE);
			lblVs.setText("V.S.");
			lblVs.setAlignment(SWT.CENTER);
			lblVs.setBounds(10, 96, 390, 30);

			Label lblTeam2 = new Label(matchComp, SWT.NONE);
			lblTeam2.setText(team2.getTeamName());
			lblTeam2.setAlignment(SWT.CENTER);
			lblTeam2.setBounds(10, 132, 390, 30);
			
			
			
			int matchDayofMonth;
			int monthofMatch;
			
//			try {
//				matchDate = sdf.parse( match1.getDate() );
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
//			System.out.println(matchDate);
			matchCal.setTime(matchDate);
			matchDayofMonth = matchCal.get(Calendar.DAY_OF_MONTH);
			monthofMatch = matchCal.get(Calendar.MONTH);
	        
			
			
	        if( dayOfMonth > matchDayofMonth && month >= monthofMatch ) {
	        	// Print final match score to matchComp
				lblTeam1.setText( team1.getTeamName() + " | " + match1.getHomeScore() );
				lblTeam2.setText( team2.getTeamName() + " | " + match1.getAwayScore() );
				
				int homeScore, awayScore;
				Color green = getDisplay().getSystemColor(SWT.COLOR_GREEN);
				Color red = getDisplay().getSystemColor(SWT.COLOR_RED);
				
				try {
//					homeScore = Integer.parseInt( match1.getHomeScore() );
//					awayScore = Integer.parseInt( match1.getAwayScore() );
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					homeScore = 0;
					awayScore = 0;
				}
				
//				if ( homeScore > awayScore ) {
//					lblTeam1.setBackground(green);
//					lblTeam2.setBackground(red);
//				} else if ( awayScore > homeScore ) {
//					lblTeam2.setBackground(green);
//					lblTeam1.setBackground(red);
//				}
	        	
	        } 
	        else if( dayOfMonth == matchDayofMonth && month == monthofMatch) {
	        	// Present 'Spectate' button on matchComp
				Button spectateMatch = new Button(matchComp, SWT.NONE);
				spectateMatch.setBounds(150, 200, 100, 50);
				spectateMatch.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						System.out.println("SPECTATING");
						shell.disposeDisplayedComposite();
						
						MatchParser match1 = new MatchParser(leagueParser.getLeagueID(), matchIDs.get(0), dbHelper);
						TeamParser team1 = new TeamParser(leagueParser.getLeagueID(), match1.getHomeTeamID(), dbHelper);
						TeamParser team2 = new TeamParser(leagueParser.getLeagueID(), match1.getAwayTeamID(), dbHelper);
						
						SpectatorComposite spectatorComposite = new SpectatorComposite(shell, SWT.NONE, shell, dbHelper, match1, team1, team2);
						shell.setDisplayedComposite(spectatorComposite);
					}
				});
				spectateMatch.setText("SPECTATE");
	        	
	        }

		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
