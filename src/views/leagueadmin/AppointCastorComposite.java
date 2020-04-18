package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class AppointCastorComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AppointCastorComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblAppointCastor = new Label(this, SWT.NONE);
		FormData fd_lblAppointCastor = new FormData();
		fd_lblAppointCastor.left = new FormAttachment(0, 307);
		fd_lblAppointCastor.top = new FormAttachment(0, 103);
		fd_lblAppointCastor.right = new FormAttachment(0, 450);
		lblAppointCastor.setLayoutData(fd_lblAppointCastor);
		lblAppointCastor.setAlignment(SWT.CENTER);
		lblAppointCastor.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblAppointCastor.setText("Appoint Castor");
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblUsername.setAlignment(SWT.CENTER);
		FormData fd_lblUsername = new FormData();
		fd_lblUsername.top = new FormAttachment(0, 138);
		lblUsername.setLayoutData(fd_lblUsername);
		lblUsername.setText("Username :");
		
		text = new Text(this, SWT.BORDER);
		fd_lblAppointCastor.bottom = new FormAttachment(text, -6);
		fd_lblUsername.right = new FormAttachment(100, -484);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(lblUsername, 2, SWT.TOP);
		fd_text.left = new FormAttachment(lblUsername, 6);
		fd_text.right = new FormAttachment(100, -318);
		text.setLayoutData(fd_text);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(text, 37, SWT.BOTTOM);
		fd_btnNewButton.top = new FormAttachment(text, 6);
		fd_btnNewButton.left = new FormAttachment(0, 334);
		fd_btnNewButton.right = new FormAttachment(0, 425);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Appoint");

		setSize(863, 521);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
