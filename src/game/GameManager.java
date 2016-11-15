package game;

import entities.board.Board;
import entities.board.Tile;
import entities.overlay.Region;
import entities.player.Player;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class GameManager {

    private List<Player> players;
    private int playerTurn;

    // *TODO PlayerNotifier notifier;
    // *TODO RegionLinker regionLinker;

    private Board board;

    public GameManager(Stack<Tile> stack, Player... players) {
        for(Player player : players) {
            this.players.add(player);
        }
        board = new Board(stack);
    }

    public void completeRegion(Region region){
        List<Player> playersToGetScore = region.getDominantPlayers();
        int score = region.getScorer().score(region);
        for(Player p : playersToGetScore){
            p.addToScore(score);
        }
    }

    public static void main(String[] args) throws IOException {



    }

}