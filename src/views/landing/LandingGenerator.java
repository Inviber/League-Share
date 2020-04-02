package views.landing;

import org.eclipse.swt.widgets.Composite;

import views.GUIShell;

public class LandingGenerator {
	
	public LandingGenerator(Composite parent, int style, GUIShell shell)
	{
		shell.setDisplayedComposite(generateComposite(parent, style, shell));
	}
	
	public LandingComposite generateComposite(Composite parent, int style, GUIShell shell) 
	{
		LandingComposite landingComposite = new LandingComposite(parent, style, shell);
		return landingComposite;
	}
}
