package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dinamo.thugbird.R;

public class MoneySkin {


    private static Bitmap skin;
    private static MoneySkin instance = null;

    protected void MoneySkin(){}

    public static Bitmap getSkin(Context context, Money money) {
        if(instance == null) {
            instance = new MoneySkin();
            Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_money);

            skin = Bitmap.createScaledBitmap(bp, money.getWidth(), money.getHeight(), false);
            return skin;
        }
        return skin;
    }
}
