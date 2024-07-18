package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool
{
    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {
        //Scaling Buffered Images
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2  = scaledImage.createGraphics(); //Creates Graphics2D, used to draw into this buffered image
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
