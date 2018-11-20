import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * This class is a super class which
 * does several methods to do operations.
 * @author Ahmet Melih Tanriyakul
 * */
public class Operations {
	/**
	 * This method reads the file and 
	 * return an arraylist which contains 
	 * the lines.
	 * @param fileName The file's name
	 * @return lines The array list contains the lines
	 * @throws IOException Throws exception
	 * */
	public static ArrayList<String> readFile(String fileName) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null ) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines;
	}
	/**
	 * This method takes a string as a parameter
	 * and returns it as a date type.
	 * @param sDate Date as a string type.
	 * @return date Date as a date type.
	 * @throws ParseException Throws exception 
	 *  */
	public static Date stringToDate(String sDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = formatter.parse(sDate);
	    return date;
	}
	/**
	 * This method is created to assign users in
	 * the given text files to the hashmap, hmUserList.
	 * @param userList The array list which contains users' informations.
	 * */
	public static void assignUsers(ArrayList<String> userList){
		for (String currentUser : userList) {
			String[] sUserInfo = currentUser.split("	");
			  try 
			    {
			      UserCollection.hmUserList.put(sUserInfo[1], new User(sUserInfo[0], sUserInfo[1], sUserInfo[2], stringToDate(sUserInfo[3]), sUserInfo[4], sUserInfo[5]));
			    } 
			    catch (Exception e)
			    {}
		}
	}
	/**
	 * This method implements the commands which are given
	 * in 'commands.txt' with respect to command's first string.
	 * @throws ParseException Throws parse exception
	 * @param commandList The array list which contains commands.
	 * */
	public static void implementCommands(ArrayList<String> commandList) throws ParseException {
		for (String command : commandList) {
			String[] commandInfo = command.split("	");
			switch (commandInfo[0]) {
			case "ADDFRIEND"	:
				try {
					String sFirstUser = commandInfo[1];
					String sSecondUser = commandInfo[2];
					UserCollection.hmUserList.get(sFirstUser).addFriend(sSecondUser);
					UserCollection.hmUserList.get(sSecondUser).addFriend(sFirstUser);
			} catch (Exception e) {
			}
				break;
			case "ADDPOST-TEXT"	:
					String[] sTaggedFriends = commandInfo[5].split(":");
					List<String> sampleList = Arrays.<String>asList(sTaggedFriends);
					ArrayList<String> alTaggedFriends =  new ArrayList<String>(sampleList);					
					UserCollection.hmUserList.get(commandInfo[1]).addPostText(commandInfo[2],
							new Location(Double.parseDouble(commandInfo[3]), Double.parseDouble(commandInfo[4])), alTaggedFriends);
				break;
			case "ADDPOST-IMAGE":
					String[] sTaggedFriendsForImage = commandInfo[5].split(":");
					List<String> sampleListForImage = Arrays.<String>asList(sTaggedFriendsForImage);
					ArrayList<String> alTaggedFriendsForImage =  new ArrayList<String>(sampleListForImage);
					UserCollection.hmUserList.get(commandInfo[1]).addPostImage(commandInfo[2],
							new Location(Double.parseDouble(commandInfo[3]), Double.parseDouble(commandInfo[4])), 
							alTaggedFriendsForImage, commandInfo[6], commandInfo[7]);
				break;
			case "ADDPOST-VIDEO":
					String[] sTaggedFriendsForVideo = commandInfo[5].split(":");
					List<String> sampleListForVideo = Arrays.<String>asList(sTaggedFriendsForVideo);
					ArrayList<String> alTaggedFriendsForVideo =  new ArrayList<String>(sampleListForVideo);
					UserCollection.hmUserList.get(commandInfo[1]).addPostVideo(commandInfo[2],
							new Location(Double.parseDouble(commandInfo[3]), Double.parseDouble(commandInfo[4])), 
							alTaggedFriendsForVideo, commandInfo[6], Integer.parseInt(commandInfo[7]));
				break;
			case "BLOCKFRIEND"		:
				String sFirstUser1 = commandInfo[1];
				String sSecondUser1 = commandInfo[2];
				UserCollection.hmUserList.get(sFirstUser1).blockUsers(sSecondUser1);
				UserCollection.hmUserList.get(sSecondUser1).blockUsers(sFirstUser1);
				break;
			default:
				break;
			}
		}
	}
}
