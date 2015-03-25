package appModel;

import appControl.DeviceController;

public class CameraDevice extends Device {
	
	public CameraDevice(int id, String dtype, String n, DeviceController d)
	{
		super(id,dtype,n,d);
	}

	public boolean refresh()
	{
		return false;
	}

}
