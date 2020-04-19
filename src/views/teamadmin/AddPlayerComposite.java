package views.teamadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AddPlayerComposite extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddPlayerComposite(Composite parent, int style) {
		super(parent, style);
		
		setSize(863, 521);
		
		Label lblAddPlayer = new Label(this, SWT.NONE);
		lblAddPlayer.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblAddPlayer.setAlignment(SWT.CENTER);
		lblAddPlayer.setBounds(364, 95, 119, 25);
		lblAddPlayer.setText("Add Player");
		
		Label lblFirstName = new Label(this, SWT.NONE);
		lblFirstName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblFirstName.setBounds(230, 122, 91, 25);
		lblFirstName.setText("First Name :");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(327, 124, 195, 21);
		
		Label lblLastName = new Label(this, SWT.NONE);
		lblLastName.setText("Last Name :");
		lblLastName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblLastName.setBounds(230, 153, 91, 25);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(327, 155, 195, 21);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("Added Player");
			}
		});
		btnNewButton.setBounds(374, 182, 98, 37);
		btnNewButton.setText("Add");
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
