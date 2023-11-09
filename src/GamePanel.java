import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class GamePanel extends JPanel implements Serializable {
    private Ball ball;
    private Player player;
    private List<Block> blocks;
    private int score;
//123
    public GamePanel() {
        setPreferredSize(new Dimension(800, 1000));
        ball = new Ball(500, 950, 20, -10, 10);
        player = new Player(400, 970, 200, 20);
   //    this.blocks = blocks;
        score = 0;

    }

    public void serializeGameState(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this); // Сериализация текущего состояния игры
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deserializeGameState(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GamePanel loadedGamePanel = (GamePanel) in.readObject();
            this.ball = loadedGamePanel.ball;
            this.player = loadedGamePanel.player;
            this.blocks = loadedGamePanel.blocks;
            this.score = loadedGamePanel.score;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
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
                g.setColor(Color.blue);
                g.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());
            }
        }
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, getWidth() - 150, 30);
    }
}