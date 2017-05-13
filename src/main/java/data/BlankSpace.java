package data;

/**
 * Created by Kaz on 2017-05-13.
 */
public class BlankSpace {
    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;
    private boolean downAvailable;
    private boolean rightAvailable;
    private BlankSpace leftSpace;
    private BlankSpace upSpace;
    private BlankSpace rightSpace;
    private BlankSpace downSpace;

    public BlankSpace(int xStart, int xEnd, int yStart, int yEnd){
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.downAvailable = true;
        this.rightAvailable = true;
        this.leftSpace = null;
        this.upSpace = null;
        this.rightSpace = null;
        this.downSpace = null;
    }
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

    public boolean isDownAvailable() {
        return downAvailable;
    }

    public void setDownAvailable(boolean downAvailable) {
        this.downAvailable = downAvailable;
    }

    public boolean isRightAvailable() {
        return rightAvailable;
    }

    public void setRightAvailable(boolean rightAvailable) {
        this.rightAvailable = rightAvailable;
    }

    public BlankSpace getLeftSpace() {
        return leftSpace;
    }

    public void setLeftSpace(BlankSpace leftSpace) {
        this.leftSpace = leftSpace;
    }

    public BlankSpace getUpSpace() {
        return upSpace;
    }

    public void setUpSpace(BlankSpace upSpace) {
        this.upSpace = upSpace;
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

    public int getWidth(){
        return xEnd-xStart+1;
    }

    public int getHeight(){
        return yEnd-yStart+1;
    }
}
