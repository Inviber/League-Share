package views.landing;

import views.leagueadmin.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import user.Account;
import database.DatabaseHelper;
import league.League;
import league.LeagueDBInterator;
import league.LeagueGenerator;
import league.LeagueParser;
import team.TeamParser;
import views.GUIShell;
import views.leagueadmin.LeagueAdminComposite;
import views.schedule.ScheduleComposite;
import views.schedule.ScheduleGenerator;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LandingComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	@SuppressWarnings("deprecation")
	public LandingComposite(Composite parent, int style, GUIShell shell) {
		super(parent, style);
		fillComposite(shell);
	}
	
//	public LandingComposite(Composite parent, int style, GUIShell shell) {
//		super(parent, style);
		
	public void fillComposite(GUIShell shell) {
			
			text = new Text(this, SWT.BORDER);
			text.setBounds(528, 83, 214, 40);
			
			Button btnNewButton = new Button(this, SWT.NONE);
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					System.out.println("search button pressed...");
					ArrayList<String> results = shell.getLeagueGenerator().searchLeagueByName(text.getText());
					
					if (results.size() != 0)
					{
						/*
						System.out.println("Results: "); // for if we want to create a search menu.
						for (int i = 0; i < results.size(); i+=2)
						{
							System.out.println(results.get(i) + " | " + results.get(i+1));
						}
						*/
						
						ScheduleGenerator scheduleGenerator = new ScheduleGenerator(shell, SWT.NONE, results.get(0), shell.getLeagueGenerator(), shell.getMatchGenerator(), shell.getTeamGenerator());
						shell.setDisplayedComposite(scheduleGenerator.getScheduleComposite());

						//shell.getAccountGenerator().getLoggedInAccount().addLeague(results.get(0), false, false); // add 0 means just the first
					}
					else
					{
						System.out.println("No results found.");
					}
					
				}
			});
			btnNewButton.setBounds(748, 83, 49, 40);
			btnNewButton.setText("Go!");
			
			Label lblNewLabel_2 = new Label(this, SWT.CENTER);
			lblNewLabel_2.setText("Seach for Leagues/Teams");
			lblNewLabel_2.setBounds(528, 60, 214, 17);
			
			Button btnNewButton_1 = new Button(this, SWT.NONE);
			btnNewButton_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDown(MouseEvent e) {
					System.out.println("creating new league...");
					String userName = shell.getAccountGenerator().getLoggedInAccount().getUsername();
					String userId = shell.getAccountGenerator().getAccountDBInterator().getUserIDByUsername(userName);
					//String userID = shell.getAccountGenerator().getAccountDBInterator().getUserIDByUsername(userId);
					
					String leagueID = shell.getLeagueGenerator().createLeague("No League Name", userId, "No Sport Name", "New League");
					
					shell.getAccountGenerator().getLoggedInAccount().addLeague(leagueID, true, true);
					
					shell.getLeagueGenerator().getLeagueDBInterator().addCasterIDs(leagueID, userId);
					
					LeagueAdminComposite leagueAdmin = new LeagueAdminComposite(shell, SWT.NONE, shell, leagueID);
					shell.setDisplayedComposite(leagueAdmin);
				}
			});
			btnNewButton_1.setBounds(10, 10, 154, 40);
			btnNewButton_1.setText("Create New League");
			
			List list = new List(this, SWT.BORDER | SWT.V_SCROLL);
			list.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseDown(MouseEvent e) {
//					System.out.println(list.getSelection()[0] + " selected...");
//				}
				@Override
				public void mouseDoubleClick(MouseEvent e) {
//					System.out.println(list.getSelection() + " selected...");
					
					ArrayList<String> followedLeagueIDs = shell.getAccountGenerator().getLoggedInAccount().getFollowedLeagueIDs();
//					LeagueDBInterator leagueDBInterator = new LeagueDBInterator(dbHelper);
//					LeagueParser parser = new LeagueParser(leagueDBInterator);
					
					if (list.getSelectionIndex() != -1) // -1 means nothing selected
					{
						ScheduleGenerator scheduleGenerator = new ScheduleGenerator(shell, SWT.NONE, followedLeagueIDs.get(list.getSelectionIndex()), shell.getLeagueGenerator(), shell.getMatchGenerator(), shell.getTeamGenerator());
//						ScheduleComposite scheduleComposite = new ScheduleComposite(shell, SWT.NONE, followedLeagueIDs.get(0), shell.getLeagueGenerator(), shell.getMatchGenerator(), shell.getTeamGenerator());
						shell.setDisplayedComposite(scheduleGenerator.getScheduleComposite());
					}
				}
			});
			list.setBounds(178, 167, 350, 200);
			
			Label lblNewLabel_3 = new Label(this, SWT.CENTER);
			lblNewLabel_3.setBounds(178, 144, 350, 17);
			lblNewLabel_3.setText("Followed Leagues");
			
			List list_1 = new List(this, SWT.BORDER | SWT.V_SCROLL);
			list_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					//System.out.println("Clickedeeee");
					
//					if (list.getSelectionIndex() != -1) // -1 means nothing selected
//					{
//						ScheduleGenerator scheduleGenerator = new ScheduleGenerator(shell, SWT.NONE, followedLeagueIDs.get(list.getSelectionIndex()), shell.getLeagueGenerator(), shell.getMatchGenerator(), shell.getTeamGenerator());
////						ScheduleComposite scheduleComposite = new ScheduleComposite(shell, SWT.NONE, followedLeagueIDs.get(0), shell.getLeagueGenerator(), shell.getMatchGenerator(), shell.getTeamGenerator());
//						shell.setDisplayedComposite(scheduleGenerator.getScheduleComposite());
//					}
					ArrayList<String> ownedLeagueIDs = shell.getAccountGenerator().getLoggedInAccount().getOwnedLeagueIDs();
					
					if(list_1.getSelectionIndex() != -1)
					{
						//System.out.println(ownedLeagueIDs.get(list_1.getSelectionIndex()));
						LeagueAdminComposite leagueAdminComposite = new LeagueAdminComposite(shell, SWT.NONE, shell, ownedLeagueIDs.get(list_1.getSelectionIndex()));
						shell.setDisplayedComposite(leagueAdminComposite);
					}
					
					
					
					//LeagueAdminComposite leagueAdminComposite = new LeagueAdminComposite();
				}
			});
			list_1.setBounds(178, 430, 350, 200);
			
			Label lblNewLabel = new Label(this, SWT.NONE);
			lblNewLabel.setAlignment(SWT.CENTER);
			lblNewLabel.setBounds(178, 407, 350, 17);
			lblNewLabel.setText("Owned Leagues");
			
			List list_2 = new List(this, SWT.BORDER | SWT.V_SCROLL);
			list_2.setBounds(742, 167, 350, 200);
			
			List list_2_1 = new List(this, SWT.BORDER | SWT.V_SCROLL);
			list_2_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					
	//				ArrayList<String> managedTeamIDs = shell.getAccount().getManagedTeamIDs();
	//				ArrayList<String> managedTeamLeagueIDs = shell.getAccount().getManagedTeamLeagueIDs();
					
	//				TeamParser parser = new TeamParser(managedTeamLeagueIDs.get(list_2_1.getSelectionIndex()), managedTeamIDs.get(list_2_1.getSelectionIndex()), dbHelper);
					
					//System.out.println(parser.getTeamID());
	//				System.out.println(parser.getTeamName() + " selected");
					//System.out.println(parser.getZipcode());
					System.out.println("clicked");
					
				}
			});
			list_2_1.setBounds(742, 430, 350, 200);
			
			Label lblManagedTeams = new Label(this, SWT.NONE);
			lblManagedTeams.setText("Managed Teams");
			lblManagedTeams.setAlignment(SWT.CENTER);
			lblManagedTeams.setBounds(742, 407, 350, 17);
			
			Label lblNewLabel_1_1 = new Label(this, SWT.NONE);
			lblNewLabel_1_1.setText("Managed Leagues");
			lblNewLabel_1_1.setAlignment(SWT.CENTER);
			lblNewLabel_1_1.setBounds(742, 144, 350, 17);
			
			
			
			
			ArrayList<String> followedLeagueIDs = shell.getAccountGenerator().getLoggedInAccount().getFollowedLeagueIDs();
			ArrayList<String> ownedLeagueIDs = shell.getAccountGenerator().getLoggedInAccount().getOwnedLeagueIDs();
			ArrayList<String> managedLeaguesIDs = shell.getAccountGenerator().getLoggedInAccount().getLeagueCastedIDs();
			ArrayList<String> managedTeamIDs = shell.getAccountGenerator().getLoggedInAccount().getManagedTeamIDs();
	//		ArrayList<String> managedTeamLeagueIDs = shell.getAccountGenerator().getLoggedInAccount().getManagedTeamLeagueIDs();
			
			
			
			if(followedLeagueIDs.size() != 0)
			{
				for(int i = 0; i < followedLeagueIDs.size(); i++)
				{
					League league = ( shell.getLeagueGenerator() ).generateLeague( followedLeagueIDs.get(i) );
	//				System.out.println(parser.getLeagueName());
					list.add(league.getLeagueName());	
				}
			}
			else
			{
				list.add("no followed leagues");
			}	
			if(ownedLeagueIDs.size() != 0)
			{
				for(int i = 0; i < ownedLeagueIDs.size(); i++)
				{
					League league = shell.getLeagueGenerator().generateLeague(ownedLeagueIDs.get(i));
	//				System.out.println(parser.getLeagueName());
					list_1.add(league.getLeagueName());
					
				}
			}
			else
			{
				list_1.add("no owned leagues");
			}
			
			if(managedLeaguesIDs.size() != 0)
			{
				//need to rename 'leagues casted' to 'managed leagues'
				for(int i = 0; i < managedLeaguesIDs.size(); i++)
				{
					League league = shell.getLeagueGenerator().generateLeague(managedLeaguesIDs.get(i));
	//				System.out.println(parser.getLeagueName());
					list_2.add(league.getLeagueName());
				}
			}
			else
			{
				list_2.add("no managed leagues");
			}
			
			
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// NEEDS FIXED
			if(managedTeamIDs.size() != 0)
			{
				for(int i = 0; i < managedTeamIDs.size(); i++)
				{
	//				Team team = shell.getTeamGenerator().generateTeam(managedTeamIDs.get(i));
					
	//				System.out.println(parser.getTeamID());
	//				System.out.println(parser.getTeamName());
	//				System.out.println(parser.getLeagueID());
	//				System.out.println(i);
	//				list_2_1.add(team.getTeamName());
				}
			}
			else
			{
				list_2_1.add("no managed teams");
			}
			
	//		System.out.println(managedTeamIDs.toString());
	//		System.out.println(managedTeamLeagueIDs.toString());
	
			
			Button btnLogout = new Button(this, SWT.NONE);
			btnLogout.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					shell.logout();
				}
			});
			btnLogout.setText("Logout");
			btnLogout.setBounds(1175, 10, 95, 40);
			
		}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
}
