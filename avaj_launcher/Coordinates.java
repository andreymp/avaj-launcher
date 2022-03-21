package avaj_launcher;

public class Coordinates {
	private int longtitude;
	private int latitude;
	private int height;

	public Coordinates(int longtitude, int latitude, int height) {
		this.longtitude = longtitude;
		this.latitude = latitude;
		this.height = height;
	}

	public final int getLongtitude() { return longtitude; }
	public final int getLatitude() { return latitude; }
	public final int getHeight() { return height; }
}