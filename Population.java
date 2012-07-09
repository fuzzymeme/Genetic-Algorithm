package richard.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Population {

	private int pop_size = 10;
	private long generation = 0;
	private final List<PopulationItem> pop = new ArrayList<PopulationItem>();
	private PopulationItemComparator populationItemComparator = new PopulationItemComparator();
	
	private static enum EvalType{COST, FITNESS};
	private EvalType costOrFitness = EvalType.COST;

	public Population(int pop_size, PopulationFactory popFactory, Environment environment)
	{
		this.pop_size = pop_size;

		for(int i = 0; i < pop_size; i++){
			PopulationItem item = popFactory.getNewObject(environment);
			pop.add(item);
			item.randomise();
		}
	}

	public void setGenerationCount(final int i){
		generation = i;
	}
	
	public long getGenerationCount(){
		return generation;
	}
	
	public int size()
	{
		return pop_size;
	}

	public PopulationItem get(int index)
	{
		return pop.get(index);
	}

	public void setFitness(int i, long value)
	{
		pop.get(i).setFitness(value);
	}

	public long getCost(int i)
	{
		return pop.get(i).getCost();
	}

	public void setCost(int i, long value)
	{
		pop.get(i).setCost(value);
	}

	public void copyOver(int from, int to)
	{
		pop.get(to).setValue(pop.get(from));
	}

	public int getIndexOfHighestFitness()
	{
		if(costOrFitness == EvalType.COST){
			System.out.println("Should use cost");
			System.exit(0);
		}
		long bestValue = Long.MIN_VALUE;
		int bestIndex = -1;
		int i = 0;
		for(i = 0; i < pop_size; i++){
			if(pop.get(i).getFitness() > bestValue){
				bestIndex = i;
				bestValue = pop.get(i).getFitness();
			}
		}
		return bestIndex;
	}

	public void sort(){

		Collections.sort(pop, populationItemComparator);
	}

	public String toString(){

		StringBuffer buffer = new StringBuffer("Population...\n");

		int i = 0;

		for(PopulationItem item: pop){
			if(costOrFitness == EvalType.FITNESS){
				buffer.append("No." + i + " Fitness = " +  item.getFitness() + " Code ");
			}
			else{
				buffer.append("No." + i + " Cost = " +  item.getCost() + " Code ");
			}
			buffer.append("" + item + ",");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	private class PopulationItemComparator implements Comparator<PopulationItem>{

		public int compare(PopulationItem o1, PopulationItem o2){
			if(o1.getCost() > o2.getCost()){
				return 1;
			}

			if(o1.getCost() < o2.getCost()){
				return -1;
			}
			return 0;
		}
	}
}
