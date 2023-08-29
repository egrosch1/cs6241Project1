package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TestTileConstructor {

	@Test
	public void shouldNotAllowNonLetters() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Tile('1'); 
		});
	}

	@Test
	public void shouldCreateOnePointTiles() {
		String onePointLetters = "EAIONRTLSU";

		for (char letter : onePointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(1, upperCaseTile.getPointValue());
			assertEquals(1, lowerCaseTile.getPointValue());
		}
	}

	@Test
	public void shouldCreateTwoPointTiles() {
		String twoPointLetters = "DG";

		for (char letter : twoPointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(2, upperCaseTile.getPointValue());
			assertEquals(2, lowerCaseTile.getPointValue());
		}
	}

	@Test
	public void shouldCreateThreePointTiles() {
		String threePointLetters = "BCMP";

		for (char letter : threePointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(3, upperCaseTile.getPointValue());
			assertEquals(3, lowerCaseTile.getPointValue());
		}
	}

	@Test
	public void shouldCreateFourPointTiles() {
		String fourPointLetters = "FHVWY";

		for (char letter : fourPointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(4, upperCaseTile.getPointValue());
			assertEquals(4, lowerCaseTile.getPointValue());
		}
	}

	@Test
	public void shouldCreateFivePointTiles() {
		String fivePointLetters = "K";

		for (char letter : fivePointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(5, upperCaseTile.getPointValue());
			assertEquals(5, lowerCaseTile.getPointValue());
		}
	}

	@Test
	public void shouldCreateEightPointTiles() {
		String eightPointLetters = "JX";

		for (char letter : eightPointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(8, upperCaseTile.getPointValue());
			assertEquals(8, lowerCaseTile.getPointValue());
		}
	}

	@Test
	public void shouldCreateTenPointTiles() {
		String tenPointLetters = "QZ";

		for (char letter : tenPointLetters.toCharArray()) {
			Tile upperCaseTile = new Tile(letter);
			Tile lowerCaseTile = new Tile(Character.toLowerCase(letter));

			assertEquals(10, upperCaseTile.getPointValue());
			assertEquals(10, lowerCaseTile.getPointValue());
		}
	}
}
