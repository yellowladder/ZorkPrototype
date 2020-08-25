package model;

public class DungeonMaster {
    private Dungeon theDungeon;
    private Player thePlayer;

    public Dungeon getDungeon(){
        return theDungeon;
    }

    public Player getPlayer(){
        return thePlayer;
    }

    public Dungeon createNewDungeon(int height, int width){
        theDungeon = new Dungeon(height, width);
        theDungeon.initDungeon();
        return theDungeon;
    }

    public Player createNewPlayer(String playerName){
        thePlayer = new Player(theDungeon);
        thePlayer.setPlayerName(playerName);
        return thePlayer;
    }

    public void placePlayerInEntranceSquare(){
        thePlayer.initPlayer();
    }
}
