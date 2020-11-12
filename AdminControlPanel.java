package MiniTwitterA2;

import java.util.ArrayList;

public class AdminControlPanel implements ControlPanel, Observer {

	/* Admin Control Panel Class that follows the Singleton Design Pattern */ 
	
	private int numUsers;
	private int numGroups;
	private int numTweets;
	private int numPosTweets;
	private static AdminControlPanel obj;
	public ArrayList<User> users;
	public ArrayList<UserGroup> groups;
	public ArrayList<IDInterface> all;
	
	private AdminControlPanel() {
		this.setUsers(0);
		this.setGroups(0);
		this.setTweets(0);
		this.setPositiveTweets(0);
		users = new ArrayList<User>();
		groups = new ArrayList<UserGroup>();
		all = new ArrayList<IDInterface>();
	}
	
	public static synchronized AdminControlPanel getInstance() {
		if (obj == null) {
			obj = new AdminControlPanel();
		}
		return obj;
	}
	
	public int findIndexOfUser(String user) {
		int ind = 0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getID().equals(user)) {
				ind = i;
			}
		}
		return ind;
	}
	
	public int totalNodeCount() {
		return all.size();
	}
	
	public void printUsers() {
		for (User user: users) {
			System.out.println(user);
		}
	}
	
	public void printGroups() {
		for (UserGroup group: groups) {
			System.out.println(group);
		}
	}
	
	public User getUser(String name) {
		for (User user: users) {
			if (user.getID().equals(name)) {
				return user;
			}
		}
		return null;
	}
	
	public void addUser(User user) {
		users.add(user);
		numUsers++;
	}
	
	public void addGroup(UserGroup group) {
		groups.add(group);
		numGroups++;
	}
	
	@Override
	public String getTotalUsers() {
		return "There are " + this.numUsers + " users.";
	}

	public void incrementUsers() {
		this.numUsers++;
	}
	
	public void setUsers(int numUsers) {
		this.numUsers = numUsers;
	}
	
	@Override
	public String getTotalGroups() {
		return "There are " + this.numGroups + " user groups.";
	}
	
	public void incrementGroups() {
		this.numGroups++;
	}
	
	public void setGroups(int numGroups) {
		this.numGroups = numGroups;
	}

	@Override
	public String getTotalTweets() {
		return "There are " + this.numTweets + " tweets total.";
	}
	
	public void incrementTweets() {
		this.numTweets++;
	}
	
	public void incrementTweetsCheck(String str) {
		this.numTweets++;
		
		if (str.equals("good")) {
			this.numPosTweets++;
		}
	}

	public void setTweets(int numTweets) {
		this.numTweets = numTweets;
	}
	
	@Override
	public String getTotalPositiveTweets() {
		// Make sure to format as percentage
		return "There are " + (double)(this.numPosTweets*100)/this.numTweets + "% positive tweets total.";
	}
	
	public void setPositiveTweets(int numPosTweets) {
		this.numPosTweets = numPosTweets;
	}

	@Override
	public void update(Subject subject) {

		if (subject instanceof User) {
			numTweets++;
			
			if (((User)subject).getTweet().equals("good")) {
				numPosTweets++;
			}
		}
		else {
			System.out.println("Error!");
		}
		
	}
}
