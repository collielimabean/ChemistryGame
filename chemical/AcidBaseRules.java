package chemical;

import chemical.Chemical.Anion;
import chemical.Chemical.Cation;

/**
 * Defines the rules used to determine whether a given chemical is strong/weak acid/base.
 * @author William Jen
 *
 */
public class AcidBaseRules extends SolubilityRules
{
	
	public AcidBaseRules()
	{
		super();
	}

	public void defineRules() 
	{
		rules = new String[3];
		
		//acid rules
		rules[0] = "The strong binary acids are HCl, HBr, and HI, but HF is not!";
		rules[1] = "Strong oxyacids (like HNO\u2083) can be determined when the (#Os - #Hs) \u2265 2";
		
		//base rules
		rules[2] = "Strong bases are compounds with an alkaline metal and a hydroxide ion.";
	}
	
	/**
	 * Takes a Cation and an Anion and determines the compound produced is an acid.
	 * @param one
	 * @param two
	 * @return true if it is an acid, false otherwise.
	 */
	public boolean isAcid(Cation one, Anion two)
	{
		
		if(one.equals(Cation.H))
		{
			
			if(super.isSoluble(one, two) && !(two.equals(Anion.OH)))
			{
				return true;
			}
			
			else
			{
				return false;
			}
			
		}
		
		else
		{
			return false;
		}
	}
	
	/**
	 * Takes a Cation and an Anion and determines the compound produced is an acid.
	 * @param one
	 * @param two
	 * @return true if base, false otherwise.
	 */
	public boolean isBase(Cation one, Anion two)
	{
		
		if(two.equals(Anion.OH))
		{
			if(!(one.equals(Cation.NH4) || one.equals(Cation.H)))
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		
		else if(one.equals(Cation.NH4) && two.equals(Anion.OH))
		{
			return true;
		}
		
		else 
		{
			return false;
		}
		
	}
	
	/**
	 * Determines whether a generated chemical is an acid/base, strong/weak.
	 * @precondition 
	 * @param one
	 * @param two
	 * @return the index of what button the response should be.
	 * 0 - Strong acid, 1 - Strong Base
	 * 2 - Weak Acid, 3 - Weak Base
	 */
	public int checkChemical(Cation one, Anion two)
	{
		
		if(this.isAcid(one, two))
		{
			
			if(two.equals(Anion.Cl) || two.equals(Anion.Br) ||two.equals(Anion.I) || two.equals(Anion.F)
					|| two.equals(Anion.S))
			{
				ruleNumber = 1;
				
				if(!(two.equals(Anion.F) && two.equals(Anion.S)))
				{
					return 0;
				}
				
				else return 2;
			}
			
			else if(two.equals(Anion.BrO3) || two.equals(Anion.BrO4) || two.equals(Anion.NO3)
						|| two.equals(Anion.ClO3) || two.equals(Anion.ClO4) || two.equals(Anion.Cr2O7)
						|| two.equals(Anion.CrO4) || two.equals(Anion.MnO4) || two.equals(Anion.SO4)
						|| two.equals(Anion.IO3) || two.equals(Anion.IO4))
			{
				
				ruleNumber = 2;
				return 0;
			}
			
			else
			{
				ruleNumber = 2;
				return 2;
			}

		}
		
		else
		{
			ruleNumber = 3;
			
			if(one.equals(Cation.Li) || one.equals(Cation.Na) || one.equals(Cation.K) || one.equals(Cation.Rb)
					|| one.equals(Cation.Cs) || one.equals(Cation.Fr) || one.equals(Cation.Ba)
					|| one.equals(Cation.Ca) || one.equals(Cation.Sr) || one.equals(Cation.Ra)
					|| one.equals(Cation.Al))
			{
				return 1;
			}
			
			else
			{
				return 3;
			}
			
		}
		
	}


}
