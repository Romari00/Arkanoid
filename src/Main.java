import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Arkanoid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        MainMenu mainMenu = new MainMenu(gamePanel);

        JPanel cards = new JPanel(new CardLayout());
        cards.add(mainMenu, "MainMenu");
        cards.add(gamePanel, "GamePanel");

        frame.add(cards);

        mainMenu.setCardLayout((CardLayout) cards.getLayout());

        GameEngine gameEngine = new GameEngine(gamePanel, mainMenu);

        frame.pack();
        frame.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);
    }
}
