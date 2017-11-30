import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Drop{
    private BufferedImage applicationLayer;
    private Graphics2D g2d;

    private int dropSize;

    private Point location;
    private Particle[] particles;
    private int numOfPart;
    private int pRadius = 5;
    private int pDiam = 2 * pRadius;

    private int intensity = 7;

    private double maxRotation = 0.5;


    public Drop(int dropSize, BufferedImage applicationLayer){
        this.applicationLayer = applicationLayer;
        g2d = applicationLayer.createGraphics();

        this.dropSize = dropSize;
        determineNumOfPart();

        location = new Point();
        particles = new Particle[numOfPart];

    }

    public BufferedImage getImage(){
        return applicationLayer;
    }

    public void setDropColor(Color color){
        g2d.setColor(color);
    }

    private void determineNumOfPart(){
        int num = 6;
        numOfPart = intensity;
        for (int i = 1; i <= dropSize; ++i){
            numOfPart += num * intensity;
            num = 2 * num;
        }
    }

    public void dripAt(Point point){
        location.setLocation(point);
        double theta;
        double p = pRadius;

        double dTheta = Math.PI / 3;

        double x = point.x;
        double y = point.y;
        int k = 0;

        int num = 6;

        for (int i = 0; i <= dropSize; i++){
            theta = 0;
            if (i == 0){
                drawParticlesAt(x, y, k, theta);
                k += intensity;
            }
            else {
                for (int j = 0; j < num; ++j) {
                    x = point.x + p * Math.sin(theta);
                    y = point.y + p * Math.cos(theta);

                    drawParticlesAt(x, y, k, theta);
                    theta += dTheta;

                    k += intensity;
                }
                p += pRadius;
                num = 2 * num;
                dTheta = dTheta / 2;
            }
        }
    }

    private void drawParticlesAt(double x, double y, int k, double theta){
        for (int i = 0; i < intensity; ++i){
            particles[k] = new Particle(x, y, theta);
            g2d.fill(new Ellipse2D.Double(particles[k].x, particles[k].y, pDiam, pDiam));
            ++k;
        }
    }

    public void spread(){
        double x, y;

        for (int i = 9; i < numOfPart; ++i){
            particles[i].determineBias();
            particles[i].determineDirection();

            particles[i].theta += maxRotation * randomValue() * particles[i].direction;

            x = 5*Math.cos(particles[i].theta) + particles[i].x;
            y = 5* -1 * Math.sin(particles[i].theta) + particles[i].y;

            g2d.fill(new Ellipse2D.Double(x, y, 10, 10));
            particles[i].x = x;
            particles[i].y = y;
        }
    }

    private double randomValue(){
        Random r = new Random();
        return r.nextDouble();
    }

}