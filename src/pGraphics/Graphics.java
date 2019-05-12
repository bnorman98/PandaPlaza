package pGraphics;

import main.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
//TODO: Remove this class safely!! or merge with GraphicalApplication
public class Graphics {

    private static Graphics instance;

    public static Graphics getInstance(){
        return instance;
    }
    public void drawAll(){
        drawTiles();
        drawThings();
        drawOrangutans();
        drawPandas();
    }
    protected void drawOrangutans() {
        ArrayList<Orangutan> orangutans = Game.getInstance().getOrangutans();
        for (Orangutan o : orangutans){
            String path = o.getTexturePath();
            Tile tile = o.getTile();

            //here comes the magic

        }
    }
    protected void drawPandas() {
        ArrayList<Panda> pandas = Game.getInstance().getPandas();
        for (Panda p : pandas){
            String path = p.getTexturePath();
            Tile tile = p.getTile();

            //here comes the magic

        }
    }
    protected void drawTiles() {
        ArrayList<Tile> tiles = Game.getInstance().getTiles();
        for (Tile t : tiles){
            String path = t.getTexturePath();

            //here comes the magic

        }
    }
    protected void drawThings() {
        ArrayList<Thing> things = Game.getInstance().getThings();
        for (Thing t : things){
            String path = t.getTexturePath();
            Tile tile = t.getTile();

            //here comes the magic

        }
    }

}
