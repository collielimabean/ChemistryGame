package chemical;

/**
 * Utility class used to contain the enumerated types Cation and Anion.
 * @author William Jen
 *
 */
public class Chemical 
{		
	/**
	 * List of all Cations used for the Solubility Game.
	 * @author William Jen
	 *
	 */
	public enum Cation
	{
		H("H", 1, 0),
		Li("Li", 1, 1),
		Na("Na", 1, 2),
		K("K", 1, 3),
		Rb("Rb", 1, 4),
		Cs("Cs", 1, 5),
		Fr("Fr", 1, 6),
		Be("Be", 1, 7),
		Mg("Mg", 2, 8),
		Ca("Ca", 2, 9),
		Sr("Sr", 2, 10),
		Ba("Ba", 2, 11),
		Ra("Ra", 2, 12),
		Al("Al", 3, 13),
		NH4("NH4", 1, 14),
		Zn2("Zn", 2, 15),
		Zn3("Zn", 3, 16),
		Fe2("Fe", 2, 17),
		Fe3("Fe", 3, 18),
		Ag("Ag", 1, 19),
		Cu2("Cu", 2, 20),
		Cu3("Cu", 3, 21),
		Ni2("Ni", 2, 22),
		Ni3("Ni", 3, 23),
		Mn2("Mn", 2, 24),
		Cr2("Cr", 2, 25),
		Cr3("Cr", 3, 26),
		Pb2("Pb", 2, 27),
		Pb4("Pb", 4, 28),
		Hg1("Hg", 1, 29)
		;
		
		private final String name;
		private final int charge;
		private final int index;
		
		Cation(String Name, int Charge, int Index)
		{
			name = processName(Name);
			charge = Charge;
			index = Index;
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getCharge()
		{
			return charge;
		}
		
		public int getIndex()
		{
			return index;
		}
		
		public boolean isTransitionMetal()
		{
			if(equals(Zn2) || equals(Zn3) || equals(Fe2) || equals(Fe3) || equals(Ag)
					|| equals(Mn2) || equals(Ni2) || equals(Ni3) || equals(Cr2) || equals(Cr3)
					|| equals(Pb2) || equals(Pb4) || equals(Hg1) || equals(Cu2) || equals(Cu3))
			{
				return true;
			}
			
			else return false;
		}
		
		public static Cation getCation(int index)
		{

			for(Cation c : Cation.values())
			{
				
				if(c.getIndex() == index)
				{
					return c;
				}
				
			}
			
			return null;
			
		}
	}
	
	/**
	 * List of all Anions used for the Solubility Game.
	 * @author William Jen
	 *
	 */
	public enum Anion
	{
		O("O", -2, 0),
		F("F", -1, 1),
		Cl("Cl", -1, 2),
		Br("Br", -1, 3),
		I("I", -1, 4),
		SO4("SO4", -2, 5),
		SO3("SO3", -2,6),
		NO2("NO2", -1,7),
		NO3("NO3", -1, 8),
		PO4("PO4", -3, 9),
		PO3("PO3", -3, 10),
		CH3COO("CH3COO", -1,11),
		C2H3O2("C2H3O2", -1, 12),
		CO3("CO3", -2,13),
		OH("OH", -1, 14),
		CrO4("CrO4", -2, 15),
		Cr2O7("Cr2O7", -2, 16),
		ClO("ClO", -1, 17),
		ClO2("ClO2", -1, 18),
		ClO3("ClO3", -1, 19),
		ClO4("ClO4", -1, 20),
		BrO("BrO", -1, 21),
		BrO2("BrO2", -1, 22),
		BrO3("BrO3", -1, 23),
		BrO4("BrO4", -1, 24),
		MnO4("MnO4", -1, 25),
		IO("IO", -1, 26),
		IO2("IO2", -1, 27),
		IO3("IO3", -1, 28),
		IO4("IO4", -1, 29),
		S("S", -2, 30);
		
		private final String name;
		private final int charge;
		private final int index;
		
		Anion(String Name, int Charge, int Index)
		{
			name = processName(Name);
			charge = Charge;
			index = Index;
		}
		
		public String getName()
		{
			return name;
		}
		
		public int getCharge()
		{
			return charge;
		}
		
		
		public int getIndex()
		{
			return index;
		}
		
		public static Anion getAnion(int index)
		{
			
			for(Anion a : Anion.values())
			{
				
				if(a.getIndex() == index)
				{
					return a;
				}
				
			}
			
			return null;
			
		}
		
	}
	
	/**
	 * Transforms the name of a chemical so that any numbers in the chemical become subscripts.
	 * @param original
	 * @return transformed name with subscripts.
	 */
	private static String processName(String original)
	{
		
		char[] or = original.toCharArray();

		int returned = -1;
		
		for(int i = 0; i < or.length; i++)
		{
			
			if(Character.isDigit(or[i]))
			{
				returned = Integer.parseInt(Character.toString(or[i]));	
		        or[i] = (char) (returned + 0x2080);
		        returned = -1;
			}

		}
		
		return new String(or);
	}
	
	/**
	 * Given a cation and an anion, this method balances the charges so that the charges agree.
	 * @param one
	 * @param two
	 * @return
	 */
	public static int[] balance(Cation one, Anion two)
	{
		
		int catCharge = one.getCharge();
		int anCharge = Math.abs(two.getCharge());
		
		int gcf = findGCF(catCharge, anCharge);
		
		int[] coef = {anCharge / gcf , catCharge / gcf};
		
		return coef;
		
	}
	
	/**
	 * Returns the gcf.
	 * @param a
	 * @param b
	 * @return gcf
	 */
	private static int findGCF(int a, int b)
	{
		
		if(b == 0)
		{
			return a;
		}
		
		return findGCF(b, a % b);
		
	}

}
