package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

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
		text.setBounds(310, 138, 180, 21);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblUsername.setBounds(223, 136, 81, 21);
		lblUsername.setText("Username:");
		
		setSize(863, 521);
		
		Label lblAppointCastor = new Label(this, SWT.NONE);
		lblAppointCastor.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblAppointCastor.setAlignment(SWT.CENTER);
		lblAppointCastor.setBounds(322, 106, 152, 25);
		lblAppointCastor.setText("Appoint Caster");
		
		Button btnAppoint = new Button(this, SWT.NONE);
		btnAppoint.setBounds(353, 165, 89, 39);
		btnAppoint.setText("Appoint");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
