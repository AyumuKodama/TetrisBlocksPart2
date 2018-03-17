import java.awt.Color;
import java.util.List;
import java.util.Random;

// Represents a Tetris piece.
public class Tetrad
{
	private Block[] blocks;	// The blocks for the piece.
	private Location[] locs;

	// Constructs a Tetrad.
	public Tetrad(BoundedGrid<Block> grid)
	{
		blocks = new Block[4];
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = new Block();
		}
		Color color = null;
		Location[] locs = new Location[4];
		
		Random r = new Random();
		int shape = r.nextInt(7);
		
		if (shape == 0) {
			
			color = Color.RED;
			locs[1] = new Location(0, 3);
			locs[0] = new Location(0, 4);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(0, 6);
			
		} else if (shape == 1) {
			
			color = Color.GRAY;
			locs[1] = new Location(0, 3);
			locs[0] = new Location(0, 4);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(1, 4);
			
		} else if (shape == 2) {
			
			color = Color.CYAN;
			locs[0] = new Location(0, 4);
			locs[1] = new Location(0, 5);
			locs[2] = new Location(1, 4);
			locs[3] = new Location(1, 5);
			
		} else if (shape == 3) {
			
			color = Color.YELLOW;
			locs[1] = new Location(0, 4);
			locs[0] = new Location(0, 5);
			locs[2] = new Location(0, 6);
			locs[3] = new Location(1, 4);
			
		} else if (shape == 4) {
			
			color = Color.MAGENTA;
			locs[1] = new Location(0, 3);
			locs[0] = new Location(0, 4);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(1, 5);
			
		} else if (shape == 5) {
			
			color = Color.BLUE;
			locs[1] = new Location(1, 3);
			locs[0] = new Location(0, 4);
			locs[2] = new Location(0, 5);
			locs[3] = new Location(1, 4);
			
		} else if (shape == 6) {
			
			color = Color.GREEN;
			locs[2] = new Location(1, 5);
			locs[1] = new Location(0, 4);
			locs[0] = new Location(0, 5);
			locs[3] = new Location(1, 6);
			
		}
		
		for (Block b : blocks) {
			b.setColor(color);
		}
		addToLocations(grid, locs);
	}


	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	//						deltaCol columns to the right, if those positions are
	//						valid and empty.
	//						Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		BoundedGrid<Block> grid;
		grid = blocks[0].getGrid();
		Location[] oldLoc = removeBlocks();
		Location[] newLoc = new Location[blocks.length];
		
		for (int i = 0; i < newLoc.length; i++) {
			newLoc[i] = new Location(oldLoc[i].getRow() + deltaRow, oldLoc[i].getCol() + deltaCol);
		}
		
		if (areEmpty(grid, newLoc)) {
			addToLocations(grid, newLoc);
			return true;
		} else {
			addToLocations(grid, oldLoc);
			return false;
		}

	
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	//                about its center, if the necessary positions are empty.
	//                Returns true if successful and false otherwise.
	public boolean rotate()
	{
		BoundedGrid<Block> grid;
		grid = blocks[0].getGrid();
		Location[] oldLoc = removeBlocks();
		Location[] newLoc = new Location[blocks.length];
		
		int row = oldLoc[0].getRow();
		int col = oldLoc[0].getCol();
		
		for (int i = 0; i < newLoc.length; i++) {
			newLoc[i] = new Location(row - col + oldLoc[i].getCol(), row + col - oldLoc[i].getRow());
		}
		
		if (areEmpty(grid, newLoc)) {
			addToLocations(grid, newLoc);
			return true;
		} else {
			addToLocations(grid, oldLoc);
			return false;
		}
		
	}


	// Precondition:  The elements of blocks are not in any grid;
	//                locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	//                and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		for (int i = 0; i < locs.length; i++) {
			blocks[i].putSelfInGrid(grid, locs[i]);
		}
	}

	// Precondition:  The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	//                and their old locations returned.
	private Location[] removeBlocks()
	{
		Location[] locs = new Location[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			locs[i] = blocks[i].getLocation();
			blocks[i].removeSelfFromGrid();
		}
		return locs;
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	//                and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		List<Location> locList = grid.getOccupiedLocations();
		boolean empty = false;
		for (int i = 0; i < locs.length; i++) {
			for (Location location : locList) {
				if (location.equals(locs[i])) {
					empty = false;
				}
				if (!empty || !grid.isValid(locs[i])) {
					return false;
				}
			}
		}
		return true;
	}
}