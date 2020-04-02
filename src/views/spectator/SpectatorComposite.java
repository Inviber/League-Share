package views.spectator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import database.DatabaseHelper;
import match.Match;
import player.Player;
import player.PlayerGenerator;
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
	private PlayerGenerator playerGenerator;
	private Player displayedPlayer;

	/**
	 * Create the composite.
	 * 
	 * @param homeTeam Home team parameter should be before away team
	 * @param awayTeam 
	 * 
	 * Called using the POJOs from Schedule, can make generators to go back to the schedule
	 */
	public SpectatorComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, Match match,
			Team homeTeam, Team awayTeam) {
		super(parent, style);
		playerGenerator = new PlayerGenerator(dbHelper);

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

		Group grpHomeTeamInfo = new Group(this, SWT.NONE);
		grpHomeTeamInfo.setText("Home Team Info");
		grpHomeTeamInfo.setBounds(20, 151, 606, 313);

		ScrolledComposite homeTeamPlayersComposite = new ScrolledComposite(grpHomeTeamInfo, SWT.BORDER | SWT.V_SCROLL);
		homeTeamPlayersComposite.setAlwaysShowScrollBars(true);
		homeTeamPlayersComposite.setExpandHorizontal(true);
		homeTeamPlayersComposite.setExpandVertical(true);
		homeTeamPlayersComposite.setBounds(0, 0, 606, 313);

		Composite composite = new Composite(homeTeamPlayersComposite, SWT.NONE);
		FillLayout fl_composite = new FillLayout(SWT.VERTICAL);
		fl_composite.spacing = 5;
		composite.setLayout(fl_composite);

		// Getting the list of home player IDs
		ArrayList<String> players = homeTeam.getPlayerIDs();
		// Generating the list of team mates
		while (!players.isEmpty()) { ////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Composite playerInfo = new Composite(composite, SWT.NONE);
			FillLayout fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			// making a player parser to access the players names
			displayedPlayer = playerGenerator.generatePlayer(homeTeam.getLeagueID(), homeTeam.getTeamID(),
					players.remove(0));
			Label lblNewLabel_4 = new Label(playerInfo, SWT.NONE);
			// lblNewLabel_4.setBounds(10, 10, 295, 20);
			lblNewLabel_4.setText(displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName());
		}
		homeTeamPlayersComposite.setContent(composite);
		homeTeamPlayersComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Group grpAwayTeamInfo = new Group(this, SWT.NONE);
		grpAwayTeamInfo.setText("Away Team Info");
		grpAwayTeamInfo.setBounds(654, 151, 606, 313);

		ScrolledComposite awayTeamPlayersComposite = new ScrolledComposite(grpAwayTeamInfo, SWT.BORDER | SWT.V_SCROLL);
		awayTeamPlayersComposite.setExpandVertical(true);
		awayTeamPlayersComposite.setExpandHorizontal(true);
		awayTeamPlayersComposite.setAlwaysShowScrollBars(true);
		awayTeamPlayersComposite.setBounds(0, 0, 606, 313);

		// Getting the list of away player IDs
		players = awayTeam.getPlayerIDs();
		// Generating the list of team mates
		while (!players.isEmpty()) { ////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Composite playerInfo = new Composite(composite, SWT.NONE);
			FillLayout fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			// making a player parser to access the players names
			displayedPlayer = playerGenerator.generatePlayer(awayTeam.getLeagueID(), awayTeam.getTeamID(),
					players.remove(0));
			Label lblNewLabel_4 = new Label(playerInfo, SWT.NONE);
			// lblNewLabel_4.setBounds(10, 10, 295, 20);
			lblNewLabel_4.setText(displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName());
		}

		awayTeamPlayersComposite.setContent(composite);
		awayTeamPlayersComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Go to caster Composite
			}
		});
		btnNewButton.setBounds(1136, 55, 134, 45);
		btnNewButton.setText("Caster View");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
