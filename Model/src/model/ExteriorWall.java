package model;

public class ExteriorWall extends MapSquareEdge {
    public ExteriorWall(String insideSquare, Dungeon dungeon){
        super(false,false,  insideSquare, MapSquare.OUTSIDE, dungeon);
    }

    @Override
    public String attemptWalkThrough() {
        return "You can't walk through the wall!";
    }
    @Override
    public String attemptSeeThrough() {
        return "You're looking at a wall.";
    }
}
