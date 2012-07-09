package richard.genetic;

public class Evaluator 
{
	public void evalAndSort(Population pop)
	{
		
		for(int i = 0; i < pop.size(); i++){
			pop.setCost(i, pop.get(i).eval());
		}

		pop.sort();
	}
}
