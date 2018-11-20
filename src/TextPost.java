import java.util.ArrayList;
import java.util.Date;
/**
 * This is a subclass derived from Post.
 * @author Ahmet Melih Tanriyakul
 * This class implements the two abstract methods
 * which are instantiated in the superclass, Post.
 * */
public class TextPost extends Post{
	
	/**
	 * @param sText Post's text.
	 * @param lLocation	User's location.
	 * @param existingFriends User's friends who are tagged in the post.
	 */
	public TextPost(String sText, Location lLocation, ArrayList<String> existingFriends) {
		Date dateAtTheMoment = new Date();
		this.setsText(sText);
		this.setlLocation(lLocation);
		this.taggedFriends = existingFriends;
		this.setdDate(dateAtTheMoment);
		this.setsPostID();
	}
	public TextPost(String sText, Location lLocation) {
		Date dateAtTheMoment = new Date();
		this.setsText(sText);
		this.setlLocation(lLocation);
		this.setdDate(dateAtTheMoment);
		this.setsPostID();
	}
	/**
	 * This method prints the user's friends who
	 * are tagged in the user's post.
	 * */
	@Override
	public String showTaggedUsers() {
		String sTaggedFriends = String.join(", ", taggedFriends);
		return sTaggedFriends;
	}
	/**
	 * This method print the location of the post.*/
	@Override
	public Location showPostLocation() {
		return this.getlLocation();	
	}
}
