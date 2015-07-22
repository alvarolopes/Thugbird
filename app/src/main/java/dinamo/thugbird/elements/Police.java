package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

import dinamo.thugbird.R;
import dinamo.thugbird.grafics.Screen;

public class Police extends Element{

    private static int HEIGHT = 120;
    private static int WIDTH = 240;
    private final Bitmap skin;
    private final int speed;

    public Police(int top, int left, Context context, Screen screen){
        super(top, left, WIDTH, HEIGHT, screen);
        this.speed =  randomSpeed();

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.police);
        this.skin = Bitmap.createScaledBitmap(bp, getWidth(), getheight(), false);
    }

    public void drawAt(Canvas canvas) {
        canvas.drawBitmap(skin, left, top, null);
    }

    private int randomSpeed(){
        Random r = new Random();
        int Low = 3;
        int High = 7;
        return r.nextInt(High-Low) + Low;
    }

    public void move() {
        left -= speed;
    }

    public boolean isOutOfScreen() {
        return left + getWidth() < 0;
    }

}
