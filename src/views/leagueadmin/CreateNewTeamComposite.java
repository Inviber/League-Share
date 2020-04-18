package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
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
		
		setSize(863, 521);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setBounds(328, 114, 166, 31);
		lblNewLabel.setText("Create New Team");
		
		Label lblTeamName = new Label(this, SWT.NONE);
		lblTeamName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamName.setAlignment(SWT.CENTER);
		lblTeamName.setBounds(196, 146, 97, 21);
		lblTeamName.setText("Team Name :");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(299, 146, 229, 21);
		
		Label lblZipcode = new Label(this, SWT.NONE);
		lblZipcode.setText("Zipcode :");
		lblZipcode.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblZipcode.setAlignment(SWT.CENTER);
		lblZipcode.setBounds(196, 173, 97, 21);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(299, 173, 229, 21);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(299, 200, 229, 21);
		
		Label lblOwnerUsername = new Label(this, SWT.NONE);
		lblOwnerUsername.setText("Owner Username :");
		lblOwnerUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblOwnerUsername.setAlignment(SWT.CENTER);
		lblOwnerUsername.setBounds(151, 200, 142, 21);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(358, 227, 97, 38);
		btnNewButton.setText("Add");
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
