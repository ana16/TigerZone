package entities.overlay;

import entities.board.Node;
import entities.board.Terrain;
import entities.board.Tiger;
import entities.overlay.TileSection;
import entities.player.Player;
import exceptions.BadPlacementException;
import exceptions.TigerAlreadyPlacedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TileSectionTest {
    @Test
    public void testAddingNodesShouldHaveCorrectNumber() {
        TileSection section = defaultTileSection();
        section.addNodes(new Node(), new Node());
        Assert.assertTrue(section.getNodes().size() == 2);
        section.addNodes(new Node());
        Assert.assertTrue(section.getNodes().size() == 3);
    }

    @Test
    public void testToStringShouldReturnCorrectString() {
        TileSection section = defaultTileSection();
        int hashCode = section.hashCode();
        Assert.assertEquals(section.toString(), "" + hashCode);
    }

    @Test
    public void testHasOpenConnectionShouldReturnTrueIfUnconnectedNodesExist() {
        TileSection section = defaultTileSection();
        section.addNodes(new Node(), new Node(), new Node());
        section.getNodes().get(0).setConnectedNode(new Node());
        Assert.assertTrue(section.hasOpenConnection());
        section.getNodes().get(1).setConnectedNode(new Node());
        Assert.assertTrue(section.hasOpenConnection());
        section.getNodes().get(2).setConnectedNode(new Node());
        Assert.assertFalse(section.hasOpenConnection());

    }

    @Test
    public void testTigerPlacementShouldWorkWithOneTigerPlaced() throws TigerAlreadyPlacedException {
        TileSection tileSection = defaultTileSection();
        Tiger tiger = new Tiger(new Player(""));
        tileSection.placeTiger(tiger);
        Assert.assertEquals(tiger, tileSection.getTiger());
    }

    @Test (expected = TigerAlreadyPlacedException.class)
    public void testPlaceTigerShouldThrowExceptionIfTigerAlreadyThere() throws TigerAlreadyPlacedException {
        Tiger tiger1 = new Tiger(new Player(""));
        Tiger tiger2 = new Tiger(new Player(""));
        TileSection section = defaultTileSection();
        section.placeTiger(tiger1);
        section.placeTiger(tiger2);
    }

    private TileSection defaultTileSection() {
        return new TileSection(Terrain.JUNGLE);
    }
}