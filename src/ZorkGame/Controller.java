package ZorkGame;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.*;
import tests.DungeonMaps;
import static model.MapSquareEdge.Direction.*;

public class Controller {


    @FXML TextArea gameLogText;
    @FXML GridPane gpDungeonMap;
    @FXML CheckBox cbxViewMap;

    //Model
    private DungeonMaster dm;
    private Dungeon theDungeon;
    private Player thePlayer;

    @FXML
    public void initialize(){
        dm = new DungeonMaster();
        theDungeon = dm.createNewDungeon(3,3);
        thePlayer = dm.createNewPlayer("default player");
        DungeonMaps.load3x3Dungeon(theDungeon);
        dm.placePlayerInEntranceSquare();
        logGameText(thePlayer.getCurrentSquare().getRoomName());
        logGameText(thePlayer.getCurrentSquare().getDescription());
        buildMap();
        updateCurrentSquareHighlight();
        gpDungeonMap.setVisible(false);
    }

    private enum BorderDirection {
        VERTICAL, HORIZONTAL, UNKNOWN
    }

    private void buildMap(){
        final int gridSize = 100;
        final int exteriorWallSize = 10;
        final int interiorWallSize = exteriorWallSize / 2;
        final Paint wallColor = Paint.valueOf("black");
        final Paint doorColor = Paint.valueOf("red");
        final Paint windowColor = Paint.valueOf("blue");


        //add room names as labels
        theDungeon.getDungeonMap().forEach((k,v) -> {
                    gpDungeonMap.add(new Label(v.getRoomName()), convertXCor(v.getxCor()), convertYCor(v.getyCor()));
                }
        );

        // add column size constraints
        for(int y = 0; y < theDungeon.getDungeonHeight(); y++){
            RowConstraints rc = new RowConstraints(gridSize);
            gpDungeonMap.getRowConstraints().add(rc);
        }

        for(int x = 0; x < theDungeon.getDungeonWidth(); x++){
            ColumnConstraints cc = new ColumnConstraints(gridSize);
            gpDungeonMap.getColumnConstraints().add(cc);
        }

        // add walls as rectangles
        theDungeon.getDungeonMap().forEach((k,v) -> {
            final int gpXCor = convertXCor(v.getxCor());
            final int gpYCor = convertYCor(v.getyCor());
            BorderPane bPane = new BorderPane();

            for (MapSquareEdge.Direction dir : MapSquareEdge.Direction.values()) {
                MapSquareEdge edge = v.getEdge(dir);
                if (!(edge instanceof UnboundedEdge)) {
                    int skinnySize = 1;
                    Paint borderColor = Paint.valueOf("black");

                    BorderDirection bDir = BorderDirection.UNKNOWN;
                    switch (dir) {
                        case NORTH:
                        case SOUTH:
                            bDir = BorderDirection.HORIZONTAL;
                            break;
                        case EAST:
                        case WEST:
                            bDir = BorderDirection.VERTICAL;
                            break;
                    }

                    Rectangle r = new Rectangle();
                    if (edge instanceof ExteriorWall) {
                        skinnySize = exteriorWallSize;
                        borderColor = wallColor;
                    } else if (edge instanceof InteriorWall) {
                        skinnySize = interiorWallSize;
                        borderColor = wallColor;
                    } else if (edge instanceof Entrance || edge instanceof Exit) {
                        skinnySize = exteriorWallSize;
                        borderColor = doorColor;
                    } else if (edge instanceof Window) {
                        skinnySize = exteriorWallSize;
                        borderColor = windowColor;
                    }

                    switch (bDir) {
                        case HORIZONTAL:
                            r.setHeight(skinnySize);
                            r.setWidth(gridSize);
                            break;
                        case VERTICAL:
                            r.setHeight(gridSize);
                            r.setWidth(skinnySize);
                            break;
                    }
                    r.setFill(borderColor);

                    switch (dir) {
                        case NORTH:
                            bPane.setTop(r);
                            break;
                        case WEST:
                            bPane.setLeft(r);
                            break;
                        case SOUTH:
                            bPane.setBottom(r);
                            break;
                        case EAST:
                            bPane.setRight(r);
                            break;
                    }

                }
            }
            gpDungeonMap.add(bPane, gpXCor, gpYCor);
            }
        );

        // nodes: add CSS, alignment
        for(Node node: getGPLabels()) {
            node.getStyleClass().add("map-square-general");
            GridPane.setHalignment(node, HPos.CENTER);
        }

        gpDungeonMap.getStyleClass().add("map-general");

    }

    private ObservableList<Node> getGPLabels(){
        return gpDungeonMap.getChildren().filtered(node -> node instanceof Label);
    }

    private int convertXCor(int mapXCor){
        return mapXCor - 1; // 0-based
    }

    private int convertYCor(int mapYCor){
        return theDungeon.getDungeonHeight() - mapYCor; // reversed
    }

    private void updateCurrentSquareHighlight(){
        MapSquare currentSquare = thePlayer.getCurrentSquare();
        int gpXCor = convertXCor(currentSquare.getxCor());
        int gpYCor = convertYCor(currentSquare.getyCor());

        for(Node node: getGPLabels()) {
                node.getStyleClass().remove("map-square-current");

                int colIndex = GridPane.getColumnIndex(node);
                int rowIndex = GridPane.getRowIndex(node);
                if (colIndex == gpXCor && rowIndex == gpYCor) {
                    node.getStyleClass().add("map-square-current");
                }
        }
    }

    public void moveButtonClickHandler(ActionEvent evt){
        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();
        if(!theDungeon.isSolved()) {
            String newText = "";
            if ("North".equals(buttonLabel)) {
                newText = thePlayer.moveToNewSquare(NORTH);
            } else if ("West".equals(buttonLabel)) {
                newText = thePlayer.moveToNewSquare(WEST);
            } else if ("South".equals(buttonLabel)) {
                newText = thePlayer.moveToNewSquare(SOUTH);
            } else if ("East".equals(buttonLabel)) {
                newText = thePlayer.moveToNewSquare(EAST);
            }
            logGameText(newText);
            updateCurrentSquareHighlight();
        }
        if(!theDungeon.isSolved()){
            logGameText(thePlayer.getCurrentSquare().getRoomName());
        }
    }

    public void lookButtonClickHandler(ActionEvent evt){
        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();
        if(!theDungeon.isSolved()) {
            String newText = "";
            if ("North".equals(buttonLabel)) {
                newText = thePlayer.lookAtNextSquare(NORTH);
            } else if ("West".equals(buttonLabel)) {
                newText = thePlayer.lookAtNextSquare(WEST);
            } else if ("South".equals(buttonLabel)) {
                newText = thePlayer.lookAtNextSquare(SOUTH);
            } else if ("East".equals(buttonLabel)) {
                newText = thePlayer.lookAtNextSquare(EAST);
            }
            logGameText(newText);
        }
    }

    public void viewMapCheckHandler(ActionEvent evt){
        gpDungeonMap.setVisible(cbxViewMap.isSelected());
    }

    public void menuClickHandler(ActionEvent evt){
        MenuItem clickedMenu = (MenuItem) evt.getTarget();
        String menuLabel = clickedMenu.getText();

        if("Restart".equals(menuLabel)){
            restartGame();
        }
    }

    public void logGameText(String newText){
        gameLogText.appendText(newText);
        gameLogText.appendText("\n");
        gameLogText.setScrollTop(Double.MAX_VALUE);
    }

    public void restartGame(){
        dm.placePlayerInEntranceSquare();
        updateCurrentSquareHighlight();
        gameLogText.clear();
        logGameText("You start over.");
        logGameText(thePlayer.getCurrentSquare().getRoomName());
        logGameText(thePlayer.getCurrentSquare().getDescription());

    }
}
