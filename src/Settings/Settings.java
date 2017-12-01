package Settings;

import java.awt.*;


public class Settings {

    protected Color paintColor;
    protected int waterBrushSize;
    protected int paintBrushSize;

    protected double verticalAngle;
    protected double horizontalAngle;

    protected int canvasWidth;
    protected int canvasHeight;


    public Settings(){
        paintColor = new Color(0,0,0,30);
        waterBrushSize = 5;
        paintBrushSize = 5;

        verticalAngle = 0;
        horizontalAngle = 0;

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

    public int getCanvasWidth(){
        return canvasWidth;
    }

    public int getCanvasHeight(){
        return canvasHeight;
    }

}
