package model;

import java.util.ArrayList;

public abstract class MapSquareEdge {
    private boolean canSeeThrough;
    private boolean canWalkThrough;
   // private String insideSquare;
   // private String outsideSquare;
    private Dungeon myDungeon;
    private ArrayList<String> squares = new ArrayList<>();

    public boolean CanSeeThrough() {
        return canSeeThrough;
    }

    public boolean CanWalkThrough() {
        return canWalkThrough;
    }

    protected MapSquareEdge(boolean canSeeThrough, boolean canWalkThrough, String insideSquare, String outsideSquare, Dungeon dungeon) {
        this.canSeeThrough = canSeeThrough;
        this.canWalkThrough = canWalkThrough;
        squares.add(insideSquare);
        squares.add(outsideSquare);
        myDungeon = dungeon;
    }

    public Dungeon getDungeon() { return myDungeon; }

    public String getInsideSquareId(){
        return squares.get(0);
    }

    public String getOutsideSquareId(){
        return squares.get(1);
    }

    public abstract String attemptWalkThrough();
    public abstract String attemptSeeThrough();

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;
    }
}
