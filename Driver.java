package MiniTwitterA2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class Driver implements ActionListener {

	JTextArea userIDText;
	JTextArea groupIDText;
	JButton buttonUserView;
	JLabel emptyLabel;
	JButton buttonShowUserTotal;
	JButton buttonShowMsgTotal;
	JButton buttonAddUser;
	JButton buttonAddGroup;
	JButton buttonShowGroupTotal;
	JButton buttonShowPosTotal;
	String temp;
	DefaultMutableTreeNode currentNode;
	JTree tree;
	DefaultMutableTreeNode root;
	AdminControlPanel controlPanel;
	JFrame frame;
	DefaultTreeModel treeModel;
	JButton postTweetButton;
	JButton followUserButton;
	JTextArea userToFollowTA;
	JTextArea currentlyFollowingTA;
	JTextArea tweetTA;
	JTextArea feedTA;
	JButton updateButton;
	
	public static void main(String[] args) {
		new Driver();
	}
	
	public Driver() {		
		controlPanel = AdminControlPanel.getInstance();
		frame = new JFrame("Admin Control Panel Frame");
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		/* Column One */
		root =new DefaultMutableTreeNode("Root");  
		currentNode = root;
		
//		controlPanel.printUsers();
		
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.setEditable(true);
		tree.addTreeSelectionListener(createSelectionListener());

		
		/* Column Two (Panel Two) */
		userIDText = new JTextArea(1,10);
		userIDText.setEditable(false);
		userIDText.append("User ID:");
		
		groupIDText = new JTextArea(1,10);
		groupIDText.setEditable(false);
		groupIDText.append("Group:");

		buttonUserView = new JButton("Open User View");
		buttonUserView.addActionListener(this);
		emptyLabel = new JLabel("  ");
		buttonShowUserTotal = new JButton("Show User Total");
		buttonShowUserTotal.addActionListener(this);
		buttonShowMsgTotal = new JButton("Show Message Total");
		buttonShowMsgTotal.addActionListener(this);
		
		GridLayout layout1 = new GridLayout(5,1);
		layout1.setHgap(20);
		layout1.setVgap(20);
		panel2.setLayout(layout1);
		panel2.add(userIDText);
		panel2.add(groupIDText);
		panel2.add(buttonUserView);
		panel2.add(emptyLabel);
		panel2.add(buttonShowUserTotal);
		panel2.add(buttonShowMsgTotal);
		
		/* Column Three (Panel Three) */
		buttonAddUser = new JButton("Add User");
		buttonAddUser.addActionListener(this);
		buttonAddGroup = new JButton("Add Group");
		buttonAddGroup.addActionListener(this);
		buttonShowGroupTotal = new JButton("Show Group Total");
		buttonShowGroupTotal.addActionListener(this);
		buttonShowPosTotal = new JButton("Show Positive Percentage");
		buttonShowPosTotal.addActionListener(this);

		
		GridLayout layout2 = new GridLayout(5,1);
		layout2.setHgap(12);
		layout2.setVgap(12);
		panel3.setLayout(layout2);
		panel3.add(buttonAddUser);
		panel3.add(buttonAddGroup);
		panel3.add(emptyLabel);
		panel3.add(emptyLabel);
		panel3.add(buttonShowGroupTotal);
		panel3.add(buttonShowPosTotal);
		
		/* Main Panel */
		panel.setBorder(BorderFactory.createEmptyBorder(150, 210, 150, 210));
		GridLayout layout3 = new GridLayout(1,3);
		layout3.setHgap(12);
		layout3.setVgap(12);
		panel.setLayout(layout3);
		panel.add(tree); 
		panel.add(panel2);
		panel.add(panel3);
		
		/* Frame */
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Admin Control Panel");
		frame.pack();
		frame.setVisible(true);
	}
	
	private TreeSelectionListener createSelectionListener() {
	        return new TreeSelectionListener() {
	            public void valueChanged(TreeSelectionEvent e) {
	                TreePath path = e.getPath();
	                currentNode = (DefaultMutableTreeNode) path.getPathComponent(path.getPathCount()-1);
	                //System.out.println(path.getPathComponent(path.getPathCount()-1));
	                if (!path.getPathComponent(path.getPathCount()-1).toString().equals("Root")) {
	                	userIDText.setText("User ID: " + path.getPathComponent(path.getPathCount()-1).toString()); 
	                }
	                else { 
	                	userIDText.setText("User ID: ");
	                }
	                
	                if ((path.getPathCount()-2)<0) {
	                	groupIDText.setText("Group ID: Root");
	                }
	                else {
		                groupIDText.setText("Group ID: "+ path.getPathComponent(path.getPathCount()-2).toString());
	                }
	            }
	        };
	    }
	
	public void addToTree(DefaultMutableTreeNode oldNode, String newNode, String type) {
		if (type.equals("group")) {
			DefaultMutableTreeNode newVal =new DefaultMutableTreeNode(newNode);  
			controlPanel.addGroup(new UserGroup(newNode));
			oldNode.add(newVal);
			tree = new JTree(root);
			((DefaultTreeModel)tree.getModel()).reload(root);
		}
		else {
			DefaultMutableTreeNode newVal =new DefaultMutableTreeNode(newNode);  
			controlPanel.addUser(new User(newNode));
			oldNode.add(newVal);
			tree = new JTree(root);
			((DefaultTreeModel)tree.getModel()).reload(root);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==buttonUserView) {
	    	displayUserViewGUI(currentNode.toString());
		}
		else if (e.getSource()==buttonShowUserTotal) {
			JOptionPane.showMessageDialog(frame,"" + controlPanel.getTotalUsers()); 
		}
		else if (e.getSource()==buttonShowMsgTotal) {
			JOptionPane.showMessageDialog(frame,"" + controlPanel.getTotalTweets());  
		}
		else if (e.getSource()==buttonAddUser) {
	    	temp = JOptionPane.showInputDialog(frame, "Enter User Name:");
	    	while (temp.equals("")) {
	    		temp = JOptionPane.showInputDialog(frame, "Enter User Name (Valid Value):");
	    	}
	    	addToTree(currentNode, temp, "user");
	    	treeModel.reload(root);
		}
		else if (e.getSource()==buttonAddGroup) {
	    	temp = JOptionPane.showInputDialog(frame, "Enter Group Name:");
	    	while (temp.equals("")) {
	    		temp = JOptionPane.showInputDialog(frame, "Enter Group Name (Valid Value):");
	    	}
	    	addToTree(currentNode, temp, "group");
	    	treeModel.reload(root);
	    	
		}
		else if (e.getSource()==buttonShowGroupTotal) {
			JOptionPane.showMessageDialog(frame,"" + controlPanel.getTotalGroups());  
		}
		else if (e.getSource()==buttonShowPosTotal) {
			JOptionPane.showMessageDialog(frame,"" + controlPanel.getTotalPositiveTweets());  
		}
		else if (e.getSource()==followUserButton) {
			int ind1 = controlPanel.findIndexOfUser(currentNode.toString());
			int ind2 = controlPanel.findIndexOfUser(userToFollowTA.getText());
			controlPanel.users.get(ind1).addFollowing(controlPanel.users.get(ind2));
			
			currentlyFollowingTA.append("\n- "+controlPanel.users.get(ind1).printNewFollowing());
			//currentlyFollowingTA.update(currentlyFollowingTA.getGraphics());
		}
		else if (e.getSource()==postTweetButton) {
			int index = controlPanel.findIndexOfUser(currentNode.toString());
			controlPanel.users.get(index).addTweet(tweetTA.getText());
			controlPanel.users.get(index).update(controlPanel.users.get(index));
			controlPanel.incrementTweetsCheck(tweetTA.getText());
			
			feedTA.setText("Feed:\n" + controlPanel.users.get(index).printFeed());
		}
		else if (e.getSource()==updateButton) {
			int temp = controlPanel.findIndexOfUser(currentNode.toString());
			
			feedTA.setText("Feed:\n" + controlPanel.users.get(temp).printFeed());
		}
		
	}
	
	
	public void displayUserViewGUI(String user){	
		JFrame frame = new JFrame("Admin Control Panel Frame");
		JPanel panel = new JPanel(new GridLayout(2,1));
		JPanel innerPanel = new JPanel(new GridBagLayout());
		JPanel innerPanel2 = new JPanel(new GridBagLayout());

		
		/* Main Panel */
		panel.setBorder(BorderFactory.createEmptyBorder(100, 140, 100, 140));
        
		GridBagConstraints c = new GridBagConstraints();
		//panel.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		
		
		userToFollowTA = new JTextArea(1, 10);
		userToFollowTA.setEditable(true);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		userToFollowTA.append("");
		innerPanel.add(userToFollowTA, c);
		c.gridx = 2;

		 
		followUserButton = new JButton("Follow User");
		followUserButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		innerPanel.add(followUserButton, c);

		 
		currentlyFollowingTA = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(currentlyFollowingTA); 
		
		currentlyFollowingTA.setEditable(false);
		int index = controlPanel.findIndexOfUser(currentNode.toString());
		currentlyFollowingTA.append("Currently Following:" + controlPanel.users.get(index).printFollowing());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		innerPanel.add(scrollPane, c);


		panel.add(innerPanel);

		
		// Second half
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		tweetTA = new JTextArea(3, 20);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		tweetTA.append("Tweet");
		tweetTA.setEditable(true);
		innerPanel2.add(tweetTA, c);
		 
		postTweetButton = new JButton("Post Tweet");
		postTweetButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		innerPanel2.add(postTweetButton, c);
		 
		feedTA = new JTextArea(5, 20);
		JScrollPane scrollPane2 = new JScrollPane(feedTA); 
		feedTA.setEditable(false);
		feedTA.append("Feed:");
		int temp = controlPanel.findIndexOfUser(currentNode.toString());
		
		feedTA.setText("Feed:\n" + controlPanel.users.get(temp).printFeed());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		innerPanel2.add(scrollPane2, c);
		updateButton = new JButton("Update");
		updateButton.addActionListener(this);
		innerPanel2.add(updateButton);
		 
		panel.add(innerPanel2);
		
		/* Frame */
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setTitle(user + " User View Panel");
		frame.pack();
		frame.setVisible(true);
	}
}