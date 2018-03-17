import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Used to display the contents of a game board
public class BlockDisplay implements KeyListener
{
	private static final Color BACKGROUND = Color.BLACK;

	private BoundedGrid<Block> board;
	private JPanel[][] grid;
	private JFrame frame;
	private ArrowListener listener;

	// Constructs a new display for displaying the given board
	public BlockDisplay(BoundedGrid<Block> board)
	{
		this.board = board;
		grid = new JPanel[board.getNumRows()][board.getNumCols()];

		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});

		//Wait until display has been drawn
		try
		{
			while (frame == null || !frame.isVisible())
				Thread.sleep(1);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private void createAndShowGUI()
	{
		//Create and set up the window.
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(board.getNumRows(), board.getNumCols()));
		frame.addKeyListener(this);

		//Create each square component.
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
			{
				grid[row][col] = new JPanel();
				grid[row][col].setBackground(BACKGROUND);
				grid[row][col].setPreferredSize(new Dimension(20, 20));
				frame.getContentPane().add(grid[row][col]);
			}

		//Show the board
		showBlocks();

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	//Redraws the board to include the pieces and border colors.
	public void showBlocks()
	{
		for (int row = 0; row < grid.length; row++)
			for (int col = 0; col < grid[row].length; col++)
			{
				Location loc = new Location(row, col);

				Block square = board.get(loc);

				if (square == null)
				{
					grid[row][col].setBackground(BACKGROUND);
					grid[row][col].setBorder(null);
				}
				else
				{
					grid[row][col].setBackground(square.getColor());
					grid[row][col].setBorder(BorderFactory.createLineBorder(BACKGROUND));
				}
			}
	}

	// Sets the title of the window.
	public void setTitle(String title)
	{
		frame.setTitle(title);
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyPressed(KeyEvent e)
	{
		if (listener == null)
			return;
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT)
			listener.leftPressed();
		else if (code == KeyEvent.VK_RIGHT)
			listener.rightPressed();
		else if (code == KeyEvent.VK_DOWN)
			listener.downPressed();
		else if (code == KeyEvent.VK_UP)
			listener.upPressed();
	}

	public void setArrowListener(ArrowListener listener)
	{
		this.listener = listener;
	}
}