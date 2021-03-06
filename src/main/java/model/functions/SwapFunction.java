package model.functions;

import data.Picture;

import java.util.List;

/**
 * Created by tkopec on 02.04.17.
 */
public abstract class SwapFunction {
    private double width;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getNgh() {
        return ngh;
    }

    private double height;
    private int ngh;

    SwapFunction(double width, double height, int ngh) {
        this.width = width;
        this.height = height;
        this.ngh = ngh;
    }

    public abstract List<Picture> swap(List<Picture> currentSolution);
}
