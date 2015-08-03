package dinamo.thugbird.elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dinamo.thugbird.R;
import dinamo.thugbird.grafics.ColorAdjustment;

public class BirdSkin {

    private static Bitmap skin;
    private static BirdSkin instance = null;

    protected void BirdSkin(){}

    public static Bitmap getSkin(Context context, Bird bird, SharedPreferences settings) {
        if(instance == null) {
            instance = new BirdSkin();

            Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_bird);

            float sat = settings.getFloat(context.getString(R.string.satKey), 256);
            float hue = settings.getFloat(context.getString(R.string.hueKey), 256);
            if (!(sat == 256 && hue == 256))
                bp = ColorAdjustment.updateHSV(bp, hue, sat);

            skin = Bitmap.createScaledBitmap(bp, bird.getWidth(), bird.getHeight(), false);

            return skin;
        }
        return skin;
    }

}
