package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageHandler extends Component{
    BufferedImage image;
    int[][][] imageAsInteger;
    Color[][] imageAsRGB;
    HashMap<Point, Pixel> pixels;

    public void loadImage(String path) {
        try {
            pixels = new HashMap<>();
            image = ImageIO.read(new File(path));
            imageAsInteger = convertTo2DWithoutUsingGetRGB(image);
            imageAsRGB = convertTo2DUsingGetRGB(image);
        } catch (IOException e) {
            System.out.println("error reading image: " + e);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    public Dimension getPreferredSize() {
        if (image == null) {
            return new Dimension(1000,1000);
        } else {
            return new Dimension(image.getWidth(null), image.getHeight(null));
        }
    }

    public void showImage(){
        JFrame f = new JFrame("Loaded Image");
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.add(this);
        f.pack();
        f.setVisible(true);
    }

    int[][][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][][] result = new int[height][width][3];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                result[row][col][0] = (pixels[pixel] >> 24) & 0xFF; // alpha
                result[row][col][1] = pixels[pixel] & 0xFF; // blue
                result[row][col][2] = (pixels[pixel + 1] >> 8) & 0xFF;// green
                result[row][col][3] = (pixels[pixel + 2] >> 16) & 0xFF; // red
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                result[row][col][0] = pixels[pixel] & 0xFF; // blue
                result[row][col][1] = (pixels[pixel + 1] >> 8) & 0xFF;// green
                result[row][col][2] = (pixels[pixel+ 2] >> 16) & 0xFF; // red
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

    Color[][] convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        Color[][] result = new Color[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(image.getRGB(col, row));
                result[row][col] = c;
                pixels.put(new Point(row,col), new Pixel(row, col, c));
            }
        }
        return result;
    }
}
