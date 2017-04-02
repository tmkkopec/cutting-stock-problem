package data;

import java.util.List;

/**
 * Created by tkopec on 02.04.17.
 */
public class Bee {
    private List<Picture> currentSolution;

    public Bee(List<Picture> currentSolution) {
        this.currentSolution = currentSolution;
    }

    public List<Picture> getCurrentSolution() {
        return currentSolution;
    }
}
