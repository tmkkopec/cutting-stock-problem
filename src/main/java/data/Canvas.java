package data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kaz on 2017-05-13.
 */
public class Canvas {
    private List<BlankSpace> blankSpaces;
    private List<Picture> pictures;

    public Canvas(int width, int height){
        blankSpaces = new LinkedList<>();
        pictures = new LinkedList<>();
        blankSpaces.add(new BlankSpace(0,width-1,0,height-1));
    }

    public void addPicture(Picture picture){
        int[] position = findPicturePosition(picture.getWidth(), picture.getHeight());
        picture.setStartingPositionX(position[0]);
        picture.setStartingPositionY(position[0]);
        pictures.add(picture);
    }

    public List<Picture> getPictures(){
        return pictures;
    }

    private int[] findPicturePosition(int width, int height){
        for(BlankSpace space: blankSpaces){
            if((space.getWidth()>=width) && (space.getHeight()>=height)){

            }
        }
        //-----
        int[] position = new int[0];
        return position;
    }

    private void cutMatchingSpace(BlankSpace space){

    }
    private void cutMatchingWidthSpace(BlankSpace space, int height){

    }
    private void cutMatchingHeightSpace(BlankSpace space, int width){

    }
    private void cutSpace(BlankSpace space, int width, int height){

    }
}
