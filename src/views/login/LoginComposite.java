package views.login;
import org.eclipse.swt.widgets.Composite;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Text;

import database.DatabaseHelper;
import user.Account;
import user.AccountDBInterator;
import user.AccountGenerator;
import views.GUIShell;
import views.landing.LandingComposite;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;

public class LoginComposite extends Composite {
	private Text text_1;
	private Text text;
	//private Composite loginComposite = this;
	Account currentUser;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public LoginComposite(Composite parent, int style, GUIShell shell) {
		super(parent, SWT.NONE);
		setLayout(null);
		
		Button loginButton = new Button(this, SWT.CENTER);
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
//				System.out.println("Username: " + text.getText() + " Password: " + text_1.getText());
			
				
				//WILL NEED TO ADD INPUT VALIDATION
				
				try 
				{
					currentUser = shell.getAccountGenerator().generateAccount(text.getText(), text_1.getText());
					if (currentUser != null)
					{						
						text.setText("");
						text_1.setText("");
						
						shell.disposeDisplayedComposite();
						LandingComposite landingComposite = new LandingComposite(shell, SWT.NONE, shell);
						shell.setDisplayedComposite(landingComposite);
					}
					else
					{
						System.out.println("Invalid password");
					}
				}
				catch (NullPointerException nullError)
				{
					System.out.println(nullError.getMessage());
				}				
			}
		});
		loginButton.setBounds(590, 380, 93, 29);
		loginButton.setText("Login");
		loginButton.setBackground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_BLACK));
		loginButton.setForeground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		text_1 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(528, 334, 214, 40);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(528, 288, 214, 40);
		
		Button rgstrButton = new Button(this, SWT.NONE);
		rgstrButton.setBounds(590, 415, 93, 29);
		rgstrButton.setText("Register");
		rgstrButton.setBackground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_BLACK));
		rgstrButton.setForeground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		Label passwdLabel = new Label(this, SWT.NONE);
		passwdLabel.setBounds(452, 345, 70, 17);
		passwdLabel.setText("Password");
		passwdLabel.setBackground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_BLACK));
		passwdLabel.setForeground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		
		Label userNameLabel = new Label(this, SWT.NONE);
		userNameLabel.setText("Username");
		userNameLabel.setBounds(452, 299, 70, 17);
		userNameLabel.setBackground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_BLACK));
		userNameLabel.setForeground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		
		String imagepath = new File("src/images/WelcomeSign.png").getAbsolutePath();
	    Image welcomeSign = new Image(shell.getDisplay(), imagepath);
		Canvas canvas = new Canvas(this, SWT.NONE);
		canvas.setBounds(10, 10, 1305, 272);
		System.out.println(canvas.getBounds());
		canvas.setLayout(new FillLayout());
		
		this.setBackground(getDisplay().getCurrent().getSystemColor(SWT.COLOR_BLACK));
		
		canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(welcomeSign, 0, 0, welcomeSign.getImageData().width,welcomeSign.getImageData().height, 0,0, 
                		canvas.getBounds().width - 5 *(canvas.getBounds().x), canvas.getClientArea().height); 
            }
        });
		

	    
		
		setTabList(new Control[]{text, text_1, loginButton, rgstrButton});
		
		rgstrButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
//				System.out.println("Username: " + text.getText() + " Password: " + text_1.getText());
			
				
				
				
				if (!shell.getAccountGenerator().getAccountDBInterator().existingAccount(text.getText())) // if account doesn't exist.
				{
					// create account
					shell.getAccountGenerator().getAccountDBInterator().createUser(text.getText(), text_1.getText());
					//WILL NEED TO ADD INPUT VALIDATION
					currentUser = shell.getAccountGenerator().generateAccount(text.getText(), text_1.getText());

					text.setText("");
					text_1.setText("");
					
					shell.disposeDisplayedComposite();
					LandingComposite landingComposite = new LandingComposite(shell, SWT.NONE, shell);
					shell.setDisplayedComposite(landingComposite);
				}
				else // if it does exist.
				{
					System.out.println("This account already exists.");
					// exists, don't create.
				}
				
				
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
