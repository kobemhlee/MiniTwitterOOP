import java.util.ArrayList;

/* User is a leaf within composite design pattern */ 

public class User extends Subject implements IDInterface, Observer {
	
	private String userID;
	private ArrayList<User> followers, following;
	private ArrayList<String> newsFeed, tweets;
	private long creationTime;
	private long lastUpdatedTime;
	
	/* userID variable name will be same name as ID
	 * FE: User with id "jake123" is jake123 */
	
	public User(String userID) {
		this.userID = userID;
		this.creationTime = System.currentTimeMillis(); 
		this.lastUpdatedTime = System.currentTimeMillis();  
		this.followers = new ArrayList<User>();
		this.following = new ArrayList<User>();
		this.newsFeed = new ArrayList<String>();
		this.tweets = new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return userID;
	}
	
	
	@Override
	public String getID() {
		return userID;
	}
	
	@Override
	public long getCreationTime() {
		return creationTime;
	}
	
	@Override
	public long getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	
	/* Function to add follower to user */
	public void addFollowedBy(User newUser) {		
		lastUpdatedTime = System.currentTimeMillis(); 
		if (!followers.contains(newUser)) {
			followers.add(newUser);
			this.attach(newUser);
		}
		else {
			System.out.println(newUser + " is already followed by " + userID + "!");
		}
	}
	
	/* Function to allow user to add a followed user */
	public void addFollowing(User newUser) { 
		lastUpdatedTime = System.currentTimeMillis(); 
		if (!following.contains(newUser)) {
			following.add(newUser);
			this.attach(newUser);
		}
		else {
			System.out.println(userID + " is already following " + newUser + "!");
		}
	}
	
	public String printNewFollowing() {
		return following.get(following.size()-1).getID();
	}
	
	public String printFollowing() {
		String ret = "";
		for (User user: following) {
			ret += user.getID() +"\n";
		}
		return ret;
	}
	
	public void addTweet(String newMsg) {
		lastUpdatedTime = System.currentTimeMillis(); 
		newsFeed.add(this.userID + ": "+newMsg);
		tweets.add(this.userID + ": "+newMsg);
		this.notifyObservers();
	}
	
	
	public String getTweet() {
		return tweets.get(tweets.size()-1);
	}
	
	public String printFeed() {
		String ret = "";
		for (String tweet: newsFeed) {
			ret += "- "+ tweet +"\n";
		}
		return ret;
	}


	@Override
	public void update(Subject subject) {
		lastUpdatedTime = System.currentTimeMillis(); 
		if (subject instanceof User) {
			if (!((User)subject).getID().equals(this.getID())){
				newsFeed.add(((User)subject).getTweet());	
			}
		}
		else {
			System.out.println("Error!");
		}
	}
	
	public String addUserView() {
		long second = (creationTime / 1000) % 60;
		long minute = (creationTime / (1000 * 60)) % 60;
		long hour = (creationTime / (1000 * 60 * 60)) % 24;
		

		String creationTimeUpdated = String.format("%02d:%02d:%02d", ((hour+4)%24), minute, second);
		
		second = (lastUpdatedTime / 1000) % 60;
		minute = (lastUpdatedTime / (1000 * 60)) % 60;
		hour = (lastUpdatedTime / (1000 * 60 * 60)) % 24;

		String creationTimeUpdated2 = String.format("%02d:%02d:%02d", ((hour+4)%24), minute, second);
		
		
		return ("Created time: " + creationTimeUpdated + "\nLast updated time: " + creationTimeUpdated2);
	}
	
}
