package avaj_launcher;

import java.io.File;
import java.util.Scanner;
import java.security.*;
import avaj_launcher.SimulationException;
import avaj_launcher.aircraft.*;

public class Simulation {

	private static Integer size = null;
	private static WeatherTower weatherTower = new WeatherTower();

	public static void main(String[] args) throws SimulationException {
		if (args.length != 1)
			throw new SimulationException("Wrong input!");
		if (!args[0].equals("scenario.txt") && !args[0].equals("scenario.md5"))
			throw new SimulationException("File should be \"scenario.txt\" or \"scenario.md5\"");
		System.out.println(args[0]);
		File file = new File(args[0]);
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				if (size == null)
					size = Integer.parseInt(sc.next());
				else {
					String type = sc.next().trim();
					String name = sc.next().trim();
					int longtitude = Integer.parseInt(sc.next().trim());
					int latitude = Integer.parseInt(sc.next().trim());
					int height = Integer.parseInt(sc.next().trim());
					AircraftFactory.newAircraft(type, name, longtitude, latitude, height).registerTower(weatherTower);
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			while (size-- > 0) {
				System.out.println("---------------" + Integer.toString(size) + "------------------");
				weatherTower.changeWeather();
			}
		}
	}
}