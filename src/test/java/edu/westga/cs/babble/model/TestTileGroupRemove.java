package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileGroupRemove {

	private TestTileGroup tileGroup;

	@BeforeEach
	public void setUp() {
		tileGroup = new TestTileGroup();
	}

	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			tileGroup.remove(null);
		});
	}

	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		assertThrows(TileNotInGroupException.class, () -> {
			tileGroup.remove(new Tile('A'));

		});
	}

	@Test
	public void canNotRemoveTileNotInTileGroup() {
		Tile tileInGroup = new Tile('A');
		Tile tileNotInGroup = new Tile('B');

		tileGroup.append(tileInGroup);

		assertThrows(TileNotInGroupException.class, () -> {
			tileGroup.remove(tileNotInGroup);
		});
	}

	@Test
	public void canRemoveOnlyTileInTileGroup() {
		Tile tile = new Tile('A');
		tileGroup.append(tile);

		try {
			tileGroup.remove(tile);
		} catch (TileNotInGroupException e) {
			fail("Unexpected exception: " + e.getMessage());
		}

		assertTrue(tileGroup.tiles().isEmpty());
	}
	
	

	private class TestTileGroup extends TileGroup {
	}
}
