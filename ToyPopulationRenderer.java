package richard.genetic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ToyPopulationRenderer implements PopulationRenderer {

	private JPanel panel; 
	
	// TODO !!!!!!!!!!!NOt keen on setView too much like Quolos
	public void setPanel(JPanel panelIn){ 
		this.panel = panelIn;
	}
	
	public void render(Population pop, int indexOfBest) {
		
		Graphics g = panel.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 700);
		
		g.setColor(Color.GREEN);
		for(int i = 0; i < pop.size();i++){
			ToyPopulationItem item = (ToyPopulationItem)pop.get(i);
			g.drawRect(item.getX(), item.getY(), 4, 4);
		}
	}

	public void render() {
		// TODO Auto-generated method stub
		
	}

	public void setPopulation(Population pop) {
		// TODO Auto-generated method stub
		
	}

}
