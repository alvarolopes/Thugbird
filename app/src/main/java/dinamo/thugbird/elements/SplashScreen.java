package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import dinamo.thugbird.R;
import dinamo.thugbird.grafics.Screen;

public class SplashScreen extends Element{
    private final Bitmap splashScreen;

    public SplashScreen(int top, int left, int width, int height, Context context, Screen screen){
        super(top,left,width,height,screen);

        Bitmap backSplash = BitmapFactory.decodeResource(context.getResources(), R.drawable.thugbird);
        this.splashScreen = Bitmap.createScaledBitmap(backSplash, width, height, false);
    }

    public void drawAt(Canvas canvas) {
        canvas.drawBitmap(splashScreen, this.left, this.top, null);
    }
}
