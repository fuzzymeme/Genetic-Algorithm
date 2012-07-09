package richard.genetic;

public class FaceDetectionPopulationFactory implements PopulationFactory
{      
	public PopulationItem getNewObject(Environment environment)
	{
		return new FacePopulationItem(environment);
	}
}
