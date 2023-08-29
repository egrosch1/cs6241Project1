package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayedWordMatches {

	private PlayedWord playedWord;

	@BeforeEach
	public void setUp() {
		playedWord = new PlayedWord();
	}

	@Test
	public void hasTilesForAMultipleLetterWord() {
		Tile tileH = new Tile('H');
		Tile tileE = new Tile('E');
		Tile tileL = new Tile('L');
		Tile tileP = new Tile('P');

		playedWord.append(tileH);
		playedWord.append(tileE);
		playedWord.append(tileL);
		playedWord.append(tileP);

		boolean result = playedWord.matches("HELP");

		assertTrue(result);
	}

	@Test
	public void hasTilesForASingleLetterWord() {
		Tile tileA = new Tile('A');
		playedWord.append(tileA);
		boolean result = playedWord.matches("A");
		assertTrue(result);
	}

	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		Tile tileH = new Tile('H');
		Tile tileE = new Tile('E');
		Tile tileL = new Tile('L');
		Tile tileP = new Tile('P');

		playedWord.append(tileH);
		playedWord.append(tileE);
		playedWord.append(tileL);
		playedWord.append(tileP);

		boolean result = playedWord.matches("PHEL");

		assertFalse(result);
	}

	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		Tile tileH = new Tile('H');
		Tile tileE = new Tile('E');
		Tile tileL = new Tile('L');

		playedWord.append(tileH);
		playedWord.append(tileE);
		playedWord.append(tileL);

		boolean result = playedWord.matches("HELP");

		assertFalse(result);
	}

	@Test
	public void canMatchWordWithDuplicateLetters() {
		Tile tileH = new Tile('H');
		Tile tileE = new Tile('E');
		Tile tileL = new Tile('L');
		Tile tileL2 = new Tile('L');
		Tile tileO = new Tile('O');

		playedWord.append(tileH);
		playedWord.append(tileE);
		playedWord.append(tileL);
		playedWord.append(tileL2);
		playedWord.append(tileO);

		boolean result = playedWord.matches("HELLO");

		assertTrue(result);
	}

	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		Tile tileH = new Tile('H');
		Tile tileE = new Tile('E');
		Tile tileL = new Tile('L');
		Tile tileP = new Tile('P');

		playedWord.append(tileH);
		playedWord.append(tileE);
		playedWord.append(tileL);
		playedWord.append(tileP);

		boolean result = playedWord.matches("");

		assertFalse(result);
	}

	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		boolean result = playedWord.matches("");

		assertFalse(result);
	}

	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			playedWord.matches(null);
		});
	}

	@Test
	public void shouldClearEmptyWord() {
		playedWord.clear();
		assertTrue(playedWord.tiles().isEmpty());
	}

	@Test
	public void shouldClearWordWithOneTile() {
		Tile tile = new Tile('A');
		playedWord.append(tile);

		playedWord.clear();

		assertTrue(playedWord.tiles().isEmpty());
	}

	@Test
	public void shouldClearWordWithManyTiles() {
		Tile tile1 = new Tile('H');
		Tile tile2 = new Tile('E');
		Tile tile3 = new Tile('L');
		Tile tile4 = new Tile('P');

		playedWord.append(tile1);
		playedWord.append(tile2);
		playedWord.append(tile3);
		playedWord.append(tile4);

		playedWord.clear();

		assertTrue(playedWord.tiles().isEmpty());
	}
}

