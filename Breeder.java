package richard.genetic;

import java.util.Random;


public class Breeder {

	private	final int percentageToCrossOver = 10; // TODO Cannot crossover more than 50%!
	private final int percentageToCopy = 10;
	private final int percentageToMutate = 5;

	private final Random random = new Random();

	public void breed(Population pop, int bestOnesIndex)
	{
		// Could simply copy the best to the rest of the population and then mutate
//		copy(bestOnesIndex, pop);
//		mutatePercentage(bestOnesIndex, pop);

		// Standard and simple breeding technique - cross over with mutation
		copy(bestOnesIndex, pop);
		crossOver(bestOnesIndex, pop);    
		mutatePercentage(bestOnesIndex, pop);

	}

	private void crossOver(int bestOnesIndex, Population pop){

		int numberToCrossOver = (pop.size() / 100) * percentageToCrossOver; 
		for(int i = 0; i < numberToCrossOver; i++){
			if(i != bestOnesIndex){
				pop.get(i + numberToCrossOver).setValue(crossOverPair(pop.get(i + 1), pop.get(i)));
			}
		}
	}

	private PopulationItem crossOverPair(PopulationItem item1, PopulationItem item2){
		return item1.crossOver(item1, item2);
	}

	private void copy(int index, Population pop)
	{
		int i = 0;
		int start = 100 - ((pop.size() / 100) * percentageToCopy);
		for(i = start; i < pop.size(); i++){
			if(i != index){
				pop.copyOver(index, i);
			}
		}
	}

	private void mutatePercentage(int immuneIndex, Population pop)
	{

		int numberToMutate = ((pop.size() / 100) * percentageToMutate);
		int mutationIndex;
		for(int i = 0; i < numberToMutate; i++){
			
			mutationIndex = random.nextInt(pop.size());
			if(mutationIndex != immuneIndex){
				pop.get(mutationIndex).mutate();
			}
		}

	}

}
