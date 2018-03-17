
import java.awt.Color;

public class Block
{
	private BoundedGrid<Block> grid;
	private Location location;
	private Color color;

	// Constructs a blue block, because blue is the greatest color ever!
	public Block()
	{
		//color = Color.BLUE;
		grid = null;
		location = null;
	}

	// Gets the color of this block.
	public Color getColor()
	{
		return color;
	}

	// Sets the color of this block to newColor.
	public void setColor(Color newColor)
	{
		color = newColor;
	}

	// Gets the grid of this block,
	// or null if this block is not contained in a grid.
	public BoundedGrid<Block> getGrid()
	{
		return grid;
	}

	// Gets the location of this block,
	// or null if this block is not contained in a grid.
	public Location getLocation()
	{
		return location;
	}

	// Removes this block from its grid.
	// Precondition:  this block is contained in a grid.
	public void removeSelfFromGrid()
	{
		grid.remove(location);
		grid = null;
		location = null;
	}

	// Puts this block into location loc of grid gr.
	// If there is another block at loc, it is removed.
	// Precondition:  (1) this block is not contained in a grid.
	//                (2) loc is valid in gr.
	public void putSelfInGrid(BoundedGrid<Block> gr, Location loc)
	{
		Block block = gr.get(loc);
		if (block != null) {
			block.removeSelfFromGrid();
		}
		gr.put(loc, this);
		grid = gr;
		location = loc;
		
	}

	// Moves this block to newLocation.
	// If there is another block at newLocation, it is removed.
	// Precondition:  (1) this block is contained in a grid.
	//                (2) newLocation is valid in the grid of this block.
	public void moveTo(Location newLocation)
	{
		grid.remove(location);
		Block block = grid.get(newLocation);
		if (block != null) {
			block.removeSelfFromGrid();
		}
		location = newLocation;
		grid.put(location, this);
	}

	// Returns a string with the location and color of this block.
	public String toString()
	{
		return "Block[location=" + location + ",color=" + color + "]";
	}
}