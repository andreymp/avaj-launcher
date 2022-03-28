package avaj_launcher.aircraft;

import avaj_launcher.Coordinates;
import avaj_launcher.Flyable;
import avaj_launcher.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public JetPlane(String name, Coordinates coordinates) { super(name, coordinates); }

	@Override
	public void updateConditions() {
		int lat = this.coordinates.getLatitude();
		int height = this.coordinates.getHeight();
		int longt = this.coordinates.getLongtitude();
		if (weatherTower.getWeather(coordinates) == "SUN") {
			lat += 10;
			height += 2;
			System.out.printf("JetPlane#%s(%d): Flying to Dubai :)\n", this.name, this.id);
		} else if (weatherTower.getWeather(coordinates) == "RAIN") {
			lat += 5;
			System.out.printf("JetPlane#%s(%d): Too hard to safe coordination...\n", this.name, this.id);
		} else if (weatherTower.getWeather(coordinates) == "FOG") {
			lat += 1;
			System.out.printf("JetPlane#%s(%d): Damn... My eyes :(\n", this.name, this.id);
		} else {
			height -= 7;
			System.out.printf("JetPlane#%s(%d): Time for skiing :)\n", this.name, this.id);
		}
		if (height > 100) height = 100;
		if (longt < 0) longt = 0;
		if (lat < 0) lat = 0;
		this.coordinates = new Coordinates(longt, lat, height);
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister(this);
			System.out.printf("Tower says: JetPlane#%s(%d) unregistered from weather tower.\n",
				this.name, this.id);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) { 
		this.weatherTower = weatherTower; 
		this.weatherTower.register(this);
		System.out.printf("Tower says: JetPlane#%s(%d) registered to weather tower.\n",
			this.name, this.id);
	}
}