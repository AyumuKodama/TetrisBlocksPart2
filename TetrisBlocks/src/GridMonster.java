
import java.awt.Color;
import java.util.List;

public class GridMonster
{
	public static void main(String[] args)
	{
		System.out.println("TESTING WITH STRINGS");
		gridTests1();

		System.out.println("\nTESTING WITH BLOCKS");
		gridTests2();

		System.out.println("\nYOUR CODE PASSED ALL THE TESTS!  8^)");
	}

	private static void gridTests1()
	{
		System.out.println("Level 1: Getting number of rows and columns");

		final BoundedGrid<String> GRID0 = new BoundedGrid<String>(1, 2);

		if (GRID0.getNumRows() != 1)
			throw new RuntimeException("getNumRows is dumb.");
		if (GRID0.getNumCols() != 2)
			throw new RuntimeException("getNumCols is dumb.");


		System.out.println("Level 2: Testing if valid");

		if (! GRID0.isValid(new Location(0, 0)))
			throw new RuntimeException("isValid is dumb.");
		if (! GRID0.isValid(new Location(0, 1)))
			throw new RuntimeException("isValid is dumb.");
		if (GRID0.isValid(new Location(0, 2)))
			throw new RuntimeException("isValid is dumb.");
		if (GRID0.isValid(new Location(1, 0)))
			throw new RuntimeException("isValid is dumb.");
		if (GRID0.isValid(new Location(0, -1)))
			throw new RuntimeException("isValid is dumb.");
		if (GRID0.isValid(new Location(-1, 0)))
			throw new RuntimeException("isValid is dumb.");

		final BoundedGrid<String> GRID1 = new BoundedGrid<String>(2, 1);

		if (! GRID1.isValid(new Location(0, 0)))
			throw new RuntimeException("isValid is dumb.");
		if (! GRID1.isValid(new Location(1, 0)))
			throw new RuntimeException("isValid is dumb.");
		if (GRID1.isValid(new Location(2, 0)))
			throw new RuntimeException("isValid is dumb.");
		if (GRID1.isValid(new Location(0, 1)))
			throw new RuntimeException("isValid is dumb.");


		System.out.println("Level 3: Getting, putting, and removing");

		final Location LOCATION = new Location(1, 0);
		final String FIRST = "first";
		final String SECOND = "second";

		String value = GRID1.get(LOCATION);
		if (value != null)
			throw new RuntimeException("get is dumb.");
		value = GRID1.put(LOCATION, FIRST);
		if (value != null)
			throw new RuntimeException("put is dumb.");
		value = GRID1.get(LOCATION);
		if (value != FIRST)
			throw new RuntimeException("put/get is dumb.");
		value = GRID1.put(LOCATION, SECOND);
		if (value != FIRST)
			throw new RuntimeException("put is dumb.");
		value = GRID1.get(LOCATION);
		if (value != SECOND)
			throw new RuntimeException("put is dumb.");
		value = GRID1.remove(LOCATION);
		if (value != SECOND)
			throw new RuntimeException("remove is dumb.");
		value = GRID1.get(LOCATION);
		if (value != null)
			throw new RuntimeException("remove is dumb.");
		value = GRID1.remove(LOCATION);
		if (value != null)
			throw new RuntimeException("remove is dumb.");


		System.out.println("Level 4: Getting occupied locations");

		List<Location> locs = GRID1.getOccupiedLocations();
		if (locs.size() != 0)
			throw new RuntimeException("getOccupiedLocations is dumb.");
		GRID1.put(new Location(1, 0), FIRST);
		locs = GRID1.getOccupiedLocations();
		if (locs.size() != 1)
			throw new RuntimeException("getOccupiedLocations is dumb.");
		if (! locs.get(0).equals(new Location(1, 0)))
			throw new RuntimeException("getOccupiedLocations is dumb.");
		GRID1.put(new Location(0, 0), SECOND);
		locs = GRID1.getOccupiedLocations();
		if (locs.size() != 2)
			throw new RuntimeException("getOccupiedLocations is dumb.");
		if (!(
			   (locs.get(0).equals(new Location(0, 0)) &&
			    locs.get(1).equals(new Location(1, 0)))
			   ||
			   (locs.get(0).equals(new Location(1, 0)) &&
			    locs.get(1).equals(new Location(0, 0)))))
			throw new RuntimeException("getOccupiedLocations is dumb.");
	}

	private static void gridTests2()
	{
		System.out.println("Level 5: Putting into an empty location");

		final Block BLOCK1 = new Block();

		BLOCK1.setColor(Color.RED);
		if (BLOCK1.getColor() != Color.RED)
			throw new RuntimeException("setColor/getColor is dumb.");

		final BoundedGrid<Block> GRID2 = new BoundedGrid<Block>(4, 5);
		final BlockDisplay DISPLAY = new BlockDisplay(GRID2);

		BLOCK1.putSelfInGrid(GRID2, new Location(0, 2));
		DISPLAY.showBlocks();
		if (GRID2.get(new Location(0, 2)) != BLOCK1)
			throw new RuntimeException("putSelfInGrid is dumb.");
		if (BLOCK1.getGrid() != GRID2)
			throw new RuntimeException("putSelfInGrid is dumb.");
		if (! BLOCK1.getLocation().equals(new Location(0, 2)))
			throw new RuntimeException("putSelfInGrid is dumb.");


		System.out.println("Level 6: Removing from grid");

		BLOCK1.removeSelfFromGrid();
		DISPLAY.showBlocks();
		if (GRID2.get(new Location(0, 2)) != null)
			throw new RuntimeException("removeSelfFromGrid is dumb.");
		if (BLOCK1.getGrid() != null)
			throw new RuntimeException("removeSelfFromGrid is dumb.");
		if (BLOCK1.getLocation() != null)
			throw new RuntimeException("removeSelfFromGrid is dumb.");


		System.out.println("Level 7: Putting into an occupied location");

		BLOCK1.putSelfInGrid(GRID2, new Location(0, 2));
		DISPLAY.showBlocks();
		final Block BLOCK2 = new Block();
		BLOCK2.putSelfInGrid(GRID2, new Location(0, 2));
		DISPLAY.showBlocks();
		if (BLOCK1.getGrid() != null)
			throw new RuntimeException("putSelfInGrid is dumb.");
		if (BLOCK1.getLocation() != null)
			throw new RuntimeException("putSelfInGrid is dumb.");
		if (BLOCK2.getGrid() != GRID2)
			throw new RuntimeException("putSelfInGrid is dumb.");
		if (! BLOCK2.getLocation().equals(new Location(0, 2)))
			throw new RuntimeException("putSelfInGrid is dumb.");
		if (GRID2.get(new Location(0, 2)) != BLOCK2)
			throw new RuntimeException("putSelfInGrid is dumb.");


		System.out.println("Level 8: Moving to an empty location");

		BLOCK2.moveTo(new Location(0, 4));
		DISPLAY.showBlocks();
		if (GRID2.get(new Location(0, 2)) != null)
			throw new RuntimeException("moveTo is dumb.");
		if (! BLOCK2.getLocation().equals(new Location(0, 4)))
			throw new RuntimeException("moveTo is dumb.");
		if (GRID2.get(new Location(0, 4)) != BLOCK2)
			throw new RuntimeException("moveTo is dumb.");


		System.out.println("Level 9: Moving to an occupied location");

		BLOCK1.putSelfInGrid(GRID2, new Location(0, 1));
		DISPLAY.showBlocks();
		BLOCK2.moveTo(new Location(0, 1));
		DISPLAY.showBlocks();
		if (GRID2.get(new Location(0, 4)) != null)
			throw new RuntimeException("moveTo is dumb.");
		if (BLOCK1.getGrid() != null)
			throw new RuntimeException("moveTo is dumb.");
		if (BLOCK1.getLocation() != null)
			throw new RuntimeException("moveTo is dumb.");
		if (BLOCK2.getGrid() != GRID2)
			throw new RuntimeException("moveTo is dumb.");
		if (! BLOCK2.getLocation().equals(new Location(0, 1)))
			throw new RuntimeException("moveTo is dumb.");
		if (GRID2.get(new Location(0, 1)) != BLOCK2)
			throw new RuntimeException("moveTo is dumb.");


		System.out.println("Level 10: Moving to its own location");

		BLOCK2.moveTo(new Location(0, 1));
		DISPLAY.showBlocks();
		if (BLOCK2.getGrid() != GRID2)
			throw new RuntimeException("moveTo is dumb.");
		if (! BLOCK2.getLocation().equals(new Location(0, 1)))
			throw new RuntimeException("moveTo is dumb.");
		if (GRID2.get(new Location(0, 1)) != BLOCK2)
			throw new RuntimeException("moveTo is dumb.");

		Block block = new Block();
		block.putSelfInGrid(GRID2, new Location(0, 3));
		block = new Block();
		block.setColor(Color.RED);
		block.putSelfInGrid(GRID2, new Location(2, 0));
		block = new Block();
		block.setColor(Color.RED);
		block.putSelfInGrid(GRID2, new Location(2, 4));
		block = new Block();
		block.setColor(Color.RED);
		block.putSelfInGrid(GRID2, new Location(3, 1));
		block = new Block();
		block.setColor(Color.RED);
		block.putSelfInGrid(GRID2, new Location(3, 2));
		block = new Block();
		block.setColor(Color.RED);
		block.putSelfInGrid(GRID2, new Location(3, 3));
		DISPLAY.showBlocks();
	}
}