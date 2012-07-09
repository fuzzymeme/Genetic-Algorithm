package richard.genetic;

import java.util.Random;

public class ToyPopulationItem extends PopulationItem{
	
	private int x = 0;
	private int y = 0;
	private Random random = new Random(); 
	
	public ToyPopulationItem(Environment environment){}
	
	public void randomise(){
		x = random.nextInt(1000);
		y = random.nextInt(1000);
	}

	public void setValue(PopulationItem popItem){
		x = ((ToyPopulationItem)popItem).getX();
		y = ((ToyPopulationItem)popItem).getY();
	}

	public void mutate(){
		x += random.nextInt(10) - 5;
		y += random.nextInt(10) - 5;
	}

	public PopulationItem crossOver(PopulationItem item1, PopulationItem item2){
		ToyPopulationItem item = new ToyPopulationItem(null);
		item.setX(((ToyPopulationItem)item1).getX());
		item.setY(((ToyPopulationItem)item2).getY());
		return item;
	}
	
	public long eval(){
		return Math.abs(500 - x) + Math.abs(500 - y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
