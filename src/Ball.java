public class Ball {
    private int x;
    private int y;
    private int diameter;
    private int speedX;
    private int speedY;

    public Ball(int x, int y, int diameter, int speedX,int speedY) {
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

    public int getDiameter() {
        return diameter;
    }
    public void move() {
        x += speedX;
        y += speedY;


        if (y <= 0 || y >= 780) {
            reverseY();
        }

        if (x <= 0 || x >= 480) {
            reverseX();
        }


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