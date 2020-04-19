package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.time.DateTimeException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import league.League;
import league.LeagueGenerator;
import match.MatchGenerator;
import team.Team;
import team.TeamGenerator;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CreateMatchComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateMatchComposite(Composite parent, int style, String leagueID, LeagueGenerator leagueGenerator, TeamGenerator teamGenerator, MatchGenerator matchGenerator) {
		super(parent, style);
		
		setSize(1157, 663);
		
		Label lblCreateMatch = new Label(this, SWT.NONE);
		lblCreateMatch.setAlignment(SWT.CENTER);
		lblCreateMatch.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblCreateMatch.setBounds(689, 226, 143, 25);
		lblCreateMatch.setText("Create Match");
		
		Label lblTeamA = new Label(this, SWT.NONE);
		lblTeamA.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamA.setAlignment(SWT.CENTER);
		lblTeamA.setBounds(593, 255, 68, 25);
		lblTeamA.setText("Team A :");
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(667, 257, 196, 23);
		
		Label lblTeamB = new Label(this, SWT.NONE);
		lblTeamB.setText("Team B :");
		lblTeamB.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamB.setAlignment(SWT.CENTER);
		lblTeamB.setBounds(593, 286, 68, 25);
		
		Combo combo_1 = new Combo(this, SWT.NONE);
		combo_1.setBounds(667, 286, 196, 23);
		
		DateTime dateTime = new DateTime(this, SWT.BORDER);
		
		dateTime.setBounds(712, 315, 96, 37);
		
		
//		combo.add("test 1");
//		combo.add("test 2");
//		combo.add("test 3");
//		
//		combo_1.add("test 4");
//		combo_1.add("test 5");
//		combo_1.add("test 6");
		
		
		League league = leagueGenerator.generateLeague(leagueID);
		ArrayList<String> teamIDs = league.getTeamIDs();
		ArrayList<Team> teams = new ArrayList<Team>();
		
		//System.out.println(teamIDs.toString());
		
		for(int i = 0; i < teamIDs.size(); i++)
		{
			Team newTeam = teamGenerator.generateTeam(leagueID, teamIDs.get(i));
			teams.add(newTeam);
		}
		
		for(int i = 0; i < teams.size(); i++)
		{
			//System.out.println(teams.get(i).getTeamName());
			combo.add(teams.get(i).getTeamName());
			combo_1.add(teams.get(i).getTeamName());
		}
		
		
		
		Button btnAdd = new Button(this, SWT.NONE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//System.out.println(combo.getItem(combo.getSelectionIndex()) + ": " + teams.get(combo.getSelectionIndex()).getTeamID());
				//System.out.println(combo_1.getItem(combo_1.getSelectionIndex()) + ": " + teams.get(combo_1.getSelectionIndex()).getTeamID());
				
				String month = Integer.toString(dateTime.getMonth() + 1);
				String day = Integer.toString(dateTime.getDay());
				String year = Integer.toString(dateTime.getYear());
				year = year.substring(2, 4);
		
				String date = month + "/" + day + "/" + year;
				
				String homeID = teams.get(combo.getSelectionIndex()).getTeamID();
				String awayID = teams.get(combo_1.getSelectionIndex()).getTeamID();
				
				matchGenerator.createMatch(leagueID, homeID, awayID, date);
								
				//System.out.println(date);
			}
		});
		btnAdd.setBounds(712, 358, 96, 37);
		btnAdd.setText("Add");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
