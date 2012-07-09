package richard.genetic;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TravellingSalesmanPopulationItemTester {

	@Test
	public void testCrossOver() {

		final int locationCount = 24;
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(Locations.LOCATION_COUNT, locationCount);
		Environment environment = new CircleLocations();
		environment.configure(config);

		TravellingSalesmanPopulationItem popItem1 = new TravellingSalesmanPopulationItem(environment);
		popItem1.randomise();
		TravellingSalesmanPopulationItem popItem2 = new TravellingSalesmanPopulationItem(environment);
		popItem2.randomise();

		System.out.println("Item1:" + popItem1);
		System.out.println("Item2:" + popItem2);

		TravellingSalesmanPopulationItem popItem3 = (TravellingSalesmanPopulationItem)popItem1.crossOver(popItem1, popItem2);
		System.out.println("Item3:" + popItem3);

		assertTrue("Cross over result is null", popItem3 != null);

		// Check has all points
		boolean[] presentPoints = new boolean[locationCount];
		for(int i = 0; i < locationCount; i++){
			presentPoints[popItem3.getVisitPoint(i)] = true;
		}

		for(int i = 0; i < locationCount; i++){
			assertTrue("Point not present", presentPoints[i]);
		}

		int crossOverStart = popItem1.getCrossOverStart();
		int crossOverEnd = popItem1.getCrossOverEnd();

		int t = 0;
		List<Integer> fromParentOne = new LinkedList<Integer>();
		for(int i = crossOverStart; i < crossOverEnd; i++){
			System.out.print(popItem3.getVisitPoint(t) + " = " + popItem1.getVisitPoint(i) + " ");
			assertTrue("Cross over fault from parent one", popItem3.getVisitPoint(t) == popItem1.getVisitPoint(i));
			fromParentOne.add(popItem1.getVisitPoint(i));
			t++;			
		}
		System.out.println("");

		//		int[] remainingPoints = new int[locationCount - t];
		//		int r = 0;
		//		for(int i = t; i < locationCount; i++){
		//			remainingPoints[r++] = popItem3.getVisitPoint(i);
		//		}
		//		System.out.println("Remaing:"+ Arrays.toString(remainingPoints));


		System.out.println("From Parent One:" + fromParentOne);

		for(int i = 0; i < locationCount; i++){

			if(popItem3.getVisitPoint(t) == popItem2.getVisitPoint(i) ){
				System.out.println(popItem3.getVisitPoint(t) + " = " + popItem2.getVisitPoint(i));
			}


			if(fromParentOne.contains(popItem2.getVisitPoint(i))){
				System.out.println(popItem2.getVisitPoint(i) + " in parent");
			}

			assertTrue("Cross over fault from parent two", popItem3.getVisitPoint(t) == popItem2.getVisitPoint(i) ||
					fromParentOne.contains(popItem2.getVisitPoint(i)));
			t++;			
		}


	}

}
