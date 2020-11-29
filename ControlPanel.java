public interface ControlPanel {

	/* Outputs total number of users across Mini Twitter */ 
	String getTotalUsers();
	
	/* Outputs total number of groups across Mini Twitter */ 
	String getTotalGroups();
	
	/* Outputs total number of tweets across Mini Twitter */ 
	String getTotalTweets();
	
	/* Return the percentage of positive tweets within Mini Twitter */ 
	String getTotalPositiveTweets(); 
	
}
