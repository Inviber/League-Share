package views.leagueadmin;

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
		fd_lblNewLabel.right = new FormAttachment(100, -138);
		fd_lblNewLabel.left = new FormAttachment(0, 584);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setText("Create Match");
		
		Label lblTeamA = new Label(this, SWT.NONE);
		lblTeamA.setAlignment(SWT.CENTER);
		lblTeamA.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblTeamA = new FormData();
		fd_lblTeamA.left = new FormAttachment(0, 472);
		lblTeamA.setLayoutData(fd_lblTeamA);
		lblTeamA.setText("Team A");
		
		Combo combo = new Combo(this, SWT.NONE);
		fd_lblNewLabel.bottom = new FormAttachment(combo, -7);
		fd_lblTeamA.bottom = new FormAttachment(combo, 0, SWT.BOTTOM);
		fd_lblTeamA.right = new FormAttachment(combo, -6);
		FormData fd_combo = new FormData();
		fd_combo.left = new FormAttachment(0, 537);
		fd_combo.right = new FormAttachment(100, -72);
		combo.setLayoutData(fd_combo);
		
		Label lblTeamB = new Label(this, SWT.NONE);
		lblTeamB.setText("Team B");
		lblTeamB.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamB.setAlignment(SWT.CENTER);
		FormData fd_lblTeamB = new FormData();
		fd_lblTeamB.left = new FormAttachment(0, 472);
		fd_lblTeamB.top = new FormAttachment(lblTeamA, 6);
		lblTeamB.setLayoutData(fd_lblTeamB);
		
		Combo combo_1 = new Combo(this, SWT.NONE);
		fd_combo.bottom = new FormAttachment(combo_1, -6);
		fd_lblTeamB.right = new FormAttachment(combo_1, -6);
		FormData fd_combo_1 = new FormData();
		fd_combo_1.right = new FormAttachment(combo, 0, SWT.RIGHT);
		fd_combo_1.top = new FormAttachment(0, 235);
		fd_combo_1.left = new FormAttachment(combo, 0, SWT.LEFT);
		combo_1.setLayoutData(fd_combo_1);
		
		DateTime dateTime = new DateTime(this, SWT.BORDER);
		FormData fd_dateTime = new FormData();
		fd_dateTime.top = new FormAttachment(combo_1, 6);
		fd_dateTime.left = new FormAttachment(0, 566);
		fd_dateTime.right = new FormAttachment(100, -113);
		dateTime.setLayoutData(fd_dateTime);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		fd_dateTime.bottom = new FormAttachment(100, -227);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(dateTime, 6);
		fd_btnNewButton.left = new FormAttachment(dateTime, 0, SWT.LEFT);
		fd_btnNewButton.right = new FormAttachment(100, -113);
		fd_btnNewButton.bottom = new FormAttachment(100, -181);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Add");
		
		setSize(863, 521);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
