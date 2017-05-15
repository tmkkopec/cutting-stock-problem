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
    private int emergencyX;
    private int emergencyY;
    private int emergencySpace;
    private boolean emergencyRight;
    private boolean emergencyDown;
    private List<Picture> pictures;
    private BlankSpace startSpace;
    private int canvasXEnd;
    private int canvasYEnd;
    private int minSurplus;
    private int pictureWidth;
    private int pictureHeight;

    public Canvas(int width, int height){
        spaceRows = new LinkedList<>();
        spaceCols = new LinkedList<>();
        pictures = new LinkedList<>();
        canvasXEnd = width -1;
        canvasYEnd = height -1;
        BlankSpace firstSpace = new BlankSpace(0,width-1,0,height-1);
        spaceRows.add(firstSpace);
        spaceCols.add(firstSpace);
        rows = 1;
        cols = 1;
        pictureX = 0;
        pictureY = 0;
    }

    private int getSurplus(int xEnd, int yEnd){
        int surplus = 0;
        if (xEnd > canvasXEnd) surplus += (xEnd - canvasXEnd) * (yEnd + 1);
        if (yEnd > canvasYEnd) surplus += (yEnd - canvasYEnd) + (xEnd + 1);
        if (xEnd > canvasXEnd && yEnd > canvasYEnd) surplus += (xEnd - canvasXEnd) * (yEnd - canvasYEnd);
        return surplus;
    }
    public void addPicture(Picture picture){
        minSurplus = getSurplus(canvasXEnd + picture.getWidth(), canvasYEnd + picture.getHeight());
        pictureWidth = picture.getWidth();
        pictureHeight = picture.getHeight();
        int[] position = findPicturePosition(picture.getWidth(), picture.getHeight());
        picture.setStartingPositionX(position[0]);
        picture.setStartingPositionY(position[1]);
        if(!(position[0] == 0 && position[1] == 0 && !pictures.isEmpty())){
            pictures.add(picture);
        }
        else{
            System.out.println("Picture "+picture.getPath()+" doesnt fit");
        }
    }

    public List<Picture> getPictures(){
        return pictures;
    }

    private int[] findPicturePosition(int width, int height){
        BlankSpace currentSpace = spaceCols.get(0);
        for(int i = 0; i < cols; i++){ //going down
            if(checkSpaceInCol(currentSpace,width,height)){
                int[] position = new int[2];
                position[0] = pictureX;
                position[1] = pictureY;
                fixGrid(width, height);
                return position;
            }
            currentSpace = currentSpace.getRightSpace();
        }
        currentSpace = spaceRows.get(0);
        for(int i = 0; i < rows; i++){
            if(checkSpaceInRow(currentSpace,width,height)){
                int[] position = new int[2];
                position[0] = pictureX;
                position[1] = pictureY;
                fixGrid(width, height);
                return position;
            }
            currentSpace = currentSpace.getDownSpace();
        }
        //TODO: Implement what happens when there is no space on canvas
        int[] position = new int[2];
        position[0] = emergencyX;
        position[1] = emergencyY;
        fixAfterIncreasing();
        return position;
    }

    private void fixAfterIncreasing(){
        //rows,cols,canvasEnd
        int newXEnd = Math.max(canvasXEnd, emergencyX + pictureWidth - 1);
        int newYEnd = Math.max(canvasYEnd, emergencyY + pictureHeight - 1);
        BlankSpace cornerSpace = new BlankSpace(canvasXEnd)
    }

    private void fixGrid(int width, int height) {
        BlankSpace currentSpace;
        BlankSpace rowSpace;
        BlankSpace colSpace;
        int startrow = 0;
        int startcol = 0;
        rowSpace = colSpace = currentSpace = spaceRows.get(0);
        for (int i = 0; i < rows; i++){
            if(currentSpace.getyStart() == pictureY) {
                startrow = i;
                rowSpace = currentSpace;
                break;
            }
            currentSpace = currentSpace.getDownSpace();
        }
        currentSpace = spaceCols.get(0);
        for (int i = 0; i < cols; i++){
            if(currentSpace.getxStart() == pictureX) {
                startcol = i;
                colSpace = currentSpace;
                break;
            }
            currentSpace = currentSpace.getRightSpace();
        }
        int endrow = startrow;
        for (int i = startrow; i < rows; i++){
            if(pictureY + height - 1 > rowSpace.getyEnd()){
                endrow++;
                rowSpace = rowSpace.getDownSpace();
            }
        }
        int endcol = startcol;
        for (int i = startcol; i < cols; i++){
            if(pictureX + width - 1 > colSpace.getxEnd()){
                endcol++;
                colSpace = colSpace.getRightSpace();
            }
        }
        for(int i = rows - 1; i >= 0; i--){
            currentSpace = getSpace(i, endcol);
            BlankSpace newSpace = new BlankSpace(pictureX + width, currentSpace.getxEnd(), currentSpace.getyStart(), currentSpace.getyEnd());
            newSpace.setAvailable(currentSpace.isAvailable());
            newSpace.setRightSpace(currentSpace.getRightSpace());
            if(currentSpace.getDownSpace() != null) newSpace.setDownSpace(currentSpace.getDownSpace().getRightSpace());
            else newSpace.setDownSpace(null);

            currentSpace.setxEnd(pictureX + width -1);
            currentSpace.setRightSpace(newSpace);
        }
        cols++;
        for(int i = cols - 1; i >= 0; i--){
            currentSpace = getSpace(endrow, i);
            BlankSpace newSpace = new BlankSpace(currentSpace.getxStart(),currentSpace.getxEnd(),pictureY + height, currentSpace.getyEnd());
            newSpace.setAvailable(currentSpace.isAvailable());
            newSpace.setDownSpace(currentSpace.getDownSpace());
            if(currentSpace.getRightSpace() != null) newSpace.setRightSpace(currentSpace.getRightSpace().getDownSpace());
            else newSpace.setRightSpace(null);

            currentSpace.setyEnd(pictureY + height -1);
            currentSpace.setDownSpace(newSpace);
        }
        rows++;
        makeUnavailable(getSpace(startrow,startcol),endrow - startrow + 1, endcol - startcol + 1);

    }

    private void makeUnavailable(BlankSpace rowSpace, int rows, int cols){
        BlankSpace colSpace;
        for (int i = 0; i < rows; i++){
            colSpace = rowSpace;
            for(int y = 0; y < cols; y++) {
                colSpace.setAvailable(false);
                colSpace = colSpace.getRightSpace();
            }
            rowSpace = rowSpace.getDownSpace();
        }
    }

    private BlankSpace getSpace(int row, int col){
        BlankSpace currentSpace = spaceCols.get(0);
        for(int i = 0; i < row; i++) currentSpace = currentSpace.getDownSpace();
        for(int i = 0; i < col; i++) currentSpace = currentSpace.getRightSpace();
        return currentSpace;
    }

    private boolean checkSpaceInRow(BlankSpace space, int width, int height){
        if(space == null) return false;
        else if(space.isAvailable()){
            emergencyRight = false;
            startSpace = space;
            pictureX = space.getxStart();
            pictureY = space.getyStart();
            if (checkRight(space, width)) {
                emergencyRight = true;
                return checkDown(space, height);
            }
        }
        return checkSpaceInRow(space.getRightSpace(),width,height);
    }

    private boolean checkSpaceInCol(BlankSpace space, int width, int height){
        if(space == null) return false;
        else if (space.isAvailable()){
            emergencyDown = false;
            startSpace = space;
            pictureX = space.getxStart();
            pictureY = space.getyStart();
            if (checkDown(space, height)) {
                emergencyDown = true;
                return checkRight(space, width);
            }
        }
        return checkSpaceInCol(space.getDownSpace(),width,height);
    }

    private boolean checkRight(BlankSpace space, int width) {
        if(space == null) {
            if(emergencyDown){
                int surplus = getSurplus(pictureX+pictureWidth-1, pictureY+pictureHeight-1);
                if (surplus <= minSurplus){
                    minSurplus = surplus;
                    emergencyX = pictureX;
                    emergencyY = pictureY;
                }
            }
            return false;
        }
        if(!space.isAvailable()) return false;
        if(space.getWidth()>= width) return true;
        return checkRight(space.getRightSpace(),width - space.getWidth());
    }

    private boolean checkDown(BlankSpace space, int height) {
        if(space == null) {
            if(emergencyRight){
                int surplus = getSurplus(pictureX+pictureWidth-1, pictureY+pictureHeight-1);
                if (surplus <= minSurplus){
                    minSurplus = surplus;
                    emergencyX = pictureX;
                    emergencyY = pictureY;
                }
            }
            return false;
        }
        if(!space.isAvailable()) return false;
        if(space.getHeight() >= height) return true;
        return checkDown(space.getDownSpace(),height - space.getHeight());
    }

}
