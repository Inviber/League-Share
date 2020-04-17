package views.spectator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;


import match.Match;
import player.Player;

import team.Team;

import views.GUIShell;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class SpectatorComposite extends Composite {
	private Text txtEnterAMessage;
	private Player displayedPlayer;

	/**
	 * Create the composite.
	 * 
	 * @param homeTeam Home team parameter should be before away team
	 * @param awayTeam 
	 * @param previousWindow - the Schedule composite should pass "this" keyword as the parameter
	 * Called using the POJOs from Schedule, can make generators to go back to the schedule
	 */
	public SpectatorComposite(Composite parent, int style, GUIShell shell, Match match,
			Team homeTeam, Team awayTeam, Composite previousWindow) {
		super(parent, style);
		
		CreateComponents(shell, parent, match, homeTeam, awayTeam, previousWindow);
		
	}
	
	private void CreateComponents(GUIShell shell, Composite parent, Match match,
			Team homeTeam, Team awayTeam, Composite previousWindow) {
		//TODO make a caster composite and replace this blank one;
		Composite temporaryCaster = new Composite(parent, SWT.NONE);

		CreateTopButtons(previousWindow, temporaryCaster, parent);

		CreateChat();

		CreateConstantLabels();

		CreateDynamicDataLabels(match, homeTeam, awayTeam, shell);

	}
	
	
	private void CreateTopButtons(Composite previousWindow, Composite CasterComposite, Composite parent){
	
	//button to switch to caster (no caster composite for now)
		Button switchToCaster = new Button(this, SWT.NONE);
		switchToCaster.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GUIShell) parent).setDisplayedComposite(CasterComposite);
				System.out.println("Caster Selected");
			}
		});
		switchToCaster.setBounds(10, 61, 134, 45);
		switchToCaster.setText("Caster View");
		
	//button to go back to schedule
		Button backButton = new Button(this, SWT.NONE);
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GUIShell) parent).setDisplayedComposite(previousWindow);
				System.out.println("Back button pressed.");
			}
		});
		backButton.setText("Back");
		backButton.setBounds(10, 10, 134, 45);
		
		
	}
	
	private void CreateConstantLabels() {
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(320, 10, 640, 36);
		lblNewLabel.setText("Spectator");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblNewLabel_1.setBounds(628, 80, 24, 20);
		lblNewLabel_1.setText("vs.");
		
		
	}
	
	//Player Lists could be done more eloquently
	private void CreateDynamicDataLabels(Match match, Team homeTeam, Team awayTeam, GUIShell shell) {
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(320, 80, 306, 20);
		lblNewLabel_2.setText(homeTeam.getTeamName());

		Label lblNewLabel_2_1 = new Label(this, SWT.NONE);
		lblNewLabel_2_1.setAlignment(SWT.CENTER);
		lblNewLabel_2_1.setText(awayTeam.getTeamName());
		lblNewLabel_2_1.setBounds(670, 80, 306, 20);

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBounds(427, 125, 431, 20);
		lblNewLabel_3.setText(match.getHomeScore() + "   -   " + match.getAwayScore());
		
		Group grpHomeTeamInfo = new Group(this, SWT.NONE);
		grpHomeTeamInfo.setText("Home Team Info");
		grpHomeTeamInfo.setBounds(20, 151, 606, 313);

		
		Group grpAwayTeamInfo = new Group(this, SWT.NONE);
		grpAwayTeamInfo.setText("Away Team Info");
		grpAwayTeamInfo.setBounds(654, 151, 606, 313);

		displayPlayers(homeTeam, shell, grpHomeTeamInfo);
		displayPlayers(homeTeam, shell, grpAwayTeamInfo);

	}
	
	private void displayPlayers(Team currentTeam, GUIShell shell, Group groupToAddTo) {
		ScrolledComposite scrollingPlayersComposite = new ScrolledComposite(groupToAddTo, SWT.BORDER | SWT.V_SCROLL);
		scrollingPlayersComposite.setAlwaysShowScrollBars(true);
		scrollingPlayersComposite.setExpandHorizontal(true);
		scrollingPlayersComposite.setExpandVertical(true);
		scrollingPlayersComposite.setBounds(0, 0, 606, 313);

		Composite playersComposite = new Composite(scrollingPlayersComposite, SWT.NONE);
		FillLayout fill = new FillLayout(SWT.VERTICAL);
		fill.spacing = 5;
		playersComposite.setLayout(fill);

		// Getting the list of home player IDs
		ArrayList<String> players = currentTeam.getPlayerIDs();
		// Generating the list of team mates
		while (!players.isEmpty()) {  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Composite playerInfo = new Composite(playersComposite, SWT.NONE);
			fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			// making a player parser to access the players names
			displayedPlayer = shell.getPlayerGenerator().generatePlayer(currentTeam.getLeagueID(), currentTeam.getTeamID(),
					players.remove(0));
			Label playerName = new Label(playerInfo, SWT.NONE);
			// lblNewLabel_4.setBounds(10, 10, 295, 20);
			playerName.setText(displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName());
		}
		scrollingPlayersComposite.setContent(playersComposite);
		scrollingPlayersComposite.setMinSize(playersComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}
	
	private void CreateChat() {
		Group grpChatToBe = new Group(this, SWT.NONE);
		grpChatToBe.setText("Chat to be completed");
		grpChatToBe.setBounds(10, 466, 1260, 219);

		ScrolledComposite scrolledComposite = new ScrolledComposite(grpChatToBe,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 21, 1260, 163);
		scrolledComposite.setExpandVertical(true);

		Button btnSubmit = new Button(grpChatToBe, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		btnSubmit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					System.out.println("Message Sent");
					// Need to actually send message here using the text field contents [Should be
					// the same as above]
				}
			}
		});
		btnSubmit.setBounds(428, 183, 90, 26);
		btnSubmit.setText("Submit");

		txtEnterAMessage = new Text(grpChatToBe, SWT.BORDER);
		txtEnterAMessage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtEnterAMessage.setText("");
			}
		});
		txtEnterAMessage.setBounds(0, 184, 428, 25);
		txtEnterAMessage.setText("Enter a message");
	}
}
