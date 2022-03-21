package avaj_launcher;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import avaj_launcher.SimulationException;
import avaj_launcher.aircraft.*;

public class Simulation {

	private static Integer size = null;
	private static WeatherTower weatherTower = new WeatherTower();

	public static void main(String[] args) throws SimulationException {
		if (args.length != 1)
			return ;
		File file = new File(args[0]);
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				if (size == null)
					size = Integer.parseInt(sc.nextLine);
				else {
					String type = sc.next().trim();
					String name = sc.next().trim();
					int longtitude = Integer.parseInt(sc.next());
					int latitude = Integer.parseInt(sc.next());
					int height = Integer.parseInt(sc.next());
					Flyable aircraft = AircraftFactory.newAircraft(type, name, longtitude, latitude, height);
					aircraft.registerTower(weatherTower);
				}
			}
		} catch(Exception e) {
			System.out.println("Exception occured while parsing");
		} finally {
			while (size-- > 0)
				weatherTower.changeWeather();
		}
	}
}