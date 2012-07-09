package richard.genetic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParameterTester {
	
	
	private void runTest(){
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put(Locations.LOCATION_COUNT, 24);

		Environment locations = new CircleLocations();
		locations.configure(config);

		GeneticAlgorithm ga = new GeneticAlgorithm(100, 2649, 
				new TravellingSalesmanPopulationFactory(), 
				new TravellingSalesmanPopulationRenderer(locations),
				locations);
	

		List<EvolutionReport> reports = new LinkedList<EvolutionReport>();
		for(int i = 0; i < 30; i++){
			
			ga.reset();
			ga.evolve();
			reports.add(ga.getReport());
		}
		
		// Calculate simple stats
		long totalBestHitGen = 0;
		long totalBestCost = 0;
		for(EvolutionReport report: reports){
			totalBestHitGen += report.getHitCurrentValueOnGeneration();
			totalBestCost += report.getCost();
		}
		
		System.out.println("Average Best Hit Gen:" + (totalBestHitGen / reports.size()));
		System.out.println("Average Best Cost:" + (totalBestCost / reports.size()));
	}
	
	
	public static void main(String[] args) {
		
		ParameterTester tester = new ParameterTester();
		tester.runTest();
	}

}
