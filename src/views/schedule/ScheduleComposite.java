package views.schedule;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import league.League;
import match.Match;
import team.Team;
import views.GUIShell;
import views.landing.LandingComposite;
import views.spectator.SpectatorGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
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
	private League league;
	private ArrayList<Match> matches;
	private ArrayList<Team> team1List;
	private ArrayList<Team> team2List;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ScheduleComposite(Composite parent, int style) {
		super(parent, style);
	}
	
	public void fillComposite(Composite parent)
	{			
		Color dark_gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
		Calendar today = Calendar.getInstance();
		int dayOfMonth = today.get(Calendar.DAY_OF_MONTH);
		int month = today.get(Calendar.MONTH);
		int year = today.get(Calendar.YEAR);
		today.clear();
		today.set(year, month, dayOfMonth);
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 77);
		fd_lblNewLabel.right = new FormAttachment(0, 1260);
		fd_lblNewLabel.top = new FormAttachment(0, 60);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText(league.getLeagueName());
		
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
				LandingComposite landingComposite = new LandingComposite(parent, SWT.NONE, ((GUIShell) parent));
				((GUIShell) parent).setDisplayedComposite(landingComposite);
				System.out.println("Back button pressed.");
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
		
		Button btnFollow = new Button(this, SWT.NONE);
		GUIShell shell = (GUIShell) parent;
		boolean following = shell.getAccountGenerator().getLoggedInAccount().getFollowedLeagueIDs().contains(matches.get(0).getLeagueID());
		if (following == false) // if user doesn't follow league.
		{
			btnFollow.setText("Follow League");
			
		}
		else
		{
			btnFollow.setText("Unfollow League");
		}
		btnFollow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (following == false) // if user doesn't follow league.
				{
					shell.getAccountGenerator().getLoggedInAccount().addLeague(matches.get(0).getLeagueID(), false, false);
				}
				else
				{
					shell.getAccountGenerator().getLoggedInAccount().unfollowLeague(matches.get(0).getLeagueID());
				}
				btnFollow.setVisible(false);

			}
		});
		
		
		
		FormData fd_btnFollow = new FormData();
		fd_btnFollow.left = new FormAttachment(lblSchedule, -107);
		fd_btnFollow.bottom = new FormAttachment(btnNewButton, 0, SWT.BOTTOM);
		fd_btnFollow.top = new FormAttachment(btnNewButton, 0, SWT.TOP);
		fd_btnFollow.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		btnFollow.setLayoutData(fd_btnFollow);
		
		for(int i = 0; i < matches.size(); i++)
		{			
			Composite matchComp = new Composite(grpMatches, SWT.NONE);
			GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_composite.widthHint = 405;
			gd_composite.heightHint = 270;
			matchComp.setLayoutData(gd_composite);

			// Format match date into a string for the matchDate label
			Date date = matches.get(i).getDate().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			String dateStr = sdf.format(date);            
			
			Label lblMatchDate = new Label(matchComp, SWT.NONE);
			lblMatchDate.setBounds(10, 10, 390, 30);
			lblMatchDate.setText(dateStr);
			lblMatchDate.setAlignment(SWT.CENTER);

			Label lblTeam1 = new Label(matchComp, SWT.NONE);
			lblTeam1.setText(team1List.get(i).getTeamName());
			lblTeam1.setAlignment(SWT.CENTER);
			lblTeam1.setBounds(10, 60, 390, 30);
			
			Label lblVs = new Label(matchComp, SWT.NONE);
			lblVs.setText("V.S.");
			lblVs.setAlignment(SWT.CENTER);
			lblVs.setBounds(10, 96, 390, 30);

			Label lblTeam2 = new Label(matchComp, SWT.NONE);
			lblTeam2.setText(team2List.get(i).getTeamName());
			lblTeam2.setAlignment(SWT.CENTER);
			lblTeam2.setBounds(10, 132, 390, 30);
	        if( matches.get(i).getDate().before(today) ) {
	        	// Print final match score to matchComp
				lblTeam1.setText( team1List.get(i).getTeamName() + " | " + matches.get(i).getHomeScore() );
				lblTeam2.setText( team2List.get(i).getTeamName() + " | " + matches.get(i).getAwayScore() );
	        }
	        else if(  matches.get(i).getDate().MONTH == today.MONTH && matches.get(i).getDate().DAY_OF_MONTH == today.DAY_OF_MONTH ) {
	        	// Present 'Spectate' button on matchComp
				Button spectateMatch = new Button(matchComp, SWT.NONE);
				spectateMatch.setBounds(150, 200, 100, 50);
				//you can't use i for matches.get(i) because it isnt final
				int x = i;
				spectateMatch.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						System.out.println("SPECTATING");
						SpectatorGenerator spectatorGenerator = new SpectatorGenerator(parent, SWT.NONE, ((GUIShell) parent), matches.get(x), team1List.get(x), team2List.get(x));
						((GUIShell)parent).setDisplayedComposite(spectatorGenerator.getSpectatorComposite());
					}
				});
				spectateMatch.setText("SPECTATE");
	        	
	        }

		}
	}
	
	public void setLeague(League league)
	{
		this.league = league;
	}
	
	public void setMatchList(ArrayList<Match> matches)
	{
		this.matches = matches;
	}
	
	public void setTeam1List(ArrayList<Team> team1List)
	{
		this.team1List = team1List;
	}
	
	public void setTeam2(ArrayList<Team> team2List)
	{
		this.team2List = team2List;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
