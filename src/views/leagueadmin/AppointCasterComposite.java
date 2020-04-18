package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class AppointCasterComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AppointCasterComposite(Composite parent, int style) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(531, 325, 76, 21);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setBounds(470, 328, 55, 15);
		lblUsername.setText("Username:");
		
		setSize(863, 521);
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
