package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;
import dinamo.thugbird.grafics.Screen;

class Police extends Element{

    private final float speed;
    private final Screen screen;
    private final Bitmap skin;

    public Police(int top, int left,Context context, Screen screen){

        super(top, left, 240, 120, screen);
        this.screen = screen;
        this.speed =  randomSpeed();
        skin = SkinFactory.getPoliceSkin(context, this);
    }

    public void drawAt(Canvas canvas) {
        canvas.drawBitmap(skin, left, top, null);
    }

    private float randomSpeed(){
        float minX = ((screen.getWidth()*(0.5f))/100);
        float maxX = ((screen.getWidth()*(1f))/100);

        Random rand = new Random();

        return rand.nextFloat() * (maxX - minX) + minX;
    }

    public void move() {
        left -= speed;
    }

    public boolean isOutOfScreen() {
        return left + getWidth() < 0;
    }


}
