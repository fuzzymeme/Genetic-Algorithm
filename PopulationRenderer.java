package richard.genetic;

import javax.swing.JPanel;

public interface PopulationRenderer {

	public void setPanel(JPanel panelIn);
	public void render();
	public void setPopulation(Population pop);
	
}
