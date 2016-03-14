package buffer.image;

/**
 * Created by bipin on 3/13/16.
 */
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Pixel {
    BufferedImage image;
    BufferedImage newImage;
    int width;
    int height;

    public Pixel() {
        try {
            File input = new File("cat.jpg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            //new image
            newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics grp = newImage.getGraphics();
            //grp.drawImage(image, 0, 0, null);

            int count = 0;

            for(int i=0; i<height; i++){

                for(int j=0; j<width; j++){

                    count++;
                    Color c = new Color(image.getRGB(j, i));

                    //System.out.println("Col: " + i + " - Row: " + j + " - RGB: " + image.getRGB(j, i));
                    //System.out.println(" - S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());

                    //Color newColor = new Color(c.getRed(), c.getGreen(), c.getBlue());
                    Color newColor = new Color( color_max(c.getRed() + (j/3)),
                                                color_max(c.getGreen() + (j/3)),
                                                color_max(c.getBlue() + (i/3))
                    );




                    newImage.setRGB(j, i, newColor.getRGB());

                }
            }

            File outputFile = new File("catNew.jpg");
            ImageIO.write(newImage, "jpg", outputFile);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int color_max(int colorVal) {
        if (colorVal > 255) {
            return 255;
        } else if (colorVal < 0) {
            return 0;
        } else {
            return colorVal;
        }
    }

    static public void main(String args[]) throws Exception
    {
        Pixel obj = new Pixel();
    }
}