package dinamo.thugbird.elements;

import dinamo.thugbird.grafics.Screen;

abstract class Element {

    int top;
    int left;
    private final int width;
    private final int height;

    Element(int top, int left, int width, int height,Screen screen)
    {
        this.top = top;
        this.width = responsibleHWidth(width, screen);
        this.height = responsibleHeight(height, screen);
        this.left = left;
    }

    public int getTop(int tolerance) { return (top+((height*tolerance)/100));}

    public int getBottom(int tolerance) { return ((top + height)-((height*tolerance)/100));}

    private int getLeft(int tolerance) { return (left+((width*tolerance)/100));}

    public int getRight(int tolerance) { return ((left+width)-((width*tolerance)/100)); }

    int getWidth(){return width;}

    public int getHeight(){return height;}

    public boolean hasCollision(Element obj2, int tolerance){
        return (hasHorizontalCollision(obj2, tolerance) && hasVerticalCollision(obj2, tolerance));
    }

    public boolean hasHorizontalCollision(int top, int bottom){
        return (this.getTop(0) < bottom && top < this.getBottom(0));
    }

    private boolean hasHorizontalCollision(Element obj2, int tolerance){
        return (this.getTop(tolerance) < obj2.getBottom(tolerance) && obj2.getTop(tolerance) < this.getBottom(tolerance));
    }

    private boolean hasVerticalCollision(Element obj2, int tolerance){
        return (this.getLeft(tolerance) < obj2.getRight(tolerance) && obj2.getLeft(tolerance) < this.getRight(tolerance));
    }

    static int responsibleHeight(int oldPixels, Screen screen){
        int currentResolution = screen.getHeight();
        int baseResolution = Screen.BASE_HEIGHT_REVOLUTION;

        float percentBaseResolution = ((oldPixels*100)/baseResolution);

        return (int)((currentResolution*percentBaseResolution)/100);
    }

    private static int responsibleHWidth(int oldPixels, Screen screen){
        int currentResolution = screen.getWidth();
        int baseResolution = Screen.BASE_WIDTH_REVOLUTION;

        float percentBaseResolution = ((oldPixels*100)/baseResolution);

        return (int)((currentResolution*percentBaseResolution)/100);
    }

    public void reset(int top, int left){
        setTop(top);
        setLeft(left);
    }

    private void setLeft(int left) {
        this.left = left;
    }

    private void setTop(int top) {
        this.top = top;
    }

}
