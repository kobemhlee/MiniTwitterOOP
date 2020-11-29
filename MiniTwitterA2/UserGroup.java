package MiniTwitterA2;

import java.util.ArrayList;

/* User group is Composite within composite design pattern */

public class UserGroup implements IDInterface {

	private String groupID;
	private ArrayList<User> users;
	private long creationTime;
	private long lastUpdatedTime;
	
	public UserGroup(String groupID) {
		this.groupID = groupID;
		this.users = new ArrayList<User>();
		this.creationTime = System.currentTimeMillis(); 
	}
	
	@Override
	public String toString() {
		return groupID;
	}
	
	@Override
	public String getID() {
		return groupID;
	}
	
	@Override
	public long getCreationTime() {
		return creationTime;
	}
	
	@Override
	public long getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	
	public void getUserIDs() {
		for (User user:users) {
			System.out.println(user.getID());
		}
	}
	
	public void addNewUser(User newUser) {
		lastUpdatedTime = System.currentTimeMillis(); 
		
		// Make sure newUser does not already exist 
		boolean dup = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getID().equals(newUser.getID())) {
				dup = true;
			}
		}
		if (!dup) {
			users.add(newUser);
		}
		else {
			System.out.println(newUser.getID() + " already exists within this user group!");
		}
	}
	
}
