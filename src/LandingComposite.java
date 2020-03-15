

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LandingComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LandingComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper) {
		super(parent, style);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(528, 83, 214, 40);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(748, 83, 49, 40);
		btnNewButton.setText("Go!");
		
		Label lblNewLabel_2 = new Label(this, SWT.CENTER);
		lblNewLabel_2.setText("Seach for Leagues/Teams");
		lblNewLabel_2.setBounds(528, 60, 214, 17);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(1116, 10, 154, 40);
		btnNewButton_1.setText("Create New League");
		
		List list = new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println(list.getSelection()[0] + " selected...");
			}
		});
		list.setBounds(100, 200, 350, 200);
		
		Label lblNewLabel_3 = new Label(this, SWT.CENTER);
		lblNewLabel_3.setBounds(100, 181, 350, 17);
		lblNewLabel_3.setText("Followed Leagues");
		
		
		ArrayList<String> followedLeagueIDs = shell.getAccount().getFollowedLeagueIDs();
//		System.out.println(followedLeagueIDs.toString());
//		LeagueParser parser = new LeagueParser(followedLeagueIDs.get(0), dbHelper);
//		list.add(parser.getLeagueName());
//		LeagueParser parser2 = new LeagueParser(followedLeagueIDs.get(1), dbHelper);
//		list.add(parser2.getLeagueName());
		
		for(int i = 0; i < followedLeagueIDs.size(); i++)
		{
			LeagueParser parser = new LeagueParser(followedLeagueIDs.get(i), dbHelper);
			System.out.println(parser.getLeagueName());
			list.add(parser.getLeagueName());
		}

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
}
