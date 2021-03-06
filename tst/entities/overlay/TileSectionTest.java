package entities.overlay;

import entities.board.Node;
import entities.board.Terrain;
import entities.board.Tiger;
import exceptions.TigerAlreadyPlacedException;
import org.junit.Assert;
import org.junit.Test;

public class TileSectionTest {
    @Test
    public void testCreatingATileSectionHasTheCorrectTerrain() {
        TileSection section = new TileSection(Terrain.LAKE);
        Assert.assertTrue(section.getTerrain() == Terrain.LAKE);
    }

    @Test
    public void testAddingNodesShouldHaveCorrectNumber() {
        TileSection section = new TileSection(Terrain.LAKE);
        section.addNodes(new Node(), new Node());
        Assert.assertEquals(section.getNodes().size(), 2);
        section.addNodes(new Node());
        Assert.assertEquals(section.getNodes().size(), 3);
    }

    @Test
    public void testToStringShouldReturnCorrectString() {
        TileSection section = defaultTileSection();
        int hashCode = section.hashCode();
        Assert.assertEquals(section.toString(), "" + hashCode);
    }

    @Test
    public void testHasOpenConnectionShouldReturnTrueIfUnconnectedNodesExist() {
        TileSection section = new TileSection(Terrain.LAKE);
        section.addNodes(new Node(), new Node(), new Node());
        section.getNodes().get(0).addConnectedNode(new Node());
        Assert.assertTrue(section.hasOpenConnection());
        section.getNodes().get(1).addConnectedNode(new Node());
        Assert.assertTrue(section.hasOpenConnection());
        section.getNodes().get(2).addConnectedNode(new Node());
        Assert.assertFalse(section.hasOpenConnection());

    }

    @Test
    public void testTigerPlacementShouldWorkWithOneTigerPlaced() throws TigerAlreadyPlacedException {
        TileSection tileSection = defaultTileSection();
        Tiger tiger = new Tiger("", false);
        tileSection.placeTiger(tiger);
        Assert.assertEquals(tiger, tileSection.getTiger());
    }

    @Test (expected = TigerAlreadyPlacedException.class)
    public void testPlaceTigerShouldThrowExceptionIfTigerAlreadyThere() throws TigerAlreadyPlacedException {
        Tiger tiger1 = new Tiger("", false);
        Tiger tiger2 = new Tiger("", false);
        TileSection section = defaultTileSection();
        section.placeTiger(tiger1);
        section.placeTiger(tiger2);
    }

    private TileSection defaultTileSection() {
        TileSection tileSection = new TileSection(Terrain.JUNGLE);
        tileSection.addNodes(new Node(), new Node());
        return tileSection;
    }
}
