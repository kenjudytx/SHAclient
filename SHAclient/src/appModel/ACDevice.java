package appModel;

import appControl.DeviceController;

public class ACDevice extends Device {
	
	public ACDevice(int id, String dtype, String n, DeviceController d)
	{
		super(id,dtype,n,d);
	}
	
	public boolean refresh()
	{
		return false;
	}

}
