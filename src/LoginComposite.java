import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LoginComposite extends Composite {
	private Text text_1;
	private Text text;
	
	Account currentUser;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoginComposite(Composite parent, int style, GUIShell shell) {
		super(parent, SWT.NONE);
		setLayout(null);
		
		Button btnNewButton = new Button(this, SWT.CENTER);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				System.out.println("Username: " + text.getText() + " Password: " + text_1.getText());

				//WILL NEED TO ADD INPUT VALIDATION
				currentUser = new Account(text.getText(), text_1.getText());
				shell.setAccount(currentUser);
				shell.setSuccessfulLogin(true);
				
				text.setText("");
				text_1.setText("");
			}
		});
		btnNewButton.setBounds(590, 380, 93, 29);
		btnNewButton.setText("Login");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(528, 334, 214, 40);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(528, 288, 214, 40);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setBounds(590, 415, 93, 29);
		btnNewButton_1.setText("Register");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(452, 345, 70, 17);
		lblNewLabel_1.setText("Password");
		
		Label lblNewLabel_1_1 = new Label(this, SWT.NONE);
		lblNewLabel_1_1.setText("Username");
		lblNewLabel_1_1.setBounds(452, 299, 70, 17);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
