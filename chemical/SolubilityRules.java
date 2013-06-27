package chemical;

import chemical.Chemical.Anion;
import chemical.Chemical.Cation;

/**
 * Defines the solubility rules used in the SolubilityGame class.
 * @author William Jen
 *
 */
public class SolubilityRules extends Rules
{

	public SolubilityRules()
	{
		super();
	}
	
	public void defineRules()
	{
		rules = new String[6];
		
		rules[0] = "All common salts of Alkaline metals (Group 1) elements and ammonium are soluble.";
		rules[1] = "All common acetates, nitrates, and perchlorates are soluble.";
		rules[2] = "All binary compounds of Halogen (Group 17) elements, other than Flouride, with metals are soluble except those of Ag, Hg (I), and Pb.";
		rules[3] = "All sulfates are soluble except those of Sr, Ba, Ca, Pb, Ag, and Hg (I).";
		rules[4] = "Except those in Rule 1, carbonates, oxides, sulfides, and phosphates are insoluble.";
		rules[5] = "All other compounds not listed are assumed to be insoluble.";
		
	}
	
	/**
	 * Determines the solubility of a compound given the Cation and the Anion.
	 * @param one
	 * @param two
	 * @return true if soluble in water, false otherwise
	 */
	public boolean isSoluble(Cation one, Anion two)
	{
		//rule #1 - ruleText[0]
		if(one.equals(Cation.H) || one.equals(Cation.Li) || one.equals(Cation.Na)
			|| one.equals(Cation.K) || one.equals(Cation.Rb) || one.equals(Cation.Cs)
			|| one.equals(Cation.Fr) || one.equals(Cation.NH4))
		{
			
			ruleNumber = 1;
			return true;
			
		}
		
		//rule #2 - ruleText[1]
		else if(two.equals(Anion.C2H3O2) || two.equals(Anion.CH3COO) || two.equals(Anion.NO2)
				|| two.equals(Anion.NO3) || two.equals(Anion.ClO) || two.equals(Anion.ClO2)
				|| two.equals(Anion.ClO3) || two.equals(Anion.ClO4))
		{
			
			ruleNumber = 2;
			return true;
			
		}
		
		//rule 3 - ruleText[2]
		else if(two.equals(Anion.Cl) || two.equals(Anion.Br) || two.equals(Anion.I))
		{
			
			ruleNumber = 3;
			
			if(one.equals(Cation.Ag) || one.equals(Cation.Pb2) || one.equals(Cation.Pb4)
					|| one.equals(Cation.Hg1) || two.equals(Anion.F))
			{
				return false;
			}
			
			return true;
		}
		
		//rule #4 - ruleText[3]
		else if(two.equals(Anion.SO4))
		{
			
			ruleNumber = 4;
			
			if(one.equals(Cation.Ba) || one.equals(Cation.Sr) || one.equals(Cation.Ca)
					|| one.equals(Cation.Pb2) || one.equals(Cation.Pb4) || one.equals(Cation.Ag)
					|| one.equals(Cation.Hg1))
			{
				return false;
			}
			
			return true;
				
		}
		
		//rule #5 - ruleText[4]
		else if(two.equals(Anion.SO3) || two.equals(Anion.CO3) || two.equals(Anion.O)
				|| two.equals(Anion.S) || two.equals(Anion.PO4))
		{
			
			ruleNumber = 5;
			
			return false;
			
		}
		
		//rule #6 - ruleText[5]
		else
		{
			
			ruleNumber = 6;
			return false;
			
		}
		
	}
	
}
