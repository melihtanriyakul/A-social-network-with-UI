import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
/**
 * This is an abstract class which implements
 * the interface, PostInterface.
 * This class has two abstract method which will
 * be implemented in its subclass.
 * @author Ahmet Melih Tanriyakul
 *  */
public abstract class Post implements PostInterface{
	private UUID sPostID;
	private String sText;
	private Date dDate;
	private Location lLocation;
	ArrayList<String> taggedFriends = new ArrayList<String>();
	
	public abstract String showTaggedUsers();
	public abstract Location showPostLocation();
	
	@Override
	public String toString() {
		return this.getsText() + "\nDate: " + this.getdDate();
	}
	
	public UUID getsPostID() {
		return sPostID;
	}
	public void setsPostID() {
		this.sPostID = UUID.randomUUID();
	}
	public String getsText() {
		return sText;
	}
	public void setsText(String sText) {
		this.sText = sText;
	}
	public Date getdDate() {
		return dDate;
	}
	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}
	public Location getlLocation() {
		return lLocation;
	}
	public void setlLocation(Location lLocation) {
		this.lLocation = lLocation;
	}
	public ArrayList<String> getTaggedFriends() {
		return taggedFriends;
	}
	public void setTaggedFriends(ArrayList<String> taggedFriends) {
		this.taggedFriends = taggedFriends;
	}

}
