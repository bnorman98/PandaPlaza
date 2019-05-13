package pGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Represents the GraphicalElements
 * That are used in the GUI
 */
public class GraphicalElement {
    /**
     * The canvas we are drawing on
     */
    private Canvas canvas;
    
    /**
     * The positions we are drawing to
     */
    private int pixelPositionX = 0, pixelPositionY = 0;
    
    /**
     * The image we are drawing on given position
     */
    private Image image;

    /**
     * The constructor of the class
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

}
