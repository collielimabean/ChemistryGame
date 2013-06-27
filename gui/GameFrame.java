package gui;
import gametype.AcidBaseGame;
import gametype.SolubilityGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The GameFrame class is the top-level container for the ChemistryGame.
 * It has a menubar at the top, containing a File menu, a dropdown menu containing the choices of the gametypes,
 * and the button "next", to allow the user to generate the next problem.
 * @author William Jen
 */
public class GameFrame extends JFrame implements ActionListener
{
	/**
	 * Generated serial number
	 */
	private static final long serialVersionUID = 7615512134568936635L;
	private JMenuBar menuBar;
	private JMenu menu;
	private JComboBox setType;
	private static JButton next;

	public GameFrame(String title)
	{
		super(title);
		super.setSize(600,500);
		super.setResizable(false);
		
		//menu initialization
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);
		
		//combobox initialization
		setType = new JComboBox();
		setType.addItem("---Select Gametype---");
		setType.addItem("Solubility Rules");
		setType.addItem("Acids / Bases");
		setType.addItem("Everything");
		
		//next button initialization
		next = new JButton("Next");
		next.setEnabled(false);
		next.addActionListener(this);
		
		//add components to menuBar
		setType.addActionListener(this);
		menuBar.add(setType);
		menuBar.add(next);
		menuBar.add(new JPanel());
		
		//file menu initialization
		JMenuItem[] items =
			{
				
				new JMenuItem("Changelog"),
				new JMenuItem("About"),
				new JMenuItem("Exit Game")
				
			};
		
		//add items to the file menu
		for (JMenuItem item : items)
		{
			item.addActionListener(this);
			menu.add(item);
		}

		this.setJMenuBar(menuBar);
		
		//default closing operation, i.e. you hit the X button on the window and program will exit.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Enables the "next" button. Default state is disabled (i.e. you can't push the button).
	 */
	public static void enableNext()
	{
		next.setEnabled(true);
	}
	
	/**
	 * Returns state of the next button.
	 * @return true if next is enabled, false otherwise
	 */
	public static boolean getNextState()
	{
		return next.isEnabled();
	}
	
	/**
	 * Defines the actions taken when an action is fired (e.g. a button is clicked, etc.)
	 */
	public void actionPerformed(ActionEvent arg0) 
	{
		//get source of the object firing the event
		Object src = arg0.getSource();
		
		//ensures the next button is false so that people cannot proceed
		next.setEnabled(false);
		
		//Defines the actions for the items in the file menu
		if(src.getClass().getName().toLowerCase().endsWith("jmenuitem"))
		{
			
			JMenuItem temp = (JMenuItem) src;
			
			if(temp.getText().contains("Change"))
			{
				JOptionPane.showMessageDialog(this, "v1.1 - Updated Acid/Base Game so that insoluble, transition metal hydroxides are weak bases. Changelog added. \n " +
								"v1.0 - Inital Release.");
			}
			
			else if(temp.getText().contains("About"))
			{
				JOptionPane.showMessageDialog(this, " v1.1 \n Made by William Jen (2013)");
			}
			
			else if(temp.getText().contains("Exit"))
			{
				super.dispose();
				System.exit(0);
			}
			
		}
		
		//Defines actions when the a new option is selected in the combobox.
		else if(src == setType)
		{
			
			JComboBox drop = (JComboBox) src;
			
			//obtains the item selected
			String itemName = (String) drop.getItemAt(drop.getSelectedIndex());
			
			if (itemName.toLowerCase().contains("rules"))
			{
				//clear the GameFrame internal JPanel
				GameRunner.setJPanel(new JPanel());
				
				//call SolubilityGame class	
				SolubilityGame g = new SolubilityGame();
				GameRunner.setJPanel(g.getJPanel());
				
			}
			
			else if (itemName.toLowerCase().contains("acid"))
			{
				//clear the GameFrame internal JPanel
				GameRunner.setJPanel(new JPanel());
				
				//call AcidBaseGame class
				AcidBaseGame g = new AcidBaseGame();
				GameRunner.setJPanel(g.getJPanel());
			}
			
			else if (itemName.toLowerCase().contains("everything"))
			{
				//clear the GameFrame internal JPanel
				GameRunner.setJPanel(new JPanel());
				
				//randomly choose either SolubilityGame or AcidBaseGame
				
				int choose = (int) (Math.random() * 2);
				
				if(choose == 0)
				{
					SolubilityGame g = new SolubilityGame();
					GameRunner.setJPanel(g.getJPanel());
				}
				
				else
				{
					AcidBaseGame g = new AcidBaseGame();
					GameRunner.setJPanel(g.getJPanel());
				}
				
			}
			
			else
			{
				GameRunner.setJPanel(new JPanel());
			}
			
		}
		
		//Defines what happens when next is enabled and clicked
		else if(src == next)
		{
			
			//obtain combobox item
			String select = (String) setType.getItemAt(setType.getSelectedIndex());
			
			if(select.toLowerCase().contains("solubility"))
			{
				next.setEnabled(false);
				
				SolubilityGame g = new SolubilityGame();
				GameRunner.setJPanel(g.getJPanel());
			}
			
			else if(select.toLowerCase().contains("acid"))
			{
				next.setEnabled(false);
				
				AcidBaseGame g = new AcidBaseGame();
				GameRunner.setJPanel(g.getJPanel());
			}
			
			else if(select.toLowerCase().contains("every"))
			{
				next.setEnabled(false);
				
				int choose = (int) (Math.random() * 2);
				
				if(choose == 0)
				{
					SolubilityGame g = new SolubilityGame();
					GameRunner.setJPanel(g.getJPanel());
				}
				
				else
				{
					AcidBaseGame g = new AcidBaseGame();
					GameRunner.setJPanel(g.getJPanel());
				}
				
			}
			
		}
		
	}
	
}
