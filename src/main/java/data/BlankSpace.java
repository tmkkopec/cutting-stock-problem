package data;

/**
 * Created by Kaz on 2017-05-13.
 */
public class BlankSpace {
    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;
    private boolean available;
    private BlankSpace rightSpace;
    private BlankSpace downSpace;

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BlankSpace getRightSpace() {
        return rightSpace;
    }

    public void setRightSpace(BlankSpace rightSpace) {
        this.rightSpace = rightSpace;
    }

    public BlankSpace getDownSpace() {
        return downSpace;
    }

    public void setDownSpace(BlankSpace downSpace) {
        this.downSpace = downSpace;
    }

    public BlankSpace(int xStart, int xEnd, int yStart, int yEnd){
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.available = true;
        this.rightSpace = null;
        this.downSpace = null;
    }


    public int getWidth(){
        return xEnd-xStart+1;
    }

    public int getHeight(){
        return yEnd-yStart+1;
    }
}
