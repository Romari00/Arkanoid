import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private Ball ball;
    private Player player;
    private List<Block> blocks;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 1000));
        ball = new Ball(300, 200, 20, -10, 10);
        player = new Player(200, 970, 200, 20);
        this.blocks = blocks;

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        if (blocks != null) {
            for (Block block : blocks) {
                g.setColor(Color.blue); // Здесь вы можете установить цвет блоков
                g.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());
            }
        }
    }
}