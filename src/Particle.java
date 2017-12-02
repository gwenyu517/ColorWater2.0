import java.awt.*;
import java.util.Random;

public class Particle {
    protected double x;
    protected double y;

    // private Color color;

    private double transmissionProb;
    protected double p;
    protected double theta;
    protected double direction;
    protected double bias;
    protected double diameter;

    public Particle(double x, double y, double theta){
    //    color = new Color(0, 0, 0, 13);
        this.x = x;
        this.y = y;

        p = 1.0;
        this.theta = theta;

        if (randomValue() > 0.5)
            direction = 1.0;
        else
            direction = -1.0;

        transmissionProb = randomValue();
        determineBias();
    }

    protected void setDiameter(double d){
        diameter = d;
    }

    protected void determineBias(){
        if (direction == -1.0)
            bias = transmissionProb;
        else
            bias = 1.0 - transmissionProb;
    }
    protected void determineDirection(){
        if (randomValue() > bias)
            direction = 1.0;
        else
            direction = -1.0;
    }

    public void setP(double p){
        this.p = p;
    }

    public double randomValue(){
        Random r = new Random();
        return r.nextDouble();
    }
}
