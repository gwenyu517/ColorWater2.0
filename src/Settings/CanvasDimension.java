package Settings;

import java.awt.*;
import java.util.Observable;

public class CanvasDimension extends Observable{

    int width;
    int height;

    public CanvasDimension(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setWidth(int width){
        this.width = width;
        setChanged();
        notifyObservers();
    }

    public void setHeight(int height){
        this.height = height;
        setChanged();
        notifyObservers();
    }
}
