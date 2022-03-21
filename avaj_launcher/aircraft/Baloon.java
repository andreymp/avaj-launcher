package avaj_launcher.aircraft;

import avaj_launcher.Coordinates;
import avaj_launcher.Flyable;
import avaj_launcher.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	public Baloon(String name, Coordinates coordinates) { super(name, coordinates); }

	@Override
	public void updateConditions() {
		int lat = this.coordinates.getLatitude();
		int height = this.coordinates.getHeight();
		int longt = this.coordinates.getLongtitude();
		if (weatherTower.getWeather(coordinates) == "SUN") {
			longt += 2;
			height += 4;
			System.out.printf("Tower says:  Baloon#%s(%ld): This is hot.\n", this.name, this.id);
		} else if (weatherTower.getWeather(coordinates) == "RAIN") {
			height -= 5;
			System.out.printf("Tower says:  Baloon#%s(%ld): So scary in this fog.\n", this.name, this.id);
		} else if (weatherTower.getWeather(coordinates) == "FOG") {
			height -= 3;
			System.out.printf("Tower says:  Baloon#%s(%ld): My rotor is going to freeze!\n", this.name, this.id);
		} else {
			height -= 15;
			System.out.printf("Tower says:  Baloon#%s(%ld): My rotor is going to freeze!\n", this.name, this.id);
		}
		if (hieght > 100) hieght = 100;
		if (longt < 0) longt = 0;
		if (lat < 0) lat = 0;
		this.coordinates = new Coordinates(longt, lat, height);
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister(this);
			System.out.printf("Tower says:  Baloon#%s(%ld) unregistered from weather tower.\n",
				this.name, this.id);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) { 
		this.weatherTower = weatherTower; 
		this.weatherTower.register(this);
		System.out.printf("Tower says: Baloon#%s(%ld) registered to weather tower.\n",
			this.name, this.id);
	}
}