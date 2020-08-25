package model;

public class Entrance extends MapSquareEdge {
    public Entrance(String insideSquare, Dungeon dungeon){
        super(false,false,  insideSquare, MapSquare.OUTSIDE, dungeon);
    }

    @Override
    public String attemptWalkThrough() {
        return "You don't really want to go back where you came from.";
    }
    @Override
    public String attemptSeeThrough() {
        return "You're looking at a really familiar door.";
    }

}
