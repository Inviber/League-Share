import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
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
		super(display, SWT.SHELL_TRIM);
		
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
