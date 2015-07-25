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
    private final float speed;
    private Screen screen;

    public Police(int top, int left, Context context, Screen screen){
        super(top, left, WIDTH, HEIGHT, screen);
        this.screen = screen;
        this.speed =  randomSpeed();

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.police);
        this.skin = Bitmap.createScaledBitmap(bp, getWidth(), getheight(), false);
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
