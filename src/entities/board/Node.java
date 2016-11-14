package entities.board;

import entities.overlay.Region;
import entities.overlay.TileSection;

public class Node {
    private Node connectedNode;
    private TileSection tileSection;
    private Terrain terrain;

    public Node() {
        this.connectedNode = null;
    }

    public TileSection getTileSection() {
        return tileSection;
    }

    public void setTileSection(TileSection tileSection) {
        this.tileSection = tileSection;
    }
    
    public Node getConnectedNode() {
        return connectedNode;
    }

    public void setConnectedNode(Node node) {
        connectedNode = node;
    }

    public boolean isConnected() {
        return connectedNode != null;
    }

    public Terrain getTerrain() {
        return terrain;
    }
}