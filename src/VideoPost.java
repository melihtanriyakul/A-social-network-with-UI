import java.util.ArrayList;
import java.util.Date;
/**
 * This is a subclass derived from TextPost.
 * @author Ahmet Melih Tanriyakul
 */
public class VideoPost extends TextPost {
	private static final int MAXIMUM_VIDEO_DURATION = 10000;
	private String sFilePath;
	private int iVideoLength;
	/**
	 * @param sText Post's text.
	 * @param lLocation	The user's location.
	 * @param taggedFriends The user's friends who are tagged in the post.
	 * @param sFilePath	The file path of the image.
	 * @param iVideoLength The length of the video.
	 * */
	public VideoPost(String sText, Location lLocation, ArrayList<String> taggedFriends, String sFilePath, int iVideoLength) {
		super(sText, lLocation, taggedFriends);
		Date dateAtTheMoment = new Date();
		this.sFilePath = sFilePath;
		this.iVideoLength = iVideoLength;
		this.setdDate(dateAtTheMoment);
	}
	public VideoPost(String sText, Location lLocation, String sFilePath, int iVideoLength) {
		super(sText, lLocation);
		Date dateAtTheMoment = new Date();
		this.sFilePath = sFilePath;
		this.iVideoLength = iVideoLength;
		this.setdDate(dateAtTheMoment);
	}
	public static int getMaximumVideoDuration() {
		return MAXIMUM_VIDEO_DURATION;
	}
	public String getsFilePath() {
		return sFilePath;
	}
	public void setsFilePath(String sFilePath) {
		this.sFilePath = sFilePath;
	}
	public int getiVideoLength() {
		return iVideoLength;
	}
	public void setiVideoLength(int iVideoLength) {
		this.iVideoLength = iVideoLength;
	}

}
