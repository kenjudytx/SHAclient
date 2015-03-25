package appModel;

import appControl.*;

public abstract class Device {
	
	private String name;
	private int deviceID;
	private String type;
	protected DeviceController dController;
	
	public Device(int id, String dtype, String n, DeviceController d)
	{
		name = n;
		type = dtype;
		deviceID = id;
		dController = d;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getDeviceID()
	{
		return deviceID;
	}
	
	public String getType()
	{
		return type;
	}
	
	public abstract boolean refresh();

}
