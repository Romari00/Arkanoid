import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.List;


public class GameEngine implements ActionListener, KeyListener, Serializable {
    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private boolean leftKey = false;
    private boolean rightKey = false;
    private Timer timer;
    private JFrame frame;
    private boolean gameOver = false;
    private Level level;



    public GameEngine(GamePanel gamePanel,  MainMenu mainMenu) {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;
        timer = new Timer(10, this);
        timer.start();
        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        level = new Level(1,3,60,20, 800, 1000);
        gamePanel.setBlocks(level.getBlocks());
        gamePanel.setLevel(level);
    }

    public void updateGame() {
        if (!mainMenu.getInMainMenu()) {
            if (leftKey) {
                int newPlayerX = gamePanel.getPlayer().getX() - 10;
                if (newPlayerX >= 0) {
                    gamePanel.getPlayer().setX(newPlayerX);
                }
            }

            if (gameOver) {
                return;
            }

            if (rightKey) {
                int newPlayerX = gamePanel.getPlayer().getX() + 10;
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

            if (gamePanel.getBall().getY() == gamePanel.getPlayer().getY() & gamePanel.getBall().getX() + 20 >= gamePanel.getPlayer().getX() & gamePanel.getBall().getX() <= gamePanel.getPlayer().getX() + gamePanel.getPlayer().getWidth()) {

                double relativeHitPosition = gamePanel.getBall().getX() - gamePanel.getPlayer().getX() - (double) gamePanel.getPlayer().getWidth() / 2;
                double normalizedHitPosition = relativeHitPosition / gamePanel.getPlayer().getWidth();
                double maxBounceAngle = Math.toRadians(100);
                double bounceAngle = normalizedHitPosition * maxBounceAngle;

                gamePanel.getBall().setSpeedX(gamePanel.getBall().getSpeedY() * Math.tan(bounceAngle));
                gamePanel.getBall().reverseY();


            }

            if (gamePanel.getBall().getY() == 990) {
                gamePanel.getBall().setY(950);
                gamePanel.getBall().setX(500);
                gamePanel.getPlayer().setX(400);
                gamePanel.repaint();
                SwingUtilities.invokeLater(() -> {
                    gameOver = true;
                    gamePanel.saveGameState("game_state.ser");
                    JOptionPane.showMessageDialog(frame, "Проиграл", "Игра окончена", JOptionPane.INFORMATION_MESSAGE);
                    gameOver = false;
                });
            }


            gamePanel.getBall().move();
            List<Block> blocks = gamePanel.getBlocks();
            if (blocks == null) {
                gamePanel.initializeLevel();
                blocks = gamePanel.getBlocks();
            }
            for (int i = 0; i < blocks.size(); i++) {
                Block block = blocks.get(i);

                double ballCenterX = gamePanel.getBall().getX() + gamePanel.getBall().getDiameter()/2;
                double ballCenterY = gamePanel.getBall().getY() + gamePanel.getBall().getDiameter()/2;

                double blockCenterX = block.getX() + block.getWidth() / 2;
                double blockCenterY = block.getY() + block.getHeight() / 2;

                double deltaX = Math.abs(ballCenterX - blockCenterX) - (gamePanel.getBall().getDiameter()/2 + block.getWidth() / 2);
                double deltaY = Math.abs(ballCenterY - blockCenterY) - (gamePanel.getBall().getDiameter()/2 + block.getHeight() / 2);

                if (deltaX <= 0 && deltaY <= 0) {
                    if (deltaX > deltaY) {
                        gamePanel.getBall().reverseX();
                    } else {
                        gamePanel.getBall().reverseY();
                    }

                    blocks.remove(i);
                    i--;

                    gamePanel.increaseScore(100);
                }
            }


            if (blocks.isEmpty() && !gameOver) {
                gamePanel.getBall().setY(500);
                gamePanel.getBall().setX(500);
                gamePanel.getPlayer().setX(400);
                gamePanel.repaint();
                gameOver = true;
                JOptionPane.showMessageDialog(frame, "Next level", "Уровень пройден", JOptionPane.INFORMATION_MESSAGE);
                level.generateNewLevel(3,3,60,20);
                gamePanel.setLevel(level);
                gameOver = false;
            }
            gamePanel.repaint();
        }
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
