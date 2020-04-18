package views.leagueadmin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;


public class EditTeamsComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EditTeamsComposite(Composite parent, int style) {
		super(parent, style);
		
		setSize(863, 521);
		
		Label lblTeams = new Label(this, SWT.NONE);
		lblTeams.setAlignment(SWT.CENTER);
		lblTeams.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTeams.setBounds(371, 107, 73, 25);
		lblTeams.setText("Teams");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(171, 139, 493, 231);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
