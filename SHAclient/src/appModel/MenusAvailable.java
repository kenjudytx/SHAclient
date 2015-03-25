package appModel;
import appControl.*;

import java.util.ArrayList;

import appView.*;

public class MenusAvailable {
	
	private boolean[] menulist;
	/* menulist
	 * 0 - Doors
	 * 1 - Lights
	 * 2 - Water
	 * 3 - A/C
	 * 4 - Security
	 * 5 - Faucets
	 * 6 - Sprinklers
	 * 7 - Cameras
	 */
	private String activeMenu;
	private MenuController mController;
	private ArrayList<Menu> myMenus;

	public MenusAvailable(MenuController myControl, boolean[] avail)
	{
		mController = myControl;
		menulist = new boolean[8];
		System.arraycopy(avail,0,menulist,0,8);
		myMenus = new ArrayList<Menu>();

		myMenus.add(new NavMenu(mController, this, "Home"));
		if(menulist[0])
			myMenus.add(new NavMenu(mController, this, "Doors"));
		if(menulist[1])
			myMenus.add(new NavMenu(mController, this, "Lights"));
		if(menulist[2])
			myMenus.add(new NavMenu(mController, this, "Water"));
		if(menulist[3])
			myMenus.add(new NavMenu(mController, this, "A/C"));
		if(menulist[4])
			myMenus.add(new NavMenu(mController, this, "Security"));
		if(menulist[5])
			myMenus.add(new NavMenu(mController, this, "Faucets"));
		if(menulist[6])
			myMenus.add(new NavMenu(mController, this, "Sprinklers"));
		if(menulist[7])
			myMenus.add(new NavMenu(mController, this, "Cameras"));
	}
	
	//Returns a boolean true if there is a device of the listed type
	//installed in the user's home system.
	public boolean isAvailable(String menu)	
	{
		switch(menu)
		{
		case "Home": return true;
		case "Doors": return menulist[0];
		case "Lights": return menulist[1];
		case "Water": return menulist[2];
		case "A/C": return menulist[3];
		case "Security": return menulist[4];
		case "Faucets": return menulist[5];
		case "Sprinklers": return menulist[6];
		case "Cameras": return menulist[7];
		}
		return false;
	}
	
	public Menu setMenu(Menu newMenu)
	{
		myMenus.add(newMenu);
		return newMenu;
	}
	
	public Menu getMenu(String name)
	{
		for(Menu m : myMenus)
		{
			if(m.getName().equals(name))
				return m;
		}
		return null;
	}
	
	public void updateAvailable(String menu)
	{
		
	}
	
	public void changeActive(String newActive)
	{
		activeMenu = newActive;
	}
}
