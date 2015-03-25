package appView;

import javax.swing.JButton;

import appModel.*;

public class DeviceButton extends JButton {

	private String icon;
	private String name;
	private Device myDevice;
	private DeviceButtonSet dbSet;	
	
	public DeviceButton(Device dev, DeviceButtonSet dbS)
	{
		name = dev.getName();
		myDevice = dev;
		dbSet = dbS;
		if(getDeviceState())
			icon = dbSet.getActiveIcon();
		else
			icon = dbSet.getDisabledIcon();
		setText(icon);
	}
	
	public boolean onPress()
	{
		if(myDevice instanceof ToggleDevice)
		{
			boolean state = ((ToggleDevice)myDevice).getState();
			if(((ToggleDevice)myDevice).setState())
			{
				if(state)
					icon = dbSet.getDisabledIcon();
				else
					icon = dbSet.getActiveIcon();
				setText(icon);
				return true;
			}
		}
		return false;
	}
	
	public boolean getDeviceState()
	{
		return ((ToggleDevice)myDevice).getState();
	}
	
	public String getDeviceName()
	{
		return myDevice.getName();
	}
	
	public int getDeviceID()
	{
		return myDevice.getDeviceID();
	}
	
	public void refresh()
	{
		boolean old = ((ToggleDevice)myDevice).getState();
		myDevice.refresh();
		if(old != ((ToggleDevice)myDevice).getState())
		{
			if(old)
				icon = dbSet.getDisabledIcon();
			else
				icon = dbSet.getActiveIcon();
			setText(icon);
		}
	}
}
