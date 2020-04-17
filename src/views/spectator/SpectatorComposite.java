package views.spectator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import match.ChatMessage;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;

public class SpectatorComposite extends Composite {
	private Text txtEnterAMessage;
	private Player displayedPlayer;
	
	private Composite chatComposite;

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

		CreateChat(shell);

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
		while (!players.isEmpty()) {  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Composite playerInfo = new Composite(composite, SWT.NONE);
			FillLayout fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			// making a player parser to access the players names
			displayedPlayer = shell.getPlayerGenerator().generatePlayer(homeTeam.getLeagueID(), homeTeam.getTeamID(),
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
			displayedPlayer = shell.getPlayerGenerator().generatePlayer(awayTeam.getLeagueID(), awayTeam.getTeamID(),
					players.remove(0));
			Label lblNewLabel_4 = new Label(playerInfo, SWT.NONE);
			// lblNewLabel_4.setBounds(10, 10, 295, 20);
			lblNewLabel_4.setText(displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName());
		}

		awayTeamPlayersComposite.setContent(composite);
		awayTeamPlayersComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}
	
	private void CreateChat(GUIShell shell) {
		Group grpChatToBe = new Group(this, SWT.NONE);
		grpChatToBe.setText("Chat");
		grpChatToBe.setBounds(10, 466, 1260, 219);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(grpChatToBe,
				SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 21, 1245, 163);
	
		this.chatComposite = new Composite(scrolledComposite, SWT.NONE);
		chatComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		scrolledComposite.setContent(this.chatComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		scrolledComposite.setMinSize(this.chatComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));	
		
		txtEnterAMessage = new Text(grpChatToBe, SWT.BORDER);
		txtEnterAMessage.setBounds(0, 184, 428, 25);
		txtEnterAMessage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtEnterAMessage.setText("");
				
			}
		});

		Button btnSubmit = new Button(grpChatToBe, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Format f = new SimpleDateFormat("hh:mm:ss");
				String timeString = "(" + f.format(new Date()) + ")";
				
				String username = shell.getAccount().getUsername();
				String leagueID = shell.getMatchGenerator().getMatchParser().getLeagueID();
				String matchID = shell.getMatchGenerator().getMatchParser().getMatchID();
				
//				ArrayList<ChatMessage> chat = shell.getMatchGenerator().getMatchDBInterator().getChat(leagueID, matchID);
//				System.out.println(chat);
				//ArrayList<ChatMessage> chat = 
				
				//String chatMessage = timeString + " " + username + ": "  + txtEnterAMessage.getText();
				String chatMessage = txtEnterAMessage.getText();
				
				
				new Label(chatComposite, SWT.NONE).setText(chatMessage);
				scrolledComposite.setContent(chatComposite);
				scrolledComposite.setMinSize(chatComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				
				
				shell.getMatchGenerator().getMatchDBInterator().postMessageToChat(leagueID, matchID, username, chatMessage, timeString);
			
				
				txtEnterAMessage.setText("");	
				
				Rectangle bounds = chatComposite.getBounds();
				//http://www.java2s.com/Tutorial/Java/0280__SWT/Scrollawidgetintoviewonfocusin.htm
		        Rectangle area = scrolledComposite.getClientArea();
		        Point origin = scrolledComposite.getOrigin();
		        if (origin.x > bounds.x)
		          origin.x = Math.max(0, bounds.x);
		        if (origin.y > bounds.y)
		          origin.y = Math.max(0, bounds.y);
		        if (origin.x + area.width < bounds.x + bounds.width)
		          origin.x = Math.max(0, bounds.x + bounds.width - area.width);
		        if (origin.y + area.height < bounds.y + bounds.height)
		          origin.y = Math.max(0, bounds.y + bounds.height - area.height);
		        scrolledComposite.setOrigin(origin);    
			}
		});

		txtEnterAMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					Format f = new SimpleDateFormat("hh:mm:ss");
					String calendarString = "(" + f.format(new Date()) + ")";
							
					new Label(chatComposite, SWT.NONE).setText(calendarString + " " + shell.getAccount().getUsername() + ": "  + txtEnterAMessage.getText());
					scrolledComposite.setContent(chatComposite);
					scrolledComposite.setMinSize(chatComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
					
					txtEnterAMessage.setText("");	
					
					Rectangle bounds = chatComposite.getBounds();
					//http://www.java2s.com/Tutorial/Java/0280__SWT/Scrollawidgetintoviewonfocusin.htm
			        Rectangle area = scrolledComposite.getClientArea();
			        Point origin = scrolledComposite.getOrigin();
			        if (origin.x > bounds.x)
			          origin.x = Math.max(0, bounds.x);
			        if (origin.y > bounds.y)
			          origin.y = Math.max(0, bounds.y);
			        if (origin.x + area.width < bounds.x + bounds.width)
			          origin.x = Math.max(0, bounds.x + bounds.width - area.width);
			        if (origin.y + area.height < bounds.y + bounds.height)
			          origin.y = Math.max(0, bounds.y + bounds.height - area.height);
			        scrolledComposite.setOrigin(origin);
				}
			}
		});
		btnSubmit.setBounds(428, 183, 90, 26);
		btnSubmit.setText("Submit");

		
		
	}
}
