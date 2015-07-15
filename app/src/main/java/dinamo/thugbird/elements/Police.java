package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

import dinamo.thugbird.R;

public class Police extends Element{

    public static final int HEIGHT = 120;
    public static final int WIDTH = 240;
    private final Bitmap skin;
    private final int speed;


    public Police(int top, int left, Context context){
        super(top, left, WIDTH, HEIGHT);
        this.speed =  randomSpeed();

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.police);
        this.skin = Bitmap.createScaledBitmap(bp, WIDTH, HEIGHT, false);
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
        return left + WIDTH < 0;
    }

}
