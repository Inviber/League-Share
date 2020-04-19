package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import league.LeagueGenerator;
import user.AccountGenerator;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AppointCasterComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AppointCasterComposite(Composite parent, int style, String leagueID, AccountGenerator accountGenerator, LeagueGenerator leagueGenerator) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(310, 138, 180, 28);
		
		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setAlignment(SWT.RIGHT);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblUsername.setBounds(198, 136, 106, 28);
		lblUsername.setText("Username");
		
		setSize(863, 521);
		
		Label lblAppointCastor = new Label(this, SWT.NONE);
		lblAppointCastor.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblAppointCastor.setAlignment(SWT.CENTER);
		lblAppointCastor.setBounds(316, 101, 174, 31);
		lblAppointCastor.setText("Appoint Caster");
		
		Button btnAppoint = new Button(this, SWT.NONE);
		btnAppoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println(text.getText());
				
				leagueGenerator.getLeagueDBInterator().addCasterIDs(leagueID, accountGenerator.getAccountDBInterator().getUserIDByUsername(text.getText()));
				
				text.setText("");
				
			}
		});
		btnAppoint.setBounds(357, 172, 89, 39);
		btnAppoint.setText("Appoint");
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
