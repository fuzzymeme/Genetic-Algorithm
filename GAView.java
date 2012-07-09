package richard.genetic;

import javax.swing.*;

public class GAView extends JFrame
{

	private static final long serialVersionUID = 9029641196791441534L;
	private final JPanel renderPanel;
	private final PopulationRenderer renderer;

	public GAView(final PopulationRenderer rendererIn)
	{
		super();
		this.renderer = rendererIn;
		
		renderPanel = new JPanel();
		
		renderer.setPanel(renderPanel);
		setResizable(false);

		// Create a viewer
		setSize(820, 800);

		getContentPane().add(renderPanel);
		setVisible(true);

		// Close the application when we close the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void update(final int generationCount,  final Population pop,  final int indexOfBest)
	{
//		SwingUtilities.invokeLater(new Runnable() {
//
//			public void run() {
				renderer.render();
//			}
//		});

	}

}
