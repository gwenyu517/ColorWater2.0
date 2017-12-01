package Settings;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;


public class Settings{

    private Color paintColor;
    private int waterBrushSize;
    private int paintBrushSize;

    private double verticalAngle;
    private double horizontalAngle;

    private CanvasDimension canvasDimension;
    private int canvasWidth;
    private int canvasHeight;


    public Settings(){
        paintColor = new Color(0,0,0,30);
        waterBrushSize = 5;
        paintBrushSize = 5;

        verticalAngle = 0;
        horizontalAngle = 0;

        canvasDimension = new CanvasDimension(1000, 1000);

        canvasWidth = 1000;
        canvasHeight = 1000;
    }

    public Color getPaintColor(){
        return paintColor;
    }

    public int getWaterBrushSize(){
        return waterBrushSize;
    }

    public int getPaintBrushSize(){
        return paintBrushSize;
    }

    public double getVerticalAngle(){
        return verticalAngle;
    }

    public double getHorizontalAngle(){
        return horizontalAngle;
    }

    public CanvasDimension getCanvasDimension(){
        return canvasDimension;
    }

    public int getCanvasWidth(){
        return canvasDimension.getWidth();
    }

    public int getCanvasHeight(){
        return canvasDimension.getHeight();
    }

    public void setPaintColor(Color color){
        paintColor = color;
    }

    public void setWaterBrushSize(int size){
        waterBrushSize = size;
    }

    public void setPaintBrushSize(int size){
        paintBrushSize = size;
    }

    public void setVerticalAngle(double angle){
        verticalAngle = angle;
    }

    public void setHorizontalAngle(double angle){
        horizontalAngle = angle;
    }

    public void setCanvasWidth(int width){
        canvasWidth = width;
    }

    public void setCanvasHeight(int height){
        canvasHeight = height;
    }

}
