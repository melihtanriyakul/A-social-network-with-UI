import java.util.HashMap;

/**
 * This is a superclass.
 * This class is responsible for managing and 
 * has several methods for that.
 * @author Ahmet Melih Tanriyakul
 * */

public class UserCollection {
	private static boolean bUserSignedIn;
	private static String currentUserName;
	
	static HashMap<String, User> hmUserList = new HashMap<String, User>();
	
	/**
	 * This method adds new user to the system, takes user's info 
	 * as a parameter.
	 * @param sUserInfo The user's informations as a string array.
	 * are not given correctly.*/
	public static void addUser(String[] sUserInfo){
		try {
			hmUserList.put(sUserInfo[2], new User(sUserInfo[1], sUserInfo[2], sUserInfo[3], Operations.stringToDate(sUserInfo[4]), sUserInfo[5], sUserInfo[6]));

		} catch (Exception e) {

		}
	}
	/**
	 * This method removes the user from the system,
	 * takes user's username as a parameter.
	 * @param sUserName The user's username.
	 * */
	public static void removeUser(String sUserName){
		if (hmUserList.containsKey(sUserName)) {
			hmUserList.remove(sUserName);
		}
		else {
		}

	}
	/**
	 * This method is created for users to sign in the system,
	 * takes the user's username and password, checks if there 
	 * is a user who has this username, if so, calls the user's 
	 * signIn method to check the user's password.
	 * @param sUserName The user's username
	 * @param sPassword The user's password
	 * @return bUserSignedIn 
	 * */
	public static boolean signIn(String sUserName, String sPassword) {
		try {
			hmUserList.get(sUserName).signIn(sPassword);
			currentUserName = hmUserList.get(sUserName).getsUserName();
			return bUserSignedIn = true;
		} catch (Exception e) {
			return bUserSignedIn = false;
		}
	}

	public static String getCurrentUserName() {
		return currentUserName;
	}

	public static void setCurrentUserName(String currentUserName) {
		UserCollection.currentUserName = currentUserName;
	}
	
}
