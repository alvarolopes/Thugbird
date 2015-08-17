package dinamo.thugbird.elements;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import dinamo.thugbird.R;
import dinamo.thugbird.grafics.ColorAdjustment;
import dinamo.thugbird.grafics.Screen;

public class SkinFactory {

    private static Bitmap moneySkin;
    private static Bitmap policeSkin;
    private static Bitmap backgroundSkin;
    private static Bitmap birdSkin;
    private static float lastHueSat;

    public static Bitmap getMoneySkin(Context context, Money money) {
        if(moneySkin == null) {

            moneySkin = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_money);

            moneySkin = Bitmap.createScaledBitmap(moneySkin, money.getWidth(), money.getHeight(), false);

            return moneySkin;
        }
        return moneySkin;
    }

    public static Bitmap getPoliceSkin(Context context, Police police) {
        if(policeSkin == null) {

            policeSkin = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_police);

            policeSkin = Bitmap.createScaledBitmap(policeSkin, police.getWidth(), police.getHeight(), false);

            return policeSkin;
        }
        return policeSkin;
    }

    public static Bitmap getBackgroundSkin(Context context, Screen screen) {
        if(backgroundSkin == null) {

            backgroundSkin = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_background);

            final int maxSize = screen.getHeight();
            int outWidth;
            int outHeight;
            int inWidth = backgroundSkin.getWidth();
            int inHeight = backgroundSkin.getHeight();
            if(inWidth > inHeight){
                outWidth = maxSize;
                outHeight = (inHeight * maxSize) / inWidth;
            } else {
                outHeight = maxSize;
                outWidth = (inWidth * maxSize) / inHeight;
            }

            backgroundSkin = Bitmap.createScaledBitmap(backgroundSkin, outWidth, outHeight, true);

            return backgroundSkin;
        }
        return backgroundSkin;
    }

    public static Bitmap getBirdSkin(Context context, Bird bird, SharedPreferences settings) {
        int thugNUmber = settings.getInt(context.getString(R.string.thugColor),2);

        if(birdSkin == null || thugNUmber != lastHueSat) {

            birdSkin = BitmapFactory.decodeResource(context.getResources(), bird.getThugBirg(thugNUmber));

            birdSkin = Bitmap.createScaledBitmap(birdSkin, bird.getWidth(), bird.getHeight(), false);

            lastHueSat = thugNUmber;

            return birdSkin;
        }

        return birdSkin;
    }

}
