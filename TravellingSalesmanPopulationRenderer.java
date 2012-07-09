package richard.genetic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JPanel;


public class TravellingSalesmanPopulationRenderer implements PopulationRenderer {

	private JPanel panel;
	private static Color c1 = new Color(0, 255, 0, 30);
	private final Stroke normalStroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	private final Stroke bestStroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	private Graphics2D graphics;
	private Image offScreenImage;
	private final Locations locations;
	private Population population;
	
	public TravellingSalesmanPopulationRenderer(final Environment environment){
		this.locations = (Locations) environment;
	}
	
	public void setPopulation(Population population){
		this.population = population;
	}
	
	public void render() {
		
		if(population == null){
			return;
		}
		
		if(offScreenImage == null){
			initView();
		}
		graphics.setColor(Color.BLACK);	
		graphics.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		
		graphics.setColor(c1);
		graphics.setStroke(normalStroke);
		
		// Makes the assumption that the items are sorted
		for(int i = 1; i < population.size();i++){
			drawItem(graphics,(TravellingSalesmanPopulationItem)population.get(i));
		}
		
		graphics.setColor(Color.RED);
		graphics.setStroke(bestStroke);
		drawItem(graphics,(TravellingSalesmanPopulationItem)population.get(0));
		
		drawLocations(graphics);
		drawLegend(graphics);
		panel.getGraphics().drawImage(offScreenImage, 0, 0, null);

	}
	
	private void drawLegend(Graphics g){
		graphics.setColor(Color.GREEN);
		g.drawString("Generation:" + population.getGenerationCount(), 20, 20);
		g.drawString("Best Score:" + population.get(0).getCost(), 20, 35);
	}
	
	private void drawLocations(Graphics g){

		g.setColor(Color.BLUE);		
		for(int i = 0; i < locations.getLocationCount(); i++){
			Point point = locations.getPoint(i);
			g.drawRect(point.x - 2, point.y - 2, 4, 4);
		}
	}
	
	private void drawItem(Graphics g, TravellingSalesmanPopulationItem item){
		
		Point lastPoint = new Point();
		for(int p = 0; p < item.getPointCount(); p++){

			Point point = locations.getPoint(item.getVisitPoint(p));
			if(p > 0){
				g.drawLine(lastPoint.x, lastPoint.y, point.x, point.y);
			}
			lastPoint = point;
		}
	}
	
	private void initView(){
		offScreenImage = panel.createImage(panel.getWidth(), panel.getHeight());
		graphics = (Graphics2D) offScreenImage.getGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void setPanel(JPanel panelIn){ 
		this.panel = panelIn;
	}
}
