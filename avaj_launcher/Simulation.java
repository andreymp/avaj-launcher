package avaj_launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import exceptions.*;
import avaj_launcher.aircraft.*;

public class Simulation {

	private static Integer size = null;
	private static boolean flag = false;
	private static boolean hasAircraft = false;
	private static WeatherTower weatherTower = new WeatherTower();

	private static boolean containsAlhpa(String str) {
		int i = str.charAt(0) == '-' ? 1 : 0;
		for (; i < str.length(); ++i)
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				return true;
		return false;
	}

	private static void parseInput(String input, String filename) throws SimulationException, MD5Exception {
		String[] arr = input.split("\\s+");
		if (!flag) {
			if (arr.length != 1)
				throw new SimulationException("First line should have Simulation size");
			size = Integer.parseInt(arr[0]);
			if (size <= 0)
				throw new SimulationException("Simulation size should be positive number");
			flag = true;
			return ;
		}
		if (arr.length != 5)
			throw new SimulationException("Wrong format of input line");
		if (!containsAlhpa(arr[2]) && !containsAlhpa(arr[3]) && !containsAlhpa(arr[4])) {
			String type = filename.endsWith(".md5") ? MD5.decrypt(arr[0]) : arr[0];
			String name = arr[1];
			int longtitude = Integer.parseInt(arr[2]);
			int latitude = Integer.parseInt(arr[3]);
			int height = Integer.parseInt(arr[4]);
			AircraftFactory.newAircraft(type, name, longtitude, latitude, height).registerTower(weatherTower);
			if (!hasAircraft) hasAircraft = true;
		} else
			throw new SimulationException("Wrong format of lattitude, longtittude or height");
	}

	private static void checkArgs(String[] args) throws SimulationException {
		if (args.length != 1)
			throw new SimulationException("Wrong input!");
		if (!args[0].endsWith("scenario.txt") && !args[0].endsWith("scenario.md5"))
			throw new SimulationException("File should ends with \"scenario.txt\" or \"scenario.md5\"");
	}

	private static void readFile(String filename) throws SimulationException, MD5Exception {
		File file = new File(filename);
		try (Scanner sc = new Scanner(file)) {
			if (!sc.hasNext())
				throw new SimulationException("You try to execute empty file");
			while (sc.hasNext())
				parseInput(sc.nextLine(), filename);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			checkArgs(args);
			readFile(args[0]);
			if (size != null && hasAircraft) {
				for (int i = 1; size > 0; --size, ++i) {
					System.out.println("---------------Iteration: " + Integer.toString(i) + "------------------");
					weatherTower.changeWeather();
				}
			}
		}
		catch(SimulationException | MD5Exception e) {
			System.out.println(e.getMessage());
		}
	}
}