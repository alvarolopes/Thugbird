package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import dinamo.thugbird.R;

class Money extends Element{

    private static final int WIDTH = 50;
    public static final int HEIGHT = 30;
    private final Bitmap skin;

    private boolean get = false;

    public Money(int top, int left, Context context){
        super(top, left, WIDTH, HEIGHT);

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.money);
        this.skin = Bitmap.createScaledBitmap(bp, WIDTH, HEIGHT, false);
    }

    public void drawAt(Canvas canvas) {
        canvas.drawBitmap(skin, left, top, null);
    }

    public void move() {
        left -= 2;
    }

    public boolean isOutOfScreen() {
        return left + WIDTH < 0;
    }

    public boolean isGet() {
        return get;
    }

    public void Get() {
        this.get = true;
    }


}