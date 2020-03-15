

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class LandingComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LandingComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.WRAP);
		lblNewLabel.setBounds(405, 69, 425, 63);
		lblNewLabel.setText("Welcome " + shell.getAccount().getFirstName());
		
		Label lblNewLabel_1 = new Label(this, SWT.WRAP);
		lblNewLabel_1.setBounds(405, 169, 425, 312);
		lblNewLabel_1.setText(shell.getAccount().getAccountDetails(true));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
