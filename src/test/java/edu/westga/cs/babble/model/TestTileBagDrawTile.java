package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileBagDrawTile {

    private TileBag tileBag;

    @BeforeEach
    public void setUp() {
        tileBag = new TileBag();
    }

    @Test
    public void shouldDrawAllTiles() {
        int totalTiles = 98;
        int drawnTiles = 0;

        assertFalse(tileBag.isEmpty());

        try {
            while (!tileBag.isEmpty()) {
                tileBag.drawTile();
                drawnTiles++;
            }
        } catch (EmptyTileBagException e) {
            fail("Unexpected EmptyTileBagException: " + e.getMessage());
        }

        assertEquals(totalTiles, drawnTiles);
        assertTrue(tileBag.isEmpty());
    }
    
    @Test
    public void canNotDrawTooManyTiles() {
        int maxTiles = 98; 

        assertFalse(tileBag.isEmpty());

        try {
            for (int i = 0; i < maxTiles; i++) {
                tileBag.drawTile();
            }
        } catch (EmptyTileBagException e) {
            fail("Unexpected EmptyTileBagException: " + e.getMessage());
        }

        assertTrue(tileBag.isEmpty());

        assertThrows(EmptyTileBagException.class, () -> tileBag.drawTile());
    }
}

