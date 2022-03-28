package avaj_launcher.aircraft;

import avaj_launcher.Flyable;
import avaj_launcher.SimulationException;
import avaj_launcher.Coordinates;

public class AircraftFactory {

	public static Flyable newAircraft(String type, String name, 
		int longtitude, int latitude, int height) throws SimulationException {

		if (longtitude < 0 || latitude < 0 || height < 0)
			throw new SimulationException("ERROR: Bad coordinates");
		if (height > 100) height = 100;
		Coordinates coordinates = new Coordinates(longtitude, latitude, height);
		if (type.toLowerCase().equals("jetplane"))
			return new JetPlane(name, coordinates);
		else if (type.toLowerCase().equals("helicopter"))
			return new Helicopter(name, coordinates);
		else if (type.toLowerCase().equals("baloon"))
			return new Baloon(name, coordinates);
		else
			throw new SimulationException("ERROR: Unknown type");
	}
}