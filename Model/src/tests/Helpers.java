package tests;

import model.*;
import java.util.ArrayList;

public class Helpers {

    static DungeonMaster initDungeonMaster(int height, int width, String playerName){
        DungeonMaster dm = new DungeonMaster();
        dm.createNewDungeon(height, width);
        dm.createNewPlayer(playerName);
        return dm;
    }


    static void addSword(Player p){
        InventoryItem sword = new InventoryItem("Broadsword");
        p.addToInventory(sword);
    }

    static void listInventoryContents(Player p){
        ArrayList<InventoryItem> inventory = p.getInventory();
        inventory.forEach(inventoryItem -> System.out.println(inventoryItem.getItemName()));
    }



}
