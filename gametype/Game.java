package gametype;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chemical.Chemical.Anion;
import chemical.Chemical.Cation;

/**
 * Overall superclass for all games. By default, it has two choices for the user, although more choices can be
 * implemented in subclasses.
 * @author William Jen
 *
 */
public abstract class Game implements ActionListener
{
	
	protected GridBagLayout layout = new GridBagLayout();
	
	protected JPanel panel;
	protected JLabel chemicalLabel;
	protected JButton choice1;
	protected JButton choice2;
	
	protected JLabel endDisplay;
	
	protected Cation one;
	protected Anion two;
	
	protected boolean userChoice; // true if choice1 pushed, false if choice2
	
	/**
	 * Default constructor. The addComponents() method must be called in the subclass constructor, NOT in the
	 * superclass constructor. Doing so will result in a NullPointerException if more user choices are to be implemented,
	 * as in the subclass AcidBaseGame.
	 */
	public Game()
	{
		
		//initialize and randomly get chemicals so that chemicalLabel can be initialized
		one = Cation.getCation((int) (Math.random() * Cation.values().length));
		two = Anion.getAnion((int) (Math.random() * Anion.values().length));
		
		//initialize swing components
		panel = new JPanel();
		panel.setLayout(layout);
		
		chemicalLabel = new JLabel(this.getChemicalLabel());
		chemicalLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 48));
		choice1 = new JButton();
		choice2 = new JButton();
				
		endDisplay = new JLabel();
		
		//actionlisteners to buttons
		choice1.addActionListener(this);
		choice2.addActionListener(this);
		
	}
	
	/**
	 * Returns the JPanel used.
	 */
	public JPanel getJPanel()
	{
		return panel;
	}
	
	/**
	 * Converts an digit into the Unicode subscript (U+2080 to U+2089)
	 * @throws IllegalArgumentException if number > 9  or number < 0
	 * @param number
	 * @return Unicode subscript
	 */
	protected String getSubscript(int number)
	{
		
		char unicode = ' ';
		
		switch(number)
		{
			case 1:
				break;
				
			case 2:
				unicode = '\u2082';
				break;
				
			case 3:
				unicode = '\u2083';
				break;
				
			case 4:
				unicode = '\u2084';
				break;
				
			case 5:
				unicode = '\u2085';
				break;
				
			case 6:
				unicode = '\u2086';
				break;
				
			case 7:
				unicode = '\u2087';
				break;
				
			case 8:
				unicode = '\u2088';
				break;
				
			case 9:
				unicode = '\u2089';
				break;
				
			case 0:
			default:
					throw new IllegalArgumentException();
		}
		
		if(unicode == ' ')
		{
			return "";
		}
		
		else 
		{
			return Character.toString(unicode);
		}
	}
	
	/**
	 * Default actions for clicking the two buttons on the parent JPanel.
	 */
	public void actionPerformed(ActionEvent arg0)
	{
	
		Object src = arg0.getSource();
		
		if(src == choice1)
		{
			userChoice = true;
			this.determineCorrect();
		}
		
		else if(src == choice2)
		{
			userChoice = false;
			this.determineCorrect();
			
		}
	}
	
	/**
	 * Adds Components to the parent JPanel.
	 */
	public abstract void addComponents();
	
	/**
	 * Takes the Cation's name and Anion's name and balances the two so that a compound is formed.
	 * All appropriate polyatomic ions with a coefficient > 1 are properly placed with parentheses.
	 * All numbers are appropriately converted into subscripts.
	 * 
	 * @return String chemical label
	 */
	public abstract String getChemicalLabel();
	
	/**
	 * Takes the value returned from the user's button click, and determines if their answer
	 * is correct according to generated rules provided by other classes.
	 */
	public abstract void determineCorrect();
	
}
