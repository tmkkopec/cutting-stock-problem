package model.functions;

import data.Picture;

import java.util.List;
import java.util.Random;

/**
 * Created by tkopec on 02.04.17.
 */
public class RandomSwap extends SwapFunction {
    public RandomSwap(double width, double height, int ngh) {
        super(width, height, ngh);
    }

    /**
     * Swaps randomly pictures in {@code currentSolution}
     * @param currentSolution collection of pictures to swap
     * @return valid and randomly swapped collection of {@code currentSolution}
     */
    @Override
    // TODO implement
    public List<Picture> swap(List<Picture> currentSolution) {
        int x = new Random().nextInt(currentSolution.size());
        int y;
        do{
            y = new Random().nextInt(currentSolution.size());
        } while (x == y);
        Picture p = currentSolution.get(x);
        currentSolution.set(x, currentSolution.get(y));
        currentSolution.set(y, p);
        return currentSolution;
    }
}
