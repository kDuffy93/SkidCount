/* NOTES
- print button
- array/sort
- hide password feild
-combine user/password to one window
-fix loop error
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.util.*;
public class skidCount extends JFrame implements ActionListener
{
    //initialises starting values/variables
    Container content = getContentPane ();
    private JPanel sortPanel;
    private JPanel buttonPanel;
    static JTextArea textPane = new JTextArea ();
    static JScrollPane scrollPane = new JScrollPane (textPane);
    JTextField OweBill = new JTextField ();
    JTextField spacer = new JTextField ();
    JTextField skidsInStock = new JTextField ();
    JLabel oweBillPrompt = new JLabel ("We Owe Bill");
    JLabel oweBillPostPrompt = new JLabel ("Skids");
    JLabel weHave = new JLabel ("We Have");
    JLabel SkidsInStock = new JLabel ("Skids In The Warehouse");
    File dataFile = new File ("SkidLog.txt");
    File dataFile1 = new File ("Bills Count.txt");
    JButton billsButton = new JButton ("Bill's Count");
    JButton warehouseButton = new JButton ("Edit Count");
    DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
    Date date = new Date ();
    String getLine;
    int startingValue;
    int BillsNumber;
    static String username;
    String startupDisplay = Integer.toString (BillsNumber);
    //construct the instance of DVD
    public skidCount ()
    {
	super ("Skid Counter");
	// creates the instance of MnuBar
	JMenuBar mnuBar = new JMenuBar ();
	setJMenuBar (mnuBar);
	// sets file menu
	JMenu mnuFile = new JMenu ("File", true);
	mnuBar.add (mnuFile);
	// populates file menu
	JMenuItem mnuFileExit = new JMenuItem ("Exit");
	mnuFile.add (mnuFileExit);
	// sets edif menu
	JMenu mnuInsert = new JMenu ("Insert", true);
	mnuBar.add (mnuInsert);
	// populates sort menu
	JMenuItem mnuInsertSkid = new JMenuItem ("Edit Count");
	mnuInsert.add (mnuInsertSkid);
	mnuInsert.addSeparator ();
	JMenuItem mnuInsertBill = new JMenuItem ("Bill's Count");
	mnuInsert.add (mnuInsertBill);
	// adds action listener to menu buttons
	mnuFileExit.addActionListener (this);
	mnuInsertSkid.addActionListener (this);
	mnuInsertBill.addActionListener (this);
	//Construct components and initialize beginning values
	JPanel sortPanel = new JPanel ();
	JPanel textPanel = new JPanel ();
	JPanel buttonPanel = new JPanel ();
	textPane.setEditable (false);
	OweBill.setEditable (false);
	skidsInStock.setEditable (false);


	// sets the layouts of the panel
	this.getContentPane ().setLayout (new BorderLayout ());
	buttonPanel.setLayout (new GridLayout (1, 3, 0, 0));
	buttonPanel.setPreferredSize (new Dimension (32, 32));
	buttonPanel.setBorder (BorderFactory.createLineBorder (Color.gray, 1));
	buttonPanel.add (billsButton);
	buttonPanel.add (warehouseButton);
	content.add (buttonPanel, BorderLayout.SOUTH);
	billsButton.addActionListener (this);
	warehouseButton.addActionListener (this);
	sortPanel.setLayout (new FlowLayout (1, 3, 0));
	// adding panels
	sortPanel.setPreferredSize (new Dimension (32, 32));
	sortPanel.setBorder (BorderFactory.createLineBorder (Color.gray, 1));

	//how many skids we have in stock in sort panel
	sortPanel.add (weHave);
	skidsInStock.setHorizontalAlignment (JTextField.CENTER);
	sortPanel.add (skidsInStock);
	sortPanel.add (SkidsInStock);
	skidsInStock.setPreferredSize (new Dimension (35, 30));
	skidsInStock.setBorder (BorderFactory.createLineBorder (Color.LIGHT_GRAY, 2));
	skidsInStock.setBackground (Color.LIGHT_GRAY);
	sortPanel.add (spacer);
	spacer.setHorizontalAlignment (JTextField.CENTER);
	spacer.setPreferredSize (new Dimension (330, 0));
	//what we owe bill bar in sort panel
	sortPanel.add (oweBillPrompt);
	OweBill.setHorizontalAlignment (JTextField.CENTER);
	sortPanel.add (OweBill);
	sortPanel.add (oweBillPostPrompt);
	OweBill.setPreferredSize (new Dimension (35, 30));
	OweBill.setBorder (BorderFactory.createLineBorder (Color.LIGHT_GRAY, 2));
	OweBill.setBackground (Color.LIGHT_GRAY);
	skidsInStock.setText ("0");
	OweBill.setText ("0");

	// lower text pane
	content.add (sortPanel, BorderLayout.NORTH);
	textPanel.add (scrollPane);
	//scrollPane.setPreferredSize (new Dimension (646, 496));
	scrollPane.setBorder (BorderFactory.createLineBorder (Color.black, 1));
	scrollPane.setBackground (Color.WHITE);
	content.add (textPanel, BorderLayout.CENTER);
    } // end of constructor method




    //checks what "bills count" is and sets the text pane accordingly
    public void billsCountReader ()
    {
	try
	{
	    DataInputStream input3 = new DataInputStream (new FileInputStream ("Bills Count.txt"));
	    int lineCount1 = 0;
	    while (true)
	    {

		String countTest1 = input3.readLine ();
		if (countTest1 == null)
		    break;
		lineCount1++;
	    }
	    input3.close ()
		;
	    DataInputStream input4 = new DataInputStream (new FileInputStream ("Bills Count.txt"));

	    int currentLine = 0;
	    while (true)
	    {
		getLine = input4.readLine ();
		if (getLine == null)
		    break;
		if (currentLine + 1 == lineCount1)
		{
		    int beforeNumber = getLine.indexOf ("Bill ", 0);
		    int afterNumber = getLine.indexOf ("skid", 0);
		    String BNString = getLine.substring (beforeNumber + 5, afterNumber - 1);
		    BillsNumber = Integer.parseInt (BNString);
		    OweBill.setText (BNString);


		}
		currentLine++;

	    }
	    input4.close ()
		;

	}
	catch (IOException IO)
	{

	}
    }


    // adds any logs to the text pane from the save file
    public void startupTextPane ()
    {
	try
	{
	    DataInputStream input5 = new DataInputStream (new FileInputStream ("SkidLog.txt"));
	    int currentLine = 0;
	    while (true)
	    {
		getLine = input5.readLine ();
		if (getLine == null)
		    break;
		String currentText = textPane.getText ();
		textPane.setText (currentText + "  -  " + getLine + "\n");
	    }
	    input5.close ()
		;
	}
	catch (IOException IO)
	{
	}
    }


    // keps the current skids in stock up to date
    public void startupFileReader ()
    {


	// read from a data file
	try
	{


	    DataInputStream input2 = new DataInputStream (new FileInputStream ("SkidLog.txt"));
	    int lineCount = 0;
	    while (true)
	    {
		String countTest = input2.readLine ();
		if (countTest == null)
		    break;
		lineCount++;
	    }
	    input2.close ()
		;
	    DataInputStream input = new DataInputStream (new FileInputStream ("SkidLog.txt"));
	    //input.skip(2);
	    int currentLine = 0;

	    while (true)
	    {
		getLine = input.readLine ();
		if (getLine == null)
		    break;
		if (currentLine + 1 == lineCount)
		{
		    int beforeNumber = getLine.indexOf ("have ", 0);
		    int afterNumber = getLine.indexOf ("   Skids", 0);
		    String cString = getLine.substring (beforeNumber + 5, afterNumber);
		    startingValue = Integer.parseInt (cString);
		    skidsInStock.setText (cString);


		}
		currentLine++;

	    }
	    input.close ()
		;

	}



	catch (IOException IO)
	{
	}
    }


    // allows the user to edit the owings to Bill
    public void billsCount () throws IOException, NumberFormatException
    {

	Object[] options = {"Adding", "Removing", "Cancel"};
	int as = JOptionPane.showOptionDialog (null, "Are you adding to or removing from what you owe to bill?", "Conformation",
		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options [2]);
	String AS = Integer.toString (as);
	int EditAmount = 0;
	billsCountReader ();
	if (AS.equalsIgnoreCase ("0"))
	{

	    String editOwing1 = JOptionPane.showInputDialog (null, "how many skids did bill leave?", "Add to Owing",
		    JOptionPane.PLAIN_MESSAGE);
	    if (editOwing1 == null || editOwing1.equals (""))
	    {
		editOwing1 = "0";
	    }
	    int EditOwing1 = Integer.parseInt (editOwing1);
	    EditAmount = EditOwing1;
	}


	else if (AS.equalsIgnoreCase ("1"))
	{
	    String editOwing0 = JOptionPane.showInputDialog (null, "how many skids did bill take?", "Remove from Owing",
		    JOptionPane.PLAIN_MESSAGE);
	    if (editOwing0 == null || editOwing0.equals (""))
	    {
		editOwing0 = "0";
	    }
	    int EditOwing0 = Integer.parseInt (editOwing0);
	    EditAmount = (EditOwing0 * -1);
	}


	int totalOwing = BillsNumber + EditAmount;
	Writer output = null;
	output = new BufferedWriter (new FileWriter (dataFile1, true));
	output.write ("( " + dateFormat.format (date) + " )   " + "we currently owe Bill " + totalOwing + " skids" + "\r\n");
	output.close ();
	String PaneValue = Integer.toString (totalOwing);
	OweBill.setText (PaneValue);
    }


    //allows the user to add a new entry and writes to to the text pane and the save file
    public void data () throws IOException, NumberFormatException
    {
	int aInt = 0;
	int mInt = 0;
	String addSkids = JOptionPane.showInputDialog (null, "How many skids came in today?", "add skids",
		JOptionPane.PLAIN_MESSAGE);
	if (!(addSkids == null))
	{
	    if (addSkids.equals (""))
	    {
		JOptionPane.showMessageDialog (null, "Sorry, you must enter a number", " username error",
			JOptionPane.ERROR_MESSAGE);
		data ();
	    }

	    try
	    {
		aInt = Integer.parseInt (addSkids);
	    }
	    catch (NumberFormatException nfe)
	    {
		JOptionPane.showMessageDialog (null, "sorry, you must enter a number.", "ERROR",
			JOptionPane.ERROR_MESSAGE);
		data ();
	    }


	    String minusSkids = JOptionPane.showInputDialog (null, "How many skids went out today?", "minus skids",
		    JOptionPane.PLAIN_MESSAGE);
	    if (!(minusSkids == null))
	    {
		if (minusSkids.equals (""))
		{
		    JOptionPane.showMessageDialog (null, " Sorry, you must enter a number", " username error",
			    JOptionPane.ERROR_MESSAGE);
		    data ();
		}
		try
		{
		    mInt = Integer.parseInt (minusSkids);

		}
		catch (NumberFormatException nfe)
		{
		    JOptionPane.showMessageDialog (null, "sorry, you must enter a number.", "ERROR",
			    JOptionPane.ERROR_MESSAGE);
		    data ();

		}
	    }
	    String driver = JOptionPane.showInputDialog (null, "who was the driver today?", " DRIVER",
		    JOptionPane.PLAIN_MESSAGE);
	    if (driver.equalsIgnoreCase ("bill"))
	    {
		JOptionPane.showMessageDialog (null, "Don't forget to edit bill's count if needed", " Reminder",
			JOptionPane.QUESTION_MESSAGE);
	    }

	    int totalSkids = startingValue + (aInt - mInt);

	    int yn = JOptionPane.showConfirmDialog (null, "Is this correct? (Y/N) \n" + "( " + dateFormat.format (date) + " )   " + "Today " + aInt + " Skids came in and " + mInt + " Went out. we now have " + totalSkids + "   Skids in the Warehouse " + "( Enterd By: " + username + " )" + "Delivered by: " + driver + ". ", "Conformation",
		    JOptionPane.YES_NO_OPTION);
	    String YN = Integer.toString (yn);
	    if (YN.equalsIgnoreCase ("0"))
	    {
		Writer output = null;

		output = new BufferedWriter (new FileWriter (dataFile, true));
		output.write ("( " + dateFormat.format (date) + " )   " + "Today " + aInt + " Skids came in and " + mInt + " Went out. we now have " + totalSkids + "   Skids in the Warehouse " + "( Enterd By: " + username + " )" + "Delivered by: " + driver + "\r\n");
		output.close ();
		startingValue = totalSkids;
		String totalSkidsString = Integer.toString (totalSkids);
		skidsInStock.setText (totalSkidsString);
		String currentText = textPane.getText ();
		textPane.setText (currentText + "  -  " + "( " + dateFormat.format (date) + " )   " + "Today " + aInt + " Skids came in and " + mInt + " Went out. we now have " + totalSkids + "   Skids in the Warehouse " + "( Enterd By: " + username + " )" + "Delivered by: " + driver + "\n");
	    }


	    else if (YN.equalsIgnoreCase ("1"))
	    {
		data ();
	    }


	    else
	    {
		System.exit (0);
	    }
	}
    }



    // runs the program, used also to require a username and password
    public static void main (String[] args) throws NullPointerException
    {
	username = JOptionPane.showInputDialog (null, "Please Enter You're UserName. \n note: if You are only a guest use: \n user: guest   Password: guest", "Username",
		JOptionPane.QUESTION_MESSAGE);
	if (username == null)
	{
	    System.exit (0);
	}
	else if (username.equals (""))
	{
	    JOptionPane.showMessageDialog (null, " Sorry, you must enter a valid username", " username error",
		    JOptionPane.ERROR_MESSAGE);
	}
	else if (username.equalsIgnoreCase ("kirk") || username.equalsIgnoreCase ("kyle") || username.equalsIgnoreCase ("bunny") || username.equalsIgnoreCase ("guest"))
	{

	    String Password = JOptionPane.showInputDialog (null, "Please Enter You're Password.", "Password",
		    JOptionPane.QUESTION_MESSAGE);
	    if (Password == null)
	    {
		System.exit (0);
	    }

	    if (username.equalsIgnoreCase ("kirk") && Password.equals ("aspire"))
	    {
		JFrame.setDefaultLookAndFeelDecorated (true);
		skidCount f = new skidCount ();
		f.getContentPane ().add (scrollPane);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.setSize (750, 500);
		f.setVisible (true);
		f.startupFileReader ();
		f.billsCountReader ();
		f.startupTextPane ();
	    }
	    else if (username.equalsIgnoreCase ("kyle") && Password.equals ("messenger"))
	    {
		JFrame.setDefaultLookAndFeelDecorated (true);
		skidCount f = new skidCount ();
		f.getContentPane ().add (scrollPane);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.setSize (750, 500);
		f.setVisible (true);
		f.startupFileReader ();
		f.billsCountReader ();
		f.startupTextPane ();
	    }
	    else if (username.equalsIgnoreCase ("bunny") && Password.equals ("aspire"))
	    {
		JFrame.setDefaultLookAndFeelDecorated (true);
		skidCount f = new skidCount ();
		f.getContentPane ().add (scrollPane);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.setSize (750, 500);
		f.setVisible (true);
		f.startupFileReader ();
		f.billsCountReader ();
		f.startupTextPane ();
	    }

	    else if (username.equalsIgnoreCase ("guest") && Password.equalsIgnoreCase ("guest"))
	    {
		skidCount f = new skidCount ();
		f.getContentPane ().add (scrollPane);
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.setSize (750, 500);
		f.setVisible (true);
		f.startupFileReader ();
		f.billsCountReader ();
		f.startupTextPane ();
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, " Sorry, that Password is incorrect, try again", " password error",
			JOptionPane.ERROR_MESSAGE);
		main (args);
	    }

	}
    }





    // end of main

    // executes based on certian actions being performed by the user
    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equalsIgnoreCase ("exit"))
	{
	    System.exit (0);
	}


	if (e.getActionCommand ().equalsIgnoreCase ("Bill's Count"))
	{
	    if (!username.equalsIgnoreCase ("guest"))
	    {
		try
		{
		    billsCount ();
		}
		catch (IOException a)
		{
		    JOptionPane.showMessageDialog (null, "Sorry, there was an ERROR in the program. Call kyle to look over it. /n ERROR CODE : AP BC IOE", "ERROR",
			    JOptionPane.ERROR_MESSAGE);

		}
	    }
	    else if (username.equalsIgnoreCase ("guest"))
	    {
		JOptionPane.showMessageDialog (null, "Sorry, Guest's cant edit Bill's count", "Privlages error",
			JOptionPane.ERROR_MESSAGE);

	    }
	}



	if (e.getActionCommand ().equalsIgnoreCase ("Edit Count"))
	{
	    if (!username.equalsIgnoreCase ("guest"))
	    {
		try
		{
		    data ();
		}
		catch (IOException a)
		{
		    JOptionPane.showMessageDialog (null, "Sorry, there was an ERROR in the program. Call kyle to look over it. /n ERROR CODE : AP EC IOE", "ERROR",
			    JOptionPane.ERROR_MESSAGE);

		}
	    }
	    else if (username.equalsIgnoreCase ("guest"))
	    {
		JOptionPane.showMessageDialog (null, "Sorry, Guest's cant edit the skid count", "Privlages error",
			JOptionPane.ERROR_MESSAGE);

	    }
	}

    }
}


