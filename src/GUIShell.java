import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GUIShell extends Shell {

	private Composite shell = this;
	private Composite loginComposite = null;
	private Composite landingComposite = null;
	private Composite scheduleComposite = null;
	private Composite spectatorComposite = null;
	private Composite casterComposite = null;
	private Composite adminComposite = null;
	
	private Account currentUser;
	boolean successfulLogin = false;
	
	
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
				
				if(shell.getSuccessfulLogin() == true)
				{
					shell.setText(shell.getAccount().getFirstName());
				}
				
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
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("League Share");
		setSize(1280, 720);
		
		loginComposite = new LoginComposite(shell, SWT.NONE, this);
		loginComposite.setSize(1280, 720);
		loginComposite.setParent(shell);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setAccount(Account currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public Account getAccount()
	{
		return this.currentUser;
	}
	
	public void setSuccessfulLogin(boolean success)
	{
		this.successfulLogin = success;
	}
	
	public boolean getSuccessfulLogin()
	{
		return this.successfulLogin;
	}

}
