package views.LeagueAdmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
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
		
		setSize(863, 521);
		
		Label lblCreateMatch = new Label(this, SWT.NONE);
		lblCreateMatch.setAlignment(SWT.CENTER);
		lblCreateMatch.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblCreateMatch.setBounds(354, 100, 143, 25);
		lblCreateMatch.setText("Create Match");
		
		Label lblTeamA = new Label(this, SWT.NONE);
		lblTeamA.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamA.setAlignment(SWT.CENTER);
		lblTeamA.setBounds(255, 130, 68, 25);
		lblTeamA.setText("Team A :");
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(329, 132, 196, 23);
		
		Label lblTeamB = new Label(this, SWT.NONE);
		lblTeamB.setText("Team B :");
		lblTeamB.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamB.setAlignment(SWT.CENTER);
		lblTeamB.setBounds(255, 168, 68, 25);
		
		Combo combo_1 = new Combo(this, SWT.NONE);
		combo_1.setBounds(329, 170, 196, 23);
		
		DateTime dateTime = new DateTime(this, SWT.BORDER);
		dateTime.setBounds(376, 199, 96, 37);
		
		Button btnAdd = new Button(this, SWT.NONE);
		btnAdd.setBounds(376, 242, 96, 37);
		btnAdd.setText("Add");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
