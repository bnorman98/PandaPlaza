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
import java.util.ArrayList;

/**
 * Represents the GraphicalApplication
 *
 */
public class GraphicalApplication extends Application {
	
	/**
	 * The main group of the graphical application
	 */
    private Group root;
	/**
	 * Represents the primary stage object
	 */
	private Stage stage;
	private Scene gameScene;
    /**
     * It draws the game onto the canvas
     * Draws the map, then draws
     * The objects (Pandas, Things, Tiles)
     */
    public void drawGame(){
        // Objects to be drawn
        ArrayList<GraphicalElement> elements = new ArrayList<>();
        // Stores the tiles position
        ArrayList<Integer> positionsX = new ArrayList<>();
        ArrayList<Integer> positionsY = new ArrayList<>();
        // The canvas we are drawing on
        Canvas canvas = new Canvas(600,600);
        
        // Calculating tiles positions in a circle
        for (int i = 0; i < Game.getInstance().getTiles().size(); i++) {
            Tile tile = Game.getInstance().getTiles().get(i);
            Image image = new Image(tile.getTexturePath());
            //Tiles are in a circle
            final double R = 250;
            int posX = 300 + (int)(Math.cos(2 * Math.PI * i / Game.getInstance().getTiles().size()) * R);
            int posY = 300 + (int)(Math.sin(2 * Math.PI * i / Game.getInstance().getTiles().size()) * R);
            positionsX.add(posX);
            positionsY.add(posY);
            tile.setPosition(new Point(posX,posY));
            elements.add(new GraphicalElement(canvas,image,posX,posY));
        }
        
        // Draw lines between tiles
        for (int i = 0; i < Game.getInstance().getTiles().size(); i++) {
            Tile tile = Game.getInstance().getTiles().get(i);
            for (Tile neighbour : tile.getNeighbours()) {
                int index = Game.getInstance().getTiles().indexOf(neighbour);
                canvas.getGraphicsContext2D().strokeLine(positionsX.get(i) + 16,positionsY.get(i) + 16,
                        positionsX.get(index) + 16, positionsY.get(index) + 16);
            }
        }
        
        // Draw orangutans and lines between followers
        for (Orangutan orangutan : Game.getInstance().getOrangutans()) {
            Image image = new Image("res/orangutan.png");
            Point pos = orangutan.getTile().getPosition();
            elements.add(new GraphicalElement(canvas,image,pos.x,pos.y));
            if(orangutan.getFollower() != null) {
                Point followerPos = orangutan.getFollower().getTile().getPosition();
                // Draw line
                canvas.getGraphicsContext2D().setLineWidth(5);
                canvas.getGraphicsContext2D().strokeLine(
                        pos.x + 16, pos.y +16,
                        followerPos.x + 16, followerPos.y + 16);
            }
            
        }
    
        // Draw pandas and lines between followers
        for (Panda panda : Game.getInstance().getPandas()) {
            Image image = new Image("res/panda.png");
            Point pos = panda.getTile().getPosition();
            elements.add(new GraphicalElement(canvas,image,pos.x,pos.y));
            if(panda.getFollower() != null) {
                Point followerPos = panda.getFollower().getTile().getPosition();
                // Draw line
                canvas.getGraphicsContext2D().setLineWidth(5);
                canvas.getGraphicsContext2D().strokeLine(
                        pos.x + 16, pos.y + 16,
                        followerPos.x + 16, followerPos.y + 16);
            }
        }
        
        // Add all elements to a list
        for (Thing thing : Game.getInstance().getThings()) {
            Image image = new Image(thing.getTexturePath());
            Point pos = thing.getTile().getPosition();
            elements.add(new GraphicalElement(canvas,image,pos.x,pos.y));
        }
        // Drawing happens here
        for (GraphicalElement element : elements) {
            element.drawImage();
        }
        // Add canvas to the root object
        root.getChildren().clear();
        root.getChildren().add(canvas);
        // Add scene to the primary stage
        stage.setScene(gameScene);
        // Stow stage
        stage.show();
    }

    /**
     * Draws the menu
     * Handles it's buttons' events
     */
	public void drawMenu(){
        // Creating buttons
        // Play button
        Button bPlay = new javafx.scene.control.Button("Play");
        bPlay.setPrefWidth(100);
        bPlay.setStyle("-fx-font-size:16");
        // Settings button
        Button bSettings = new javafx.scene.control.Button("Settings");
        bSettings.setPrefWidth(100);
        bSettings.setStyle("-fx-font-size:16");
        // Load button
        Button bLoad = new javafx.scene.control.Button("Load");
        bLoad.setPrefWidth(100);
        bLoad.setStyle("-fx-font-size:16");
        // Donate a 5 button
        Button bDonate = new javafx.scene.control.Button("Donate a 5");
        bDonate.setPrefWidth(100);
        bDonate.setStyle("-fx-font-size:16");
        // Exit button
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
				new Thread(() -> Game.getInstance().runGame()).start();
            }
        });
        
        // Loading game on Load
		bLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Game.getInstance().deserialize("savefile.txt");
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
        
        // Add scene to primary stage
        stage.setScene(menuScene);
        // Show the stage
        stage.show();
    }

    /**
     * Starts the game
     * @param primaryStage is the "main" Stage.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Deserializing
        Game.getInstance().deserialize("savefile.txt");
        
        stage = primaryStage;
        stage.setTitle("PandaPlaza");
		
		Game.getInstance().setView(this);
		root = new Group();
		gameScene = new Scene(root,600,600);
		
        drawMenu();
    }

    /**
     * The main method of the graphics program
     */
    public static void main(String[] args) {
        launch(args);
    }
}
