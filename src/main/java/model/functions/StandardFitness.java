package model.functions;

import data.Picture;

import java.util.List;

/**
 * Created by tkopec on 02.04.17.
 */
public class StandardFitness extends FitnessFunction {
    public StandardFitness(Double width, Double height) {
        super(width, height);
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
        return 0;
    }
}
