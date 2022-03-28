package avaj_launcher;

import java.util.ArrayList;

public class Tower {

	private ArrayList<Flyable> objects = new ArrayList<>(); 

	public void register(Flyable fyable) { objects.add(fyable); }
	public void unregister(Flyable fyable) { objects.remove(fyable); }
	protected void conditionsChanged() {
		for (int i = 0; i < objects.size(); ++i)
			objects.get(i).updateConditions();
	}
}