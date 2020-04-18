package views.leagueadmin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class UpdateInfoComposite extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UpdateInfoComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		Label lblUpdateLeagueInformation = new Label(this, SWT.CENTER);
		lblUpdateLeagueInformation.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		FormData fd_lblUpdateLeagueInformation = new FormData();
		fd_lblUpdateLeagueInformation.top = new FormAttachment(0, 109);
		fd_lblUpdateLeagueInformation.left = new FormAttachment(0, 258);
		fd_lblUpdateLeagueInformation.right = new FormAttachment(100, -270);
		fd_lblUpdateLeagueInformation.bottom = new FormAttachment(0, 144);
		lblUpdateLeagueInformation.setLayoutData(fd_lblUpdateLeagueInformation);
		lblUpdateLeagueInformation.setText("Update League Information");
		
		Label lblLeagueName = new Label(this, SWT.CENTER);
		lblLeagueName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblLeagueName = new FormData();
		fd_lblLeagueName.left = new FormAttachment(0, 178);
		fd_lblLeagueName.bottom = new FormAttachment(lblUpdateLeagueInformation, 30, SWT.BOTTOM);
		fd_lblLeagueName.top = new FormAttachment(lblUpdateLeagueInformation, 6);
		lblLeagueName.setLayoutData(fd_lblLeagueName);
		lblLeagueName.setText("League Name: ");
		
		text = new Text(this, SWT.BORDER);
		fd_lblLeagueName.right = new FormAttachment(100, -508);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.RIGHT);
		fd_text.top = new FormAttachment(lblUpdateLeagueInformation, 5);
		fd_text.left = new FormAttachment(lblLeagueName, 6);
		text.setLayoutData(fd_text);
		
		Label lblSport = new Label(this, SWT.CENTER);
		lblSport.setText("Sport: ");
		lblSport.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblSport = new FormData();
		fd_lblSport.right = new FormAttachment(lblLeagueName, 0, SWT.RIGHT);
		fd_lblSport.top = new FormAttachment(lblLeagueName, 3);
		fd_lblSport.left = new FormAttachment(lblLeagueName, 0, SWT.LEFT);
		lblSport.setLayoutData(fd_lblSport);
		
		text_1 = new Text(this, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.RIGHT);
		fd_text_1.top = new FormAttachment(text, 6);
		fd_text_1.left = new FormAttachment(text, 0, SWT.LEFT);
		text_1.setLayoutData(fd_text_1);
		
		text_2 = new Text(this, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.right = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.RIGHT);
		fd_text_2.top = new FormAttachment(text_1, 6);
		fd_text_2.left = new FormAttachment(0, 277);
		text_2.setLayoutData(fd_text_2);
		
		Label lblDescription = new Label(this, SWT.CENTER);
		lblDescription.setText("Description: ");
		lblDescription.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblDescription = new FormData();
		fd_lblDescription.top = new FormAttachment(lblSport, 13);
		fd_lblDescription.right = new FormAttachment(lblLeagueName, 0, SWT.RIGHT);
		fd_lblDescription.left = new FormAttachment(lblLeagueName, 0, SWT.LEFT);
		lblDescription.setLayoutData(fd_lblDescription);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(text_2, 48, SWT.BOTTOM);
		fd_btnNewButton.top = new FormAttachment(text_2, 6);
		fd_btnNewButton.right = new FormAttachment(100, -337);
		fd_btnNewButton.left = new FormAttachment(100, -448);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Update");
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
