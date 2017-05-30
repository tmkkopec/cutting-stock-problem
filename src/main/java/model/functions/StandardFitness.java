package model.functions;

import data.Canvas;
import data.Picture;
import gui.Visualiser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tkopec on 02.04.17.
 */
public class StandardFitness extends FitnessFunction {
    private boolean presentation;
    private static int counter = 1;
    private PrintWriter writer;
    private String CSV_SEPARATOR = ";";

    int bestFitness = -(Integer.MAX_VALUE - 2);
    List<Picture> bestPictures = new LinkedList<>();

    public StandardFitness(Double width, Double height) throws FileNotFoundException, UnsupportedEncodingException {
        super(width, height);
        presentation = false;
        writer = new PrintWriter("fitness.csv", "UTF-8");
    }

    /**
     * Places pictures on canvas of width {@code width} and height {@code height} and then finds
     * amount of empty area
     * @param currentSolution collection of pictures
     * @return amount of empty area
     */
    @Override
    // TODO implement
    public double evaluate(List<Picture> currentSolution) {
        if (currentSolution == null) return - (int)(getHeight()*getWidth());
        Canvas canvas = new Canvas(getWidth().intValue(),getHeight().intValue());
        for(Picture picture: currentSolution)canvas.addPicture(picture);
        if(canvas.isAllFit())canvas.updateSize();
        else return - (int)(canvas.getHeight()*canvas.getWidth());
        int square = 0;
        for(Picture picture: canvas.getPictures()){
            square += picture.getHeight()*picture.getWidth();
        }
//        Visualiser v = new Visualiser(canvas.getPictures());
//        v.visualise();
        int fit = square - (canvas.getHeight() * canvas.getWidth());
        System.out.println("Fitness: " + -fit);

        if(bestFitness < fit && canvas.isAllFit()){
            bestFitness = fit;
            bestPictures = currentSolution;
        }
        writer.println(counter + CSV_SEPARATOR + -fit);
        counter++;
        return square - (int)(canvas.getHeight()*canvas.getWidth());
    }

    public boolean presentationEvaluation(List<Picture> currentSolution){
        if(currentSolution == null) return false;
        Canvas canvas = new Canvas(getWidth().intValue(),getHeight().intValue());
        for(Picture picture: bestPictures)
            canvas.addPicture(picture);
        if(!canvas.isAllFit())
            return false;
        else
            canvas.updateSize();

        //System.out.println("Width: "+canvas.getWidth()+" Height: "+canvas.getHeight());

        int square = 0;
        for(Picture picture: canvas.getPictures()){
            square += picture.getHeight()*picture.getWidth();
        }
        Visualiser v = new Visualiser(canvas.getPictures());
        v.visualise();
        int fit = square - (int)(canvas.getHeight()*canvas.getWidth());
        System.out.println("Best fitness: "+ - bestFitness);
        writer.println(counter + CSV_SEPARATOR + -fit);
        writer.close();
        return true;
    }
}
