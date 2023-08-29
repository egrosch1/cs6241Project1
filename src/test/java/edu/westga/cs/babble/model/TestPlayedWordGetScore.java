package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayedWordGetScore {

	private PlayedWord playedWord;

	@BeforeEach
	public void setUp() {
		playedWord = new PlayedWord();
	}

	@Test
	public void emptyWordShouldHaveScoreOfZero() {
		int score = playedWord.getScore();

		assertEquals(0, score);
	}

	@Test
	public void scoreAOneTileWord() {
		Tile tile = new Tile('A');
		playedWord.append(tile);

		int score = playedWord.getScore();

		assertEquals(1, score);
	}


	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		Tile tileA = new Tile('A');
		Tile tileB = new Tile('B');
		Tile tileC = new Tile('K');

		playedWord.append(tileA);
		playedWord.append(tileB);
		playedWord.append(tileC);

		int score = playedWord.getScore();

		assertEquals(1 + 3 + 5, score);
	}

	@Test
	public void scoreAWordContainingDuplicateTiles() {
		Tile tileA = new Tile('A');
		Tile tileB = new Tile('B');
		Tile tileK = new Tile('K');
		Tile tileD = new Tile('A');



		playedWord.append(tileA);
		playedWord.append(tileB);
		playedWord.append(tileK);
		playedWord.append(tileD);

		int score = playedWord.getScore();

		assertEquals(1 + 3 + 5 + 1, score);
	}
}
