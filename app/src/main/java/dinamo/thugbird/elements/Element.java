package dinamo.thugbird.elements;

import dinamo.thugbird.grafics.Screen;

public abstract class Element {

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

    public int getWidth(){return width;};
    public int getheight(){return height;};

    public boolean hasCollision(Element obj2, int tolerance){
        return (hasHorizontalCollision(obj2, tolerance) && hasVerticalCollision(obj2, tolerance));
    }

    public boolean hasCollision(int top, int bottom, int left, int right){
        return (hasHorizontalCollision(top, bottom) && hasVerticalCollision(left, right));
    }

    public boolean hasHorizontalCollision(int top, int bottom){
        return (this.getTop(0) < bottom && top < this.getBottom(0));
    }

    private boolean hasVerticalCollision(int left, int right){
        return (this.getLeft(0) < right && left < this.getRight(0));
    }

    boolean hasHorizontalCollision(Element obj2, int tolerance){
        return (this.getTop(tolerance) < obj2.getBottom(tolerance) && obj2.getTop(tolerance) < this.getBottom(tolerance));
    }

    private boolean hasVerticalCollision(Element obj2, int tolerance){
        return (this.getLeft(tolerance) < obj2.getRight(tolerance) && obj2.getLeft(tolerance) < this.getRight(tolerance));
    }


    public boolean getClick(float y, float x) {
        return (y > this.getTop(-2) && y < (this.getBottom(-2))) &&
                (x > this.getLeft(-2) && x < (this.getRight(-2)));

    }

    public static int responsibleHeight(int oldPixels, Screen screen){
        int currentResolution = screen.getHeight();
        int baseResolution = screen.BASE_HEIGHT_REVOLUTION;

        float percentualBaseResolution = ((oldPixels*100)/baseResolution);

        int newPixels = (int)((currentResolution*percentualBaseResolution)/100);

        return newPixels;
    }

    public static int responsibleHWidth(int oldPixels, Screen screen){
        int currentResolution = screen.getWidth();
        int baseResolution = screen.BASE_WIDTH_REVOLUTION;

        float percentualBaseResolution = ((oldPixels*100)/baseResolution);

        int newPixels = (int)((currentResolution*percentualBaseResolution)/100);

        return newPixels;
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
