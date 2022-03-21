package avaj_launcher;

public class WeatherProvider {

	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

	private WeatherProvider() {}

	public static final WeatherProvider getProvider() {
		return weatherProvider;
	}

	public static final String getCurrentWeather(Coordinates coordinartes) {
		return weather[(coordinartes.getHeight() + coordinartes.getLatitude() +
			coordinartes.getLongtitude()) % 4];
	}

}