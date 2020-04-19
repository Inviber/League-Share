package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import league.LeagueGenerator;
import team.TeamGenerator;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CreateNewTeamComposite extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateNewTeamComposite(Composite parent, int style, String leagueID, TeamGenerator teamGenerator) {
		super(parent, style);
		
		setSize(863, 521);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setBounds(299, 86, 229, 38);
		lblNewLabel.setText("Create New Team");
		
		Label lblTeamName = new Label(this, SWT.NONE);
		lblTeamName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTeamName.setAlignment(SWT.CENTER);
		lblTeamName.setBounds(215, 126, 78, 28);
		lblTeamName.setText("Team Name :");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(299, 130, 229, 28);
		
		Label lblZipcode = new Label(this, SWT.NONE);
		lblZipcode.setText("Zipcode");
		lblZipcode.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblZipcode.setAlignment(SWT.CENTER);
		lblZipcode.setBounds(206, 160, 88, 28);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(299, 164, 229, 28);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println(text.getText());
				System.out.println(text_1.getText());
	
				teamGenerator.getTeamDBInterator().createTeam(leagueID, text.getText(), text_1.getText());
				
				text.setText("");
				text_1.setText("");

			}
		});
		btnNewButton.setBounds(362, 200, 97, 38);
		btnNewButton.setText("Add");
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
