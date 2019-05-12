package pGraphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.*;


import java.awt.*;
import java.util.ArrayList;

public class GraphicalApplication extends Application {

    Group root;
    Stage stage;

    public void drawGame(){
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
        stage.show();

    }

    //Entry point
    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO: Draw menu first instead of game
        Game.getInstance().setView(this);
        root = new Group();
        stage = primaryStage;
        Scene scene = new Scene(root,600,600);
        //TODO: This is just for testing, deserialize elsewhere
        Game.getInstance().deserialize("savefile.txt");
        //
        drawGame();
        stage.setTitle("PandaPlaza");
        stage.setScene(scene);
        stage.show();

        Game.getInstance().runGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
