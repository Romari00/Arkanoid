import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class GameEngine implements ActionListener, KeyListener {
    private GamePanel gamePanel;
    private boolean leftKey = false;
    private boolean rightKey = false;
    private Timer timer;
    private JFrame frame;
    private boolean gameOver = false;
    private Level level;


    public GameEngine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        timer = new Timer(10, this);
        timer.start();
        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        level = new Level();
        gamePanel.setBlocks(level.getBlocks());

    }

    public void updateGame() {
        if (leftKey) {
            int newPlayerX = gamePanel.getPlayer().getX() - 15;
            if (newPlayerX >= 0) {
                gamePanel.getPlayer().setX(newPlayerX);
            }
        }

        if (gameOver) {
            return;
        }

        if (rightKey) {
            int newPlayerX = gamePanel.getPlayer().getX() + 15;
            if (newPlayerX + gamePanel.getPlayer().getWidth() <= gamePanel.getWidth()) {
                gamePanel.getPlayer().setX(newPlayerX);
            }
        }

        if (gamePanel.getBall().getX() < -9 || gamePanel.getBall().getX() >= 789) {
            gamePanel.getBall().reverseX();
        }

        if (gamePanel.getBall().getY() < 14) {
            gamePanel.getBall().reverseY();
        }

        if (gamePanel.getBall().getY() == gamePanel.getPlayer().getY() & gamePanel.getBall().getX()+20 >= gamePanel.getPlayer().getX() & gamePanel.getBall().getX() <= gamePanel.getPlayer().getX() + gamePanel.getPlayer().getWidth()) {

            double relativeHitPosition = gamePanel.getBall().getX() - gamePanel.getPlayer().getX() - (double) gamePanel.getPlayer().getWidth() / 2;
            double normalizedHitPosition = relativeHitPosition / gamePanel.getPlayer().getWidth();
            double maxBounceAngle = Math.toRadians(100);
            double bounceAngle = normalizedHitPosition * maxBounceAngle;

            gamePanel.getBall().setSpeedX(gamePanel.getBall().getSpeedY() * Math.tan(bounceAngle));
            gamePanel.getBall().reverseY();


        }

        if (gamePanel.getBall().getY() == 990) {
            gamePanel.getBall().setY(500);
            gamePanel.getBall().setX(500);
            gamePanel.getPlayer().setX(400);
            gamePanel.repaint();
            SwingUtilities.invokeLater(() -> {
                gameOver = true;
                JOptionPane.showMessageDialog(frame, "Game Over", "Игра окончена", JOptionPane.INFORMATION_MESSAGE);
                gameOver = false;
            });
        }


        gamePanel.getBall().move();
        List<Block> blocks = level.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (gamePanel.getBall().getX() + gamePanel.getBall().getDiameter() >= block.getX() &&
                    gamePanel.getBall().getX() <= block.getX() + block.getWidth() &&
                    gamePanel.getBall().getY() + gamePanel.getBall().getDiameter() >= block.getY() &&
                    gamePanel.getBall().getY() <= block.getY() + block.getHeight()) {
                blocks.remove(i);
                i--;
                gamePanel.getBall().reverseY();
            }
        }
        if (blocks.isEmpty()) {
            gamePanel.getBall().setY(500);
            gamePanel.getBall().setX(500);
            gamePanel.getPlayer().setX(400);
            gamePanel.repaint();
            gameOver = true;
            JOptionPane.showMessageDialog(frame, "Next Level", "Следующий уровень", JOptionPane.INFORMATION_MESSAGE);
            level.NewLev();
            gameOver = false;
        }
        gamePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    updateGame();
            }
        });
    }



    @Override
    public void keyTyped(KeyEvent e) {
        // Пустая реализация
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyRelease(e);
    }

    private void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = true;
        }
    }

    private void handleKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = false;
        }
    }
}
