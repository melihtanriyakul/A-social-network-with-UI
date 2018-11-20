/**
 * This is a superclass.
 * This class is created for Post class, 
 * will be used for Post's location.
 * @author Ahmet Melih Tanriyakul
 * */
public class Location {
	private double dLatitude;
	private double dLongitude;
	/**
	 * @param dLongitude The longtiude of the location
	 * @param dLatitude The latitude of the location
	 * */
	public Location(double dLongitude, double dLatitude) {
		this.dLongitude = dLongitude;
		this.dLatitude = dLatitude;
	}
	@Override
	public String toString() {
		return "Location: " + this.dLongitude + ", " + this.dLatitude;
	}
	public double getdLatitude() {
		return dLatitude;
	}
	public void setdLatitude(double dLatitude) {
		this.dLatitude = dLatitude;
	}
	public double getdLongitude() {
		return dLongitude;
	}
	public void setdLongitude(double dLongitude) {
		this.dLongitude = dLongitude;
	}
	
}
