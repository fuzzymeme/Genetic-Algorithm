package richard.genetic;

import java.awt.Point;
import java.util.Map;
import java.util.Random;

public class RandomLocations extends Locations {


	public void configure(Map<String, Object> config){

		double locationCount = (Integer) config.get(Locations.LOCATION_COUNT);
		Random random = new Random(12345);
		int range = 680;
		for(double i = 0; i < locationCount; i++){
			locations.add( new Point(random.nextInt(range) + 20, random.nextInt(range) + 40));
		}
	}
}
