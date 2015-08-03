package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dinamo.thugbird.R;
import dinamo.thugbird.grafics.Screen;

public class BackgroundSkin {

    private static Bitmap skin;
    private static BackgroundSkin instance = null;

    protected void BackgroundSkin(){}

    public static Bitmap getSkin(Context context, Screen screen) {
        if(instance == null) {
            instance = new BackgroundSkin();

            Bitmap background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background);

            final int maxSize = screen.getHeight();
            int outWidth;
            int outHeight;
            int inWidth = background.getWidth();
            int inHeight = background.getHeight();
            if(inWidth > inHeight){
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            skin = Bitmap.createScaledBitmap(background, outWidth, outHeight, true);

            return skin;
        }
        return skin;
    }
}

