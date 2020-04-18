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
		setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 130);
		fd_lblNewLabel.right = new FormAttachment(0, 437);
		fd_lblNewLabel.top = new FormAttachment(0, 100);
		fd_lblNewLabel.left = new FormAttachment(0, 325);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblNewLabel.setText("Players");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.bottom = new FormAttachment(0, 422);
		fd_scrolledComposite.right = new FormAttachment(0, 598);
		fd_scrolledComposite.top = new FormAttachment(0, 136);
		fd_scrolledComposite.left = new FormAttachment(0, 174);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
