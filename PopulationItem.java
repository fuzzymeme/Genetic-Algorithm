package richard.genetic;


public abstract class PopulationItem
{
	protected long fitness;
	protected long cost;
	
	public abstract void randomise();

	public abstract void setValue(PopulationItem popItem);

	public abstract void mutate();
	
	public abstract PopulationItem crossOver(PopulationItem item1, PopulationItem item2);

	public abstract long eval();
	
	public long getFitness(){
		return fitness;
	}
	
	public void setFitness(long newValue){
		fitness = newValue;
	}
	
	public long getCost(){
		return cost;
	}
	
	public void setCost(long newValue){
		cost = newValue;
	}
	
}
