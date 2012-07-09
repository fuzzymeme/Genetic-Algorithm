package richard.genetic;

public class ToyPopulationFactory implements PopulationFactory {

	
	public PopulationItem getNewObject(Environment environment){
		return new ToyPopulationItem(environment);
	}
}
