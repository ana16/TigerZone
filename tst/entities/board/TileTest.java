package entities.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TileTest {
    private Tile leftAdjacentTile;
    private Tile rightAdjacentTile;
    private Tile topAdjacentTile;
    private Tile bottomAdjacentTile;
    private Tile testTile;

    @Before
    public void setup() {
        testTile = new Tile();
        leftAdjacentTile = new Tile();
        rightAdjacentTile = new Tile();
        bottomAdjacentTile = new Tile();
        topAdjacentTile = new Tile();
    }
}
