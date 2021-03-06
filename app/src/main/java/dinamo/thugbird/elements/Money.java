package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import dinamo.thugbird.R;
import dinamo.thugbird.grafics.Screen;

class Money extends Element{

    private static final int WIDTH = 60;
    public static final int HEIGHT = 40;
    private final Bitmap skin;
    private final Matrix matrix;

    private boolean get = false;

    public Money(int top, int left, Context context, Screen screen){
        super(top, left, WIDTH,HEIGHT, screen) ;

        matrix = new Matrix();
        skin = SkinFactory.getMoneySkin(context, this);
    }

    public void drawAt(Canvas canvas) {

        matrix.reset();
        matrix.setRotate(top, skin.getWidth() / 2, skin.getHeight() / 2);
        matrix.postTranslate(left, top);

        canvas.drawBitmap(skin, matrix, null);
    }

    public void move() {
        left -= 2;
    }

    public boolean isOutOfScreen() {
        return left + getWidth() < 0;
    }

    public boolean isGet() {
        return get;
    }

    public void Get() {
        this.get = true;
    }

    @Override
    public void reset(int top, int left) {
        super.reset(top, left);
        get = false;
    }
}
