import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

public class SpectatorComposite extends Composite {
	private Text txtEnterAMessage;

	/**
	 * Create the composite.
	 * @param homeTeamParser 
	 * @param awayTeamParser
	 * Home Team Parser must be passed first
	 */
	public SpectatorComposite(Composite parent, int style, GUIShell shell, DatabaseHelper dbHelper, MatchParser mParser, TeamParser homeTeamParser, TeamParser awayTeamParser) {
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
		lblNewLabel_3.setText(mParser.getHomeScore() + "   -   " + mParser.getAwayScore());
		
		Group grpChatToBe = new Group(this, SWT.NONE);
		grpChatToBe.setText("Chat to be completed");
		grpChatToBe.setBounds(10, 466, 1260, 219);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(grpChatToBe, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 21, 1260, 163);
		scrolledComposite.setExpandVertical(true);
		
		Button btnSubmit = new Button(grpChatToBe, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});

		btnSubmit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.CR){
					System.out.println("Message Sent");
					//Need to actually send message here using the text field contents [Should be the same as above]
				}
			}
		});
		btnSubmit.setBounds(428, 183, 90, 26);
		btnSubmit.setText("Submit");
		
		txtEnterAMessage = new Text(grpChatToBe, SWT.BORDER);
		txtEnterAMessage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtEnterAMessage.setText("");
			}
	});
		txtEnterAMessage.setBounds(0, 184, 428, 25);
		txtEnterAMessage.setText("Enter a message");

		Group grpHomeTeamInfo = new Group(this, SWT.NONE);
		grpHomeTeamInfo.setText("Home Team Info");
		grpHomeTeamInfo.setBounds(20, 151, 606, 313);
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(grpHomeTeamInfo, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite_1.setAlwaysShowScrollBars(true);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		scrolledComposite_1.setBounds(0, 0, 606, 313);
		
		Composite composite = new Composite(scrolledComposite_1, SWT.NONE);
		FillLayout fl_composite = new FillLayout(SWT.VERTICAL);
		fl_composite.spacing = 5;
		composite.setLayout(fl_composite);
		
		//Getting the list of player IDs
		ArrayList<String> players = homeTeamParser.getPlayerIDs();
		//Generating the list of team mates
		while(!players.isEmpty()) { ////////////////////////////////////////////////////////////////////////////////////////////////////////////
			Composite playerInfo = new Composite(composite, SWT.NONE);
			FillLayout fill = new FillLayout(SWT.VERTICAL);
			playerInfo.setLayout(fill);
			//making a player parser to access the players names
			PlayerParser playerParser = new PlayerParser(homeTeamParser.getLeagueID(), homeTeamParser.getTeamID(), players.remove(0));
			Label lblNewLabel_4 = new Label(playerInfo, SWT.NONE);
			//lblNewLabel_4.setBounds(10, 10, 295, 20);
			lblNewLabel_4.setText(playerParser.getFirstName() + " " + playerParser.getLastName());
		}
		scrolledComposite_1.setContent(composite);
		scrolledComposite_1.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Group grpAwayTeamInfo = new Group(this, SWT.NONE);
		grpAwayTeamInfo.setText("Away Team Info");
		grpAwayTeamInfo.setBounds(654, 151, 606, 313);
		
		ScrolledComposite scrolledComposite_1_1 = new ScrolledComposite(grpAwayTeamInfo, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite_1_1.setExpandVertical(true);
		scrolledComposite_1_1.setExpandHorizontal(true);
		scrolledComposite_1_1.setAlwaysShowScrollBars(true);
		scrolledComposite_1_1.setBounds(0, 0, 606, 313);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Go to caster Composite
			}
		});
		btnNewButton.setBounds(1136, 55, 134, 45);
		btnNewButton.setText("Caster View");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
