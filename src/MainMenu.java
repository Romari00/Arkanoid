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

    public MainMenu(GamePanel gamePanel)  {
        this.gamePanel = gamePanel;

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        add(startButton);

        startNewGameButton = new JButton("Start New Game");
        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        add(startButton);
        add(startNewGameButton);
    }


    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    private void startGame() {
        System.out.println("Start Game button pressed");
        gamePanel.serializeGameState("game_state.ser");
        cardLayout.show(getParent(), "GamePanel");
        gamePanel.requestFocusInWindow();
        this.inMainMenu = false;
    }

//    private void startNewGame() {
//        System.out.println("Start New Game button pressed");

//        gamePanel.startNewGame();
//        cardLayout.show(getParent(), "GamePanel");
//        gamePanel.requestFocusInWindow();
//        this.inMainMenu = false;
//    }


    public boolean getInMainMenu() {
        return inMainMenu;
    }
}//123

