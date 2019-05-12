package pGraphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class GraphicalElement {

    private Canvas canvas;
    private int pixelPositionX = 0, pixelPositionY = 0;
    private Image image;

    public GraphicalElement(Canvas _canvas, Image _image, int x, int y){
        canvas = _canvas;
        image = _image;
        pixelPositionX = x;
        pixelPositionY = y;
    }

    public void drawImage(){
        canvas.getGraphicsContext2D().drawImage(image,pixelPositionX,pixelPositionY,32,32);
    }

    public void setPosition(int x, int y){
        pixelPositionX = x;
        pixelPositionY = y;
    }

}
