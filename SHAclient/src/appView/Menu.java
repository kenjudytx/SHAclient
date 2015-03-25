package appView;
import javax.swing.*;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import appControl.*;
import appModel.*;

public abstract class Menu extends JPanel implements ActionListener{

	protected JLabel name;
	protected ArrayList<DeviceButtonSet> dbSet;
	protected ArrayList<MenuButton> myButtons;
	protected MenuController mController;
	protected MenusAvailable mAvailable;
	
	public Menu(MenuController myControl) {
		super();
		mController = myControl;
	}
	
	public Menu(MenuController myControl, MenusAvailable mAvail, String title) {
		super();
		mController = myControl;
		mAvailable = mAvail;
		name = new JLabel(title);
		
	}
	
	public String getName()
	{
		return name.getText();
	}
	
	private void refreshDevices()
	{
		if(dbSet != null)
			for(DeviceButtonSet d : dbSet)
				d.refresh();
	}
	
	public void refreshMenu()
	{
		/*
		 * include timer which refreshes menu every X seconds
		 */
		refreshDevices();
	}
	
	
}
