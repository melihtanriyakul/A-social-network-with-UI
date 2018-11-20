import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

public class Main {

	private JFrame frmFacebookLoginPage;
	private JTextField textField;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JList list;
	private JButton btnNewUser;
	private JLabel lblRegisteredUsers;
	private JPasswordField passwordField;
	private JPanel panel;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		String userFile = args[0];
		String commandFile = args[1];
		
		ArrayList<String> userList = Operations.readFile(userFile);
		ArrayList<String> commandList = Operations.readFile(commandFile);
		
		Operations.assignUsers(userList);
		Operations.implementCommands(commandList);
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFacebookLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFacebookLoginPage = new JFrame("Facebook Login Page");
		frmFacebookLoginPage.setResizable(false);
		frmFacebookLoginPage.getContentPane().setBackground(Color.WHITE);
		frmFacebookLoginPage.setBounds(100, 100, 611, 515);
		frmFacebookLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFacebookLoginPage.getContentPane().setLayout(null);
		
		frmFacebookLoginPage.setLocationRelativeTo(null);
		textField = new JTextField();
		textField.setBounds(417, 113, 180, 25);
		frmFacebookLoginPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(417, 149, 180, 23);
		frmFacebookLoginPage.getContentPane().add(passwordField);
		
		JLabel userName = new JLabel("Username");
		userName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userName.setBounds(340, 116, 67, 14);
		frmFacebookLoginPage.getContentPane().add(userName);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(340, 151, 67, 14);
		frmFacebookLoginPage.getContentPane().add(lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (passwordField.getPassword().length > 0 && textField.getText().length() > 0) {
					String password = new String(passwordField.getPassword());
					String username = textField.getText();
					try {
						UserCollection.hmUserList.get(username).signIn(password);
						if (UserCollection.hmUserList.get(username).isSignIn()){
							JOptionPane.showMessageDialog(null, "You have successfully signed in!");
							ProfilePage pp = new ProfilePage(UserCollection.hmUserList.get(username));
							pp.setVisible(true);
							pp.setLocationRelativeTo(null);								
						}
						else 
							JOptionPane.showMessageDialog(null, "Invalid password!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Your username does not match any account.");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill the fields!");
			}
		});
		btnLogin.setBounds(417, 193, 180, 35);
		frmFacebookLoginPage.getContentPane().add(btnLogin);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 380, 587, 62);
		frmFacebookLoginPage.getContentPane().add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setVisibleRowCount(2);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel list1 = new DefaultListModel();
		for (String currentUser : UserCollection.hmUserList.keySet()) {
			list1.addElement(currentUser);
		}
		list.setModel(list1);
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(list.getSelectedValue() != null) {
					String uName = (String) list.getSelectedValue();
					try {
						textField.setText(uName);
						passwordField.setText(UserCollection.hmUserList.get(uName).getsPassword());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		});
		
		JButton removeUser = new JButton("Remove User");
		removeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){ 
					if(list.getSelectedValue() != null) {
						String userName = (String) list.getSelectedValue();
						String name = UserCollection.hmUserList.get(userName).getsName();
						if (UserCollection.hmUserList.containsKey(userName)) {
							for (User currentUser : UserCollection.hmUserList.values()) {
								if (currentUser.hmBlockedUsers.containsKey(userName)){
									currentUser.hmBlockedUsers.remove(userName);
								}
								if (currentUser.hmFriends.containsKey(userName)){
									currentUser.hmFriends.remove(userName);
								}
								if (currentUser.hmBlockedFriends.containsKey(userName)){
									currentUser.hmBlockedFriends.remove(userName);
								}
								for (int i = 0; i < currentUser.getAlPosts().size(); i++) {
									if (currentUser.getAlPosts().get(i).taggedFriends.contains(name)) {
										currentUser.getAlPosts().get(i).taggedFriends.remove(name);
									}
								}
							}
							for (int j = 0;  j < UserCollection.hmUserList.get(userName).getAlPosts().size(); j++) {
								UserCollection.hmUserList.get(userName).getAlPosts().remove(j);
							}
							UserCollection.hmUserList.remove(userName);
							list1.clear();
							for (String currentUser : UserCollection.hmUserList.keySet()) {
								list1.addElement(currentUser);
							}
							list.setModel(list1);
						}
					}
				}
			}
		});
		removeUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		removeUser.setBounds(10, 446, 156, 25);
		frmFacebookLoginPage.getContentPane().add(removeUser);
		
		btnNewUser = new JButton("New User...");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser cus = new CreateUser(list1);
				cus.setVisible(true);
				cus.setLocationRelativeTo(null);
			}
		});
		btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewUser.setBounds(435, 446, 162, 25);
		frmFacebookLoginPage.getContentPane().add(btnNewUser);
		
		lblRegisteredUsers = new JLabel("Registered Users");
		lblRegisteredUsers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegisteredUsers.setBounds(10, 355, 103, 25);
		frmFacebookLoginPage.getContentPane().add(lblRegisteredUsers);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 105, 320, 82);
		ImageIcon image = new ImageIcon(getClass().getResource("Facebook.png"));
		JLabel label = new JLabel(image);
		label.setBackground(Color.WHITE);
		panel.add(label, BorderLayout.CENTER);	
		frmFacebookLoginPage.getContentPane().add(panel);
		
	}
}
