package richard.genetic;

public class TravellingSalesmanPopulationFactory implements PopulationFactory {

	public PopulationItem getNewObject(Environment environment) {
		return new TravellingSalesmanPopulationItem(environment);
	}

}
