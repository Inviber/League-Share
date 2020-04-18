package views.LeagueAdmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class CreateNewTeamComposite extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateNewTeamComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblCreateNewTeam = new Label(this, SWT.NONE);
		FormData fd_lblCreateNewTeam = new FormData();
		fd_lblCreateNewTeam.top = new FormAttachment(0, 109);
		fd_lblCreateNewTeam.left = new FormAttachment(0, 258);
		fd_lblCreateNewTeam.right = new FormAttachment(100, -270);
		fd_lblCreateNewTeam.bottom = new FormAttachment(0, 144);
		lblCreateNewTeam.setLayoutData(fd_lblCreateNewTeam);
		lblCreateNewTeam.setAlignment(SWT.CENTER);
		lblCreateNewTeam.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblCreateNewTeam.setText("Create New Team");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(lblCreateNewTeam, 6);
		fd_lblNewLabel.left = new FormAttachment(0, 207);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Team Name: ");
		
		text = new Text(this, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(lblCreateNewTeam, 0, SWT.RIGHT);
		fd_text.top = new FormAttachment(lblCreateNewTeam, 6);
		fd_text.left = new FormAttachment(lblNewLabel);
		text.setLayoutData(fd_text);
		
		text_1 = new Text(this, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(lblCreateNewTeam, 0, SWT.RIGHT);
		fd_text_1.top = new FormAttachment(text, 12);
		fd_text_1.left = new FormAttachment(0, 284);
		text_1.setLayoutData(fd_text_1);
		
		Label lblZipcode = new Label(this, SWT.NONE);
		lblZipcode.setAlignment(SWT.CENTER);
		lblZipcode.setText("Zipcode:");
		lblZipcode.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblZipcode = new FormData();
		fd_lblZipcode.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		fd_lblZipcode.top = new FormAttachment(lblNewLabel, 16);
		fd_lblZipcode.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		lblZipcode.setLayoutData(fd_lblZipcode);
		
		text_2 = new Text(this, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.right = new FormAttachment(lblCreateNewTeam, 0, SWT.RIGHT);
		fd_text_2.top = new FormAttachment(text_1, 10);
		fd_text_2.left = new FormAttachment(text, 0, SWT.LEFT);
		text_2.setLayoutData(fd_text_2);
		
		Label lblOwnerUsername = new Label(this, SWT.NONE);
		lblOwnerUsername.setText("Owner username: ");
		lblOwnerUsername.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblOwnerUsername.setAlignment(SWT.CENTER);
		FormData fd_lblOwnerUsername = new FormData();
		fd_lblOwnerUsername.top = new FormAttachment(text_2, 1, SWT.TOP);
		fd_lblOwnerUsername.left = new FormAttachment(lblNewLabel, -44, SWT.LEFT);
		fd_lblOwnerUsername.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		lblOwnerUsername.setLayoutData(fd_lblOwnerUsername);
		
		Button btnAdd = new Button(this, SWT.NONE);
		FormData fd_btnAdd = new FormData();
		fd_btnAdd.bottom = new FormAttachment(text_2, 48, SWT.BOTTOM);
		fd_btnAdd.top = new FormAttachment(text_2, 6);
		fd_btnAdd.right = new FormAttachment(100, -337);
		fd_btnAdd.left = new FormAttachment(100, -448);
		btnAdd.setLayoutData(fd_btnAdd);
		btnAdd.setText("Add");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
