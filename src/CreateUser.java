import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CreateUser extends JFrame{
	private JTextField userName;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField nameAndSurname;
	private JTextField schoolGraduated;
	private JTextField birthDate;
	public CreateUser(DefaultListModel list1) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setSize(379, 428);
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(15, 155, 100, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(15, 180, 100, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblPasswordRetype = new JLabel("Password (re-type)");
		lblPasswordRetype.setBounds(15, 205, 111, 14);
		getContentPane().add(lblPasswordRetype);
		
		JLabel lblNameSurname = new JLabel("Name Surname");
		lblNameSurname.setBounds(15, 230, 100, 14);
		getContentPane().add(lblNameSurname);
		
		JLabel lblSchoolGratuated = new JLabel("School graduated");
		lblSchoolGratuated.setBounds(15, 255, 100, 14);
		getContentPane().add(lblSchoolGratuated);
		
		JLabel lblBirthDate = new JLabel("Birth Date");
		lblBirthDate.setBounds(15, 282, 100, 14);
		getContentPane().add(lblBirthDate);
		
		JLabel lblRelationshipStatus = new JLabel("Relationship Status");
		lblRelationshipStatus.setBounds(15, 307, 100, 14);
		getContentPane().add(lblRelationshipStatus);
		
		userName = new JTextField();
		userName.setBounds(125, 152, 86, 20);
		getContentPane().add(userName);
		userName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(125, 202, 86, 20);
		getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(125, 177, 86, 20);
		getContentPane().add(passwordField_1);
		
		nameAndSurname = new JTextField();
		nameAndSurname.setBounds(125, 227, 218, 20);
		getContentPane().add(nameAndSurname);
		nameAndSurname.setColumns(10);
		
		schoolGraduated = new JTextField();
		schoolGraduated.setBounds(125, 252, 218, 20);
		getContentPane().add(schoolGraduated);
		schoolGraduated.setColumns(10);
		
		birthDate = new JTextField();
		birthDate.setBounds(125, 280, 86, 20);
		getContentPane().add(birthDate);
		birthDate.setColumns(10);
		
		JComboBox relationshipStatus = new JComboBox();
		relationshipStatus.addItem("Single");
		relationshipStatus.addItem("In a relationship");
		relationshipStatus.addItem("Divorced");
		relationshipStatus.addItem("Complicated");
		relationshipStatus.setBounds(125, 307, 139, 20);
		getContentPane().add(relationshipStatus);
		setVisible(true);
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!userName.getText().isEmpty()) {
						String password = new String(passwordField.getPassword());
						String passwordReType = new String(passwordField_1.getPassword());
						if (password.length() != 0){
							if(passwordReType.length() != 0){
								if(!nameAndSurname.getText().isEmpty()){
									if(!schoolGraduated.getText().isEmpty()) {										
										if (!UserCollection.hmUserList.containsKey(userName.getText())) {
											if (password.equals(passwordReType)){
												UserCollection.hmUserList.put(userName.getText(), new User(nameAndSurname.getText(), userName.getText(), password, Operations.stringToDate(birthDate.getText()),
														schoolGraduated.getText(), relationshipStatus.getSelectedItem().toString()));
												JOptionPane.showMessageDialog(null, "The user successfully created!");
												list1.addElement(userName.getText());
												dispose();
											}
											else {
												JOptionPane.showMessageDialog(null, "The passwords don't match.");
											}
										}
										else
											JOptionPane.showMessageDialog(null, "The username that you've entered have been already used.");
									}
									else
										JOptionPane.showMessageDialog(null, "You should fill 'School Graduated' field!");
								}
								else
									JOptionPane.showMessageDialog(null, "You should fill 'Name Surname' field!");
							}
							else
								JOptionPane.showMessageDialog(null, "You should fill 'Password(re-type) field!");
						}
						else
							JOptionPane.showMessageDialog(null, "You should fill 'Password' field!");
					}
					else
						JOptionPane.showMessageDialog(null, "You should fill 'Username' field!");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "You should fill 'Birth Date' field correctly!");
				}
			}
		});
		btnNewButton.setBounds(113, 355, 131, 23);
		getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		ImageIcon image = new ImageIcon(getClass().getResource("Facebook.png"));
		panel.add(new JLabel(image), BorderLayout.CENTER);
		panel.setBounds(22, 11, 312, 91);
		getContentPane().add(panel);
		
		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCreateUser.setBounds(138, 113, 106, 20);
		getContentPane().add(lblCreateUser);
	}
}
