package appControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import appModel.*;

public class Connect extends Controller{

	private String homeIP;
	private String serverIP;
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket sock;
	private int serverPort;
	private int homePort;
	
	private boolean localDebug;
	private boolean mySQLdisable;
	
	
	/*	Basic setup information and debugging
	 * 
	 */
	public Connect()
	{
		//uses fake information to test GUI
		//does not connect to HSAserver or HSAhome
		localDebug = false;
		
		//disable HSAserver with required MySQL DB
		//still connect to HSAhome hosted on local machine
		//or elsewhere if homeIP is changed
		mySQLdisable = true;
		homeIP = "127.0.0.1";
		
		//IP for server running HSAserver app & MySQL DB to get user details
		serverIP = new String("192.168.1.11");
		serverPort = 5000;
		
		homePort = 5100;
	}
	
	public void setUser(User u)
	{
		myUser = u;
	}
	
	/*	Connect to server with user DB and retrieves the IP 
	 * 	of the user's home system
	 * 
	 * 	Missing proper password handling verification
	 */
	public int getUserID(String userName, char[] password)
	{
		//Temporary password handling
		//Need to add actual password handling
		char[] cPass = {'p','a','s','s','w','o','r','d'};
		if(!Arrays.equals(password, cPass))
			return 0;
		
		if(localDebug||mySQLdisable)
			return 434324;
		
		//Connects to server and queries DB
		//data should be returned in "userID@IP" formate
		String reponse = null;
		try {
			setUpNetworking(serverIP,serverPort);
			writer.println("search");
			writer.println(userName);
			writer.flush();
			reponse = reader.readLine();
		} catch(SocketTimeoutException ex) {
			JOptionPane.showMessageDialog(null,"Timeout: Server not responding");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			try {
				writer.close();
				reader.close();
				sock.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		String delim ="[@]";
		String[] tokens = reponse.split(delim);
		
		if(tokens.length == 2) //Set userID and homeIP if found
		{
			homeIP = tokens[1];
			return Integer.parseInt(tokens[0]);
		}
		else  //Return 0 for user not found in DB
			return 0;
	}
	
	
	/*
	 *	Starts socket networking
	 *	reader, writer, sock must be closed by caller
	 */
	private void setUpNetworking(String networkIP, int networkPort)
	{
		try{
			sock = new Socket(networkIP, networkPort);
			sock.setSoTimeout(5000);
			InputStreamReader streamR = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamR);
			writer = new PrintWriter(sock.getOutputStream());
		}catch(IOException ex){
			System.out.println("Can't connect to server!");
			ex.printStackTrace();
		}
	}
	
	public boolean[] getSystemInfo()
	{
		if(localDebug)
			return new boolean[]{true,true,true,true,false,true,true,false};
	
		String[] response = new String[8];
		try {
			setUpNetworking(homeIP,homePort);
			writer.println("systemInfo");
			writer.flush();
			for(int k = 0; k < 8; k++)
				response[k] = reader.readLine();
		} catch(SocketTimeoutException ex) {
			JOptionPane.showMessageDialog(null,"Timeout: Server not responding");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			try {
				writer.close();
				reader.close();
				sock.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		boolean[] parsed = new boolean[8];
		for(int k = 0; k < 8; k++)
			parsed[k] = Boolean.parseBoolean(response[k]);
		
		return parsed; 
	}
	
	
	/*
	 * 	Connects to home system using homeIP 
	 *	Retrieves information on all devices installed in home system 
	 */
	public String[] getDevicesInfo()
	{
		if(localDebug)
		{
			String[] tempRead = new String[]
				{"342343@Door@Front Door@1","11241@Door@Back Door@1","934333@Garage@Left Garage@1","32324@Garage@Right Garage@0",
				"324324@Light@Kitchen@1","3222324@Light@Living Room@0","3121224@Light@Dinning Room@1","39924@Light@Front Exterior@0","124324@Light@Master Bath@1",
				"32767324@Light@Master Bed@0","3242174@Light@Study@0","477724@Light@Guest Bedroom@1","3241004@Light@Hallway@1","end"};
			return tempRead;
		}
		
		String[] response = null;
		try {
			setUpNetworking(homeIP,homePort);
			writer.println("allDevices");
			writer.flush();
			int num = Integer.parseInt(reader.readLine());
			System.out.println("Device #: "+num);
			response = new String[num + 1];
			for(int k = 0; k < num; k++)
				response[k] = reader.readLine();
			response[num] = "end";
			return response;
		} catch(SocketTimeoutException ex) {
			JOptionPane.showMessageDialog(null,"Timeout: Server not responding");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			try {
				writer.close();
				reader.close();
				sock.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return response;
	}
	
	/*
	 * 	Connects to home system using homeIP
	 * 	Toggles a single device
	 */
	public boolean modifyDevice(int dID)
	{
		int returnCode;
		
		try {
			setUpNetworking(homeIP,homePort);
			writer.println("modifyDevice");
			writer.println(dID);
			writer.flush();
			returnCode = Integer.parseInt(reader.readLine());
			if(returnCode == 1)
				return true;
			else 
			{
				errorNotification(returnCode);
				return false;
			}
				
		} catch(SocketTimeoutException ex) {
			JOptionPane.showMessageDialog(null,"Timeout: Server not responding");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			try {
				writer.close();
				reader.close();
				sock.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
				
		return false;
		
	}
	
	/*
	 * 	Connects to home system using homeIP
	 * 	Queries the status of a single device
	 */
	
	public int updateDevice(int dID)
	{
		int returnCode;
		
		try {
			setUpNetworking(homeIP,homePort);
			writer.println("updateDevice");
			writer.println(dID);
			writer.flush();
			returnCode = Integer.parseInt(reader.readLine());
			if(returnCode == 1 || returnCode == 0)
				return returnCode;
			else 
			{
				errorNotification(returnCode);
				return returnCode;
			}
				
		} catch(SocketTimeoutException ex) {
			JOptionPane.showMessageDialog(null,"Timeout: Server not responding");
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			try {
				writer.close();
				reader.close();
				sock.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		//Return fail to error code
		//3 temporary value
		return 3;
		
	}
	
	
}
