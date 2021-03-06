package server;

import wrappers.Placement;
import exceptions.ParseFailureException;
import game.LocationAndOrientation;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import wrappers.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by ianmaccallum on 11/26/16.
 */
public class ProtocolMessageParserTest {

    ProtocolMessageParser parser;

    @Before
    public void setup(){
        parser = new ProtocolMessageParser();
    }



    // MARK: - Other protocol tests
    @Test
    public void parseGID() throws Exception {
//        String gid0 = "ub4h3brdh34";
//        String input = "GAME " + gid0 + " ";
        String input = "MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 1 PLACE TLTJ-";
        String gid1 = parser.parseGID(input);
        System.out.println(gid1);
//        assertEquals(gid0, gid1);
    }



    // MARK: - Authentication protocol tests
    @Test
    public void parseIsSparta() throws Exception {
        boolean t = parser.parseIsSparta("THIS IS SPARTA!");
        assertTrue(t);
    }

    @Test
    public void parseIsHello() throws Exception {
        boolean t = parser.parseIsHello("HELLO!");
        assertTrue(t);
    }

    @Test
    public void parseWelcomePID() throws Exception {
        String id = "12384843";
        String input = "WELCOME " + id + " PLEASE WAIT FOR THE NEXT CHALLENGE";
        String pid = parser.parseWelcomePID(input);
        assertEquals(id, pid);
    }



    // MARK: - Challenge protocol tests
    @Test
    public void parseNewChallenge() throws Exception {
        String cid0 = "1";
        int rounds0 = 1;
//        String input = "NEW CHALLENGE " + cid0 + " YOU WILL PLAY " + rounds0 + " MATCHES";

        String input = "NEW CHALLENGE 1 YOU WILL PLAY 1 MATCH";
        Pair<String, Integer> pair = parser.parseNewChallenge(input);
        String cid1 = pair.getKey();
        int rounds1 = pair.getValue().intValue();

        System.out.println("CID " + cid1);
        System.out.println("Rounds " + rounds1);
        assertEquals(cid0, cid1);
        assertEquals(rounds0, rounds1);
    }

    @Test
    public void parseIsEndOfChallenges() throws Exception {
        String input = "END OF CHALLENGES";
        boolean t = parser.parseIsEndOfChallenges(input);
        assertTrue(t);
    }

    @Test
    public void parseIsWaitForNextChallenge() throws Exception {
        String input = "PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN";
        boolean t = parser.parseIsWaitForNextChallenge(input);
        assertTrue(t);
    }



    // MARK - Round protocol tests
    @Test
    public void parseBeginRound() throws Exception {
        String rid0 = "s4d54rvf56";
        int rounds0 = 5;
        String input = "BEGIN ROUND " + rid0 + " OF " + rounds0;

        Pair<String, Integer> pair = parser.parseBeginRound(input);
        String rid1 = pair.getKey();
        int rounds1 = pair.getValue().intValue();

        assertEquals(rid0, rid1);
        assertEquals(rounds0, rounds1);
    }

    @Test
    public void parseEndRound() throws Exception {
        String rid0 = "j38duin2h82";
        int rounds0 = 5;
        String input = "END OF ROUND " + rid0 + " OF " + rounds0;

        Pair<String, Integer> pair = parser.parseEndRound(input);
        String rid1 = pair.getKey();
        int rounds1 = pair.getValue().intValue();

        assertEquals(rid0, rid1);
        assertEquals(rounds0, rounds1);
    }

    @Test
    public void parseEndRoundAndWait() throws Exception {
        String rid0 = "930euwjdmi";
        int rounds0 = 5;
        String input = "END OF ROUND " + rid0 + " OF " + rounds0 + " PLEASE WAIT FOR THE NEXT MATCH";

        Pair<String, Integer> pair = parser.parseEndRoundAndWait(input);
        String rid1 = pair.getKey();
        int rounds1 = pair.getValue().intValue();

        assertEquals(rid0, rid1);
        assertEquals(rounds0, rounds1);
    }




    // MARK: - Match protocol tests
    @Test
    public void parseOpponentPID() throws Exception {
        String id = "12384843";
        String pid = parser.parseOpponentPID("YOUR OPPONENT IS PLAYER " + id);
        assertEquals(id, pid);
    }

    @Test
    public void parseStartingTile() throws Exception {
        String tile0 = "LLJJ-";
        int x0 = 4;
        int y0 = 3;
        int o0 = 2;

        String input = "STARTING TILE IS " + tile0 + " AT " + x0 + " " + y0 + " " + o0;
        Pair<String, LocationAndOrientation> pair = parser.parseStartingTile(input);

        LocationAndOrientation lo = pair.getValue();
        Point location = lo.getLocation();

        String tile1 = pair.getKey();
        int x1 = location.x;
        int y1 = location.y;
        int o1 = lo.getOrientation();

        assertEquals(tile0, tile1);
        assertEquals(x0, x1);
        assertEquals(y0, y1);
        assertEquals(o0, o1);
    }

    @Test
    public void parseRemainingTiles() throws Exception {
        String items = "[JJJJ- JJJJX JJJJX JJJJX JJJJX JJTJX JJTJX TTTT- TJTJ- TJTJ- TJTJ- TJTJ- TJTJ- TJTJ- TJTJ- TJTJ-]";
        String input = "THE REMAINING 9 TILES ARE " + items;
        Pair<Integer, String[]> pair = parser.parseRemainingTiles(input);

        Integer count = pair.getKey();
        String[] arr = pair.getValue();

        System.out.println("Count " + count.intValue());
        for (String s : arr) {
            System.out.println(s);
        }
    }

    @Test
    public void parseMatchBeginsPlanTime() throws Exception {
        int time0 = 2;
        String input = "MATCH BEGINS IN " + time0 + " SECONDS";

        int time1 = parser.parseMatchBeginsPlanTime(input).intValue();

        assertEquals(time0, time1);
    }

    @Test
    public void parseGameOver() throws Exception {
        String gid0 = "1";
        String pid_a0 = "TEAMC";
        String pid_b0 = "TEAMD";

        String input = "GAME 1 OVER PLAYER TEAMC FORFEITED PLAYER TEAMD WIN";

//        String input = "GAME " + gid0 + " OVER PLAYER " + pid_a0 + " FORFEITED PLAYER " + pid_b0 + " WIN";
        try {
            parser.parseGameOver(input);
            assertTrue(true);
        } catch (ParseFailureException e) {
            assertTrue(false);
        }



    }



    // MARK: - Move protocol tests
    @Test
    public void parseBeginTurn() throws Exception {

        String input = "MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 1 PLACE TLTJ-";


//        String gid0 = "n23iujrn";
//        int time0 = 1;
//        int move0 = 0;
//        String tile0 = "LLJJ0-";

//        String input = "MAKE YOUR MOVE IN GAME " + gid0 + " WITHIN " + time0 + " SECONDS: MOVE " + move0 + " PLACE " + tile0;
        BeginTurnWrapper wrapper = parser.parseBeginTurn(input);

        String gid1 = wrapper.getGid();
        int time1 = wrapper.getTime();
        int move1 = wrapper.getMoveNumber();
        String tile1 = wrapper.getTile();


        System.out.println(gid1);
        System.out.println(time1);
        System.out.println(move1);
        System.out.println(tile1);
//
//        assertEquals(gid0, gid1);
//        assertEquals(time0, time1);
//        assertEquals(move0, move1);
//        assertEquals(tile0, tile1);
    }

    @Test
    public void parseConfirmMove() throws Exception {
        String gid0 = "1";
        String pid0 = "TEAMD";
        int move0 = 1;
//        String error0 = "TIMEOUT";

        String input = "GAME 1 MOVE 1 PLAYER TEAMD PLACED TLLT- AT 0 -1 0 NONE";
//        String input = "GAME " + gid0 + " MOVE " + move0 + " PLAYER " + pid0 + " FORFEITED: " + error0;
        ConfirmedMoveWrapper wrapper = parser.parseConfirmMove(input);

        String gid1 = wrapper.getGid();
        String pid1 = wrapper.getPid();
        int move1 = wrapper.getMoveNumber();
//        String error1 = wrapper.getForfeitMessage();

        assertEquals(gid0, gid1);
        assertEquals(pid0, pid1);
        assertEquals(move0, move1);
//        assertEquals(error0, error1);
    }

    @Test
    public void parseForfeit() throws Exception {
        String error0 = "ILLEGAL TILE PLACEMENT";
        String input = "FORFEITED: " + error0;

        String error1 = parser.parseForfeit(input);
        assertEquals(error0, error1);
    }

    @Test
    public void parsePlacementMove() throws Exception {
        String tile0 = "TLLT-";
        int x0 = 0;
        int y0 = -1;
        int o0 = 0;
        String type0 = "NONE";
        int zone0 = 7;
//        String input = "PLACED " + tile0 + " AT " + x0 + " " + y0 + " " + o0 + " " + type0 + " " + zone0;

        String input = "PLACED TLLT- AT 0 -1 0 NONE";
        PlacementMoveWrapper wrapper = parser.parsePlacementMove(input);

        String tile1 = wrapper.getTile();
        Point location = wrapper.getLocation();
        int x1 = location.x;
        int y1 = location.y;
        int o1 = wrapper.getOrientation();
        int zone1 = wrapper.getZone();
        Placement p = wrapper.getPlacedObject();

        assertEquals(tile0, tile1);
        assertEquals(x0, x1);
        assertEquals(y0, y1);
        assertEquals(o0 / 90, o1);
//        assertEquals(zone0, zone1);
    }

    @Test
    public void parseNonplacementMove() throws Exception {
        String tile0 = "LLJJ-";
        int x0 = 4;
        int y0 = 3;

//        String input = "TILE " + tile0 + " UNPLACEABLE PASSED";
//        String input = "TILE " + tile0 + " UNPLACEABLE ADDED ANOTHER TIGER TO " + x0 + " " + y0;
        String input = "TILE " + tile0 + " UNPLACEABLE RETRIEVED TIGER AT " + x0 + " " + y0;
        NonplacementMoveWrapper wrapper = parser.parseNonplacementMove(input);

        System.out.println("Unplaceable type " + wrapper.getType());

        String tile1 = wrapper.getTile();
        Point location = wrapper.getTigerLocation();
        int x1 = location.x;
        int y1 = location.y;

        assertEquals(tile0, tile1);
        assertEquals(x0, x1);
        assertEquals(y0, y1);
    }

    @Test
    public void parseTigerZone() throws Exception {
        int zone0 = 6;
        String input = "TIGER " + zone0;

        int zone1 = parser.parseTigerZone(input);
        assertEquals(zone0, zone1);
    }
}