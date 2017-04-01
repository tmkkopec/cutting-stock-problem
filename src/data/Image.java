package data;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private String path;

    private int width;

    private int height;

    private int startingPositionX;

    private int startingPositionY;


    public Image(String path){
        this.path = path;
        try {
            initImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Image(File file){
        this.path = file.getPath();
        try {
            initImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initImage() throws IOException {
        BufferedImage bi = ImageIO.read(new File(path));
        width = bi.getWidth();
        height = bi.getHeight();
    }


    public String getPath() {
        return path;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public int getStartingPositionX() {
        return startingPositionX;
    }


    public int getStartingPositionY() {
        return startingPositionY;
    }


    /*
        Note that point (0, 0) is on the left, top corner of the page.
    */
    public int getEndingPositionX() {
        return startingPositionX + width;
    }

    /*
        Note that point (0, 0) is on the left, top corner of the page.
    */
    public int getEndingPositionY() {
        return startingPositionY + height;
    }

}
