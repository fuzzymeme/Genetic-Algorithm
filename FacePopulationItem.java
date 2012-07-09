package richard.genetic;

import java.util.Random;

public class FacePopulationItem extends PopulationItem
{    

	private int x = 0;
	private int y = 0;
	private int scalePercentage = 100; 
	private int maxX = 100;
	private int maxY = 100;
	private static Random rand = new Random();

	public FacePopulationItem(Environment environment){}
	
	public int getX()
	{
		return x;
	}

	public void setX(int newValue)
	{
		x = newValue;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int newValue)
	{
		y = newValue;
	}

	public int getScalePercentage()
	{
		return scalePercentage;
	}

	public void setScalePercentage(int newValue)
	{
		scalePercentage = newValue;
	}

	public void randomise()
	{
		x = rand.nextInt(maxX);
		y = rand.nextInt(maxY);
		scalePercentage = rand.nextInt(101);
	}

	public void setValue(PopulationItem popItem)
	{
		if(popItem instanceof FacePopulationItem){
			FacePopulationItem pi = (FacePopulationItem) popItem;
			x = pi.getX();
			y = pi.getY();
			scalePercentage = pi.getScalePercentage();
		}
	}

	public void mutate()
	{
		x = mutate(x, 4, maxX);
		y = mutate(y, 4, maxY);
		scalePercentage = mutate(scalePercentage, 4, 100);

	}

	private int mutate(int value, 
			int plusOrMinus, 
			int maxValue)
	{
		value += (rand.nextInt(plusOrMinus * 2) - plusOrMinus);
		if(value < 0){
			value = 0;
		}
		if(value > maxValue){
			value = maxValue;
		}
		return value;
	}

	public long eval()
	{
		long v = Math.abs(38 - x);
		v+= Math.abs(64 - y);
		v+= Math.abs(32 - scalePercentage);
		return v;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("x = " + x);
		buffer.append(", y = " + y);
		buffer.append(", scalePercentage = " + scalePercentage);
		return buffer.toString();
	}

	@Override
	public PopulationItem crossOver(PopulationItem item1, PopulationItem item2) {
		// TODO Auto-generated method stub
		return null;
	}

}
