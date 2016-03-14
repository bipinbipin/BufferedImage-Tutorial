package buffer.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by bipin on 3/13/16.
 */
public class edgeDetection {
    BufferedImage imgOut;
    BufferedImage imgIn;
    BufferedImage imgUnder;
    int height;
    int width;
    int red, green, blue = 0;

    public edgeDetection() {

        File input = new File("cat.jpg");
        File input2 = new File("cat.jpg");


        //create the detector
        CannyEdgeDetector detector = new CannyEdgeDetector();
        //adjust its parameters as desired
        detector.setLowThreshold(0.5f);
        detector.setHighThreshold(1f);


        try {
            imgIn = ImageIO.read(input);
            imgUnder = ImageIO.read(input2);
            Graphics imgUnder_gfx = imgUnder.getGraphics();

            width = imgIn.getWidth();
            height = imgIn.getHeight();

            //apply it to an image
            detector.setSourceImage(imgIn);
            detector.process();
            //imgOut is the edge found image
            imgOut = detector.getEdgesImage();

            //parse img
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    Color c = new Color(imgOut.getRGB(w, h));
                    //leaves original values in the black space
//                    if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255){
//
//                        System.out.println("(" + w + "," + h + ") - WHITE FOUND!");
//                        imgUnder.setRGB(w, h, c.getRGB());
//                    }

                    // is pixel white
                    if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255) {

                        //find top left corner
                        if (h > 1 && w > 1) {
                            Color cLeft = new Color(imgOut.getRGB(w - 1, h));
                            Color cLeftAbove = new Color(imgOut.getRGB(w - 1, h - 1));
                            Color cAbobe = new Color(imgOut.getRGB(w, h - 1));
                            Color cLeftBelow = new Color(imgOut.getRGB(w - 1, h + 1));
                            Color cBelow = new Color(imgOut.getRGB(w, h + 1));
                            //is left black
                            if (cLeft.getRed() == 0 && cLeft.getGreen() == 0 && cLeft.getBlue() == 0) {
                                //is Left Above black
                                if (cLeftAbove.getRed() == 0 && cLeftAbove.getGreen() == 0 && cLeftAbove.getBlue() == 0) {
                                    // is above black
                                    if (cAbobe.getRed() == 0 && cAbobe.getGreen() == 0 && cAbobe.getBlue() == 0) {
                                        //is below left black
                                        if (cLeftBelow.getRed() == 0 && cLeftBelow.getGreen() == 0 && cLeftBelow.getBlue() == 0) {
                                             //is below black
                                            if (cBelow.getRed() == 0 && cBelow.getGreen() == 0 && cBelow.getBlue() == 0) {
                                                //System.out.println("(" + w + "," + h + ") - CORNER FOUND!");
                                                //imgUnder.setRGB(w, h, new Color(255,0,0).getRGB());
                                                imgUnder_gfx.drawString("cat", w, h);
                                            }
                                        }

                                    }
                                }
                            } else {

                                //else is not a corner, just white pixel
                                //imgUnder.setRGB(w, h, c.getRGB());
                            }
                        }

                        //System.out.println("(" + w + "," + h + ") - WHITE FOUND!");

                    }
//                    imgUnder.setRGB(w, h, c.getRGB());
                    imgUnder.setRGB(w, h, new Color(0,0,0).getRGB());
                }
            }

            File outputFile = new File("cat_overlay.jpg");
            ImageIO.write(imgUnder, "jpg", outputFile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    static public void main(String args[]) throws Exception
    {
        edgeDetection obj = new edgeDetection();
    }
}
