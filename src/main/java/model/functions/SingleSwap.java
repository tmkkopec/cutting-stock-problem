package model.functions;

import data.Picture;

import java.util.List;

/**
 * Created by tkopec on 02.04.17.
 */
public class SingleSwap extends SwapFunction {

    public SingleSwap(double width, double height, int ngh) {
        super(width, height, ngh);
    }

    /**
     * Swaps single picture in neighbourhood
     * @param currentSolution collection of pictures to swap
     * @return valid and swapped collection of {@code currentSolution}
     */
    @Override
    // TODO implement
    public List<Picture> swap(List<Picture> currentSolution) {
        Picture first = currentSolution.remove(0);
        currentSolution.add(first);
        return currentSolution;
    }
}
