package gui;

import data.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Visualiser {

    public static final int canvasWidth = 500;

    public static final int canvasHeight = 500;

    private List<Picture> pictures;

    private JFrame frame;


    public Visualiser(List<Picture> pictures){
        this.pictures = pictures;
        setUpFrame();
    }


    private void setUpFrame(){
        frame = new JFrame("Bees are awesome");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        frame.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2,
                dim.height / 2 - frame.getHeight() / 2);
    }


    public void visualise(){
        JPanel canvas = new JPanel(null);

        for(Picture p: pictures){
            try {
                BufferedImage image = ImageIO.read(new File(p.getPath()));

                JLabel iLabel = new JLabel(new ImageIcon(image));

                iLabel.setBounds(p.getStartingPositionX(), p.getStartingPositionY(), p.getWidth(), p.getHeight());

                canvas.add(iLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        frame.getContentPane().removeAll();     // reusable
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
    }


    public List<Picture> getPictures() {
        return pictures;
    }


    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
