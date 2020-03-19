import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class SpectatorComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param homeTeamParser 
	 * @param awayTeamParser
	 * Home Team Parser must be passed first
	 */
	public SpectatorComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, TeamParser homeTeamParser, TeamParser awayTeamParser, MatchParser mParser) {
		super(parent, style);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(320, 10, 640, 36);
		lblNewLabel.setText("Spectator");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblNewLabel_1.setBounds(628, 80, 24, 20);
		lblNewLabel_1.setText("vs.");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(320, 80, 306, 20);
		lblNewLabel_2.setText(homeTeamParser.getTeamName());
		
		Label lblNewLabel_2_1 = new Label(this, SWT.NONE);
		lblNewLabel_2_1.setAlignment(SWT.CENTER);
		lblNewLabel_2_1.setText(awayTeamParser.getTeamName());
		lblNewLabel_2_1.setBounds(670, 80, 306, 20);
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBounds(427, 125, 431, 20);
		lblNewLabel_3.setText(mParser.getHomeScore() + " " + mParser.getAwayScore());
		
		Group grpChatToBe = new Group(this, SWT.NONE);
		grpChatToBe.setText("Chat to be completed");
		grpChatToBe.setBounds(10, 470, 1260, 240);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(grpChatToBe, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 22, 1260, 197);
		scrolledComposite.setExpandVertical(true);
		
		text = new Text(grpChatToBe, SWT.BORDER);
		text.setBounds(0, 216, 434, 24);
		
		Button btnSubmit = new Button(grpChatToBe, SWT.NONE);
		btnSubmit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR){
					System.out.println("Message Sent");
					//Need to actually send message here using the text field contents
				}
			}
		});
		btnSubmit.setBounds(432, 214, 90, 26);
		btnSubmit.setText("Submit");
		
		Group grpHomeTeamInfo = new Group(this, SWT.NONE);
		grpHomeTeamInfo.setText("Home Team Info");
		grpHomeTeamInfo.setBounds(20, 151, 606, 313);
		
		Group grpAwayTeamInfo = new Group(this, SWT.NONE);
		grpAwayTeamInfo.setText("Away Team Info");
		grpAwayTeamInfo.setBounds(674, 151, 606, 313);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
