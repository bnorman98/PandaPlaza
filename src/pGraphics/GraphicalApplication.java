package pGraphics;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents the GraphicalApplication
 *
 */
public class GraphicalApplication extends Application {

    private Group root;
    private Stage stage;

    /**
     * It draws the game onto the canvas
     * Draws the map, then draws
     * The objects (Pandas, Things, Tiles)
     */
    public void drawGame(){
        Game.getInstance().setView(this);
        root = new Group();
        Scene gameScene = new Scene(root,600,600);
    
        new Thread(() -> Game.getInstance().runGame()).start();
        
        ArrayList<GraphicalElement> elements = new ArrayList<>();
        Canvas canvas = new Canvas(600,600);
        for (int i = 0; i < Game.getInstance().getTiles().size(); i++) {
            Tile tile = Game.getInstance().getTiles().get(i);
            String s = tile.getTexturePath();
            Image image = new Image(tile.getTexturePath());
            //Tiles are in a circle
            final double R = 250;
            int posX = 300 + (int)(Math.cos(2 * Math.PI * i / Game.getInstance().getTiles().size()) * R);
            int posY = 300 + (int)(Math.sin(2 * Math.PI * i / Game.getInstance().getTiles().size()) * R);
            tile.setPosition(new Point(posX,posY));
            elements.add(new GraphicalElement(canvas,image,posX,posY));
        }

        for (int i = 0; i < Game.getInstance().getTiles().size() - 1; i++) {
            final double R = 250;
            int posX = 300 + 16 + (int) (Math.cos(2 * Math.PI * i / Game.getInstance().getTiles().size()) * R);
            int posY = 300 + 16 + (int) (Math.sin(2 * Math.PI * i / Game.getInstance().getTiles().size()) * R);
            int nextPosX = 300 + 16 + (int) (Math.cos(2 * Math.PI * (i+1) / Game.getInstance().getTiles().size()) * R);
            int nextPosY = 300 + 16 + (int) (Math.sin(2 * Math.PI * (i+1) / Game.getInstance().getTiles().size()) * R);
            canvas.getGraphicsContext2D().strokeLine(posX,posY,nextPosX,nextPosY);
        }

        for (Orangutan orangutan : Game.getInstance().getOrangutans()) {
            Image image = new Image("res/orangutan.png");
            Point pos = orangutan.getTile().getPosition();
            elements.add(new GraphicalElement(canvas,image,pos.x,pos.y));
        }

        for (Panda panda : Game.getInstance().getPandas()) {
            Image image = new Image("res/panda.png");
            Point pos = panda.getTile().getPosition();
            elements.add(new GraphicalElement(canvas,image,pos.x,pos.y));
        }

        for (Thing thing : Game.getInstance().getThings()) {
            Image image = new Image(thing.getTexturePath());
            Point pos = thing.getTile().getPosition();
            elements.add(new GraphicalElement(canvas,image,pos.x,pos.y));
        }
        for (GraphicalElement element : elements) {
            element.drawImage();
        }
        root.getChildren().clear();
        root.getChildren().add(canvas);
        
        stage.setScene(gameScene);
        stage.show();
    }

    /**
     * Draws the menu
     */
    private void drawMenu(){
        // Creating buttons
        Button bPlay = new javafx.scene.control.Button("Play");
        bPlay.setPrefWidth(100);
        bPlay.setStyle("-fx-font-size:16");
    
        Button bSettings = new javafx.scene.control.Button("Settings");
        bSettings.setPrefWidth(100);
        bSettings.setStyle("-fx-font-size:16");
    
        Button bLoad = new javafx.scene.control.Button("Load");
        bLoad.setPrefWidth(100);
        bLoad.setStyle("-fx-font-size:16");
    
        Button bDonate = new javafx.scene.control.Button("Donate a 5");
        bDonate.setPrefWidth(100);
        bDonate.setStyle("-fx-font-size:16");
    
        Button bExit = new Button("Exit");
        bExit.setPrefWidth(100);
        bExit.setStyle("-fx-font-size:16");
        // Close window on exit
        bExit.setOnAction(event -> stage.close());
    
        // Start game on play
        bPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Start game
                drawGame();
            }
        });
    
        // Creating VBox for Buttons
        VBox vbox = new VBox(bPlay, bSettings, bLoad, bDonate, bExit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
    
        // Creating ImageView
        Image image = new Image("res/menu.jpg");
        ImageView imageView = new ImageView(image);
    
        // Creating a StackPane
        StackPane stackPane = new StackPane();
    
        // Setting margin for the Buttons
        stackPane.setMargin(vbox, new Insets(100, 50, 20, 50));
    
        // Retrieving the observable list of the Stack Pane
        ObservableList list = stackPane.getChildren();
    
        // Adding all the nodes to the pane
        list.addAll(imageView, vbox);
    
        // Creating Scene containing the menu
        Scene menuScene = new Scene(stackPane);
    
        stage.setScene(menuScene);
        stage.show();
    }

    /**
     * Starts the game
     * @param primaryStage is the "main" Stage.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO: This is just for testing, deserialize elsewhere
        Game.getInstance().deserialize("savefile.txt");
        Game g = Game.getInstance();
        
        stage = primaryStage;
        stage.setTitle("PandaPlaza");
        drawMenu();
    }

    /**
     * The main method of the graphics program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
