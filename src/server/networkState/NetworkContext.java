package server.networkState;

import java.awt.*;

/**
 * Created by ianmaccallum on 11/28/16.
 */
public class NetworkContext {

    private NetworkState state;
    private String tournamentPassword;
    private String username;
    private String password;
    private String pid;
    private String opid;
    private String cid;
    private String rid;
    private int round;
    private int roundCount;
    private boolean shouldReturn = false;

    private String startingTile;
    private Point startingTileLocation;
    private int startingTileOrientation;

    private int remainingTileCount;
    private String[] remainingTiles;

    public NetworkContext(String tournamentPassword, String username, String password) {
        this.tournamentPassword = tournamentPassword;
        this.username = username;
        this.password = password;
    }

    public NetworkState getState() {
        return state;
    }

    public void setState(NetworkState state) {
        this.state = state;
    }

    public String getTournamentPassword() {
        return tournamentPassword;
    }

    public void setTournamentPassword(String tournamentPassword) {
        this.tournamentPassword = tournamentPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public boolean shouldReturn() {
        return shouldReturn;
    }

    public void setShouldReturn(boolean shouldReturn) {
        this.shouldReturn = shouldReturn;
    }

    public String getStartingTile() {
        return startingTile;
    }

    public void setStartingTile(String startingTile) {
        this.startingTile = startingTile;
    }

    public Point getStartingTileLocation() {
        return startingTileLocation;
    }

    public void setStartingTileLocation(Point startingTileLocation) {
        this.startingTileLocation = startingTileLocation;
    }

    public int getStartingTileOrientation() {
        return startingTileOrientation;
    }

    public void setStartingTileOrientation(int startingTileOrientation) {
        this.startingTileOrientation = startingTileOrientation;
    }

    public int getRemainingTileCount() {
        return remainingTileCount;
    }

    public void setRemainingTileCount(int remainingTileCount) {
        this.remainingTileCount = remainingTileCount;
    }

    public String[] getRemainingTiles() {
        return remainingTiles;
    }

    public void setRemainingTiles(String[] remainingTiles) {
        this.remainingTiles = remainingTiles;
    }
}
