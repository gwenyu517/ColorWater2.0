import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Drop{
    private BufferedImage applicationLayer;
    private Graphics2D g2d;

    private BufferedImage wetAreaMask;

    private int dropSize;

    private Point location;
    private Particle[] particles;
    private int numOfPart;
    private int pRadius = 5;
    private int pDiam = 2 * pRadius;

    private int intensity = 2;

    private double maxRotation = 0.8;


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

    public void setWetAreaMask(BufferedImage wetAreaMask){
        this.wetAreaMask = wetAreaMask;
    }

    private void determineNumOfPart(){
        int num = 6;
        for (int i = 2; i <= dropSize; ++i){
            num = 2 * num;
        }
        numOfPart = num * intensity;
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
                drawParticlesAt(x, y);
            }
            else if (i == dropSize){
                for (int j = 0; j < num; ++j) {
                    x = point.x + p * Math.sin(theta);
                    y = point.y + p * Math.cos(theta);

                    drawAndSaveParticlesAt(x, y, k, theta);
                    theta += dTheta;

                    k += intensity;
                }
            }
            else {
                for (int j = 0; j < num; ++j) {
                    x = point.x + p * Math.sin(theta);
                    y = point.y + p * Math.cos(theta);

                    drawParticlesAt(x, y);
                    theta += dTheta;
                }
                p += pRadius;
                num = 2 * num;
                dTheta = dTheta / 2;
            }
        }
    }

    private void drawAndSaveParticlesAt(double x, double y, int k, double theta){
        for (int i = 0; i < intensity; ++i){
            particles[k] = new Particle(x, y, theta);
            g2d.fill(new Ellipse2D.Double(particles[k].x, particles[k].y, pDiam, pDiam));
            ++k;
        }
    }

    private void drawParticlesAt(double x, double y){
        for (int i = 0; i < intensity; ++i){
            g2d.fill(new Ellipse2D.Double(x, y, pDiam, pDiam));
        }
    }

    public void spread(){
        double x, y;

        for (int i = 0; i < numOfPart; ++i){
            particles[i].determineBias();
            particles[i].determineDirection();

            particles[i].theta += maxRotation * randomValue() * particles[i].direction;


            x = pDiam*Math.cos(particles[i].theta) + particles[i].x;
            y = pDiam* -1 * Math.sin(particles[i].theta) + particles[i].y;


            if (withinWetMask(x, y)){
                g2d.fill(new Ellipse2D.Double(x, y, pDiam, pDiam));
                particles[i].x = x;
                particles[i].y = y;
            }
            else{
                g2d.fill(new Ellipse2D.Double(particles[i].x, particles[i].y, pDiam, pDiam));
            }
        }
        pDiam = (int)(pDiam * 0.8);
    }

    private boolean withinWetMask(double x, double y){
        if (wetAreaMask == null)
            return true;
        else if (extractR(wetAreaMask.getRGB((int)x, (int)y)) < 253)
            return true;
        else
            return false;
    }

    private static int extractR(int ARGB){
        return (ARGB >>> 16) & 0x000000FF;
    }

    private double randomValue(){
        Random r = new Random();
        return r.nextDouble();
    }

}