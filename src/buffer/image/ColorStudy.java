package buffer.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by bipin on 3/13/16.
 */
public class ColorStudy {

    BufferedImage rgb_img;
    int height;
    int width;
    int red, green, blue = 0;

    public ColorStudy() {
        width=256;
        height=256;

        File outputImage = new File("RGB_STUDY");

        try {
            rgb_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    Color c = new Color(red + h, 0, blue + w);
                    rgb_img.setRGB(w, h, c.getRGB());
                }
            }

            File outputFile = new File("RGB_STUDY.jpg");
            ImageIO.write(rgb_img, "jpg", outputFile);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    static public void main(String args[]) throws Exception
    {
        ColorStudy obj = new ColorStudy();
    }
}
