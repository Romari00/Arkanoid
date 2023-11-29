import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.io.Serializable;

public class MainMenu extends JPanel implements Serializable {
    private JButton startButton;
    private GamePanel gamePanel;
    private boolean inMainMenu = true;
    private CardLayout cardLayout;
    private JButton startNewGameButton;

    public MainMenu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        startButton = new JButton("Продолжить игру");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        add(startButton);

        startNewGameButton = new JButton("Новая игра");
        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        add(startNewGameButton);
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    private void startGame() {
        if (!inMainMenu) {
            return;
        }

        cardLayout.show(getParent(), "GamePanel");
        gamePanel.requestFocusInWindow();

        if (startButton.getModel().isEnabled()) {
            gamePanel.loadGameState("game_state.ser");
         }


        inMainMenu = false;
    }

    private void startNewGame() {
        if (!inMainMenu) {
            return;
        }

        cardLayout.show(getParent(), "GamePanel");
        gamePanel.requestFocusInWindow();

        if (startNewGameButton.getModel().isEnabled()) {
            gamePanel.startNewGame();
        }


        inMainMenu = false;
    }

    public boolean getInMainMenu() {
        return inMainMenu;
    }
}