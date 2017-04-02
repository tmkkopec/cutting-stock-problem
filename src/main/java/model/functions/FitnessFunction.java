package model.functions;

import data.Picture;

import java.util.List;

/**
 * Created by tkopec on 02.04.17.
 */
public abstract class FitnessFunction {
    private Double width;
    private Double height;

    FitnessFunction(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    public abstract double evaluate(List<Picture> currentSolution);
}
