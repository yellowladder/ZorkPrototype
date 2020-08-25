package model;


import java.util.ArrayList;
import java.util.HashMap;
import static model.MapSquareEdge.Direction.WEST;
import static model.MapSquareEdge.Direction.NORTH;
import static model.MapSquareEdge.Direction.SOUTH;
import static model.MapSquareEdge.Direction.EAST;

public class MapSquare {

    public static final String OUTSIDE = "OUTSIDE";
    public static final String UNKNOWN = "";

    private HashMap<MapSquareEdge.Direction, MapSquareEdge> edges = new HashMap<>();
    private String description;
    private String roomName;
    private final int xCor;
    private final int yCor;
    private final String squareId;
    private Dungeon myDungeon;
    private ArrayList<InventoryItem> roomContents = new ArrayList<>();

    public MapSquare(int xCor, int yCor, Dungeon dungeon) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.squareId = getSquareIdFormat(xCor, yCor); //String.format("%d-%d", xCor, yCor);
        setDefaultEdges();
        myDungeon = dungeon;
    }


    public int getxCor() {
        return xCor;
    }

    public int getyCor() {
        return yCor;
    }

    public String getSquareId(){
        return squareId;
    }

    public static String getSquareIdFormat(int xCor, int yCor){
        return String.format("%d-%d", xCor, yCor);
    }

    public Dungeon getDungeon() { return myDungeon; }

    public HashMap<MapSquareEdge.Direction, MapSquareEdge> getEdges() {
        return edges;
    }

    public void setDefaultEdges(){
        setNorthEdge(new UnboundedEdge(squareId, UNKNOWN, getDungeon()));
        setWestEdge(new UnboundedEdge(squareId, UNKNOWN, getDungeon()));
        setSouthEdge(new UnboundedEdge(squareId, UNKNOWN, getDungeon()));
        setEastEdge(new UnboundedEdge(squareId, UNKNOWN, getDungeon()));
    }

    public MapSquareEdge getEdge(MapSquareEdge.Direction direction){
        return edges.get(direction);
    }

    public void setEdge(MapSquareEdge edge, MapSquareEdge.Direction direction){
        edges.put(direction, edge);
    }


    public void setNorthEdge(MapSquareEdge northEdge) { edges.put(NORTH, northEdge); }
    public void setWestEdge(MapSquareEdge westEdge) {
        edges.put(WEST, westEdge);
    }
    public void setSouthEdge(MapSquareEdge southEdge) {
        edges.put(SOUTH, southEdge);
    }
    public void setEastEdge(MapSquareEdge eastEdge) {
        edges.put(EAST, eastEdge);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String describeEdges(){
        String newLine = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("Can we walk?")
                .append(newLine)
                .append("North: ")
                .append(getEdge(NORTH).CanWalkThrough())
                .append(newLine)
                .append("West: ")
                .append(getEdge(WEST).CanWalkThrough())
                .append(newLine)
                .append("South: ")
                .append(getEdge(SOUTH).CanWalkThrough())
                .append(newLine)
                .append("East: ")
                .append(getEdge(EAST).CanWalkThrough());
        return sb.toString();
    }

    public String describeVisibility(){
        String newLine = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("Can we see?")
                .append(newLine)
                .append("North: ")
                .append(getEdge(NORTH).CanSeeThrough())
                .append(newLine)
                .append("West: ")
                .append(getEdge(WEST).CanSeeThrough())
                .append(newLine)
                .append("South: ")
                .append(getEdge(SOUTH).CanSeeThrough())
                .append(newLine)
                .append("East: ")
                .append(getEdge(EAST).CanSeeThrough());
        return sb.toString();
    }
}
