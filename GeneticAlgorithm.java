package richard.genetic;

import java.util.HashMap;
import java.util.Map;

public class GeneticAlgorithm 
{
	private GAView gaView = null;
	private Population pop = null;
	private final Evaluator evaluator = new Evaluator();
	private final Breeder breeder = new Breeder();

	private int populationSize = 10; 
	private int numberOfGenerations = 10;
	private final PopulationFactory populationFactory;
	private final Environment environment;
	private final PopulationRenderer renderer;

	private boolean show = true;
	
	private long target = 1918;
	private long hitTargetOnGeneration = -1;
	private EvolutionReport report;

	public GeneticAlgorithm(int populationSize,  int numberOfGenerations, PopulationFactory populationFactory, 
			PopulationRenderer renderer, Environment environment)
	{
		this.populationSize = populationSize;
		this.numberOfGenerations = numberOfGenerations;
		this.populationFactory = populationFactory;
		this.environment = environment;
		this.renderer = renderer;
		gaView = new GAView(renderer);
	}

	public void reset() {
		
		report = new EvolutionReport();
		hitTargetOnGeneration = -1;
		createPopulation();
	}
	
	public EvolutionReport getReport(){
		return report;
	}
	
	public void createPopulation()
	{
		pop = new Population(populationSize, populationFactory, environment);
		renderer.setPopulation(pop);
	}

	public void evolve()
	{
		evaluator.evalAndSort(pop);

		int i = 0;
		long lastPaintTime = 0;
		double frameRate = 1.8;
		int throttleTime = Math.min(400, numberOfGenerations);
		int minThrottle = 20;
		if(!show){
			throttleTime = minThrottle;
		}
		
		long before = System.currentTimeMillis();
		long lastBest = Long.MAX_VALUE;
		for(i = 0; i < numberOfGenerations && hitTargetOnGeneration == -1; i++){

			breeder.breed(pop, 0);
			evaluator.evalAndSort(pop);
			pop.setGenerationCount(i);

			if((System.currentTimeMillis() - lastPaintTime) > (1000.0 / frameRate) || i == (numberOfGenerations - 1)){
				if(show){
					gaView.update(i, pop, 0);	
					lastPaintTime = System.currentTimeMillis();
					try {
						Thread.sleep(throttleTime);
					} catch (Exception e) {}
					throttleTime-= 40;
					System.out.println("Gen:" + i + " Best value:" + pop.get(0).eval());
				}
				
				
			}
			
			throttleTime = Math.max(throttleTime, minThrottle);
			if(pop.get(0).eval() == target && hitTargetOnGeneration == -1){
				hitTargetOnGeneration = i;
				System.out.println("Hit target on generation:" + i);
				gaView.update(i, pop, 0);	
			}
			
			// TODO Should make the cached eval explicit! 
			if(lastBest != pop.get(0).eval()){
				lastBest = pop.get(0).eval();
				report.setCost(pop.get(0).eval());
				report.setHitCurrentValueOnGeneration(i);
			}
		}
		
		//System.out.println("Gen:" + i + " Best value:" + pop.get(0).eval());
		System.out.println("Elapsed time:" + ((System.currentTimeMillis() - before) / 1000.0));
		
		System.out.println(report);
	}

	public static void main(String[] args)
	{	

		Map<String, Object> config = new HashMap<String, Object>();
		config.put(Locations.LOCATION_COUNT, 24);

		Environment locations = new RandomLocations();
		locations.configure(config);

		GeneticAlgorithm ga = new GeneticAlgorithm(100, 8649, 
				new TravellingSalesmanPopulationFactory(), 
				new TravellingSalesmanPopulationRenderer(locations),
				locations);

		ga.reset();

		ga.evolve();

	}

	

}
