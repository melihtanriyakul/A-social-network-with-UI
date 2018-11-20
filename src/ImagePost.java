import java.util.ArrayList;
import java.util.Date;
/**
 * This is the subclass derived from TextPost.
 * @author Ahmet Melih Tanriyakul
 */
public class ImagePost extends TextPost{
	private String sFileName;
	private String sImageResolution;
	/**
	 * @param sText Post's text.
	 * @param lLocation	User's location.
	 * @param taggedFriends User's friends who are tagged in the post.
	 * @param sFileName	The file name of the image.
	 * @param sImageResolution The resolution of the image.
	 * */
	public ImagePost(String sText, Location lLocation, ArrayList<String> taggedFriends, String sFileName, String sImageResolution) {
		super(sText, lLocation, taggedFriends);
		Date dateAtTheMoment = new Date();
		this.sFileName = sFileName;
		this.sImageResolution = sImageResolution;
		this.setdDate(dateAtTheMoment);
	}
	public ImagePost(String sText, Location lLocation, String sFileName, String sImageResolution) {
		super(sText, lLocation);
		Date dateAtTheMoment = new Date();
		this.sFileName = sFileName;
		this.sImageResolution = sImageResolution;
		this.setdDate(dateAtTheMoment);
	}
	public String getsFileName() {
		return sFileName;
	}

	public void setsFileName(String sFileName) {
		this.sFileName = sFileName;
	}

	public String getsImageResolution() {
		return sImageResolution;
	}

	public void setsImageResolution(String sImageResolution) {
		this.sImageResolution = sImageResolution;
	}

}
