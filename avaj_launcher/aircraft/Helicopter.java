package avaj_launcher.aircraft;

import avaj_launcher.Coordinates;
import avaj_launcher.Flyable;
import avaj_launcher.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public Helicopter(String name, Coordinates coordinates) { super(name, coordinates); }

	@Override
	public void updateConditions() {
		int lat = this.coordinates.getLatitude();
		int height = this.coordinates.getHeight();
		int longt = this.coordinates.getLongtitude();
		if (weatherTower.getWeather(coordinates) == "SUN") {
			longt += 10;
			height += 2;
		} else if (weatherTower.getWeather(coordinates) == "RAIN")
			longt += 5;
		else if (weatherTower.getWeather(coordinates) == "FOG")
			longt += 1;
		else
			height -= 12;
		if (hieght > 100)
			hieght = 100;
		if (longt < 0) 
			longt = 0;
		if (lat < 0) 
			lat = 0;
		this.coordinates = new Coordinates(longt, lat, height);
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister(this);
			System.out.printf("Tower says:  Helicopter#%s(%ld) unregistered from weather tower.\n",
				this.name, this.id);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) { 
		this.weatherTower = weatherTower; 
		this.weatherTower.register(this);
		System.out.printf("Tower says: Helicopter#%s(%ld) registered to weather tower.\n",
			this.name, this.id);
	}
}