package chemical;

/**
 * This is the superclass for defining a generic gametype's rules.
 * @author William Jen
 */
public abstract class Rules
{
	
	protected String[] rules;
	protected int ruleNumber = 0;
	
	public Rules()
	{
		this.defineRules();
	}
	
	/**
	 * Gets the appropiate rule from the defined ruleset.
	 * @throws IllegalArgumentException if ruleNumber is out of bounds or below zero.
	 * @param ruleNumber
	 * @return the rule associated with @param ruleNumber
	 */
	public String getRule(int ruleNumber)
	{
		
		if(ruleNumber > rules.length || ruleNumber < 0)
		{
			throw new IllegalArgumentException();
		}
		
		return rules[ruleNumber - 1];
	}
	
	/**
	 * Returns the rule number.
	 * @return ruleNumber
	 */
	public int getRuleNumber()
	{
		return ruleNumber;
	}
	
	/**
	 * Initializes the rules field and all of the elements inside.
	 */
	public abstract void defineRules();
}
