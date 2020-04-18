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
		fd_lblUpdateLeagueInformation.top = new FormAttachment(0, 174);
		fd_lblUpdateLeagueInformation.right = new FormAttachment(100, -568);
		fd_lblUpdateLeagueInformation.left = new FormAttachment(0, 584);
		fd_lblUpdateLeagueInformation.bottom = new FormAttachment(0, 199);
		lblUpdateLeagueInformation.setLayoutData(fd_lblUpdateLeagueInformation);
		lblUpdateLeagueInformation.setText("Update League Information");
		
		Label lblLeagueName = new Label(this, SWT.CENTER);
		lblLeagueName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblLeagueName = new FormData();
		fd_lblLeagueName.left = new FormAttachment(0, 470);
		lblLeagueName.setLayoutData(fd_lblLeagueName);
		lblLeagueName.setText("League Name: ");
		
		text = new Text(this, SWT.BORDER);
		fd_lblLeagueName.top = new FormAttachment(text, 1, SWT.TOP);
		fd_lblLeagueName.right = new FormAttachment(text, -8);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.RIGHT);
		fd_text.top = new FormAttachment(lblUpdateLeagueInformation, 6);
		fd_text.left = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.LEFT);
		text.setLayoutData(fd_text);
		
		Label lblSport = new Label(this, SWT.CENTER);
		lblSport.setText("Sport: ");
		lblSport.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblSport = new FormData();
		fd_lblSport.left = new FormAttachment(0, 526);
		lblSport.setLayoutData(fd_lblSport);
		
		text_1 = new Text(this, SWT.BORDER);
		fd_lblSport.top = new FormAttachment(text_1, 1, SWT.TOP);
		fd_lblSport.right = new FormAttachment(text_1, -6);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(text, 6);
		fd_text_1.left = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.LEFT);
		fd_text_1.right = new FormAttachment(100, -568);
		text_1.setLayoutData(fd_text_1);
		
		text_2 = new Text(this, SWT.BORDER);
		FormData fd_text_2 = new FormData();
		fd_text_2.left = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.LEFT);
		fd_text_2.right = new FormAttachment(lblUpdateLeagueInformation, 0, SWT.RIGHT);
		fd_text_2.top = new FormAttachment(text_1, 6);
		text_2.setLayoutData(fd_text_2);
		
		Label lblDescription = new Label(this, SWT.CENTER);
		lblDescription.setText("Description: ");
		lblDescription.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		FormData fd_lblDescription = new FormData();
		fd_lblDescription.top = new FormAttachment(text_2, 1, SWT.TOP);
		fd_lblDescription.right = new FormAttachment(text_2, -1);
		fd_lblDescription.left = new FormAttachment(0, 497);
		lblDescription.setLayoutData(fd_lblDescription);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(text_2, 6);
		fd_btnNewButton.right = new FormAttachment(100, -648);
		fd_btnNewButton.left = new FormAttachment(0, 650);
		fd_btnNewButton.bottom = new FormAttachment(100, -367);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Update");
		
		setSize(1396, 678);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
