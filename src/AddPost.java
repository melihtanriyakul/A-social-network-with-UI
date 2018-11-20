import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPost extends JFrame {

	private JPanel contentPane;
	private JTextField textOfPost;
	private JTextField textLatitude;
	private JTextField textLongitude;
	private JTextField txtFileName;
	private JTextField textWidth;
	private JTextField textHeight;
	private JTextField textDuration;
	/**
	 * Create the frame.
	 */
	public AddPost(User currentUser) {
		setTitle("Add Post");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPostType = new JLabel("Post Type");
		lblPostType.setBounds(10, 21, 57, 17);
		contentPane.add(lblPostType);
		
		JComboBox comboPostType = new JComboBox();
		comboPostType.addItem("TextPost");
		comboPostType.addItem("ImagePost");
		comboPostType.addItem("VideoPost");
		comboPostType.setBounds(67, 21, 126, 20);
		contentPane.add(comboPostType);
		
		JLabel lblText = new JLabel("Text:");
		lblText.setBounds(10, 49, 46, 14);
		contentPane.add(lblText);
		
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setBounds(10, 77, 51, 14);
		contentPane.add(lblLatitude);
		
		textOfPost = new JTextField();
		textOfPost.setBounds(67, 49, 445, 20);
		contentPane.add(textOfPost);
		textOfPost.setColumns(10);
		
		textLatitude = new JTextField();
		textLatitude.setBounds(67, 74, 95, 20);
		contentPane.add(textLatitude);
		textLatitude.setColumns(10);
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setBounds(167, 77, 62, 14);
		contentPane.add(lblLongitude);
		
		textLongitude = new JTextField();
		textLongitude.setBounds(239, 74, 95, 20);
		contentPane.add(textLongitude);
		textLongitude.setColumns(10);
		
		JButton btnAddPost = new JButton("Add Post");
		btnAddPost.setBounds(417, 18, 95, 23);
		contentPane.add(btnAddPost);
		
		JLabel lblFilename = new JLabel("Filename: ");
		lblFilename.setBounds(10, 122, 57, 14);
		contentPane.add(lblFilename);
		lblFilename.setVisible(false);
		
		txtFileName = new JTextField();
		txtFileName.setBounds(67, 119, 167, 20);
		contentPane.add(txtFileName);
		txtFileName.setColumns(10);
		txtFileName.setVisible(false);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(10, 147, 57, 14);
		contentPane.add(lblWidth);
		lblWidth.setVisible(false);
		
		textWidth = new JTextField();
		textWidth.setBounds(67, 144, 57, 20);
		contentPane.add(textWidth);
		textWidth.setColumns(10);
		textWidth.setVisible(false);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(128, 147, 46, 14);
		contentPane.add(lblHeight);
		lblHeight.setVisible(false);
		
		textHeight = new JTextField();
		textHeight.setBounds(172, 144, 62, 20);
		contentPane.add(textHeight);
		textHeight.setColumns(10);
		textHeight.setVisible(false);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(10, 147, 57, 14);
		contentPane.add(lblDuration);
		lblDuration.setVisible(false);
		
		textDuration = new JTextField();
		textDuration.setBounds(67, 144, 57, 20);
		contentPane.add(textDuration);
		textDuration.setColumns(10);
		textDuration.setVisible(false);
		
		comboPostType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboPostType.getSelectedItem().toString().equals("TextPost")) {
					lblFilename.setVisible(false);
					txtFileName.setVisible(false);
					lblWidth.setVisible(false);
					textWidth.setVisible(false);
					lblHeight.setVisible(false);
					textHeight.setVisible(false);
					lblDuration.setVisible(false);
					textDuration.setVisible(false);
				}
				else if (comboPostType.getSelectedItem().toString().equals("ImagePost")) {
					lblFilename.setVisible(true);
					txtFileName.setVisible(true);
					lblWidth.setVisible(true);
					textWidth.setVisible(true);
					lblHeight.setVisible(true);
					textHeight.setVisible(true);
					lblDuration.setVisible(false);
					textDuration.setVisible(false);
				}
				else if (comboPostType.getSelectedItem().toString().equals("VideoPost")) {
					lblFilename.setVisible(true);
					txtFileName.setVisible(true);
					lblWidth.setVisible(false);
					textWidth.setVisible(false);
					lblHeight.setVisible(false);
					textHeight.setVisible(false);
					lblDuration.setVisible(true);
					textDuration.setVisible(true);
				}				
			}
		});
		
		btnAddPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboPostType.getSelectedItem().toString().equals("TextPost")){
					try {
						currentUser.addPostText(textOfPost.getText(), new Location(Double.parseDouble(textLongitude.getText()), Double.parseDouble(textLatitude.getText())));
						dispose();
						ProfilePage currentProfilePage = new ProfilePage(currentUser);
						currentProfilePage.setVisible(true);
						currentProfilePage.setLocationRelativeTo(null);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Please fill the fields correctly.");
					}
				}
				else if (comboPostType.getSelectedItem().toString().equals("VideoPost")){
					try {
						currentUser.addPostVideo(textOfPost.getText(), new Location(Double.parseDouble(textLongitude.getText()), Double.parseDouble(textLatitude.getText()))
								,txtFileName.getText(), Integer.parseInt(textDuration.getText()));
						dispose();
						ProfilePage currentProfilePage = new ProfilePage(currentUser);
						currentProfilePage.setVisible(true);
						currentProfilePage.setLocationRelativeTo(null);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Please fill the fields correctly.");
					}
				}
				else if (comboPostType.getSelectedItem().toString().equals("ImagePost")){
					try {
						String imageResolution = textWidth.getText() + " x " + textHeight.getText();
						currentUser.addPostImage(textOfPost.getText(), new Location(Double.parseDouble(textLongitude.getText()), Double.parseDouble(textLatitude.getText()))
								,txtFileName.getText(), imageResolution);
						dispose();
						ProfilePage currentProfilePage = new ProfilePage(currentUser);
						currentProfilePage.setVisible(true);
						currentProfilePage.setLocationRelativeTo(null);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Please fill the fields correctly.");
					}
				}
			}
		});
	}
}
