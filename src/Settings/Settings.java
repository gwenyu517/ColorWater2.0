package Settings;

import java.awt.*;


public class Settings {

    protected Color paintColor;
    protected int brushSize;

    protected double verticalAngle;
    protected double horizontalAngle;

    protected int canvasWidth;
    protected int canvasHeight;


    public Settings(){
        paintColor = new Color(0,0,0,30);
        brushSize = 5;

        verticalAngle = 0;
        horizontalAngle = 0;

        canvasWidth = 500;
        canvasHeight = 500;

    }

}
