package views.leagueadmin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import league.LeagueGenerator;

import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UpdateInfoComposite extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UpdateInfoComposite(Composite parent, int style, String leagueID, LeagueGenerator leagueGenerator) {
		super(parent, style);
		
		setSize(863, 521);
		
		Label lblUpdateLeagueInformation = new Label(this, SWT.NONE);
		lblUpdateLeagueInformation.setAlignment(SWT.CENTER);
		lblUpdateLeagueInformation.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblUpdateLeagueInformation.setBounds(286, 77, 266, 25);
		lblUpdateLeagueInformation.setText("Update League Information");
		
		Label lblLeagueName = new Label(this, SWT.NONE);
		lblLeagueName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLeagueName.setAlignment(SWT.CENTER);
		lblLeagueName.setBounds(164, 108, 113, 25);
		lblLeagueName.setText("League Name :");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(283, 108, 266, 21);
		
		Label lblSport = new Label(this, SWT.NONE);
		lblSport.setText("Sport :");
		lblSport.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblSport.setAlignment(SWT.CENTER);
		lblSport.setBounds(219, 139, 58, 25);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(283, 139, 266, 21);
		
		Label lblLeagueName_1_1 = new Label(this, SWT.NONE);
		lblLeagueName_1_1.setText("Description :");
		lblLeagueName_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLeagueName_1_1.setAlignment(SWT.CENTER);
		lblLeagueName_1_1.setBounds(174, 170, 103, 25);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(283, 174, 266, 21);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
//				System.out.println(text.getText());
//				System.out.println(text_1.getText());
//				System.out.println(text_2.getText());
				
				leagueGenerator.getLeagueDBInterator().updateLeague(leagueID, text.getText(), "", text_1.getText(), text_2.getText());
				
				text.setText("");
				text_1.setText("");
				text_2.setText("");
			}
		});
		btnNewButton.setBounds(360, 201, 103, 45);
		btnNewButton.setText("Update");
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
