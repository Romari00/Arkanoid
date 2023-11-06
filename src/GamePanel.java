import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private Ball ball;
    private Player player;
    private List<Block> blocks;
    private int score;
//123
    public GamePanel() {
        setPreferredSize(new Dimension(800, 1000));
        ball = new Ball(300, 200, 20, -10, 10);
        player = new Player(200, 970, 200, 20);
        this.blocks = blocks;
        score = 0;

    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks; // Устанавливаем список блоков
    }
    public void increaseScore(int points) {
        score += points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        if (blocks != null) {
            for (Block block : blocks) {
                g.setColor(Color.blue); // цвет блоков
                g.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());
            }
        }
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, getWidth() - 150, 30);
    }
}