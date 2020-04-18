package views.LeagueAdmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TrackNewPlayerStatsComposite extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TrackNewPlayerStatsComposite(Composite parent, int style) {
		super(parent, style);

		setSize(863, 521);
		
		Label lblTrackNewPlayer = new Label(this, SWT.NONE);
		lblTrackNewPlayer.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTrackNewPlayer.setAlignment(SWT.CENTER);
		lblTrackNewPlayer.setBounds(297, 82, 248, 26);
		lblTrackNewPlayer.setText("Track New Player Statistic");
		
		Label lblStatisticName = new Label(this, SWT.NONE);
		lblStatisticName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblStatisticName.setBounds(179, 114, 112, 26);
		lblStatisticName.setText("Statistic Name :");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(297, 114, 248, 21);
		
		Label lblDefaultValue = new Label(this, SWT.NONE);
		lblDefaultValue.setText("Default Value :");
		lblDefaultValue.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblDefaultValue.setBounds(179, 146, 106, 26);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(296, 148, 249, 21);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(363, 172, 106, 41);
		btnNewButton.setText("Add");
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
