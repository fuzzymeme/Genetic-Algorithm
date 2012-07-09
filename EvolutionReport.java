package richard.genetic;

public class EvolutionReport {
	
	private long hitCurrentValueOnGeneration = -1;
	private long cost = -1;
	
	
	public long getHitCurrentValueOnGeneration() {
		return hitCurrentValueOnGeneration;
	}
	
	public void setHitCurrentValueOnGeneration(long hitCurrentValueOnGeneration) {
		this.hitCurrentValueOnGeneration = hitCurrentValueOnGeneration;
	}
	
	public long getCost() {
		return cost;
	}
	
	public void setCost(long cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Report:\n\tHit Best On Generation="
				+ hitCurrentValueOnGeneration + "\n\tCost=" + cost;
	}

}
