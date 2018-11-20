import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.JTable;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.ietf.jgss.ChannelBinding;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProfilePage extends JFrame{
	private JTextField textField;
	private JTextField dateOfBirth;
	private JTextField school;
	ArrayList<JPanel> postList = new ArrayList<JPanel>();
	ArrayList<JPanel> friendPostList = new ArrayList<JPanel>();
	
	public ProfilePage(User currentUser) {
		setResizable(false);
		setTitle("Profil Page");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		this.setSize(700, 700);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 694, 37);
		panel.setBackground(UIManager.getColor("RadioButtonMenuItem.selectionBackground"));
		getContentPane().add(panel);
		
		JButton button = new JButton("Create a Post");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(467, 11, 118, 20);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPost ap = new AddPost(currentUser);
				ap.setVisible(true);
				ap.setLocationRelativeTo(null);
				dispose();
			}
		});
		panel.setLayout(null);
		panel.add(button);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser.signOut();
				dispose();
			}
		});
		btnLogout.setBounds(595, 11, 73, 20);
		panel.add(btnLogout);
		
		JLabel lblSearch = new JLabel("Search Friends");
		lblSearch.setForeground(Color.WHITE);
		lblSearch.setBounds(133, 14, 93, 14);
		panel.add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(224, 11, 144, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(190, 220, 484, 430);
		getContentPane().add(tabbedPane);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(211, 211, 211), null));
		JPanel jp1 = new JPanel();
        JScrollPane scrollPane_1 = new JScrollPane(jp1);
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jp1.setPreferredSize(new Dimension(430, 2000));
        tabbedPane.add(scrollPane_1, "Posts");
        jp1.setLayout(null);
        JPanel jp2 = new JPanel();
        JScrollPane scrollPane_2 = new JScrollPane(jp2);
        scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jp2.setPreferredSize(new Dimension(430, 2000));
        tabbedPane.add(scrollPane_2,"Friends' Posts");
        jp2.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 452, 170, 198);
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(211, 211, 211), new Color(211, 211, 211), new Color(211, 211, 211), new Color(211, 211, 211)));
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        
        
        
        JScrollPane normalFriendsScroolPane = new JScrollPane();
        normalFriendsScroolPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        normalFriendsScroolPane.setBounds(10, 11, 150, 151);
        panel_1.add(normalFriendsScroolPane);
        normalFriendsScroolPane.setVisible(true);
        
        JList normalFriends = new JList();
        normalFriendsScroolPane.setViewportView(normalFriends);
        DefaultListModel nFriends = new DefaultListModel();
        for (String currentFriend : currentUser.hmFriends.keySet()) {
			if (!currentUser.hmBlockedFriends.containsKey(currentFriend)){
				nFriends.addElement(currentUser.hmFriends.get(currentFriend).getsUserName());
			}
		}
        normalFriends.setModel(nFriends);
        normalFriends.setVisible(true);

        JScrollPane blockedFriendsScroolPane = new JScrollPane();
        blockedFriendsScroolPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        blockedFriendsScroolPane.setBounds(10, 11, 150, 151);
        panel_1.add(blockedFriendsScroolPane);
        blockedFriendsScroolPane.setVisible(false);
        
        JList blockedFriends = new JList();
        blockedFriendsScroolPane.setViewportView(blockedFriends);
        
        DefaultListModel bFriends = new DefaultListModel();
        for (String blockedUser : currentUser.hmBlockedFriends.keySet()) {
        	bFriends.addElement(blockedUser);
		}
        blockedFriends.setModel(bFriends);
        blockedFriends.setVisible(false);
        JLabel lblFriends = new JLabel("Friends");
        lblFriends.setBounds(10, 427, 46, 14);
        lblFriends.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(lblFriends);
        
        JRadioButton rdbtnNormal = new JRadioButton("Normal");
        rdbtnNormal.setSelected(true);
        rdbtnNormal.setFont(new Font("Tahoma", Font.PLAIN, 10));
        rdbtnNormal.setBounds(49, 424, 64, 23);
        getContentPane().add(rdbtnNormal);
        
        JRadioButton rdbtnBlocked = new JRadioButton("Blocked");
        rdbtnBlocked.setSelected(false);
        rdbtnBlocked.setFont(new Font("Tahoma", Font.PLAIN, 10));
        rdbtnBlocked.setBounds(115, 424, 69, 23);
        getContentPane().add(rdbtnBlocked);
        
        JButton removeSelectedUser = new JButton("Remove Selected User");
        removeSelectedUser.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (normalFriends.getSelectedValue() != null && normalFriends.isVisible()) {
        			String userName = (String) normalFriends.getSelectedValue();
        			currentUser.hmFriends.remove(userName);
        			DefaultListModel model = (DefaultListModel) normalFriends.getModel();
        			int selectedIndex = normalFriends.getSelectedIndex();
        			try {
        			    model.remove(selectedIndex);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "You have done something wrong!");
        			}
        		}
        		else if (blockedFriends.getSelectedValue() != null && blockedFriends.isVisible()){
        			String userName = (String) blockedFriends.getSelectedValue();
        			currentUser.hmFriends.remove(userName);
        			currentUser.hmBlockedFriends.remove(userName);
        			DefaultListModel model = (DefaultListModel) blockedFriends.getModel();
        			int selectedIndex = blockedFriends.getSelectedIndex();
        			try {
        			    model.remove(selectedIndex);

					} catch (Exception e) {
						
        			}
        		}
        		dispose();
        		ProfilePage aa = new ProfilePage(currentUser);
        		aa.setVisible(true);
        		aa.setLocationRelativeTo(null);
        	}
        });
        removeSelectedUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
        removeSelectedUser.setBounds(10, 164, 150, 23);
        panel_1.add(removeSelectedUser);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_2.setBounds(10, 220, 170, 198);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblDateOfBirth = new JLabel("Date of Birth");
        lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDateOfBirth.setBounds(10, 11, 86, 20);
        panel_2.add(lblDateOfBirth);
        
        dateOfBirth = new JTextField();
        dateOfBirth.setBounds(10, 31, 86, 20);
        dateOfBirth.setVisible(false);
        panel_2.add(dateOfBirth);
        dateOfBirth.setColumns(10);
        
        JLabel lblSchool = new JLabel("School Graduated");
        lblSchool.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSchool.setBounds(10, 56, 116, 14);
        panel_2.add(lblSchool);
        
        school = new JTextField();
        school.setBounds(10, 81, 116, 20);
        school.setVisible(false);
        panel_2.add(school);

        school.setColumns(10);
        
        JLabel lblRelationshipStatus = new JLabel("Relationship Status");
        lblRelationshipStatus.setBounds(10, 112, 116, 14);
        panel_2.add(lblRelationshipStatus);
        
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Single");
        comboBox.addItem("In a relationship");
        comboBox.addItem("Divorced");
        comboBox.addItem("Complicated");
        comboBox.setBounds(10, 133, 131, 20);
        panel_2.add(comboBox);
        comboBox.setVisible(false);
        
        JLabel relationshipStatus = new JLabel();
        relationshipStatus.setText(currentUser.getsRelationshipStatus());
        relationshipStatus.setBounds(10, 133, 131, 20);
        panel_2.add(relationshipStatus);
        
        
        JLabel changedSchool = new JLabel("School");
        changedSchool.setText(currentUser.getsSchoolInfo());
        changedSchool.setBounds(10, 81, 140, 20);
        panel_2.add(changedSchool);
        
        JLabel changedDate = new JLabel("New label");
        changedDate.setText(dateToString(currentUser.getdDateOfBirth()));
        changedDate.setBounds(10, 31, 140, 20);
        panel_2.add(changedDate);
        
        JLabel lblInformation = new JLabel("Information");
        lblInformation.setBounds(20, 205, 93, 14);
        getContentPane().add(lblInformation);
        
        JPanel userIcon = new JPanel();
        userIcon.setBounds(10, 48, 150, 150);
        ImageIcon image = new ImageIcon(getClass().getResource("PersonIcon.png"));
		userIcon.setLayout(null);
        getContentPane().add(userIcon);
        JLabel icon = new JLabel(image);
        icon.setBounds(0, 0, 150, 150);
        userIcon.add(icon);
        icon.setVerticalAlignment(SwingConstants.TOP);
        
        JLabel lblNameandsurname = new JLabel("NameAndSurname");
        lblNameandsurname.setText(currentUser.getsName());
        lblNameandsurname.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNameandsurname.setBounds(170, 167, 217, 31);
        getContentPane().add(lblNameandsurname);
		JComponent panel1 = null;

		JButton btnSave = new JButton("Save");
        btnSave.setBounds(10, 164, 89, 23);
        btnSave.setVisible(false);
        panel_2.add(btnSave);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(10, 164, 89, 23);
        panel_2.add(btnUpdate);
        
        btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changedSchool.setText(school.getText());
				currentUser.setsSchoolInfo(school.getText());
				changedDate.setText(dateOfBirth.getText());
				try {
					currentUser.setdDateOfBirth(Operations.stringToDate(dateOfBirth.getText()));
				} catch (ParseException e1) {
				}
				currentUser.setsRelationshipStatus(comboBox.getSelectedItem().toString());
				relationshipStatus.setText(comboBox.getSelectedItem().toString());
				school.setVisible(false);
        		dateOfBirth.setVisible(false);
        		comboBox.setVisible(false);
        		btnSave.setVisible(false);
        		btnUpdate.setVisible(true);
        		changedSchool.setVisible(true);
        		changedDate.setVisible(true);
        		relationshipStatus.setVisible(true);
        		
			}
		});
        
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		btnUpdate.setVisible(false);
        		changedSchool.setVisible(false);
        		changedDate.setVisible(false);
        		relationshipStatus.setVisible(false);
        		btnSave.setVisible(true);
        		school.setText(changedSchool.getText());
        		dateOfBirth.setText(changedDate.getText());
        		school.setVisible(true);
        		dateOfBirth.setVisible(true);
        		comboBox.setVisible(true);
        	}
        });
        
        int count = 0;
		for (Post currentPost : currentUser.getAlPosts()) {
			placePost(jp1, count, currentPost, currentPost.getClass().getSimpleName(), currentUser);
			count++;
		}
		int count1 = 0;
		for(User currentUserFriend : currentUser.hmFriends.values()){
			if (!currentUser.hmBlockedFriends.containsKey(currentUserFriend.getsUserName())) {
				for (int j = 0; j < currentUserFriend.getAlPosts().size(); j++){
					placeFriendPost(jp2, count1, currentUserFriend.getAlPosts().get(j), currentUserFriend.getAlPosts().get(j).getClass().getSimpleName(), currentUserFriend.getsName());
					count1++;
				}
			}
		}

        rdbtnNormal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				blockedFriendsScroolPane.setVisible(false);
				normalFriendsScroolPane.setVisible(true);
				normalFriends.setVisible(true);
				rdbtnNormal.setSelected(true);
        		rdbtnBlocked.setSelected(false);
			}
		}); 
        rdbtnBlocked.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				normalFriendsScroolPane.setVisible(false);
				blockedFriendsScroolPane.setVisible(true);
				blockedFriends.setVisible(true);
				rdbtnBlocked.setSelected(true);
        		rdbtnNormal.setSelected(false);
			}
		});
	}
	public void placePost(JPanel jp1,int postNumber,Post currentPost, String postType, User currentUser) {
		postList.add(new JPanel());
        postList.get(postNumber).setLayout(null);
        jp1.add(postList.get(postNumber));
        postList.get(postNumber).setBounds(0, (60 * postNumber) + 2, 475, 60);
        postList.get(postNumber).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        if (postType.equals("VideoPost")) {
        	JLabel lblPostsIcon = new JLabel("V");
            lblPostsIcon.setBounds(0, 0, 40, 39);
            lblPostsIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblPostsIcon.setFont(new Font("Tahoma", Font.BOLD, 30));
            postList.get(postNumber).add(lblPostsIcon);
        }
        else if(postType.equals("ImagePost")){
        	JLabel lblPostsIcon = new JLabel("I");
            lblPostsIcon.setBounds(0, 0, 40, 39);
            lblPostsIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblPostsIcon.setFont(new Font("Tahoma", Font.BOLD, 30));
            postList.get(postNumber).add(lblPostsIcon);
        }
        else if(postType.equals("TextPost")) {
        	JLabel lblPostsIcon = new JLabel("T");
            lblPostsIcon.setBounds(0, 0, 40, 39);
            lblPostsIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblPostsIcon.setFont(new Font("Tahoma", Font.BOLD, 30));
            postList.get(postNumber).add(lblPostsIcon);
        }
        
        JLabel lblTaggedFriends_1 = new JLabel("Tagged Friends:");
        lblTaggedFriends_1.setText("Tagged Friends: " + currentPost.showTaggedUsers());
        lblTaggedFriends_1.setBounds(0, 45, 465, 15);
        postList.get(postNumber).add(lblTaggedFriends_1);
        
        JLabel lblPostsText_1 = new JLabel("Post's Text");
        lblPostsText_1.setText("<html>" + currentPost.getsText() + "</html>" );
        lblPostsText_1.setForeground(Color.BLUE);
        lblPostsText_1.setVerticalAlignment(SwingConstants.TOP);
        lblPostsText_1.setBounds(40, 0, 320, 45);
        postList.get(postNumber).add(lblPostsText_1);
        
        JButton btnTagUser = new JButton("Tag User");
        btnTagUser.setBounds(365, 0, 90, 39);
        postList.get(postNumber).add(btnTagUser);
        btnTagUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaggableFriends tf = new TaggableFriends(currentPost,currentUser, lblTaggedFriends_1);
				tf.setVisible(true);
				tf.setLocationRelativeTo(null);
				
			}
		});
        
        
	}
	public void placeFriendPost(JPanel jp2,int postNumber,Post currentPost, String postType, String postOwnerName){
		friendPostList.add(new JPanel());
		friendPostList.get(postNumber).setLayout(null);
        jp2.add(friendPostList.get(postNumber));
        friendPostList.get(postNumber).setBounds(0, (60 * postNumber) + 2, 475, 60);
        friendPostList.get(postNumber).setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        if (postType.equals("VideoPost")) {
        	JLabel lblPostsIcon = new JLabel("V");
            lblPostsIcon.setBounds(0, 15, 40, 39);
            lblPostsIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblPostsIcon.setFont(new Font("Tahoma", Font.BOLD, 30));
            friendPostList.get(postNumber).add(lblPostsIcon);
        }
        else if(postType.equals("ImagePost")){
        	JLabel lblPostsIcon = new JLabel("I");
            lblPostsIcon.setBounds(0, 15, 40, 39);
            lblPostsIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblPostsIcon.setFont(new Font("Tahoma", Font.BOLD, 30));
            friendPostList.get(postNumber).add(lblPostsIcon);
        }
        else if(postType.equals("TextPost")) {
        	JLabel lblPostsIcon = new JLabel("T");
            lblPostsIcon.setBounds(0, 15, 40, 39);
            lblPostsIcon.setHorizontalAlignment(SwingConstants.CENTER);
            lblPostsIcon.setFont(new Font("Tahoma", Font.BOLD, 30));
            friendPostList.get(postNumber).add(lblPostsIcon);
        }
        
        JLabel lblTaggedFriends_1 = new JLabel("Tagged Friends:");
        lblTaggedFriends_1.setText("Tagged Friends: " + currentPost.showTaggedUsers());
        lblTaggedFriends_1.setBounds(0, 45, 465, 15);
        friendPostList.get(postNumber).add(lblTaggedFriends_1);
        
        JLabel lblPostsText_1 = new JLabel("Post's Text");
        lblPostsText_1.setText("<html>" + currentPost.getsText() + "</html>" );
        lblPostsText_1.setForeground(Color.BLUE);
        lblPostsText_1.setVerticalAlignment(SwingConstants.TOP);
        lblPostsText_1.setBounds(40, 15, 320, 30);
        friendPostList.get(postNumber).add(lblPostsText_1);
        
        JLabel hasShared = new JLabel();
        hasShared.setText(postOwnerName + " has shared"); 
        hasShared.setBounds(0, 0, 250, 15);
        friendPostList.get(postNumber).add(hasShared);
        
	}
	public String dateToString(Date givenDate){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(givenDate);
		return date;
	}
}
