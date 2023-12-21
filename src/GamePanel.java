import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class GamePanel extends JPanel implements Serializable {
    private Ball ball;
    private Player player;
    private List<Block> blocks;
    private int score;
    private Level level;
    private int lives;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 1000));
        ball = new Ball(500, 950, 20, -10, 10);
        player = new Player(400, 970, 200, 20);
        score = 0;
        lives = 3;

    }

    public void saveGameState(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(ball);
            out.writeObject(player);
            out.writeObject(blocks);
            out.writeInt(score);
            out.writeInt(lives);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGameState(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            ball = (Ball) in.readObject();
            player = (Player) in.readObject();
            blocks = (List<Block>) in.readObject();
            score = in.readInt();
            lives = in.readInt();
            repaint();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void initializeLevel() {
        blocks = level.getBlocks();
    }

    public void setLevel(Level level) {
        this.level = level;
        initializeLevel();
    }

    public void startNewGame() {
        ball = new Ball(500, 950, 20, -10, 10);
        player = new Player(400, 970, 200, 20);
        initializeLevel();
        score = 0;
        lives = 3;
        repaint();
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
    public List<Block> getBlocks() {
        return blocks;
    }
    public void increaseScore(int points) {
        score += points;
    }
    public int getLives() {
        return lives;
    }
    public void decreaseLives() {
        lives -= 1;
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
        g.drawString("Lives: " + lives, getWidth() - 750, 30);
    }
}