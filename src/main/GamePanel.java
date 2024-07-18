package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable //Inherits JPanel Class (Subclass)
{
	// Screen Settings
    final int originalTileSize = 16; //16*16 tile for retro feel
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48*48 tile, has to be public in order to access from other packages
    public final int maxScreenCol = 16; //16 tiles horizontally
    public final int maxScreenRow = 12; //12 tiles vertically
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public int screenHeight = tileSize * maxScreenRow; //576 pixels

    // WORLD MAP PARAMETERS (settings)
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
   /* public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;*/

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; //Thread: the direction that is taken while a program is executing

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];


    // Constructor
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Sets the size of the JPanel Class
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //When true, all the drawing from this component wil be done in an offscreen painting buffer, improves rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); //With this the mains.GamePanel can be 'focuses' to receive key input

    }

    public void setupGame()
    {
        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Game Loop - Core of the game

    //Sleep Method
    /*@Override
    public void run() //starts a new thread by calling this method
    {

        double drawInterval = 1000000000/FPS; //0.01666...s
        double nextDrawTime = System.nanoTime() + drawInterval; //Returns the current value of the running JVMs high res src in ns.

        while (gameThread != null)
        {
            //1. update information such as character positions
            update();
            //2. draw the screen with the updated information
            repaint();

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }*/

    //Accumulator method
    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            //Shows FPS count
            /*if (timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }*/
        }
    }

    public void update()
    {
        //in java the up left corner is (0,0). Increase in x to the right, Increase in y down.
        player.update();
    }

    public void paintComponent(Graphics g) //Graphics class - class that has many functions/objects on the screen
    {
        super.paintComponent(g);//super means parent class of JPanel in this case

        Graphics2D g2 = (Graphics2D)g;

        //Debugging
        long drawStart = 0;
        if (keyH.checkDrawTime == true)
        {
            drawStart = System.nanoTime();
        }

        //Tile
        tileM.draw(g2);

        //Object
        for (int i = 0; i < obj.length; i++)
        {
            if (obj[i] != null)
            {
                obj[i].draw(g2, this);
            }
        }

        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        //Debugging
        if (keyH.checkDrawTime == true)
        {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);

        }


        g2.dispose(); //dispose of this graphics context/system resources that its using to save memory/increase performance
    }

    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
}
