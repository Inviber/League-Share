package views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import database.DatabaseHelper;
import league.LeagueGenerator;
import match.MatchGenerator;
import player.PlayerGenerator;
import team.TeamGenerator;
import user.Account;
import user.AccountGenerator;
import views.login.LoginComposite;

public class GUIShell extends Shell {
	
	private static DatabaseHelper dbHelper;
	private Composite shell = this;
	private Composite displayedComposite = null;
	
	private Composite loginComposite = null;
//	private Composite landingComposite = null;
//	private Composite scheduleComposite = null;
//	private Composite spectatorComposite = null;
//	private Composite casterComposite = null;
//	private Composite adminComposite = null;
		
	private static AccountGenerator accountGenerator;
	private static LeagueGenerator leagueGenerator;
	private static MatchGenerator matchGenerator;
	private static TeamGenerator teamGenerator;
	private static PlayerGenerator playerGenerator;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			GUIShell shell = new GUIShell(display);
			
			//Centering application when it opens
			Monitor primary = display.getPrimaryMonitor();
			Rectangle bounds = primary.getBounds();
			Rectangle rect = shell.getBounds();
			int x = bounds.x + (bounds.width - rect.width) / 2;
			int y = bounds.y + (bounds.height - rect.height) / 2;
			shell.setLocation(x, y);
			
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {			
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public GUIShell(Display display) {
		super(display, SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.TITLE);
		
		dbHelper = new DatabaseHelper("mongodb+srv://abachmann:mongodb@cluster0-zozah.mongodb.net/test?retryWrites=true&w=majority", "LeagueShare");

		accountGenerator = new AccountGenerator(dbHelper);
		leagueGenerator = new LeagueGenerator(dbHelper);
		matchGenerator = new MatchGenerator(dbHelper);
		teamGenerator = new TeamGenerator(dbHelper);
		playerGenerator = new PlayerGenerator(dbHelper);
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("League Share");
		setSize(1280, 720);
		
		loginComposite = new LoginComposite(shell, SWT.NONE, this);
		
		displayedComposite = loginComposite;
		displayedComposite.setSize(1280, 720);
		displayedComposite.setParent(shell);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void logout()
	{
		disposeDisplayedComposite();
		loginComposite = new LoginComposite(shell, SWT.NONE, this);
		
		displayedComposite = loginComposite;
		displayedComposite.setSize(1280, 720);
		displayedComposite.setParent(shell);
	}
	
	public void disposeDisplayedComposite()
	{
		displayedComposite.dispose();
	}
		
	public void setDisplayedComposite(Composite currentComposite)
	{
		this.displayedComposite.dispose();
		this.displayedComposite = currentComposite;
		displayedComposite.setSize(1280, 720);
	}
	
	public AccountGenerator getAccountGenerator() {
		return accountGenerator;
	}
	
	public LeagueGenerator getLeagueGenerator() {
		return leagueGenerator;
	}
	
	public MatchGenerator getMatchGenerator() {
		return matchGenerator;
	}
	
	public TeamGenerator getTeamGenerator() {
		return teamGenerator;
	}
	public PlayerGenerator getPlayerGenerator() {
		return playerGenerator;
	}

}
