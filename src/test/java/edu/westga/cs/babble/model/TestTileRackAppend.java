package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileRackAppend {

    private TileRack tileRack;

    @BeforeEach
    public void setUp() {
        tileRack = new TileRack();
    }

    @Test
    public void shouldNotAppendToFullRack() {
        for (int i = 0; i < TileRack.MAX_SIZE; i++) {
            tileRack.append(new Tile('A'));
        }

        assertThrows(TileRackFullException.class, () -> {
            tileRack.append(new Tile('B'));
        });

        assertEquals(TileRack.MAX_SIZE, tileRack.tiles().size());
    }
    
}
