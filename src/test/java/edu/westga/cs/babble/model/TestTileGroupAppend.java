package edu.westga.cs.babble.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestTileGroupAppend {


	private TestTileGroup tileGroup;

	@BeforeEach
	public void setUp() {
		tileGroup = new TestTileGroup();
	}

	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			tileGroup.append(null);
		});
	}

	@Test
	public void emptyGroupShouldBeEmpty() {
		assertTrue(tileGroup.tiles().isEmpty());
	}

	@Test
	public void shouldHaveOneTileInTileGroup() {
		Tile tile = new Tile('A');
		tileGroup.append(tile);
		assertEquals(1, tileGroup.tiles().size());
	}

	@Test
	public void shouldHaveManyTilesInTileGroup() {
		Tile tileA = new Tile('A');
		Tile tileB = new Tile('B');
		Tile tileC = new Tile('C');

		tileGroup.append(tileA);
		tileGroup.append(tileB);
		tileGroup.append(tileC);

		assertEquals(3, tileGroup.tiles().size());
	}

	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		Tile tileA = new Tile('A');
		Tile tileB = new Tile('B');

		tileGroup.append(tileA);
		tileGroup.append(tileB);
		tileGroup.append(tileA); 

		assertEquals(3, tileGroup.tiles().size()); 
	}

	@Test
	public void canNotAddSameTileTwice() {
		Tile tileA = new Tile('A');

		tileGroup.append(tileA);

		assertThrows(IllegalArgumentException.class, () -> {
			tileGroup.append(tileA); 
		});
	}

	private class TestTileGroup extends TileGroup {
	}
}