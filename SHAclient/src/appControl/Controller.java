package appControl;
import javax.swing.JOptionPane;

import appModel.*;

public abstract class Controller {
	
	protected User myUser;
	protected Connect conController;

	public void errorNotification(int errorNum)
	{
		JOptionPane.showMessageDialog(null,"Test Error");
	}
}
