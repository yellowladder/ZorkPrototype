package tests;

import model.*;
import static model.MapSquare.OUTSIDE;

public class DungeonMaps {

    public static void load1x1Dungeon(Dungeon myDungeon){
        //single square
        MapSquare ms11 = myDungeon.getMapSquare("1-1");
        ms11.setDescription("This is 1-1");
        ms11.setRoomName("The Room");

        //Entrance
        Entrance ent = new Entrance("1-1", myDungeon);
        ms11.setSouthEdge(ent);
        myDungeon.setDungeonEntrance(ent);

        //Exit
        Exit ex = new Exit("1-1", myDungeon);
        ms11.setNorthEdge(ex);
        myDungeon.setDungeonExit(ex);

        //Window 1
        Window w1 = new Window("1-1",OUTSIDE, myDungeon);
        ms11.setEastEdge(w1);

    }

    public static void load1x2Dungeon(Dungeon myDungeon){
        //Squares
        MapSquare ms11 = myDungeon.getMapSquare("1-1");
        ms11.setDescription("This is 1-1");
        ms11.setRoomName("Entry Room");

        MapSquare ms12 = myDungeon.getMapSquare("1-2");
        ms12.setDescription("This is 1-2");
        ms12.setRoomName("Exit Room");

        //Entrance
        Entrance ent = new Entrance("1-1", myDungeon);
        ms11.setSouthEdge(ent);
        myDungeon.setDungeonEntrance(ent);

        //Exit
        Exit ex = new Exit("1-2", myDungeon);
        ms12.setNorthEdge(ex);
        myDungeon.setDungeonExit(ex);

        //Window 1
        Window w1 = new Window("1-1",OUTSIDE, myDungeon);
        ms11.setEastEdge(w1);
    }

    public static void load3x3Dungeon(Dungeon myDungeon){

        //1-1
        MapSquare ms11 = myDungeon.getMapSquare("1-1");
        ms11.setDescription("This is 1-1");
        ms11.setRoomName("Kitchen");

        //1-2
        MapSquare ms12 = myDungeon.getMapSquare("1-2");
        ms12.setDescription("This is 1-2");
        ms12.setRoomName("West Hall");

        //1-3
        MapSquare ms13 = myDungeon.getMapSquare("1-3");
        ms13.setDescription("This is 1-3");
        ms13.setRoomName("Dining Room");

        //2-1
        MapSquare ms21 = myDungeon.getMapSquare("2-1");
        ms21.setDescription("This is 2-1");
        ms21.setRoomName("South Hall");

        //2-2
        MapSquare ms22 = myDungeon.getMapSquare("2-2");
        ms22.setDescription("This is 2-2");
        ms22.setRoomName("Closet");

        //2-3
        MapSquare ms23 = myDungeon.getMapSquare("2-3");
        ms23.setDescription("This is 2-3");
        ms23.setRoomName("North Hall");

        //3-1
        MapSquare ms31 = myDungeon.getMapSquare("3-1");
        ms31.setDescription("This is 3-1");
        ms31.setRoomName("Lobby");

        //3-2
        MapSquare ms32 = myDungeon.getMapSquare("3-2");
        ms32.setDescription("This is 3-2");
        ms32.setRoomName("East Hall");

        //3-3
        MapSquare ms33 = myDungeon.getMapSquare("3-3");
        ms33.setDescription("This is 3-3");
        ms33.setRoomName("Living Room");

        //Entrance
        Entrance ent = new Entrance("3-1", myDungeon);
        ms31.setSouthEdge(ent);
        myDungeon.setDungeonEntrance(ent);

        //Exit
        Exit ex = new Exit("1-3", myDungeon);
        ms13.setNorthEdge(ex);
        myDungeon.setDungeonExit(ex);

        //Window 1
        Window w1 = new Window("2-2","3-2", myDungeon);
        ms22.setEastEdge(w1);
        ms32.setWestEdge(w1);

        //Window 2
        Window w2 = new Window("3-2", OUTSIDE, myDungeon);
        ms32.setEastEdge(w2);

        //Window 3
        Window w3 = new Window("1-1", OUTSIDE, myDungeon);
        ms11.setWestEdge(w3);

        //Interior Wall 1
        InteriorWall iw1 = new InteriorWall("2-2","2-3", myDungeon);
        ms22.setNorthEdge(iw1);
        ms23.setSouthEdge(iw1);

        //Interior Wall 2
        InteriorWall iw2 = new InteriorWall("2-1","2-2", myDungeon);
        ms21.setNorthEdge(iw1);
        ms22.setSouthEdge(iw1);

    }
}
