package model;

import data.Bee;
import data.Picture;
import data.ResourceManager;
import model.functions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by tkopec on 02.04.17.
 */
public class Supervisor {
    private PriorityQueue<Bee> beesPopulation;
    private int n;
    private int m;
    private int e;
    private int ngh;
    private int n2;
    private int n1;
    private int maxIterations;
    private double width;
    private double height;
    private FitnessFunction fitnessFunction;
    private SwapFunction swapFunction;
    private RandomSwap randomSwapFunction;
    private List<Picture> basicSolution;


    public Supervisor(String pathToImages, String propertiesFile) throws Exception {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(propertiesFile)){//loader.getResourceAsStream(propertiesFile)) {
            props.load(input);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error loading properties file");
        }

        // check if all necessary properties are provided
        List<String> paramsList =
                Arrays.asList("n", "m", "e", "ngh", "n2", "n1", "maxIterations", "width", "height", "fitnessFunction");
        if (!props.stringPropertyNames().containsAll(paramsList))
            throw new IllegalArgumentException("Not enough properties in properties file: " + propertiesFile);

        // init basic algorithm params
        this.n = Integer.parseInt(props.getProperty("n"));
        this.m = Integer.parseInt(props.getProperty("m"));
        this.e = Integer.parseInt(props.getProperty("e"));
        this.ngh = Integer.parseInt(props.getProperty("ngh"));
        this.n2 = Integer.parseInt(props.getProperty("n2"));
        this.n1 = Integer.parseInt(props.getProperty("n1"));
        this.maxIterations = Integer.parseInt(props.getProperty("maxIterations"));

        this.basicSolution = new ResourceManager().getPicturesFromFolder(pathToImages);
        calculateCanvasSize(basicSolution);
//        this.width = Double.parseDouble(props.getProperty("width"));
//        this.height = Double.parseDouble(props.getProperty("height"));

        // init fitness and swap functions
        Class<?> fitness = Class.forName("model.functions." + props.getProperty("fitnessFunction"));
        this.fitnessFunction = (StandardFitness) fitness.getConstructor(Double.class, Double.class)
                .newInstance(width, height);
        this.swapFunction = new SingleSwap(width, height, ngh);
        this.randomSwapFunction = new RandomSwap(width, height, ngh);

        // inti rest params


        this.beesPopulation = getEmptyBeesPopulation();
        initPopulation();
    }

    private void calculateCanvasSize(List<Picture> pictures){
        int square = 0;
        for(Picture picture: pictures)square += picture.getHeight()*picture.getWidth();
        square = (int)Math.sqrt(3*square);
        int width = square/2;
        int height = square/3;
        this.width = (int)2*width;
        this.height = (int)2*height;
        //System.out.println("Width: "+this.width+" height: "+this.height);
    }
    /**
     * NOT TESTED YET! NEED IMPLEMENTATIONS FROM model.functions PACKAGE
     */
    public void start() {
        PriorityQueue<Bee> newPopulation = getEmptyBeesPopulation();
        int currentIteration = 0;

        // while maxIterations is not reached
        while (currentIteration++ < maxIterations) {
            // send n2 bees to best e out of m sites
            sendExtraBees(0, e, newPopulation, n2);

            // send n1 bees to m - e sites
            sendExtraBees(e, m - e, newPopulation, n1);

            // send n - m bees to random search
            sendToRandomSearch(n - m, newPopulation);

            // update beesPopulation with newly generated population
            beesPopulation = newPopulation;
        }
        int i = 0;
        //while(i<10){
            if(!fitnessFunction.presentationEvaluation(beesPopulation.poll().getCurrentSolution())) System.out.println("Brak rozwiazan");

       // }
    }

    private void sendToRandomSearch(int beesAmount, PriorityQueue<Bee> newPopulation) {
        for (int i = 0; i < beesAmount; i++) {
            newPopulation.add(new Bee(randomSwapFunction.swap(basicSolution)));
        }
    }

    private void sendExtraBees(int firstBee, int lastBee, PriorityQueue<Bee> newPopulation, int beesAmount) {
        for (int i = firstBee; i < lastBee; i++) {
            // keep track on currently best bee on a site
            Bee bestBee = getNthElement(i);
            // save original bee from this site
            Bee mainBee = getNthElement(i);

            // send n2 bees and look for better bees better than mainBee
            for (int j = 0; j < beesAmount; j++) {
                List<Picture> swappedSet = swapFunction.swap(mainBee.getCurrentSolution());
                if (fitnessFunction.evaluate(swappedSet) > fitnessFunction.evaluate(bestBee.getCurrentSolution()))
                    bestBee = new Bee(swappedSet);
            }

            // save bee in a new population
            newPopulation.add(bestBee);
        }
    }

    private PriorityQueue<Bee> getEmptyBeesPopulation() {
        return new PriorityQueue<>(n, (o1, o2) -> {
            double solution1 = fitnessFunction.evaluate(o1.getCurrentSolution());
            double solution2 = fitnessFunction.evaluate(o2.getCurrentSolution());
            if (solution1 > solution2)
                return 1;
            else if (solution1 < solution2)
                return -1;
            else
                return 0;
        });
    }

    private Bee getNthElement(int nth) {
        int i = 0;
        for (Bee bee : beesPopulation)
            if (i++ == nth)
                return bee;
        throw new IllegalArgumentException(String.format("No %sth element in beesPopulation (population size is %d)",
                nth, beesPopulation.size()));
    }

    private void initPopulation() throws IOException, URISyntaxException {
        for (int i = 0; i < n; i++)
            beesPopulation.add(new Bee(basicSolution));
    }
}
