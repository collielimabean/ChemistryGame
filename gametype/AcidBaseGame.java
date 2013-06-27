package gametype;

import gui.GameFrame;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import chemical.AcidBaseRules;
import chemical.Chemical;
import chemical.Chemical.Anion;
import chemical.Chemical.Cation;

/**
 * Defines the gametype for strong/weak acids/bases;
 */
public class AcidBaseGame extends Game
{
	
	private JButton weakAcid;
	private JButton weakBase;
	
	/**
	 * Index of button clicked. 0 = choice1, 1 = weakAcid, 2 = choice2, 3 = weakBase
	 * 
	 */
	private int buttonIndex;
	
	public AcidBaseGame()
	{
		super();
		
		this.setChemical(); 
		
		chemicalLabel = new JLabel(this.getChemicalLabel());
		chemicalLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 48));
		
		choice1.setText("Strong Acid");
		choice2.setText("Strong Base");
		
		weakAcid = new JButton("Weak Acid");
		weakBase = new JButton("Weak Base");
		
		weakAcid.addActionListener(this);
		weakBase.addActionListener(this);

		this.addComponents();

	}
	
	public String getChemicalLabel() 
	{
		int[] coefficients = Chemical.balance(one, two);
		
		String cation, anion;
		
		if(one.equals(Cation.NH4) && two.equals(Anion.OH))
		{
			return "NH" + this.getSubscript(3);
		}
		
		if(one.equals(Cation.H) && two.equals(Anion.CH3COO))
		{
			return "CH" + this.getSubscript(3) + "COOH";
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
	
	/**
	 * Because only certain combinations of Cations and Anions are acids, this method
	 * sets the inherited fields Cation one and Anion two so that an acid or a base will be formed. 
	 */
	private void setChemical()
	{
		
		int choose = (int) (Math.random() * 2);
		
		//acid
		if(choose == 0)
		{
			
			one = Cation.H;
			
			while(two.equals(Anion.O) || two.equals(Anion.OH))
			{
				two = Anion.getAnion((int) (Math.random() * Anion.values().length));
			}

		}
		
		//base
		else
		{
			
			two = Anion.OH;
			
			while(one.equals(Cation.H) || one.equals(Cation.Mg) || one.equals(Cation.Be) || one.equals(Cation.Al))
			{
				one = Cation.getCation((int) (Math.random() * Cation.values().length));
			}
			
		}
		
	}
	
	public void addComponents() 
	{
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(chemicalLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(choice1, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		panel.add(choice2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		panel.add(weakAcid, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		panel.add(weakBase, c);
		
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(endDisplay, c);
		
	}

	public void determineCorrect() 
	{
		AcidBaseRules r = new AcidBaseRules();
		
		if(buttonIndex != r.checkChemical(one, two))
		{
			String display = "<html>Incorrect: " + r.getRule(r.getRuleNumber());
			display += "</html>";
			
			endDisplay.setText(display);
		}
		
		else
		{
			String display = "<html>Correct: " + r.getRule(r.getRuleNumber());
			display += "</html>";
			
			endDisplay.setText(display);
		}
		
		endDisplay.validate();
		GameFrame.enableNext();
		
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		
		Object src = arg0.getSource();
		
		if(src == choice1)
		{
			buttonIndex = 0;
			this.determineCorrect();
		}
		
		else if(src == choice2)
		{
			buttonIndex = 1;
			this.determineCorrect();
		}
		
		else if(src == weakAcid)
		{
			buttonIndex = 2;
			this.determineCorrect();
		}
		
		else if(src == weakBase)
		{
			buttonIndex = 3;
			this.determineCorrect();
		}
		
	}
	
}
