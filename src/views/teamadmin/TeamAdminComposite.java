package views.teamadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import team.Team;
import org.eclipse.wb.swt.SWTResourceManager;

public class TeamAdminComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TeamAdminComposite(Composite parent, int style, Team team) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 31);
		fd_lblNewLabel.right = new FormAttachment(100, -454);
		fd_lblNewLabel.bottom = new FormAttachment(0, 83);
		fd_lblNewLabel.left = new FormAttachment(0, 483);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText(team.getTeamName());
		
		Group grpTeamOptions = new Group(this, SWT.NONE);
		FormData fd_grpTeamOptions = new FormData();
		fd_grpTeamOptions.bottom = new FormAttachment(0, 681);
		fd_grpTeamOptions.right = new FormAttachment(0, 373);
		fd_grpTeamOptions.top = new FormAttachment(0, 104);
		fd_grpTeamOptions.left = new FormAttachment(0, 123);
		grpTeamOptions.setLayoutData(fd_grpTeamOptions);
		grpTeamOptions.setText("Team Options");
		
		
		Button btnNewButton = new Button(grpTeamOptions, SWT.NONE);
		btnNewButton.setBounds(25, 36, 192, 48);
		btnNewButton.setText("Modify Roster");
		
		Button btnAddPlayer = new Button(grpTeamOptions, SWT.NONE);
		btnAddPlayer.setText("Add Player");
		btnAddPlayer.setBounds(25, 110, 192, 48);
		
		Button btnBack = new Button(this, SWT.NONE);
		FormData fd_btnBack = new FormData();
		fd_btnBack.bottom = new FormAttachment(lblNewLabel, -8, SWT.BOTTOM);
		fd_btnBack.right = new FormAttachment(0, 138);
		fd_btnBack.left = new FormAttachment(0, 30);
		fd_btnBack.top = new FormAttachment(lblNewLabel, 3, SWT.TOP);
		btnBack.setLayoutData(fd_btnBack);
		btnBack.setText("Back");
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
