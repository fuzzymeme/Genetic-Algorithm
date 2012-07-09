package richard.genetic;

import java.awt.Point;
import java.util.Map;

public class CircleLocations extends Locations{


	public void configure(Map<String, Object> config){

		double locationCount = (Integer) config.get(Locations.LOCATION_COUNT);
		double circleSize = 320;
		double iDivPi;
		for(double i = 0; i < locationCount; i++){
			iDivPi = ((Math.PI * 2.0) / locationCount) * i;
			locations.add( new Point((int)(Math.cos(iDivPi) * circleSize) + 400, (int)(Math.sin(iDivPi) * circleSize) + 400));
		}
	}
}
