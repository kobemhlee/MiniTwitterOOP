import java.util.ArrayList;
import java.util.HashSet;

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
		
		if (str.contains("good") || str.contains("great") || str.contains("best")) {
			this.numPosTweets++;
		}
	}

	public void setTweets(int numTweets) {
		this.numTweets = numTweets;
	}
	
	@Override
	public String getTotalPositiveTweets() {
		// Make sure to format as percentage
		return "Positive Tweet Percentage: " + (int)(this.numPosTweets*100)/this.numTweets + "%";
	}
	
	public void setPositiveTweets(int numPosTweets) {
		this.numPosTweets = numPosTweets;
	}

	@Override
	public void update(Subject subject) {

		if (subject instanceof User) {
			numTweets++;
			
			if (((User)subject).getTweet().contains("good")) {
				numPosTweets++;
			}
		}
		else {
			System.out.println("Error!");
		}
		
	}
	
	public boolean validUsers() {
		HashSet<String> temp = new HashSet<String>();
		for (User user: users) {
			if (containsSpaces(user.getID()) || temp.contains(user.getID())) {
				return false;
			}
			else {
				temp.add(user.getID());
			}
		}
		return true;
	}
	
	public boolean containsSpaces(String val) {
		for (int i = 0; i < val.length(); i++) {
			if (val.charAt(i)==' ') {
				return true;
			}
		}
		return false;
	}
	
	public String getLastUpdatedUser() {
		User last;
		
		if (users.size() > 0) {
			last = users.get(0);
			
			for (User user: users) {
				if (user.getLastUpdatedTime() > last.getLastUpdatedTime()) {
					last = user;
				}
			}
		}
		else {
			return "N/A";
		}
		
		return last.getID();
	}
	
	public String printUserTimes(String userName) {
		for (User user: users) {
			if (user.getID().equals(userName)) {
				return user.addUserView();
			}
			else {
				return "N/A";
			}
		}
		return "N/A";
	}
}
