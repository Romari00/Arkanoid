import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;


public class MainMenu extends JPanel {
    private JButton startButton;
    private GamePanel gamePanel;
    private boolean inMainMenu = true;
    private CardLayout cardLayout;

    public MainMenu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        add(startButton);
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    private void startGame() {
        System.out.println("Start button pressed");
        cardLayout.show(getParent(), "GamePanel");
        gamePanel.requestFocusInWindow();
        inMainMenu = false;
    }
}//123

