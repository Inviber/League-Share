package views.spectator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import league.League;
import match.ChatMessage;
import match.Match;
import player.Player;
import player.PlayerGenerator;
import team.Team;
import views.GUIShell;
import views.caster.CasterGenerator;
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
	private ArrayList<String> casterIDs;
	
	private Composite chatComposite;
	private ScrolledComposite scrolledComposite;
	private Group grpChatToBe;
	private String username;
	private String leagueID;
	private String matchID;
	private GUIShell shell;
	private Thread chatThread;
	//private boolean chatThread = true;

	/**
	 * Create the composite.
	 * 
	 * @param homeTeam       Home team parameter should be before away team
	 * @param awayTeam
	 * @param previousWindow - the Schedule composite should pass "this" keyword as
	 *                       the parameter Called using the POJOs from Schedule, can
	 *                       make generators to go back to the schedule
	 */
	public SpectatorComposite(Composite parent, int style, GUIShell shell, Match match, Team homeTeam, Team awayTeam,
			Composite previousWindow) {
		super(parent, style);
		
		this.shell = shell;
		username = shell.getAccountGenerator().getLoggedInAccount().getUsername();
		leagueID = match.getLeagueID();
		matchID = match.getMatchID();
		
		// generate league to get casterIDs
		League league = shell.getLeagueGenerator().generateLeague(leagueID);
		this.casterIDs = league.getCasterIDs();
		
		grpChatToBe = new Group(this, SWT.NONE);
		grpChatToBe.setText("Chat");
		grpChatToBe.setBounds(10, 466, 1260, 219);
				
		scrolledComposite = new ScrolledComposite(grpChatToBe,
				SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 21, 1245, 163);
				
		
		chatComposite = new Composite(scrolledComposite, SWT.NONE);
		chatComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		scrolledComposite.setContent(chatComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinSize(chatComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));	

		CreateComponents(shell, parent, match, homeTeam, awayTeam, previousWindow);
		
		chatThread = new Thread(new Runnable() 
		{
		  public void run() 
		  {
		     while (true) 
		     {
		        
		        Display.getDefault().asyncExec(new Runnable() 
		        {
		           public void run() 
		           {
		              refreshChat();
		           }
		        });
		        
		        try { Thread.sleep(1000); } catch (Exception e) { }
		     }
		  }
		});
	
		chatThread.start();
	}
	
	private void CreateComponents(GUIShell shell, Composite parent, Match match,
			Team homeTeam, Team awayTeam, Composite previousWindow) {

		
		CreateTopButtons(previousWindow, parent, match, homeTeam, awayTeam);

		CreateChat(shell, match);

		CreateConstantLabels();

		CreateDynamicDataLabels(match, homeTeam, awayTeam, shell);
	}

	private void CreateTopButtons(Composite previousWindow, Composite parent, Match match, Team homeTeam, Team awayTeam) {
		if ( isCasterForLeague() ) {
			CasterGenerator casterGenerator = new CasterGenerator(parent, SWT.NONE, match, homeTeam, awayTeam);
			Composite caster = casterGenerator.getCasterComposite();
			Button switchToCaster = new Button(this, SWT.NONE);
			switchToCaster.addSelectionListener(new SelectionAdapter() {
				@SuppressWarnings("deprecation")
				@Override
				public void widgetSelected(SelectionEvent e) {
					chatThread.stop();
					((GUIShell) parent).setDisplayedComposite(caster);
					
				}
			});
			switchToCaster.setBounds(10, 61, 134, 45);
			switchToCaster.setText("Caster View");
		}
		

		// button to go back to schedule
		Button backButton = new Button(this, SWT.NONE);
		backButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chatThread.stop();
				
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
		displayPlayers(awayTeam, shell, grpAwayTeamInfo);

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
		while (!players.isEmpty()) { 
			Composite playerInfo = new Composite(playersComposite, SWT.NONE);
			fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			
			// making a player parser to access the players names
			displayedPlayer = shell.getPlayerGenerator().generatePlayer(currentTeam.getLeagueID(),
					currentTeam.getTeamID(), players.remove(0));

			Label playerName = new Label(playerInfo, SWT.NONE);
			String playerStats = displayedPlayer.getFirstName() + " " + displayedPlayer.getLastName();

			for(String statName : displayedPlayer.getStatisticNames())
				playerStats += "\n     " + statName + ": " + displayedPlayer.getStatistics().get(statName);
			
			playerName.setText(playerStats);
		}
		scrollingPlayersComposite.setContent(playersComposite);
		scrollingPlayersComposite.setMinSize(playersComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	
	private void CreateChat(GUIShell shell, Match match) {
    
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
				String chatMessage = txtEnterAMessage.getText();
				
				shell.getMatchGenerator().getMatchDBInterator().postMessageToChat(leagueID, matchID, username, chatMessage, timeString);
				txtEnterAMessage.setText("");	
				
		        refreshChat();
			}
		});

		txtEnterAMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					Format f = new SimpleDateFormat("hh:mm:ss");
					String timeString = "(" + f.format(new Date()) + ")";
					String chatMessage = txtEnterAMessage.getText();
					
					shell.getMatchGenerator().getMatchDBInterator().postMessageToChat(leagueID, matchID, username, chatMessage, timeString);
					txtEnterAMessage.setText("");	
					
			        refreshChat();
				}
			}
		});
		btnSubmit.setBounds(428, 183, 90, 26);
		btnSubmit.setText("Submit");
	}
	
	private void refreshChat() 
	{
				
		chatComposite = new Composite(scrolledComposite, SWT.NONE);
		chatComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		ArrayList<ChatMessage> chat = shell.getMatchGenerator().getMatchDBInterator().getChat(leagueID, matchID);
	
		for(int i = 0; i < chat.size(); i++)
		{
			String dbMessage = chat.get(i).getTime() + " " + chat.get(i).getUsername() + ": " + chat.get(i).getMessage(); 
			new Label(chatComposite, SWT.NONE).setText(dbMessage);
		}
		
		scrolledComposite.setContent(chatComposite);
		scrolledComposite.setMinSize(chatComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
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
	
	public boolean isCasterForLeague() {
		// userID for loggedInAccount
		String userID = shell.getAccountGenerator().getLoggedInAccount().getID();
		
		// if userID not found in casterIDs then isCaster remains false
		boolean isCaster = false;
		
		for ( int i = 0; i < casterIDs.size(); i++ ) {
			if ( userID.contentEquals( casterIDs.get(i) ) ) {
				isCaster = true;
				return isCaster;
			}
		}
		
		return isCaster;
	}
	
	public void postAnnouncement(Text casterAnnouncement, String casterName) {
		Format f = new SimpleDateFormat("hh:mm:ss");
		String timeString = "(" + f.format(new Date()) + ")";
		String chatMessage = casterAnnouncement.getText();
		
		shell.getMatchGenerator().getMatchDBInterator().postMessageToChat(leagueID, matchID, casterName, chatMessage, timeString);
		
        refreshChat();
	}
}
