package richard.genetic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Locations implements Environment{

	protected final List<Point> locations = new ArrayList<Point>();
	public final static String LOCATION_COUNT = "locationCount";
	
	public abstract void configure(Map<String, Object> config);
	
	public Point getPoint(int i){
		return locations.get(i);
	}
	
	public int getLocationCount(){
		return locations.size();
	}

	public static double calcDist(Point p1, Point p2){

		return Math.hypot(p1.x - p2.x, p1.y - p2.y);
	}

}
