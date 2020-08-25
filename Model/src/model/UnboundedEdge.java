package model;

public class UnboundedEdge extends MapSquareEdge {
    public UnboundedEdge(String insideSquare, String outsideSquare, Dungeon dungeon){
        super(true,true, insideSquare, outsideSquare, dungeon);
    }

    @Override
    public String attemptWalkThrough() {
        return "You walk to the next room.";
    }
    @Override
    public String attemptSeeThrough() {
        String outsideSquareId = getOutsideSquareId();
        MapSquare outsideSquare = getDungeon().getMapSquare(outsideSquareId);
        return String.format("You see %s.", outsideSquare.getRoomName());
    }
}
