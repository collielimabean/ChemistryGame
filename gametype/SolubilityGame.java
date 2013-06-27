package gametype;

import gui.GameFrame;

import java.awt.GridBagConstraints;

import chemical.Chemical;
import chemical.Chemical.Anion;
import chemical.Chemical.Cation;
import chemical.SolubilityRules;

/**
 * Defines the game type for Solubility Rules.
 * @author William Jen
 *
 */
public class SolubilityGame extends Game
{

	public SolubilityGame()
	{
		super();
		
		choice1.setText("Soluble");
		choice2.setText("Insoluble");
		
		this.addComponents();
	}

	public void addComponents()
	{
			
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(chemicalLabel, c);
		
		c.fill = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 3;
		panel.add(choice1, c);
		
		c.fill = GridBagConstraints.LINE_END;
		c.gridx = 2;
		c.gridy = 3;
		panel.add(choice2, c);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 5;
		panel.add(endDisplay, c);
		
	}

	public String getChemicalLabel()
	{
		int[] coefficients = Chemical.balance(one, two);
		
		String cation, anion;
		
		if(one.equals(Cation.NH4) && two.equals(Anion.OH))
		{
			return "NH" + this.getSubscript(3);
		}
		
		if(coefficients[0] > 1 && one.getName().length() > 2)
		{
			cation = "(" + one.getName() + ")" + this.getSubscript(coefficients[0]);
		}
		
		else
		{
			cation = one.getName() + this.getSubscript(coefficients[0]);
		}
		
		if(coefficients[1] > 1 && two.getName().length() > 1)
		{
			anion = "(" + two.getName() + ")" + this.getSubscript(coefficients[1]);
		}
		
		else
		{
			anion = two.getName() + this.getSubscript(coefficients[1]);
		}
		
		return cation + anion;
	}
	
	public void determineCorrect()
	{
		
		SolubilityRules r = new SolubilityRules();
		
		if(userChoice != r.isSoluble(one, two))
		{
			String display = "<html>Incorrect: Rule " + r.getRuleNumber() + ": ";
			display += r.getRule(r.getRuleNumber());
			display += "</html>";
			
			endDisplay.setText(display);
		}
		
		else
		{
			String display = "<html>Correct: Rule " + r.getRuleNumber();
			
			display += "</html>";
			
			endDisplay.setText(display);
		}
		
		endDisplay.validate();
		GameFrame.enableNext();
	}

}
