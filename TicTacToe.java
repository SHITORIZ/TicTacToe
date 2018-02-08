import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class TicTacToe extends JFrame //My TicTacToe extends JFrame class
{
	private JButton button[] = new JButton[9];//JButton button[] array that holds 9 buttons
	private int[] [] wins = new int [] [] { //this new style of double bracket array I found and learned worked perfect for my 8 combination of wins
			{0, 1, 2}, {3, 4, 5}, {6, 7, 8},//first bracket represents the 8 different winning combinations and the second represents the 3 numbers in each bracket
			{0, 3, 6}, {1, 4, 7}, {2, 5, 8},//so almost like an array with in an array.  Basically first array handles the overall win combination and second holds
			{0, 4, 8}, {2, 4, 6}};			//the actual location of each button in the combination.  This handles horizontal, vertical, and diagnol
	private String letter = "";//String variable which gets empty brackets to get something later on
	private int XorO = 0;//integer value XorO counts how many times an X or O has been placed on a button
	private boolean Winner = false;//boolean winner variable set to false
	
	public TicTacToe()//Constructor for class TicTacToe with no parameters
	{
		setSize(300, 300);//setting GUI window to size of 300 by 300 to match one from hypergrade
		setTitle("Tic Tac Toe");//setting title for GUI window with Tic Tac Toe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//makes sure the GUI is actually closed when closed and doesn't keep running
		
		JMenuBar bar = new JMenuBar();//Makes new object variable "bar" from JMenuBar
		JMenu file = new JMenu("File");//Makes new object variable "file" from JMenu and inputs string "file" to give a name to it
		JMenuItem m1 = new JMenuItem("Exit");//Makes new object variable m1 from JMenuItem and inputs string "exit" to give a name to this option under file
		file.add(m1);//actually adds the exit option to our file bar using the add() method
		bar.add(file);//actually adds the file menu to the Menu bar
		add(bar, BorderLayout.NORTH);//sets this Menu bar to the North BorderLayout part the GUI
		
		m1.addActionListener(new ExitListener());//Makes Action Listener listen for when the m1(exit) option is pressed and if so carry out the ExitListener class
		
		JPanel region = new JPanel();//Create a new JPanel with variable region.
		region.setLayout(new GridLayout(3, 3));//makes this new JPanel region a GridLayout 3 by 3 with setLayout
		for(int x = 0; x <= 8; x++)//for Loop that is less than or equal to 8
		{
			button[x] = new JButton();//9 buttons made with in loop with each iteration where x starts at 0 button[x].
			region.add(button[x]);//all 9 buttons added to the GridLayout JPanel region in the order they made according to the loop so button[0] - button[9]
			button[x].addActionListener(new buttonResponder());//gives all 9 buttons a ActionListener to listen for when they are pressed, and if so carry out the buttonResponder() class
		}
			
		add(region, BorderLayout.CENTER);//add this JPanel region/gridLayou of buttons to the center region of the GUI
		setVisible(true);//setVisible to true so that we can see the GUI/
	}
	
	private class buttonResponder implements ActionListener//buttonResponder class implements ActionListener and carries out the action for 9 buttons created
	{
		public void actionPerformed(ActionEvent e)//actionPerformed Method where the magic happens
		{
			
			JButton buttonPressed = (JButton)e.getSource();//Made an object variable buttonPressed which tells me when ever a button is pressed with the e.getSource() method
			if(buttonPressed.getText().equals(""))//if the button pressed has not yet been assigned and X or O carry out the if statement
				{
					XorO++;//add one to the XorO variable
					if(XorO % 2 == 0)//if the XorO variable is even set the letter variable to get "O"
						{
						   letter = "O";//letting letter variable get "O".
						} 
					else //if the XorO variable is odd set the letter variable to get "X"
						{
						    letter = "X";//letting letter variable get "X".
						}
					buttonPressed.setText(letter);//after setting letter variable to X or O set the button that was pressed get that Letter(X or O)
				}
					
			for(int x = 0; x <=7; x++)//for loop that is less than or equal to 7(0-7) to deal with all 8 possible winning combinations with that double bracket array
				{
					//this part for me was just something awesome..because I had 8 if statements doing this exact thing but with the double bracket array I was able to just input
					//my array right into one of those if statements and make a loop for it to cover all 8 possible win combinations
					//as said before the first bracket which is changed through each loop iteration which gets x will check each of the 8 brackets in my array
					//the second bracket or array within the array will be set to handle the three different buttons( position 0, 1, and 2 within the array
					//after checking for each win combination..if one is found set the Winner variable to true which tells us that we have a winner.
					if(button[wins[x][0]].getText().equals(button[wins[x][1]].getText()) && button[wins[x][1]].getText().equals(button[wins[x][2]].getText()) && !button[wins[x][0]].getText().equals("")) 
						{
							Winner = true;//setting Winner variable to true
						}
				}
					
			if (Winner == true)//if Winner variable is true carry out the if statement
				{
					JOptionPane.showMessageDialog(null, "Winner:" + letter);//brings up a message dialog window that lets the user know that they won and if it was X or O that won.
					clear();//call my clear method which resets all buttons to ("") and resets all variables(letter, XorO, and Winner)
				}
		 }
	 }
					
	public void clear()//clear method to handle the reset of the game when there is a Winner or Winner equals true.
	{
		for(int x = 0; x <= 8; x++)//for loop that is less than or equal to 8 to handle the reset of all 9 buttons back to ("")
			{
				button[x].setText("");//set each button back ("") with the setText() method.
			}
		letter = "";//set letter variable back to "";
		XorO = 0;//set XorO variable back to zero.
		Winner = false;//set Winner variable back to false.
	}
	
	private class ExitListener implements ActionListener//ExitListener class which implements ActionListener to Listen for the button which it was assigned(m1("exit"))
	{
		public void actionPerformed(ActionEvent e)//actionPerformed method where all the magic happens
		{	
			System.exit(0);//close or exit GUI window completely
		}
	}
	
	public static void main(String[] args) //main method which creates a instance of the class FlameBay and gives the class its own main Method
	{
		new TicTacToe();
	}

}

