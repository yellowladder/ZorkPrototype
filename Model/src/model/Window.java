package model;

public class Window extends MapSquareEdge {
    public Window(String insideSquare, String outsideSquare, Dungeon dungeon){
        super(true,false,  insideSquare, outsideSquare, dungeon);
    }

    @Override
    public String attemptWalkThrough() {
        return "Your attempt at defenestration was unsuccessful.";
    }
    @Override
    public String attemptSeeThrough() {
        String outsideSquareId = getOutsideSquareId();
        if (outsideSquareId.equals(MapSquare.OUTSIDE)) {
            return "Through the window, the outdoors looks lovely, but uncertain.";
        }
        else {
            MapSquare outsideSquare = getDungeon().getMapSquare(outsideSquareId);
            return String.format("Through the window, you see %s.", outsideSquare.getRoomName());
        }
    }

}
