package appControl;

import java.util.*;
import appModel.*;

public class DeviceController extends Controller {

	private ArrayList<Device> myDevices;
	
	public DeviceController(Connect con, User us)
	{
		myUser = us;
		conController = con;
		
		String[] tempRead = con.getDevicesInfo();
		
		int k=0;
		myDevices = new ArrayList<Device>(tempRead.length-1);
		String delim = "[@]";
		while(!tempRead[k].equals("end"))
		{
			String[] deviceInfo = tempRead[k].split(delim);
			if(deviceInfo[1].equals("Door")||deviceInfo[1].equals("Garage")
					||deviceInfo[1].equals("Light")||deviceInfo[1].equals("Faucet")
					||deviceInfo[1].equals("Sprinkler"))
			myDevices.add(new ToggleDevice(Integer.parseInt(deviceInfo[0]),
												deviceInfo[1], deviceInfo[2], deviceInfo[3], this));
			k++;
			System.out.println("On Line: " + k +" with "+tempRead[k]);
		}
		
	}
	
	public ArrayList<Device> getDeviceOfType(String reqT)
	{
		ArrayList<Device> ofType = new ArrayList<Device>();
		for(Device d : myDevices)
			if(d.getType().equals(reqT))
				ofType.add(d);
		return ofType;
	}
	
	public boolean modifyDevice(int dID)
	{	
		return conController.modifyDevice(dID);
	}
	
	public int updateDevice(int dID)
	{
		return conController.updateDevice(dID);
	}
}
