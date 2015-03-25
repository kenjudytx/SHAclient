package appModel;

import appControl.*;

public class ToggleDevice extends Device {
	
	private boolean state;
	
	public ToggleDevice(int id, String dtype, String n, String is, DeviceController d)
	{
		super(id,dtype,n,d);
		if(is.equals("1"))
			state = true;
		else 
			state = false;
	}
	
	public boolean getState()
	{
		return state;
	}
	
	public boolean setState()
	{
		if(dController.modifyDevice(getDeviceID()))
		{
			state = !state;
			return true;
		}
		return false;
	}
	
	public boolean refresh()
	{
		int rc = dController.updateDevice(getDeviceID());
		
		if(rc == 1)
			state = true;
		else if(rc == 0)
			state = false;
		
		return state;
	}

}
