package model;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private ArrayList<InventoryItem> inventory = new ArrayList<>();
    private MapSquare currentSquare;
    private Dungeon myDungeon;

    public Player(Dungeon dungeon){
        myDungeon = dungeon;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public MapSquare getCurrentSquare() { return currentSquare; }

    public void addToInventory(InventoryItem i){
        inventory.add(i);
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public void initPlayer(){
        currentSquare = myDungeon.getDungeonEntranceSquare();
    }

    public String moveToNewSquare(MapSquareEdge.Direction direction){
        MapSquareEdge edge = getCurrentSquare().getEdge(direction);
        // test Walkability
        if (edge.CanWalkThrough()) {
            // is it the exit?
            if (edge == myDungeon.getDungeonExit()) {
                myDungeon.setSolved(true);
            } else {
                // change location
                currentSquare = myDungeon.getNeighborSquare(getCurrentSquare(), direction);
            }
        }
        return edge.attemptWalkThrough();
    }

    public String lookAtNextSquare(MapSquareEdge.Direction direction){
        MapSquareEdge edge = getCurrentSquare().getEdge(direction);
        return edge.attemptSeeThrough();
    }
}
