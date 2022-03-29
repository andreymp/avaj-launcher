package avaj_launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.*;
import avaj_launcher.SimulationException;
import avaj_launcher.aircraft.*;

public class Simulation {

	private static Integer size = null;
	private static boolean flag = false;
	private static WeatherTower weatherTower = new WeatherTower();

	private static boolean containsAlhpa(String str) {
		int i = str.charAt(0) == '-' ? 0 : 1;
		for (; i < str.length(); ++i)
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				return false;
		return true;
	}

	private static void parseInput(String input) throws SimulationException {
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
		if (!containsAlhpa(arr[2]) && !containsAlhpa(arr[3]) && !containsAlhpa(arr[4]))
			throw new SimulationException("Wrong format of lattitude, longtittude or height");
		String type = arr[0];
		String name = arr[1];
		int longtitude = Integer.parseInt(arr[2]);
		int latitude = Integer.parseInt(arr[3]);
		int height = Integer.parseInt(arr[4]);
		AircraftFactory.newAircraft(type, name, longtitude, latitude, height).registerTower(weatherTower);
	}

	private static void checkArgs(String[] args) throws SimulationException {
		if (args.length != 1)
			throw new SimulationException("Wrong input!");
		if (!args[0].endsWith("scenario.txt") && !args[0].endsWith("scenario.md5"))
			throw new SimulationException("File should ends with \"scenario.txt\" or \"scenario.md5\"");
	}

	private static void readFile(File file) throws SimulationException {
		try (Scanner sc = new Scanner(file)) {
			if (!sc.hasNext())
				throw new SimulationException("You try to execute empty file");
			while (sc.hasNext())
				parseInput(sc.nextLine());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			checkArgs(args);
			File file = new File(args[0]);
			readFile(file);
			if (size != null) {
				for (int i = 1; size > 0; --size, ++i) {
					System.out.println("---------------Iteration: " + Integer.toString(i) + "------------------");
					weatherTower.changeWeather();
				}
			}
		}
		catch(SimulationException e) {
			System.out.println(e.getMessage());
		}
	}
}