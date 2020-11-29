public interface IDInterface {
	
	/* Serves as component within composite design pattern 
	 * getID() returns ID of either user or user group */
	public String getID();
	
	public String toString();
	
	public long getCreationTime();
	
	public long getLastUpdatedTime();
}
