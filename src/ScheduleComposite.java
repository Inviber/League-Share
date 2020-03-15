import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ScheduleComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ScheduleComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, LeagueParser leagueParser) {
		super(parent, style);
		
		System.out.println("schedule made");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 60, 1260, 37);
		lblNewLabel.setText(leagueParser.getLeagueName());
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.disposeDisplayedComposite();
				LandingComposite landingComposite = new LandingComposite(shell, SWT.NONE, shell, dbHelper);
				shell.setDisplayedComposite(landingComposite);
			}
		});
		btnNewButton.setBounds(10, 10, 68, 40);
		btnNewButton.setText("Back");
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
