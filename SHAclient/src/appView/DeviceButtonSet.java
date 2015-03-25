package appView;
import java.util.*;

import appControl.*;
import appModel.*;

public class DeviceButtonSet {

	private String activeIcon;
	private String disabledIcon;
	private ArrayList<DeviceButton> dButtons;
	private NavMenu myMenu;
	private MenuController mController;
	
	public DeviceButtonSet(NavMenu menu, String dtype, MenuController mControl)
	{
		myMenu = menu;
		mController = mControl;
		
		
		//Temporary text description instead of icons for device status
		switch(dtype)
		{
			case "Door": activeIcon = "Locked";
				disabledIcon ="Unlocked";		
			break;
			case "Garage": activeIcon = "Locked";
				disabledIcon ="Unlocked";
			break;
			default: activeIcon = "On";
				disabledIcon = "Off";
		}
		ArrayList<Device> tempD = mController.getDeviceController().getDeviceOfType(dtype);
		dButtons = new ArrayList<DeviceButton>(tempD.size());
		for(Device d : tempD)
			dButtons.add(new DeviceButton(d,this));
		
		
	}
	
	public void refresh()
	{
		for(DeviceButton db : dButtons)
			db.refresh();
	}
	
	public ArrayList<DeviceButton> getDeviceButtons()
	{
		return dButtons;
	}
	
	public int getSize()
	{
		return dButtons.size();
	}
	
	public DeviceButton getButton(int at)
	{
		return dButtons.get(at);
	}
	
	public String getActiveIcon()
	{
		return activeIcon;
	}
	
	public String getDisabledIcon()
	{
		return disabledIcon;
	}
}

