public class Tetris implements ArrowListener
{
	private BoundedGrid<Block> grid;	// The grid containing the Tetris pieces.
	private BlockDisplay display;		// Displays the grid.
	private Tetrad activeTetrad;		// The active Tetrad (Tetris Piece).

	// Constructs a Tetris Game
	public Tetris()
	{
		grid = new BoundedGrid<Block>(20, 10);
		display = new BlockDisplay(grid);
		display.setTitle("Tetris");
		display.setArrowListener(this);
		activeTetrad = new Tetrad(grid);
	}

	// Play the Tetris Game
	public void play()
	{
		display.showBlocks();
		boolean forever = true;
		while (forever) {
			activeTetrad.translate(1, 0);
			sleep(1);
			display.showBlocks();
		}
	}


	// Precondition:  0 <= row < number of rows
	// Postcondition: Returns true if every cell in the given row
	//                is occupied; false otherwise.
	private boolean isCompletedRow(int row)
	{
		for (int i = 0; i < grid.getNumCols(); i++) {
			Location loc = new Location(row, i);
			if (grid.get(loc) == null) {
				return false;
			}
		}
		return true;
	}

	// Precondition:  0 <= row < number of rows;
	//                The given row is full of blocks.
	// Postcondition: Every block in the given row has been removed, and
	//                every block above row has been moved down one row.
	private void clearRow(int row)
	{
		if (isCompletedRow(row)) {
			for (int i = 0; i < grid.getNumCols(); i++) {
				Location loc = new Location(row, i);
				grid.get(loc).removeSelfFromGrid();
			}
			for (int i = row - 1; row >= 0; row--) {
				for (int j = 0; j < grid.getNumCols(); j++) {
					Location loc = new Location(i, j);
					Location newLoc = new Location(i + 1, j);
					grid.get(loc).moveTo(newLoc);
				}
			}
		}
		
	}

	// Postcondition: All completed rows have been cleared.
	private void clearCompletedRows()
	{
		for (int i = 0; i < grid.getNumRows(); i++) {
			clearRow(i);
		}
	}

	// Sleeps (suspends the active thread) for duration seconds.
	private void sleep(double duration)
	{
		final int MILLISECONDS_PER_SECOND = 1000;

		int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.err.println("Can't sleep!");
		}
	}
	
	public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
	}
	public void downPressed() {
		activeTetrad.translate(1, 0);
		display.showBlocks();
	}
	public void leftPressed() {
		activeTetrad.translate(0,-1);
		display.showBlocks();
	}
	public void rightPressed() {
		activeTetrad.translate(0, 1);
		display.showBlocks();
	}


	// Creates and plays the Tetris game.
	public static void main(String[] args)
	{
		Tetris tetris = new Tetris();
		tetris.play();
	}
}