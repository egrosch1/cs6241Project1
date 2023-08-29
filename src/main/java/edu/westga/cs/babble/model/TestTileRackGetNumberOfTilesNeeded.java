package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileRackGetNumberOfTilesNeeded {

    private TileRack tileRack;

    @BeforeEach
    public void setUp() {
        tileRack = new TileRack();
    }

    @Test
    public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
        int numberOfTilesNeeded = tileRack.getNumberOfTilesNeeded();

        assertEquals(TileRack.MAX_SIZE, numberOfTilesNeeded);
    }
    
    @Test
    public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() {
        Tile tile = new Tile('A');
        tileRack.append(tile);

        int numberOfTilesNeeded = tileRack.getNumberOfTilesNeeded();

        assertEquals(TileRack.MAX_SIZE - 1, numberOfTilesNeeded);
    }
    
    @Test
    public void tileRackWithSeveralTilesShouldNeedSomeTiles() {
        Tile tile1 = new Tile('A');
        Tile tile2 = new Tile('B');
        Tile tile3 = new Tile('C');
        tileRack.append(tile1);
        tileRack.append(tile2);
        tileRack.append(tile3);

        int numberOfTilesNeeded = tileRack.getNumberOfTilesNeeded();

        assertEquals(TileRack.MAX_SIZE - 3, numberOfTilesNeeded);
    }
    
    @Test
    public void fullRackNeedsZeroTiles() {
        for (char letter = 'A'; letter <= 'G'; letter++) {
            Tile tile = new Tile(letter);
            tileRack.append(tile);
        }

        int numberOfTilesNeeded = tileRack.getNumberOfTilesNeeded();

        assertEquals(0, numberOfTilesNeeded);
    }
}
