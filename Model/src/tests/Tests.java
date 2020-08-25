package tests;

import model.*;
import static model.MapSquareEdge.Direction.*;
import static tests.Helpers.*;
import static tests.DungeonMaps.*;

public class Tests {
   static String testPlayerName = "test player";


     static void testLoadUnboundedEdges(){
        DungeonMaster dm = initDungeonMaster(3,3,testPlayerName);
        Dungeon d = dm.getDungeon();
        MapSquare ms22 = d.getMapSquare("2-2");
        System.out.println(ms22.getEdge(MapSquareEdge.Direction.NORTH).getInsideSquareId());
        System.out.println(ms22.getEdge(MapSquareEdge.Direction.NORTH).getOutsideSquareId());
        MapSquare ms23 = d.getMapSquare("2-3");
        System.out.println(ms23.getEdge(MapSquareEdge.Direction.NORTH).getInsideSquareId());
        System.out.println(ms23.getEdge(MapSquareEdge.Direction.NORTH).getOutsideSquareId());
        MapSquare ms13 = d.getMapSquare("1-3");
        System.out.println(ms13.getEdge(MapSquareEdge.Direction.NORTH).getInsideSquareId());
        System.out.println(ms13.getEdge(MapSquareEdge.Direction.NORTH).getOutsideSquareId());

        load3x3Dungeon(d);
        System.out.println(ms13.getEdge(MapSquareEdge.Direction.NORTH) instanceof Exit);
    }

     static void test3x3Dungeon(){
        DungeonMaster dm = initDungeonMaster(3,3,testPlayerName);
        Dungeon d = dm.getDungeon();
        System.out.println(d.getMapSquare(2,3).getSquareId());
        load3x3Dungeon(d);
        System.out.println(d.getMapSquare("1-2").getDescription());
        System.out.println(d.getMapSquare("2-2").getSquareId());
        System.out.println(d.getMapSquare("2-2").describeEdges());
        System.out.println(d.getMapSquare("2-2").describeVisibility());
        System.out.println(String.format("Entrance: %s",d.getDungeonEntrance().getInsideSquareId()));
        System.out.println(String.format("Exit: %s",d.getDungeonExit().getInsideSquareId()));
        MapSquare ms31 = d.getMapSquare("3-1");
        System.out.println(String.format("3-1's east neighbor: %s",d.getNeighborSquareId(ms31, EAST)));
        System.out.println(String.format("3-1's west neighbor: %s",d.getNeighborSquareId(ms31, WEST)));
    }

     static void test1x1Dungeon(){
        DungeonMaster dm = initDungeonMaster(1,1,testPlayerName);
        Dungeon d = dm.getDungeon();
        load1x1Dungeon(d);
        MapSquare ms11 = d.getMapSquare("1-1");
        System.out.println(ms11.getDescription());
        System.out.println(ms11.describeEdges());
        System.out.println(ms11.describeVisibility());
        System.out.println(String.format("Entrance: %s",d.getDungeonEntrance().getInsideSquareId()));
        System.out.println(String.format("Exit: %s",d.getDungeonExit().getInsideSquareId()));
        System.out.println(String.format("1-1's east neighbor: %s",d.getNeighborSquareId(ms11, EAST)));
        System.out.println(String.format("1-1's west neighbor: %s",d.getNeighborSquareId(ms11, WEST)));
    }

     static void test1x2Dungeon(){
        DungeonMaster dm = initDungeonMaster(2,1,testPlayerName);
        Dungeon d = dm.getDungeon();
        load1x2Dungeon(d);
        MapSquare ms11 = d.getMapSquare("1-1");
        MapSquare ms12 = d.getMapSquare("1-2");
        System.out.println(ms11.getDescription());
        System.out.println(ms11.describeEdges());
        System.out.println(ms11.describeVisibility());
        System.out.println(ms12.getDescription());
        System.out.println(ms12.describeEdges());
        System.out.println(ms12.describeVisibility());

        System.out.println(String.format("Entrance: %s",d.getDungeonEntrance().getInsideSquareId()));
        System.out.println(String.format("Exit: %s",d.getDungeonExit().getInsideSquareId()));
        System.out.println(String.format("1-1's north neighbor: %s",d.getNeighborSquareId(ms11, NORTH)));
        System.out.println(String.format("1-2's south neighbor: %s",d.getNeighborSquareId(ms12, SOUTH)));


        Player p = dm.getPlayer();
        System.out.println(p.getPlayerName());
        addSword(p);
        listInventoryContents(p);
        dm.placePlayerInEntranceSquare();
        System.out.println(String.format("Current location: %s", p.getCurrentSquare().getRoomName()));

        //test movement
        System.out.println(String.format("Solved? %b", d.isSolved()));
        String moveAttempt1 = p.moveToNewSquare(NORTH);
        System.out.println(moveAttempt1);
        System.out.println(String.format("Current location: %s", p.getCurrentSquare().getRoomName()));
        String moveAttempt2 = p.moveToNewSquare(NORTH);
        System.out.println(moveAttempt2);
        System.out.println(String.format("Solved? %b", d.isSolved()));

    }

     static void test3x3HappyPath(){
        DungeonMaster dm = initDungeonMaster(3,3,testPlayerName);
        Dungeon d = dm.getDungeon();
        load3x3Dungeon(d);
        Player p = dm.getPlayer();
        dm.placePlayerInEntranceSquare();
        System.out.println(p.moveToNewSquare(NORTH));
        System.out.println(p.moveToNewSquare(NORTH));
        System.out.println(p.moveToNewSquare(WEST));
        System.out.println(p.moveToNewSquare(WEST));
        System.out.println(p.moveToNewSquare(NORTH));
    }

    static void testLookingAround(){
       DungeonMaster dm = initDungeonMaster(2,1,testPlayerName);
       Dungeon d = dm.getDungeon();
       load1x2Dungeon(d);
       Player p = dm.getPlayer();
       dm.placePlayerInEntranceSquare();
       System.out.println("Looking at entrance:");
       System.out.println(p.lookAtNextSquare(SOUTH));
       System.out.println("Looking out window:");
       System.out.println(p.lookAtNextSquare(EAST));
       System.out.println("Looking at wall:");
       System.out.println(p.lookAtNextSquare(WEST));
       System.out.println("Looking at next room:");
       System.out.println(p.lookAtNextSquare(NORTH));
       System.out.println("Moving to next square:");
       System.out.println(p.moveToNewSquare(NORTH));
       System.out.println("Looking at exit:");
       System.out.println(p.lookAtNextSquare(NORTH));
       System.out.println("Exiting:");
       System.out.println(p.moveToNewSquare(NORTH));
    }

}
