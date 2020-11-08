package MiniTwitterA2;

import java.util.ArrayList;

/* User is a leaf within composite design pattern */ 

public class User implements IDInterface {
	
	private String userID;
	private ArrayList<String> followers, following, newsFeed;
	
	public User(String userID) {
		this.userID = userID;
		this.followers = new ArrayList<String>();
		this.following = new ArrayList<String>();
		this.newsFeed = new ArrayList<String>();
	}
	
	@Override
	public String getID() {
		return userID;
	}
	
	/* Function to add follower to user */
	public void addFollowedBy(String newUser) {		
		if (!followers.contains(newUser)) {
			followers.add(newUser);
		}
		else {
			System.out.println(newUser + " is already followed by " + userID + "!");
		}
	}
	
	/* Function to allow user remove a follower */
	public void removeFollowedBy(String newUser) {
		if (followers.contains(newUser)) {
			followers.remove(newUser);
		}
		else {
			System.out.println(newUser + " is not followed by " + userID + "!");
		}
	}
	
	/* Function to allow user to add a followed user */
	public void addFollowing(String newUser) { 
		if (!following.contains(newUser)) {
			following.add(newUser);
		}
		else {
			System.out.println(userID + " is already following " + newUser + "!");
		}
	}
	
	/* Function to allow user to remove a followed user */ 
	public void removeFollowing(String newUser) {
		if (following.contains(newUser)) {
			following.remove(newUser);
		}
		else {
			System.out.println(userID + " is not already following " + newUser + "!");
		}
	}
	
	public void addTweet(String newMsg) {
		newsFeed.add(newMsg);
	}
	
	public void removeTweet(String msg) {
		newsFeed.remove(msg);
	}
	
}
