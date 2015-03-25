package appView;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import appControl.*;
import appModel.*;

public class NavMenu extends Menu {

	
	public NavMenu(MenuController myControl, MenusAvailable mAvail, String title)
	{
		super(myControl, mAvail, title);
		
		myButtons = new ArrayList<MenuButton>();
		Dimension dim = mController.frame.getContentPane().getSize();
		int dimX = dim.width;
		int dimY = dim.height;
		
		
		//Creates home menu
		if(title.equals("Home"))
		{
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{25, 0, 0, 25};
			gridBagLayout.rowHeights = new int[]{10, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			//Menu Label
			name.setPreferredSize(new Dimension(dimX-60,(dimY-60)/4));
			name.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 1;
			c.gridy = 1;
			add(name, c);
			
												//Creates navigation buttons for home menu
			myButtons.add(new MenuButton(this,"Doors",null,(dimX-60)/2,(dimY-60)/4));	//disables any button that corresponds to
			if(!mAvailable.isAvailable("Doors"))										//a device that is not currently installed
				myButtons.get(0).setEnabled(false);
			myButtons.add(new MenuButton(this,"Lights",null,(dimX-60)/2,(dimY-60)/4));
			if(!mAvailable.isAvailable("Lights"))
				myButtons.get(1).setEnabled(false);
			myButtons.add(new MenuButton(this,"Water",null,(dimX-60)/2,(dimY-60)/4));
			if(!mAvailable.isAvailable("Water"))
				myButtons.get(2).setEnabled(false);
			myButtons.add(new MenuButton(this,"A/C",null,(dimX-60)/2,(dimY-60)/4));
			if(!mAvailable.isAvailable("A/C"))
				myButtons.get(3).setEnabled(false);
			myButtons.add(new MenuButton(this,"Security",null,(dimX-60),(dimY-60)/4));
			if(!mAvailable.isAvailable("Security"))
				myButtons.get(4).setEnabled(false);
			
			//Doors
			c = new GridBagConstraints();
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 1;
			c.gridy = 2;
			add(myButtons.get(0), c);
			
			//Lights
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 2;
			c.gridy = 2;
			add(myButtons.get(1), c);
			
			
			//Water
			c.insets = new Insets(0, 0, 5, 5);
			c.gridx = 1;
			c.gridy = 3;
			add(myButtons.get(2), c);
		
			//A/C
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 2;
			c.gridy = 3;
			add(myButtons.get(3), c);
			
			
			//Security
			c.gridwidth = 2;
			c.gridx = 1;
			c.gridy = 4;
			add(myButtons.get(4), c);
		} //End of home menu creation
		
		
		//Creates Water SubMenu
		if(title.equals("Water"))
		{
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{25, 0, 0, 25};
			gridBagLayout.rowHeights = new int[]{10, 0, 0, 10};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
			
			//Menu Label
			name.setPreferredSize(new Dimension(dimX-60,(dimY-60)/4));
			name.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0, 0, 0, 0);
			c.gridx = 1;
			c.gridy = 1;
			add(name, c);
			
			myButtons.add(new MenuButton(this,"Home",null,(dimX-60)/3,(dimX-60)/3));	//Creates navigation buttons for home menu
			myButtons.add(new MenuButton(this,"Faucets",null,(dimX-60)/2,(dimY-60)*3/4));	//disables any button that corresponds to
			if(!mAvailable.isAvailable("Faucets"))											//a device that is not currently installed
				myButtons.get(0).setEnabled(false);
			myButtons.add(new MenuButton(this,"Sprinklers",null,(dimX-60)/2,(dimY-60)*3/4));
			if(!mAvailable.isAvailable("Sprinklers"))
				myButtons.get(1).setEnabled(false);
			
			//Home
			c = new GridBagConstraints();
			c.insets = new Insets(0, 0, 0, 0);
			c.gridx = 2;
			c.gridy = 1;
			add(myButtons.get(0), c);
			
			//Faucets
			c.insets = new Insets(0, 0, 0, 5);
			c.gridx = 1;
			c.gridy = 2;
			add(myButtons.get(1), c);
			
			//Sprinklers
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 2;
			c.gridy = 2;
			add(myButtons.get(2), c);
		} //End of Water Menu Creation
		
		if(title.equals("Doors")||title.equals("Lights")||title.equals("Faucets")||
				title.equals("Sprinklers"))
		{
			
			dbSet = new ArrayList<DeviceButtonSet>();
			int[] numI = new int[]{0,0};
			int numRows = 3;
			
			//Create the DeviceButtonSets for Doors Menu
			if(title.equals("Doors"))
			{
				dbSet.add(new DeviceButtonSet(this,"Door",mController));
				dbSet.add(new DeviceButtonSet(this,"Garage",mController));
				numI[0] = dbSet.get(0).getSize();
				numI[1] = dbSet.get(1).getSize();
				numRows = numI[0] + numI[1] + 3;
			}
			
			//Create the DeviceButtonSets for Lights Menu
			if(title.equals("Lights"))
			{
				dbSet.add(new DeviceButtonSet(this,"Light",mController));
				numI[0] = dbSet.get(0).getSize();
				numRows = numI[0] + 3;
			}
			
			//Create the DeviceButtonSets for Faucets Menu
			if(title.equals("Faucets"))
			{
				dbSet.add(new DeviceButtonSet(this,"Faucet",mController));
				numI[0] = dbSet.get(0).getSize();
				numRows = numI[0] + 3;
			}
			
			//Create the DeviceButtonSets for Sprinklers Menu
			if(title.equals("Sprinklers"))
			{
				dbSet.add(new DeviceButtonSet(this,"Sprinkler",mController));
				numI[0] = dbSet.get(0).getSize();
				numRows = numI[0] + 3;
			}
		
			JPanel jp = new JPanel();
			jp.setPreferredSize(dim);
			

			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{25, 0, 0, 25};
			//gridBagLayout.rowHeights = new int[]{10, 0,0,0, 0, 10};
			gridBagLayout.rowHeights = new int[numRows];
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[numRows];
			gridBagLayout.rowWeights[numRows - 1] = Double.MIN_VALUE;
			jp.setLayout(gridBagLayout);
			
			
			
			//Add Menu Label
			name.setPreferredSize(new Dimension(dimX-60,(dimY-60)/4));
			name.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(0, 0, 5, 0);
			c.weightx = 1.0;
			c.gridx = 1;
			c.gridy = 1;
			c.anchor = GridBagConstraints.WEST;
			jp.add(name, c);
			
			//Create and Add Menu Home button
			myButtons.add(new MenuButton(this,"Home",null,(dimX-60)/3,(dimX-60)/3));
			c.insets = new Insets(0, 0, 5, 0);
			c.gridx = 2;
			c.gridy = 1;
			c.anchor = GridBagConstraints.EAST;
			jp.add(myButtons.get(0), c);
			
			for(int k = 0; k < numI[0]; k++)
			{
				//Create and Add Device Label
				c.anchor = GridBagConstraints.WEST;
				c.insets = new Insets(5, 0, 0, 0);
				c.gridx = 1;
				c.gridy = k+2;
				jp.add(new JLabel(((dbSet.get(0)).getButton(k)).getDeviceName()),c);
				
				//Add DeviceButton
				c.anchor = GridBagConstraints.EAST;
				c.insets = new Insets(5, 0, 0, 0);
				c.gridx = 2;
				c.gridy = k+2;
				jp.add((dbSet.get(0)).getButton(k),c);
			}
			
			/*
			 * Need to add buttons for lock all unlock all
			 */
			
			for(int k = 0; k < numI[1]; k++)
			{
				//Create and Add Device Label
				c.weightx = 1.0;
				c.anchor = GridBagConstraints.WEST;
				c.insets = new Insets(5, 0, 0, 0);
				c.gridx = 1;
				c.gridy = k + numI[0] +2;
				jp.add(new JLabel(((dbSet.get(1)).getButton(k)).getDeviceName()),c);
				
				//Add DeviceButton
				c.anchor = GridBagConstraints.EAST;
				c.insets = new Insets(5, 0, 0, 0);
				c.gridx = 2;
				c.gridy = k + numI[1] +2;
				jp.add((dbSet.get(1)).getButton(k),c);
			}
			
			/*
			 *	Need to add buttons for open all close all
			 * 	Need to add scrollable area if devices exceed screen size
			 */
			
			/*JScrollPane sPanel = new JScrollPane(jp,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sPanel.setPreferredSize(dim);
			add(sPanel,BorderLayout.CENTER); */
			
			add(jp);
			
			for(DeviceButtonSet ds : dbSet)
			{
				ArrayList<DeviceButton> db = ds.getDeviceButtons();
				for(DeviceButton d : db)
					d.addActionListener(this);
			}
				
			
		} //End of Faucet/Sprinkler/Door/Light Menu Creation 
		
		for(MenuButton mb : myButtons)
			mb.addActionListener(this);
		
	} //End of NavMenu Constructor
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof MenuButton)
			((MenuButton)e.getSource()).onPress();
		if(e.getSource() instanceof DeviceButton)
			((DeviceButton)e.getSource()).onPress();
	}
}
