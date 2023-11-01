import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    private Ball ball;
    private Player player;

    public GamePanel() {
        setPreferredSize(new Dimension(500, 800));
        ball = new Ball(300, 200, 20, -10, -10);
        player = new Player(200, 770, 100, 20);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
    }
}