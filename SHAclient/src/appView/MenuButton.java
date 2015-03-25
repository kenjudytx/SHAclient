package appView;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.*;


public class MenuButton extends JButton {

	private String icon;
	private String name;
	private Menu myMenu;
	
	public MenuButton(Menu mMenu, String bname, String bicon)
	{
		super();
		myMenu = mMenu;
		icon = bicon;
		name = bname;
		setText(name);
	}
	
	public MenuButton(Menu mMenu, String bname, String bicon,int xSize, int ySize)
	{
		super();
		myMenu = mMenu;
		icon = bicon;
		name = bname;
		setText(name);
		this.setPreferredSize(new Dimension(xSize,ySize));
	}
	
	public void onPress()
	{
		switch(name)
		{
		case "Login": myMenu.mController.verifyUser(((LoginMenu)myMenu).getUser(),((LoginMenu)myMenu).getPW());
			break;
		default: (myMenu.mController.changeMenu(name)).refreshMenu();
			break;
		}
	}
}
