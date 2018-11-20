import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaggableFriends extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public TaggableFriends(Post currentPost, User currentUser, JLabel lblTaggedFriends_1) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTaggableFriends = new JLabel("Taggable Friends");
		lblTaggableFriends.setBounds(10, 11, 107, 14);
		contentPane.add(lblTaggableFriends);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 36, 264, 195);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
		DefaultListModel taggables = new DefaultListModel();
		
		for (User currentFriend : currentUser.hmFriends.values()) {
			if (!currentPost.taggedFriends.contains(currentFriend.getsName())){
				if (!currentUser.hmBlockedFriends.containsValue(currentFriend)) {
					taggables.addElement(currentFriend.getsName());
				}
			}
		}
		list.setModel(taggables);
		JButton btnTagFriend = new JButton("Tag User");
		btnTagFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					currentPost.taggedFriends.add(list.getSelectedValue().toString());
					lblTaggedFriends_1.setText("Tagged Friends: " + currentPost.showTaggedUsers());
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "You haven't selected any user to tag!");
				}
			}
		});
		btnTagFriend.setBounds(10, 238, 264, 23);
		contentPane.add(btnTagFriend);
	}
}
