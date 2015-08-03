package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dinamo.thugbird.R;

public class PoliceSkin {

    private static Bitmap skin;
    private static PoliceSkin instance = null;

    protected void PoliceSkin(){}

    public static Bitmap getSkin(Context context, Police police) {
        if(instance == null) {
            instance = new PoliceSkin();
            Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_police);

            skin = Bitmap.createScaledBitmap(bp, police.getWidth(), police.getHeight(), false);
            return skin;
        }
        return skin;
    }
}
