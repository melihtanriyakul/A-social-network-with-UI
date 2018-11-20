import java.util.Date;
import java.util.UUID;
/**
 * This is an interface which is
 *  implemented by Post class.
 * @author Ahmet Melih Tanriyakul
*/
public interface PostInterface {
	public void setsText(String sText);
	public String getsText();
	public UUID getsPostID();
	public Date getdDate();
	}
