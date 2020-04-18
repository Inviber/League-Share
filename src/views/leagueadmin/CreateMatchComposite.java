package views.LeagueAdmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;

public class CreateMatchComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateMatchComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -317);
		fd_lblNewLabel.left = new FormAttachment(0, 320);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("Create Match");
		
		Label lblTeamA = new Label(this, SWT.NONE);
		lblTeamA.setAlignment(SWT.CENTER);
		lblTeamA.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblTeamA = new FormData();
		fd_lblTeamA.left = new FormAttachment(0, 241);
		lblTeamA.setLayoutData(fd_lblTeamA);
		lblTeamA.setText("Team A");
		
		Combo combo = new Combo(this, SWT.NONE);
		fd_lblNewLabel.bottom = new FormAttachment(combo, -6);
		fd_lblTeamA.top = new FormAttachment(combo, 1, SWT.TOP);
		fd_lblTeamA.right = new FormAttachment(combo, -6);
		FormData fd_combo = new FormData();
		fd_combo.top = new FormAttachment(0, 132);
		fd_combo.right = new FormAttachment(100, -306);
		fd_combo.left = new FormAttachment(0, 306);
		combo.setLayoutData(fd_combo);
		
		Label lblTeamB = new Label(this, SWT.NONE);
		lblTeamB.setText("Team B");
		lblTeamB.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamB.setAlignment(SWT.CENTER);
		FormData fd_lblTeamB = new FormData();
		fd_lblTeamB.top = new FormAttachment(lblTeamA, 6);
		fd_lblTeamB.right = new FormAttachment(lblTeamA, -6, SWT.RIGHT);
		lblTeamB.setLayoutData(fd_lblTeamB);
		
		Combo combo_1 = new Combo(this, SWT.NONE);
		FormData fd_combo_1 = new FormData();
		fd_combo_1.right = new FormAttachment(lblTeamB, 180, SWT.RIGHT);
		fd_combo_1.top = new FormAttachment(combo, 6);
		fd_combo_1.left = new FormAttachment(lblTeamB, 12);
		combo_1.setLayoutData(fd_combo_1);
		
		DateTime dateTime = new DateTime(this, SWT.BORDER);
		FormData fd_dateTime = new FormData();
		fd_dateTime.bottom = new FormAttachment(combo_1, 36, SWT.BOTTOM);
		fd_dateTime.top = new FormAttachment(combo_1, 6);
		fd_dateTime.left = new FormAttachment(0, 336);
		fd_dateTime.right = new FormAttachment(100, -343);
		dateTime.setLayoutData(fd_dateTime);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(dateTime, 46, SWT.BOTTOM);
		fd_btnNewButton.top = new FormAttachment(dateTime, 6);
		fd_btnNewButton.right = new FormAttachment(dateTime, 0, SWT.RIGHT);
		fd_btnNewButton.left = new FormAttachment(0, 336);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Add");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
