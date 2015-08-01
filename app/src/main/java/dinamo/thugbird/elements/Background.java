package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import dinamo.thugbird.R;
import dinamo.thugbird.grafics.Screen;

class Background {

    private Bitmap background;
    private int position;

    public Background(int position, Screen screen, Context context){
        this.position = position;

        this.background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background);

        final int maxSize = screen.getHeight();
        int outWidth;
        int outHeight;
        int inWidth = this.background.getWidth();
        int inHeight = this.background.getHeight();
        if(inWidth > inHeight){
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        this.background = Bitmap.createScaledBitmap(this.background, outWidth, outHeight, true);
    }

    public void drawAt(Canvas canvas){
        canvas.drawBitmap(background, position, 0, null);
    }

    public void move() {
        position -=1;
    }

    public boolean isOutOfScreen() {
        return Math.abs(this.position) > this.background.getWidth();
    }

    public int getRight() {
        return position + this.background.getWidth();
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
