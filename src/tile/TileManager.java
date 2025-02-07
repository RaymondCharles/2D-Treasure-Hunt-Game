package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    //Constructor
    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage()
    {
        //Optimized Tile Setup
        setUp(0, "grass", false);
        setUp(1, "wall", true);
        setUp(2, "water", true);
        setUp(3, "earth", false);
        setUp(4, "tree", true);
        setUp(5, "sand", false);

        /*try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }

    public void setUp(int index, String imageName, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath)
    {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine(); //reads a line of text

                while (col < gp.maxWorldCol)
                {
                    String[] numbers = line.split(" "); //Splits this string at a space around matches of the given regex

                    int num  = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e)
        {

        }
    }
    public void draw(Graphics2D g2)
    {
        //Testing Purposes
        /*g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);*/

        //Sample 5x5 map - quite inefficient
/*        g2.drawImage(tile[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 0, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 48, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 96, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 192, 96, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 144, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 144, gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 48, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 144, 192, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 192, gp.tileSize, gp.tileSize, null);*/

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize; //position of the map
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; //where on the screen you draw
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
