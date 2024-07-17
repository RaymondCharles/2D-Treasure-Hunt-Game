package main;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allows user to properly close window when x button is clicked
        window.setResizable(false);
        window.setTitle("Treasure Hunt 2D");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //Causes window to be sized to fit the preferred size and layouts of its subcomponents

        window.setLocationRelativeTo(null); // makes sure the window is displayed at the centre of the screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
