package model;

public class Exit extends MapSquareEdge {
    public Exit(String insideSquare, Dungeon dungeon){
        super(false,true,  insideSquare, MapSquare.OUTSIDE, dungeon);
    }

    @Override
    public String attemptWalkThrough() {
        return "You've reached the exit!";
    }
    @Override
    public String attemptSeeThrough() {
        return "You're looking at an unfamiliar door.";
    }
}
