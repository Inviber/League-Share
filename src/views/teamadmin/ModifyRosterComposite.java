package views.teamadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class ModifyRosterComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ModifyRosterComposite(Composite parent, int style) {
		super(parent, style);
		
		setSize(863, 521);
		
		Label lblPlayers = new Label(this, SWT.NONE);
		lblPlayers.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblPlayers.setAlignment(SWT.CENTER);
		lblPlayers.setBounds(380, 79, 101, 25);
		lblPlayers.setText("Players");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(184, 110, 506, 300);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
