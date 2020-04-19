package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import league.LeagueGenerator;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TrackNewPlayerStatsComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TrackNewPlayerStatsComposite(Composite parent, int style, String leagueID, LeagueGenerator leagueGenerator) {
		super(parent, style);

		setSize(863, 521);
		
		Label lblTrackNewPlayer = new Label(this, SWT.NONE);
		lblTrackNewPlayer.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTrackNewPlayer.setAlignment(SWT.CENTER);
		lblTrackNewPlayer.setBounds(297, 82, 248, 30);
		lblTrackNewPlayer.setText("Track New Player Statistic");
		
		Label lblStatisticName = new Label(this, SWT.NONE);
		lblStatisticName.setAlignment(SWT.CENTER);
		lblStatisticName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblStatisticName.setBounds(162, 118, 134, 28);
		lblStatisticName.setText("Statistic Name");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(297, 118, 248, 28);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(text.getText());
				
				leagueGenerator.getLeagueDBInterator().createTrackedStatistic(leagueID, text.getText());
				
				text.setText("");
			}
		});
		btnNewButton.setBounds(365, 152, 106, 41);
		btnNewButton.setText("Add");
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
