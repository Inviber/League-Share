package views.castor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ExpandBar;

public class CastorComposite extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CastorComposite(Composite parent, int style) {
		super(parent, style);
		
		Color dark_gray = getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
		setLayout(new FormLayout());
		
		Group scoreGroup = new Group(this, SWT.NONE);
		FormData fd_scoreGroup = new FormData();
		fd_scoreGroup.bottom = new FormAttachment(0, 110);
		fd_scoreGroup.right = new FormAttachment(0, 1270);
		fd_scoreGroup.top = new FormAttachment(0, 10);
		fd_scoreGroup.left = new FormAttachment(0, 10);
		scoreGroup.setLayoutData(fd_scoreGroup);
		
		Label lblTeamname_1 = new Label(scoreGroup, SWT.NONE);
		lblTeamname_1.setAlignment(SWT.CENTER);
		lblTeamname_1.setBounds(165, 10, 375, 30);
		lblTeamname_1.setText("team1.name");
		
		Label lblTeamname_2 = new Label(scoreGroup, SWT.NONE);
		lblTeamname_2.setText("team2.name");
		lblTeamname_2.setAlignment(SWT.CENTER);
		lblTeamname_2.setBounds(596, 10, 375, 30);
		
		Label lblTeam1score = new Label(scoreGroup, SWT.NONE);
		lblTeam1score.setAlignment(SWT.CENTER);
		lblTeam1score.setBounds(315, 46, 75, 30);
		lblTeam1score.setText("team1.score");
		
		Label lblTeam2score = new Label(scoreGroup, SWT.NONE);
		lblTeam2score.setText("team2.score");
		lblTeam2score.setAlignment(SWT.CENTER);
		lblTeam2score.setBounds(745, 46, 75, 30);
		
		Button spectateButton = new Button(scoreGroup, SWT.NONE);
		spectateButton.setBounds(1131, 10, 119, 30);
		spectateButton.setText("Spectator View");
		
		Button team1ScoreIncrementButton = new Button(scoreGroup, SWT.NONE);
		team1ScoreIncrementButton.setBounds(175, 46, 40, 30);
		team1ScoreIncrementButton.setText("+");
		
		Button team1ScoreDecrementButton = new Button(scoreGroup, SWT.NONE);
		team1ScoreDecrementButton.setText("-");
		team1ScoreDecrementButton.setBounds(221, 46, 40, 30);
		
		Button team2ScoreIncrementButton = new Button(scoreGroup, SWT.NONE);
		team2ScoreIncrementButton.setText("+");
		team2ScoreIncrementButton.setBounds(885, 46, 40, 30);
		
		Button team2ScoreDecrementButton = new Button(scoreGroup, SWT.NONE);
		team2ScoreDecrementButton.setText("-");
		team2ScoreDecrementButton.setBounds(931, 46, 40, 30);
		
		Label lblVs = new Label(scoreGroup, SWT.NONE);
		lblVs.setAlignment(SWT.CENTER);
		lblVs.setBounds(546, 10, 40, 30);
		lblVs.setText("V.S.");
		
		Button btnSubmit = new Button(this, SWT.NONE);
		FormData fd_btnSubmit = new FormData();
		fd_btnSubmit.bottom = new FormAttachment(100, -25);
		fd_btnSubmit.right = new FormAttachment(100, -55);
		btnSubmit.setLayoutData(fd_btnSubmit);
		btnSubmit.setText("Submit");
		
		text = new Text(this, SWT.BORDER);
		fd_btnSubmit.top = new FormAttachment(text, -2, SWT.TOP);
		fd_btnSubmit.left = new FormAttachment(text, 6);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -136);
		text.setLayoutData(fd_text);
		
		Label lblPostAnnouncement = new Label(this, SWT.NONE);
		fd_text.left = new FormAttachment(lblPostAnnouncement, 6);
		fd_text.top = new FormAttachment(0, 672);
		lblPostAnnouncement.setAlignment(SWT.CENTER);
		FormData fd_lblPostAnnouncement = new FormData();
		fd_lblPostAnnouncement.bottom = new FormAttachment(100, -30);
		fd_lblPostAnnouncement.left = new FormAttachment(0, 55);
		fd_lblPostAnnouncement.right = new FormAttachment(100, -1095);
		lblPostAnnouncement.setLayoutData(fd_lblPostAnnouncement);
		lblPostAnnouncement.setText("Post Announcement");
		
		// Team 1 player list and expandable stat menu group
		Group statsTeam1Group = new Group(this, SWT.NONE);
		statsTeam1Group.setLayout(new GridLayout(1, true));
		statsTeam1Group.setBackground(dark_gray);
		fd_lblPostAnnouncement.top = new FormAttachment(0, 675);
		FormData fd_statsTeam1Group = new FormData();
		fd_statsTeam1Group.bottom = new FormAttachment(text, -6);
		fd_statsTeam1Group.top = new FormAttachment(scoreGroup, 6);
		fd_statsTeam1Group.left = new FormAttachment(0, 20);
		fd_statsTeam1Group.right = new FormAttachment(0, 605);
		statsTeam1Group.setLayoutData(fd_statsTeam1Group);
		
		// ADD ACTION LISTENER TO EXPAND COMPOSITE
		Composite statComposite_team1 = new Composite(statsTeam1Group, SWT.NONE);
		statComposite_team1.setBounds(10, 10, 565, 30);
		
		Label lblPlayername = new Label(statComposite_team1, SWT.NONE);
		lblPlayername.setAlignment(SWT.CENTER);
		lblPlayername.setBounds(10, 10, 75, 15);
		lblPlayername.setText("player.name");
		
		// START EXPANDED COMPOSITE EXAMPLE----------------------------------------------------->
		Composite statComposite_team1_expanded = new Composite(statsTeam1Group, SWT.NONE);
		statComposite_team1_expanded.setBounds(10, 46, 565, 250);
		
		Label lblPlayername_1 = new Label(statComposite_team1_expanded, SWT.NONE);
		lblPlayername_1.setText("player.name");
		lblPlayername_1.setAlignment(SWT.CENTER);
		lblPlayername_1.setBounds(10, 10, 75, 15);
		
		Label lblStat1 = new Label(statComposite_team1_expanded, SWT.NONE);
		lblStat1.setAlignment(SWT.CENTER);
		lblStat1.setBounds(100, 50, 275, 30);
		lblStat1.setText("stat1");
		
		Button stat1DecrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat1DecrementButton.setText("-");
		stat1DecrementButton.setBounds(427, 50, 40, 30);
		
		Button stat1IncrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat1IncrementButton.setBounds(381, 50, 40, 30);
		stat1IncrementButton.setText("+");
		
		Label lblStat2 = new Label(statComposite_team1_expanded, SWT.NONE);
		lblStat2.setText("stat2");
		lblStat2.setAlignment(SWT.CENTER);
		lblStat2.setBounds(100, 86, 275, 30);
		
		Button stat2IncrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat2IncrementButton.setText("+");
		stat2IncrementButton.setBounds(381, 86, 40, 30);
		
		Button stat2DecrementButton = new Button(statComposite_team1_expanded, SWT.NONE);
		stat2DecrementButton.setText("-");
		stat2DecrementButton.setBounds(427, 86, 40, 30);
		// END EXPANDED COMPOSITE EXAMPLE <-------------------------------------------------------
		
		// Team 2 player list and expandable stat menu group
		Group statsTeam2Group = new Group(this, SWT.NONE);
		statsTeam2Group.setLayout(new GridLayout(1, true));
		statsTeam2Group.setBackground(dark_gray);
		FormData fd_statsTeam2Group = new FormData();
		fd_statsTeam2Group.bottom = new FormAttachment(btnSubmit, -6);
		fd_statsTeam2Group.top = new FormAttachment(scoreGroup, 6);
		fd_statsTeam2Group.right = new FormAttachment(100, -10);
		fd_statsTeam2Group.left = new FormAttachment(100, -604);
		statsTeam2Group.setLayoutData(fd_statsTeam2Group);
		
		Composite statComposite_team2 = new Composite(statsTeam2Group, SWT.NONE);
		statComposite_team2.setBounds(10, 10, 565, 30);
		
		Label lblPlayername_2 = new Label(statComposite_team2, SWT.NONE);
		lblPlayername_2.setText("player.name");
		lblPlayername_2.setAlignment(SWT.CENTER);
		lblPlayername_2.setBounds(10, 10, 75, 15);

	}
	
	public void fillComposite(Composite parent)
	{
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
