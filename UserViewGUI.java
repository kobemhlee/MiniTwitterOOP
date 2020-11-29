import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserViewGUI implements ActionListener{
	
	// PROB delete this class later
	
	public UserViewGUI(User user) {
		displayUserViewGUI(user);
	}
	
	public void displayUserViewGUI(User user){	
		JFrame frame = new JFrame("Admin Control Panel Frame");
		JPanel panel = new JPanel(new GridLayout(2,1));
		JPanel innerPanel = new JPanel(new GridBagLayout());
		JPanel innerPanel2 = new JPanel(new GridBagLayout());

		
		/* Main Panel */
		panel.setBorder(BorderFactory.createEmptyBorder(100, 140, 100, 140));
        
		GridBagConstraints c = new GridBagConstraints();
		JButton button;
		//panel.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		
		
		JTextArea textArea = new JTextArea(1, 10);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		textArea.append("Username");
		innerPanel.add(textArea, c);
		c.gridx = 2;

		 
		button = new JButton("Follow User");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		innerPanel.add(button, c);

		 
		textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		textArea.append("Currently Following:");
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
		JTextArea textArea2 = new JTextArea(4, 25);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		textArea2.append("Tweet");
		innerPanel2.add(textArea2, c);
		 
		JButton button2 = new JButton("Post Tweet");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		innerPanel2.add(button2, c);
		 
		JTextArea textArea3 = new JTextArea(5, 20);
		JScrollPane scrollPane2 = new JScrollPane(textArea3); 
		textArea3.setEditable(false);
		textArea3.append("Feed:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		innerPanel2.add(scrollPane2, c);
		 
		panel.add(innerPanel2);
		
		/* Frame */
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("User View Panel");
		frame.pack();
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
