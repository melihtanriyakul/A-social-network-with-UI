import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * This is a superclass which has several method
 * to implement the operations for users. This class
 * also three hashmap and one arraylist to keep
 * user's friends, user's friends who are blocked,
 * other users who are block by the user and finally
 * user's post.
 * @author Ahmet Melih Tanriyakul
 * */
public class User {
	private String sName;
	private String sUserName;
	private String sPassword;
	private Date dDateOfBirth;
	private String sSchoolInfo;
	private String sRelationshipStatus;
	private Date dLastLogIn;
	private boolean signIn;
	HashMap<String, User> hmFriends = new HashMap<String, User>();
	HashMap<String, User> hmBlockedUsers = new HashMap<String, User>();
	HashMap<String, User> hmBlockedFriends = new HashMap<String, User>();
	private ArrayList<Post> alPosts = new ArrayList<Post>();
	/**
	 * @param sName The user's name.
	 * @param sUserName The user's username.
	 * @param sPassword The user's password.
	 * @param dDateOfBirth The user's date of birth.
	 * @param sSchoolInfo The user's school informations.
	 * */
	public User(String sName, String sUserName, String sPassword, Date dDateOfBirth, String sSchoolInfo, String sRelationshipStatus) {
		this.sName = sName;
		this.sUserName = sUserName;
		this.sPassword = sPassword;
		this.dDateOfBirth = dDateOfBirth;
		this.sSchoolInfo = sSchoolInfo;
		this.sRelationshipStatus = sRelationshipStatus;
	}
	@Override
	public String toString() {
		return ("Name: " + getsName()+"\n" + "Username: " + getsUserName()
		+ "\n" + "Date of Birth: " + getdDateOfBirth() + "\n" + "School: " + getsSchoolInfo() + "\n"
		+ "-----------------------");
	}
	/**
	 * This method is created for the user to sign in,
	 * takes the user's password as a parameter, and if 
	 * the user enters his/her password correctly, he/she
	 * successfully sign in the system.
	 * @param sPassword The user's password.
	 * @return signIn The user's status
	 * */
	public boolean signIn(String sPassword){
		if (this.sPassword.equals(sPassword)){
			Date dateAtTheMoment = new Date();
			this.dLastLogIn = dateAtTheMoment;
			return signIn = true;
		}
		else{
			return signIn = false;
		}
	}
	/**
	 * This method is created for the user to sign out.
	 * @return signIn The user's status.
	 * */
	public boolean signOut() {
		if (this.signIn) {
			return this.signIn = false;
		}
		else {
			return this.signIn = false;
		}
	}
	/**
	 * This method updates the user's profile by taking user's new 
	 * informations as a parameters.
	 * @param sName The user's new name
	 * @param dDateOfBirth The user's new date of birth
	 * @param aSchoolInfo The user's new school informations.
	 * */
	public void updateProfile(String sName, Date dDateOfBirth, String aSchoolInfo) {
		this.sName = sName;
		this.dDateOfBirth = dDateOfBirth;
		this.sSchoolInfo = aSchoolInfo;
	}
	/**
	 * This method changes the user's password if the user 
	 * enter his/her current password correctly.
	 * @param currentPassword The user' current password.
	 * @param newPassword The user's new password.
	 * */
	public void changePassword(String currentPassword, String newPassword){
		if (this.sPassword.equals(currentPassword)) {
			this.sPassword = newPassword;
		}
	}
	/**
	 * This method add the user whose username is given as a parameter
	 * to the user's friends list.
	 * @param sUserName The username of the user who will be added
	 * our user's friends.
	 * */
	public void addFriend(String sUserName) {
		if (hmFriends.containsKey(sUserName)){
		}
		else {
			hmFriends.put(sUserName, UserCollection.hmUserList.get(sUserName));
		}
	}
	/**
	 * This method remove the user whose username is given as a parameter
	 * from our user's friends list.
	 * @param sUserName The username of the user who will be removed
	 * from our user's friend list.
	 * */
	public void removeFriend(String sUserName){
		if (this.hmFriends.containsKey(sUserName)) {
			this.hmFriends.remove(sUserName);
		}
		else{
		}
	}
	/**
	 * This method takes the informations of the post
	 * as a parameters and adds the post 
	 * to the user's post list, alPosts.
	 * @param sText The post's text.
	 * @param lLocation The location of the post.
	 * @param taggedFriends The user's friends who are tagged in the post.
	 * */
	public void addPostText(String sText, Location lLocation, ArrayList<String> taggedFriends) {
		ArrayList<String> existingFriends = new ArrayList<String>();
		for (String currentUser : taggedFriends) {
			if(this.hmFriends.containsKey(currentUser)){
				if(!this.hmBlockedFriends.containsKey(currentUser))
					existingFriends.add(this.hmFriends.get(currentUser).getsName());
				
			}
			else {
			}
		}
		alPosts.add(new TextPost(sText, lLocation, existingFriends));

	}
	public void addPostText(String sText, Location lLocation) {		
		alPosts.add(new TextPost(sText, lLocation));
	}
	/**
	 * This method creates an Image Post and add the post 
	 * to the user's array list, alPosts which contains the user's post.
	 * @param sText The post's text.
	 * @param lLocation The location of the post.
	 * @param taggedFriends The user's friends who are tagged in the post.
	 * @param sFileName The file name of the image.
	 * @param sImageResolution The resolution of the image.
	 * */
	public void addPostImage(String sText, Location lLocation, ArrayList<String> taggedFriends, String sFileName, String sImageResolution){
		ArrayList<String> existingFriends = new ArrayList<String>();
		for (String currentUser : taggedFriends) {
			if(this.hmFriends.containsKey(currentUser)){
				if(!this.hmBlockedFriends.containsKey(currentUser))
					existingFriends.add(this.hmFriends.get(currentUser).getsName());
			}
			else {
			}
		}
		alPosts.add(new ImagePost(sText, lLocation, existingFriends, sFileName, sImageResolution));
	}
	public void addPostImage(String sText, Location lLocation, String sFileName, String sImageResolution){
		
		alPosts.add(new ImagePost(sText, lLocation, sFileName, sImageResolution));
	}
	/**
	 * This method creates an Video Post and add the post 
	 * to the user's array list, alPosts which contains the user's post.
	 * @param sText The post's text.
	 * @param lLocation The location of the post.
	 * @param taggedFriends The user's friends who are tagged in the post.
	 * @param sFilePath The file path of the video.
	 * @param iVideoLength The length of the video.
	 * */
	public void addPostVideo(String sText, Location lLocation, ArrayList<String> taggedFriends, String sFilePath, int iVideoLength){
		if (iVideoLength > VideoPost.getMaximumVideoDuration()) {
		}
		else {
			ArrayList<String> existingFriends = new ArrayList<String>();
			for (String currentUser : taggedFriends) {
				if(this.hmFriends.containsKey(currentUser)){
					if(!this.hmBlockedFriends.containsKey(currentUser))
						existingFriends.add(this.hmFriends.get(currentUser).getsName());
					
				}
				else {
				}
			}
			alPosts.add(new VideoPost(sText, lLocation, existingFriends, sFilePath, iVideoLength));
		}
	}
	public void addPostVideo(String sText, Location lLocation, String sFilePath, int iVideoLength){
		if (iVideoLength > VideoPost.getMaximumVideoDuration()) {
		}
		else {
			alPosts.add(new VideoPost(sText, lLocation, sFilePath, iVideoLength));
		}
	}
	/**
	 * This method removes the user's last post.
	 * */
	public void removeLastPost(){
		if (this.alPosts.size() > 0) {
			this.alPosts.remove(alPosts.size()-1);
		}
		else {
		}
	}
	/**
	 * This method blocks the user whose name is given as a parameter,
	 * adds him/her to our user's array list, hmBlockedFriend if the user
	 * is a friend of our users. If not, adds the user to hmBlockedUsers.
	 * @param sUserName The username of the user who will be blocked
	 * */
	public void blockUsers(String sUserName) {
		if (UserCollection.hmUserList.containsKey(sUserName)) {
			if(this.hmFriends.containsKey(sUserName)) {
				if (this.hmBlockedFriends.containsKey(sUserName)){
				}
				else {
					this.hmBlockedFriends.put(sUserName, this.hmFriends.get(sUserName));
					this.hmBlockedUsers.put(sUserName, this.hmFriends.get(sUserName));
				}
			}
			else if (this.hmBlockedUsers.containsKey(sUserName)) {
			}
				else {
					this.hmBlockedUsers.put(sUserName, UserCollection.hmUserList.get(sUserName));
				}
			}
		else{
		}
	}
	/**
	 * This method blocks the user whose name is given as a parameter,
	 * removes the user from our user's array list , hmBlockedFriend or 
	 * hmBlockedUser.
	 * @param sUserName The username of the user will be unblocked
	 * */
	public void unblockUsers(String sUserName) {
		if (this.hmBlockedFriends.containsKey(sUserName)) {
			this.hmBlockedFriends.remove(sUserName);
			this.hmBlockedUsers.remove(sUserName);
		}
		else if (this.hmBlockedUsers.containsKey(sUserName)) {
			this.hmBlockedFriends.remove(sUserName);
		}
		else {
		}
	}
	/**
	 * This method prints all the friends of the user.
	 * */
	public void listFriends() {
		if (this.hmFriends.size() == 0) {
		}
		else {
			for (String currentUser : this.hmFriends.keySet()) {
			}
		}
	}
	/**
	 * This method print all the users in the system.
	 * */
	public void listUsers(){
		for (String currentUser : UserCollection.hmUserList.keySet()) {
		}
	}
	/**
	 * This method print all the friends blocked by the user.
	 * */
	public void showBlockedFriends() {
		if (this.hmBlockedUsers.size() == 0){
		}
		else if (this.hmBlockedFriends.size() == 0) {
		}
		else {
			for (String currentFriend : this.hmBlockedFriends.keySet()) {
			}
		}
	}
	/**
	 * This method print all the users blocked by the user.
	 * */
	public void showBlockedUsers() {
		if (this.hmBlockedUsers.size() == 0){
		}
		else {
			for (String currentUser : this.hmBlockedUsers.keySet()) {
			}
		}
	}

	/**
	 * This method shows the posts of the user whose username
	 * is given as a parameter.
	 * @param sUserName The user's username.
	 * */
	public void showPosts(String sUserName){
		if (UserCollection.hmUserList.containsKey(sUserName)) {
			if (UserCollection.hmUserList.get(sUserName).getAlPosts().size() == 0) {
			}
			else{
				for (Post currentPost : UserCollection.hmUserList.get(sUserName).getAlPosts()) {
					if (currentPost instanceof ImagePost){
						ImagePost currentImagePost = (ImagePost) currentPost;
						currentImagePost.showPostLocation();
						if (currentImagePost.taggedFriends.size() > 0)
							currentImagePost.showTaggedUsers();
					}
					else if (currentPost instanceof VideoPost){
						VideoPost currentVideoPost = (VideoPost) currentPost;
						currentVideoPost.showPostLocation();
						if (currentVideoPost.taggedFriends.size() > 0)
							currentVideoPost.showTaggedUsers();
					}
					else if (currentPost instanceof TextPost) {
						currentPost.showPostLocation();
						if (currentPost.taggedFriends.size() > 0)
							currentPost.showTaggedUsers();
					}
				}
			}
		}
		else {
		}
	}
	public boolean isSignIn() {
		return signIn;
	}
	public void setSignIn(boolean signIn) {
		this.signIn = signIn;
	}
	
	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsUserName() {
		return sUserName;
	}

	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}

	public Date getdDateOfBirth() {
		return dDateOfBirth;
	}

	public void setdDateOfBirth(Date dDateOfBirth) {
		this.dDateOfBirth = dDateOfBirth;
	}

	public String getsSchoolInfo() {
		return sSchoolInfo;
	}

	public void setsSchoolInfo(String sSchoolInfo) {
		this.sSchoolInfo = sSchoolInfo;
	}

	public Date getdLastLogIn() {
		return dLastLogIn;
	}

	public void setdLastLogIn(Date dLastLogIn) {
		this.dLastLogIn = dLastLogIn;
	}

	public HashMap<String, User> getHmFriends() {
		return hmFriends;
	}

	public void setHmFriends(HashMap<String, User> hmFriends) {
		this.hmFriends = hmFriends;
	}

	public HashMap<String, User> getHmBlockedUsers() {
		return hmBlockedUsers;
	}

	public void setHmBlockedUsers(HashMap<String, User> hmBlockedUsers) {
		this.hmBlockedUsers = hmBlockedUsers;
	}

	public ArrayList<Post> getAlPosts() {
		return alPosts;
	}

	public void setAlPosts(ArrayList<Post> alPosts) {
		this.alPosts = alPosts;
	}
	public String getsRelationshipStatus() {
		return sRelationshipStatus;
	}
	public void setsRelationshipStatus(String sRelationshipStatus) {
		this.sRelationshipStatus = sRelationshipStatus;
	}
	public HashMap<String, User> getHmBlockedFriends() {
		return hmBlockedFriends;
	}
	public void setHmBlockedFriends(HashMap<String, User> hmBlockedFriends) {
		this.hmBlockedFriends = hmBlockedFriends;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

}
