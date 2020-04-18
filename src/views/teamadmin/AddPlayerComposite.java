package views.teamadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class AddPlayerComposite extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddPlayerComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblAddPlayer = new Label(this, SWT.NONE);
		FormData fd_lblAddPlayer = new FormData();
		fd_lblAddPlayer.left = new FormAttachment(0, 327);
		fd_lblAddPlayer.right = new FormAttachment(0, 439);
		fd_lblAddPlayer.top = new FormAttachment(0, 111);
		lblAddPlayer.setLayoutData(fd_lblAddPlayer);
		lblAddPlayer.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblAddPlayer.setAlignment(SWT.CENTER);
		lblAddPlayer.setText("Add Player");
		
		Label lblFirstName = new Label(this, SWT.NONE);
		lblFirstName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblFirstName = new FormData();
		fd_lblFirstName.top = new FormAttachment(0, 144);
		fd_lblFirstName.left = new FormAttachment(0, 209);
		lblFirstName.setLayoutData(fd_lblFirstName);
		lblFirstName.setText("First Name :");
		
		text = new Text(this, SWT.BORDER);
		fd_lblAddPlayer.bottom = new FormAttachment(text, -6);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 148);
		fd_text.right = new FormAttachment(lblFirstName, 180, SWT.RIGHT);
		fd_text.left = new FormAttachment(lblFirstName, 6);
		text.setLayoutData(fd_text);
		
		Label lblLastName = new Label(this, SWT.NONE);
		lblLastName.setText("Last Name :");
		lblLastName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblLastName = new FormData();
		fd_lblLastName.top = new FormAttachment(lblFirstName, 6);
		fd_lblLastName.left = new FormAttachment(lblFirstName, 0, SWT.LEFT);
		lblLastName.setLayoutData(fd_lblLastName);
		
		text_1 = new Text(this, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_1.top = new FormAttachment(text, 6);
		fd_text_1.left = new FormAttachment(text, 0, SWT.LEFT);
		text_1.setLayoutData(fd_text_1);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(lblAddPlayer, 0, SWT.LEFT);
		fd_btnNewButton.bottom = new FormAttachment(text_1, 42, SWT.BOTTOM);
		fd_btnNewButton.top = new FormAttachment(text_1, 6);
		fd_btnNewButton.right = new FormAttachment(lblAddPlayer, -5, SWT.RIGHT);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Add");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
