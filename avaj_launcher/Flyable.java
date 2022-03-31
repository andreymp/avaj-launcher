package avaj_launcher;

import util.Writer;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower WeatherTower);
}