import data.Canvas;
import data.Picture;
import data.ResourceManager;
import gui.Visualiser;
import model.Supervisor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;

import static data.ResourceManager.getPathToImages;
import static data.ResourceManager.getPathToProperties;

public class Main {

    public boolean cmd = true;

    public static void main(String[] args) {
        try {
            Main main = new Main();
            if (main.cmd) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Path to images: ");
                String resources = br.readLine();
                System.out.println("Path to application.properties: ");
                String properties = br.readLine();
                System.out.println(resources);
                System.out.println(properties);
                Supervisor supervisor = new Supervisor(resources, properties);
                long start = System.nanoTime();
                supervisor.start();
                long end = System.nanoTime() - start;
                System.out.println("Complete time: " + (((double)end - start))/(1000000000) + " s");

            } else {
                Supervisor supervisor = new Supervisor(getPathToImages(), getPathToProperties());
                long start = System.nanoTime();
                supervisor.start();
                long end = System.nanoTime();
                System.out.println("Complete time: " + (((double)end - start))/(1000000000) + " s");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO Delete this method
    private void sampleUsage() {
        // supervisor sample test
        try {
            new Supervisor(getPathToImages(), "application.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResourceManager rm = new ResourceManager();
        Canvas canvas = new Canvas(400, 400);
        try {
            List<Picture> list = rm.getPicturesFromResources();
            for (Picture picture : list) canvas.addPicture(picture);
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
