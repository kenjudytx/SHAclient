package appView;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import appControl.*;
import appModel.*;

public class LoginMenu extends Menu implements ActionListener {

	private JTextField user;
	private JPasswordField pw;
	private MenuButton login;
	private JPanel myPanel;
	private JLabel text1;
	private JLabel text2;
	private ImageIcon titleI;
	
	public LoginMenu(MenuController myControl)
	{
		super(myControl);
		titleI = new ImageIcon("./src/resources/logo.png");
		name = new JLabel(titleI);
		user = new JTextField(20);
		pw = new JPasswordField(20);
		login = new MenuButton(this,"Login",null);
		login.addActionListener(this);
		text1 = new JLabel("User Name");
		text2 = new JLabel("Password");
		myPanel = new JPanel(new GridLayout(0,1));
		myPanel.add(text1);
		myPanel.add(user);
		myPanel.add(text2);
		myPanel.add(pw);
		myPanel.add(login);
		add(name, BorderLayout.PAGE_START);
		add(myPanel,BorderLayout.CENTER);
		
	}
	
	public String getUser()
	{
		return user.getText();
	}
	
	public char[] getPW()
	{
		return pw.getPassword();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		((MenuButton)e.getSource()).onPress();
	}
}
