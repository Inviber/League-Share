package views.LeagueAdmin;

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
		setLayout(new FormLayout());
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.top = new FormAttachment(0, 151);
		fd_scrolledComposite.left = new FormAttachment(0, 145);
		fd_scrolledComposite.bottom = new FormAttachment(0, 344);
		fd_scrolledComposite.right = new FormAttachment(0, 624);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Label lblTeams = new Label(this, SWT.NONE);
		lblTeams.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTeams.setAlignment(SWT.CENTER);
		FormData fd_lblTeams = new FormData();
		fd_lblTeams.top = new FormAttachment(scrolledComposite, -31, SWT.TOP);
		fd_lblTeams.bottom = new FormAttachment(scrolledComposite, -6);
		fd_lblTeams.left = new FormAttachment(0, 357);
		fd_lblTeams.right = new FormAttachment(100, -371);
		lblTeams.setLayoutData(fd_lblTeams);
		lblTeams.setText("Teams");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
