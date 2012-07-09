package richard.genetic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TravellingSalesmanPopulationItem extends PopulationItem {
	
	private final List<Integer> pointOrder = new ArrayList<Integer>();
	private static Random random = new Random();
	private static Locations locations;
	private static double crossOverLengthTotal = 0;
	private static double crossOverLengthTotalCount = 0;
	private long evaluation = -1;
	private int crossOverStart = -1;
	private int crossOverEnd = -1;
	
	public TravellingSalesmanPopulationItem(Environment environment){
		locations = (Locations) environment;
	}
	
	public int getPointCount(){
		return pointOrder.size();
	}
	
	public int getVisitPoint(int i){
		return pointOrder.get(i);
	}
	
	public void setVisitPoints(List<Integer> newValues){
		
		evaluation = -1;
		pointOrder.clear();
		pointOrder.addAll(newValues);

	}

	@Override
	public PopulationItem crossOver(PopulationItem item1, PopulationItem item2) {
		
		List<Integer> newOrder = new ArrayList<Integer>();
		int count = ((TravellingSalesmanPopulationItem)item1).getPointCount();
		int halfSize = pointOrder.size() / 2;
		crossOverStart = random.nextInt(pointOrder.size());
		//start = 0;
//		int breakPoint = Math.min(halfSize + random.nextInt(halfSize), pointOrder.size());
		crossOverEnd = Math.min(crossOverStart + random.nextInt(pointOrder.size()), pointOrder.size());
		//breakPoint = Math.min(start + 3, pointOrder.size());
		//System.out.println("From " + start + " to " + breakPoint);
		for(int i = crossOverStart; i < crossOverEnd; i++){
			newOrder.add(((TravellingSalesmanPopulationItem)item1).getVisitPoint(i));
		}
		
		//System.out.println("New order prt1" + newOrder);
		crossOverLengthTotal += (crossOverEnd - crossOverStart);
		//System.out.println("crossOverLengthTotal:" + crossOverLengthTotal);
		crossOverLengthTotalCount++;
		//System.out.println("Average Cross Over Length:" + (crossOverLengthTotal / crossOverLengthTotalCount));
		
		for(int i = 0; i < count; i++){
			int newPoint = ((TravellingSalesmanPopulationItem)item2).getVisitPoint(i);
			if(!newOrder.contains(newPoint)){
				newOrder.add(newPoint);
			}
		}
		
		//System.out.println("New order prt2" + newOrder);
		
		TravellingSalesmanPopulationItem newItem = new TravellingSalesmanPopulationItem(locations);
		newItem.setVisitPoints(newOrder);
		return newItem;
	}

	@Override
	public long eval() {
		
		if(evaluation != -1){
			return evaluation;
		}
		
		double total = 0;
		int p = 0;
		Point lastPoint = new Point();
		for(Integer i: pointOrder){
			Point point = locations.getPoint(i);
			if(p > 0){
				total += Locations.calcDist(lastPoint, point);
			}
			lastPoint = point;
			p++;
		}

		evaluation = (long) total;
		return (long) total;
	}

	@Override
	public void mutate() {
	
		evaluation = -1;
		int locationCount = pointOrder.size();
		int size = Math.min(2, locationCount / 8);
		for(int i = 0; i < size; i++){
			int one = random.nextInt(locationCount);
			int other = random.nextInt(locationCount);
			int swap1 = pointOrder.get(one);
			int swap2 = pointOrder.get(other);
			pointOrder.remove(one);			
			pointOrder.add(one, swap2);
			pointOrder.remove(other);
			pointOrder.add(other, swap1);
		}
		
	}

	@Override
	public void randomise() {
		
		evaluation = -1;
		pointOrder.clear();
		for(int i = 0; i < locations.getLocationCount(); i++){
			pointOrder.add(i);
		}
		
		Collections.shuffle(pointOrder);

		//show 
		//System.out.println(pointOrder);
		
	}

	@Override
	public void setValue(PopulationItem popItem) {
		
		evaluation = -1;		
		pointOrder.clear();
		TravellingSalesmanPopulationItem item = (TravellingSalesmanPopulationItem) popItem;
		for(int i = 0; i < item.getPointCount(); i++){
			pointOrder.add(item.getVisitPoint(i));
		}
	}
	
	public int getCrossOverStart(){
		return crossOverStart;
	}
	
	public int getCrossOverEnd(){
		return crossOverEnd;
	}

	public String toString(){	

		return pointOrder.toString();
	}

}
