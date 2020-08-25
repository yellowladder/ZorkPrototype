package model;

import java.util.HashMap;
import java.util.Map;

public class Dungeon {


    private boolean isSolved;
    private String dungeonName;

    private final int dungeonHeight;
    private final int dungeonWidth;

    private HashMap<String,MapSquare> dungeonMap = new HashMap<>();
    private Entrance dungeonEntrance;
    private Exit dungeonExit;

    public Dungeon(int height, int width){
        dungeonHeight = height;
        dungeonWidth = width;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }



    public int getDungeonHeight() {
        return dungeonHeight;
    }

    public int getDungeonWidth() {
        return dungeonWidth;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }


    public Entrance getDungeonEntrance() {
        return dungeonEntrance;
    }
    public MapSquare getDungeonEntranceSquare() {
        return getMapSquare(dungeonEntrance.getInsideSquareId());
    }

    public void setDungeonEntrance(Entrance dungeonEntrance) {
        this.dungeonEntrance = dungeonEntrance;
    }

    public Exit getDungeonExit() {
        return dungeonExit;
    }

    public void setDungeonExit(Exit dungeonExit) {
        this.dungeonExit = dungeonExit;
    }

    public void initDungeon() {
        createDungeonMap();
        loadExteriorWalls();
        loadInteriorUnboundedEdges();
    }

    private void createDungeonMap(){
        for(int y = 1; y <= dungeonHeight; ++y){
            for(int x = 1; x <= dungeonWidth; ++x){
                MapSquare ms = new MapSquare(x, y, this);
                dungeonMap.put(ms.getSquareId(), ms);
            }
        }
    }


    public HashMap<String, MapSquare> getDungeonMap() {
        return dungeonMap;
    }

    public MapSquare getMapSquare(String squareId){
        return dungeonMap.get(squareId);
    }

    public MapSquare getMapSquare(int xCor, int yCor){
        String squareId = MapSquare.getSquareIdFormat(xCor, yCor);
        return getMapSquare(squareId);
    }

    public String getNeighborSquareId(MapSquare thisSquare, MapSquareEdge.Direction neighborDirection){
        int thisXCor = thisSquare.getxCor();
        int thisYCor = thisSquare.getyCor();
        int destXCor;
        int destYCor;

        switch(neighborDirection){
            case NORTH:
                destXCor = thisXCor;
                destYCor = thisYCor + 1;
                break;
            case SOUTH:
                destXCor = thisXCor;
                destYCor = thisYCor - 1;
                break;
            case WEST:
                destXCor = thisXCor - 1;
                destYCor = thisYCor;
                break;
            case EAST:
                destXCor = thisXCor + 1;
                destYCor = thisYCor;
                break;
            default:
                destXCor = -1;
                destYCor = -1;
                break;
        }


        if(destXCor < 1 || destYCor < 1 || destXCor > dungeonWidth || destYCor > dungeonHeight) {
            return MapSquare.OUTSIDE;
        }
        else{
            return MapSquare.getSquareIdFormat(destXCor, destYCor);
        }
    }

    public MapSquare getNeighborSquare(MapSquare thisSquare, MapSquareEdge.Direction neighborDirection){
        return getMapSquare(getNeighborSquareId(thisSquare, neighborDirection));
    }


    private void loadExteriorWalls(){
        for(int y = 1; y <= dungeonHeight; ++y){
            for(int x = 1; x <= dungeonWidth; ++x){
                MapSquare ms = getMapSquare(x,y);
                String sqid = ms.getSquareId();
                // west walls
                if (x == 1){
                    ExteriorWall w = new ExteriorWall(sqid,this);
                    ms.setWestEdge(w);
                }
                // south walls
                if (y == 1){
                    ExteriorWall w = new ExteriorWall(sqid,this);
                    ms.setSouthEdge(w);
                }
                // east walls
                if (x == dungeonWidth){
                    ExteriorWall w = new ExteriorWall(sqid,this);
                    ms.setEastEdge(w);
                }
                // north walls
                if (y == dungeonHeight){
                    ExteriorWall w = new ExteriorWall(sqid,this);
                    ms.setNorthEdge(w);
                }
            }
        }
    }

    private void loadInteriorUnboundedEdges(){
        for(int y = 1; y <= dungeonHeight; ++y){
            for(int x = 1; x <= dungeonWidth; ++x){
                MapSquare ms = getMapSquare(x,y);
                String sqid = ms.getSquareId();

                // replace default UnboundedEdge objects with loaded ones
                for (MapSquareEdge.Direction dir : MapSquareEdge.Direction.values()) {
                    MapSquareEdge edge = ms.getEdge(dir);
                    if (edge instanceof UnboundedEdge){
                        String newNeighbor = getNeighborSquareId(ms, dir);
                        UnboundedEdge newEdge = new UnboundedEdge(sqid, newNeighbor, this);
                        ms.setEdge(newEdge, dir);
                    }

                }
            }
        }
    }
}
