import data.Canvas;
import data.Picture;
import data.ResourceManager;
import gui.Visualiser;
import model.Supervisor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String []args){
        new Main().sampleUsage();
    }


    // TODO Delete this method
    private void sampleUsage(){
        // supervisor sample test
        try {
            new Supervisor("application.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResourceManager rm = new ResourceManager();
        Canvas canvas = new Canvas(400, 400);
        try {
            List<Picture> list = rm.getPicturesFromResources();
            Collections.shuffle(list, new Random(System.nanoTime()));
            for(Picture picture: list) canvas.addPicture(picture);
            //list.get(0).setStartingPositionX(20);
            //list.get(0).setStartingPositionY(30);

            //list.get(1).setStartingPositionX(100);
            //list.get(1).setStartingPositionY(300);

            Visualiser v = new Visualiser(canvas.getPictures());
            v.visualise();


//            // Reuse
            Thread.sleep(2000);
//
//            list.get(1).setStartingPositionX(200);
//            list.get(1).setStartingPositionY(200);
//            v.setPictures(list);
//
//            v.visualise();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
