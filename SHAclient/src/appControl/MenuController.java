package appControl;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import appModel.*;
import appView.*;

public class MenuController extends Controller {

	private ArrayList<Menu> myMenus;
	private MenusAvailable mAvailable;
	public JPanel current;
	public JFrame frame;
	private DeviceController dController;
	
	MenuController(JFrame myframe)
	{
		conController = new Connect();
		frame = myframe;
		myMenus = new ArrayList<Menu>();
		myMenus.add(new LoginMenu(this));
		current = new JPanel(new GridLayout(0,1));
		current.add(myMenus.get(0));
		frame.add(current);
		frame.pack();
		frame.setSize(270,480);
		frame.setVisible(true);
	}
	
	public Menu changeMenu(String menu)
	{
		mAvailable.changeActive(menu);
		current.removeAll();
		current.revalidate();
		current.repaint();
		
		Menu newMenu = mAvailable.getMenu(menu);
		
		current.add(newMenu);
		
		frame.pack();
		frame.setSize(270,480);
		frame.setVisible(true);
		return newMenu;
	}
	
	public boolean updateMenu(String menu)
	{
		return false;
	}
	
	public void verifyUser(String userName, char[] userPW)
	{
		int uID = conController.getUserID(userName,userPW);
		if(uID == 0)
		{
			JOptionPane.showMessageDialog(null,"Bad username name or password");
		}
		else{
			myUser = new User(uID);
			conController.setUser(myUser);
			dController = new DeviceController(conController,myUser);
			mAvailable = new MenusAvailable(this,conController.getSystemInfo());
			current.removeAll();
			current.revalidate();
			current.repaint();
			current.add(mAvailable.getMenu("Home"));
			frame.pack();
			
			frame.setSize(270,480);
			frame.setVisible(true);
		}
	}
	
	public DeviceController getDeviceController()
	{
		return dController;
	}
}
