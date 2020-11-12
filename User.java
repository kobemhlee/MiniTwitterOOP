package MiniTwitterA2;

import java.util.ArrayList;

/* User is a leaf within composite design pattern */ 

public class User extends Subject implements IDInterface, Observer {
	
	private String userID;
	private ArrayList<User> followers, following;
	private ArrayList<String> newsFeed, tweets;
	
	/* userID variable name will be same name as ID
	 * FE: User with id "jake123" is jake123 */
	
	public User(String userID) {
		this.userID = userID;
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
	
	/* Function to add follower to user */
	public void addFollowedBy(User newUser) {		
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
		newsFeed.add(newMsg);
		tweets.add(newMsg);
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
		if (subject instanceof User) {
			if (!((User)subject).getID().equals(this.getID())){
				newsFeed.add(((User)subject).getTweet());	
			}
		}
		else {
			System.out.println("Error!");
		}
	}
	
}
