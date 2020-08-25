package model;

public class InteriorWall extends MapSquareEdge {
    public InteriorWall(String insideSquare, String outsideSquare, Dungeon dungeon){
        super(false,false,  insideSquare, outsideSquare, dungeon);
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
