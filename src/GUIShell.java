

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class GUIShell extends Shell {
	
	private DatabaseHelper dbHelper;
	private Composite shell = this;
	private Composite displayedComposite = null;
	
	private Composite loginComposite = null;
//	private Composite landingComposite = null;
//	private Composite scheduleComposite = null;
//	private Composite spectatorComposite = null;
//	private Composite casterComposite = null;
//	private Composite adminComposite = null;
	
	private Account currentUser;
	
	
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

		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("League Share");
		setSize(1280, 720);
		
		loginComposite = new LoginComposite(shell, SWT.NONE, this, dbHelper);
		
		displayedComposite = loginComposite;
		displayedComposite.setSize(1280, 720);
		displayedComposite.setParent(shell);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void disposeDisplayedComposite()
	{
		displayedComposite.dispose();
	}
		
	public void setDisplayedComposite(Composite currentComposite)
	{
		this.displayedComposite = currentComposite;
		displayedComposite.setSize(1280, 720);
	}
	
	public void setAccount(Account currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public Account getAccount()
	{
		return this.currentUser;
	}

}
