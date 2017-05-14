package data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kaz on 2017-05-13.
 */
public class Canvas {
    private List<BlankSpace> spaceRows;
    private List<BlankSpace> spaceCols;
    private int rows;
    private int cols;
    private int pictureX;
    private int pictureY;
    private List<Picture> pictures;

    public Canvas(int width, int height){
        spaceRows = new LinkedList<>();
        spaceCols = new LinkedList<>();
        pictures = new LinkedList<>();
        BlankSpace firstSpace = new BlankSpace(0,width-1,0,height-1);
        spaceRows.add(firstSpace);
        spaceCols.add(firstSpace);
        rows = 1;
        cols = 1;
        pictureX = 0;
        pictureY = 0;
    }

    public void addPicture(Picture picture){
        int[] position = findPicturePosition(picture.getWidth(), picture.getHeight());
        picture.setStartingPositionX(position[0]);
        picture.setStartingPositionY(position[1]);
        pictures.add(picture);
    }

    public List<Picture> getPictures(){
        return pictures;
    }

    private int[] findPicturePosition(int width, int height){
        for(int i = 0; i < cols; i++){ //going down
            if(checkSpaceInCol(spaceCols.get(i),width,height)){
                int[] position = new int[2];
                position[0] = pictureX;
                position[1] = pictureY;
                fixGrid(width, height);
                return position;
            }
        }
        for(int i = 0; i < rows; i++){
            if(checkSpaceInRow(spaceRows.get(i),width,height)){
                int[] position = new int[2];
                position[0] = pictureX;
                position[1] = pictureY;
                fixGrid(width, height);
                return position;
            }
        }
        //check right
        //-----
        int[] position = new int[0];
        return position;
    }

    private void fixGrid(int width, int height) {

    }

    private boolean checkSpaceInRow(BlankSpace space, int width, int height){
        if(space == null) return false;
        else if(space.isAvailable()){
            pictureX = space.getxStart();
            pictureY = space.getyStart();
            if (checkRight(space, width)) return checkDown(space, height);
        }
        return checkSpaceInRow(space.getRightSpace(),width,height);
    }

    private boolean checkSpaceInCol(BlankSpace space, int width, int height){
        if(space == null) return false;
        else if (space.isAvailable()){
            pictureX = space.getxStart();
            pictureY = space.getyStart();
            if (checkDown(space, height)) return checkRight(space, width);
        }
        return checkSpaceInCol(space.getDownSpace(),width,height);
    }
    //TODO: Implement this methood
    private boolean checkRight(BlankSpace space, int width) {
        return false;
    }
    //TODO: Implement this method
    private boolean checkDown(BlankSpace space, int height) {
        return false;
    }

}
