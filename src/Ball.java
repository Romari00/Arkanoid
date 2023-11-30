import java.io.*;
import java.awt.*;
public class Ball implements Serializable {
    private int x;
    private int y;
    private int diameter;
    private double speedX;
    private double speedY;

    public Ball(int x, int y, int diameter, double speedX,double speedY) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setSpeedX(double x) {
        this.speedX = x;
    }
    public double getSpeedY() {
        return speedY;
    }

    public int getDiameter() {
        return diameter;
    }
    public void move() {
        x += speedX;
        y += speedY;
    }
    public void reverseX() {
        speedX = -speedX;
        System.out.println("Horizontal speed changed to: " + speedX);
        System.out.println("Coordinate ballX: " + x);
    }
    public void reverseY() {
        speedY = -speedY;
        System.out.println("Vertical speed changed to: " + speedY);
        System.out.println("Coordinate ballY: " + y);
    }

}