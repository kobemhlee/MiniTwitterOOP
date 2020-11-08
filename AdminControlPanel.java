package MiniTwitterA2;

public class AdminControlPanel implements ControlPanel {

	/* Admin Control Panel Class that follows the Singleton Design Pattern */ 
	
	private int numUsers;
	private int numGroups;
	private int numTweets;
	private int numPosTweets;
	private static AdminControlPanel obj;
	
	private AdminControlPanel() {
		obj.setUsers(0);
		obj.setGroups(0);
		obj.setTweets(0);
		obj.setPositiveTweets(0);
	}
	
	public static synchronized AdminControlPanel getInstance() {
		if (obj == null) {
			obj = new AdminControlPanel();
		}
		return obj;
	}
	
	@Override
	public String getTotalUsers() {
		return "There are " + this.numUsers + " users.";
	}

	public void incrementTotal() {
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

	public void setTweets(int numTweets) {
		this.numTweets = numTweets;
	}
	
	@Override
	public String getTotalPositiveTweets() {
		// Make sure to format as percentage
		return "There are " + this.numPosTweets + "% positive tweets total.";
	}
	
	public void setPositiveTweets(int numPosTweets) {
		this.numPosTweets = numPosTweets;
	}
	
}
