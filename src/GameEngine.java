import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




public class GameEngine implements ActionListener, KeyListener {
    private GamePanel gamePanel;
    private Ball ball;
    private Player player;
    private boolean leftKey = false;
    private boolean rightKey = false;
    private Timer timer;

    public GameEngine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        ball = new Ball(300, 200, 20, -10, -10);
        player = new Player(200, 770, 100, 20);
        timer = new Timer(10, this);
        timer.start();
        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

    }

    public void updateGame() {
        if (leftKey) {
            int newPlayerX = player.getX() - 20;
            if (newPlayerX >= 0) {
                player.setX(newPlayerX);
            }
        }
        if (rightKey) {
            int newPlayerX = player.getX() + 20;
            if (newPlayerX + player.getWidth() <= gamePanel.getWidth()) {
                player.setX(newPlayerX);
            }
        }
        ball.move();
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
