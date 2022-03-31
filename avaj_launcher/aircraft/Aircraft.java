package avaj_launcher.aircraft;

import avaj_launcher.Coordinates;
import java.io.FileWriter;

public class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 1;

	public Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}

	private static long nextId() { return Aircraft.idCounter++;}
}
