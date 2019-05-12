package pGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Represents the GraphicalElements
 * That are used in the GUI
 */
public class GraphicalElement {

    private Canvas canvas;
    private int pixelPositionX = 0, pixelPositionY = 0;
    private Image image;

    /**
     * The ctor of the class
     */
    public GraphicalElement(Canvas _canvas, Image _image, int x, int y){
        canvas = _canvas;
        image = _image;
        pixelPositionX = x;
        pixelPositionY = y;
    }

    /**
     * Draws the Image
     */
    public void drawImage(){
        canvas.getGraphicsContext2D().drawImage(image,pixelPositionX,pixelPositionY,32,32);
    }

    /**
     * Sets the position of the element
     * @param x the X position, that will be set
     * @param y the Y position, that will be set
     */
    public void setPosition(int x, int y){
        pixelPositionX = x;
        pixelPositionY = y;
    }

}
