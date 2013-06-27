package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
/**
 * This class contains the main method for the overall game.
 * @author William Jen
 */
public class GameRunner 
{
	
	private static GameFrame frame;
	private static JPanel panel;
	
	/**
	 * Sets the look of the program to the OS - Windows applications look like Windows, and
	 * Macintosh programs look like Mac programs.
	 */
	private static void setDesign()
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		
		//bad exception quashing, but doesn't matter in this case
		catch (Exception e) { }
	}
	
	/**
	 * Returns the GameFrame.
	 */
	public static GameFrame getGameFrame()
	{
		return frame;
	}
	
	/**
	 * Returns the inner JPanel.
	 */
	public static JPanel getJPanel()
	{
		return panel;
	}
	
	/**
	 * @param newPanel - the JPanel that will replace the old Panel
	 * Replaces original panel in GameFrame with a new JPanel supplied in parameter
	 */
	public static void setJPanel(JPanel newPanel)
	{
		frame.getContentPane().remove(panel);
		panel = newPanel;
		frame.getContentPane().add(panel);
		frame.validate();
	}
	
	
	public static void main(String[] args)
	{
		
		setDesign();
		
		frame = new GameFrame("Chemistry Game");
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

		frame.add(panel);
		frame.setVisible(true);

	}
	
}
