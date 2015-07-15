package dinamo.thugbird.grafics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Colors {

    public static Paint getScoreColor() {
        Paint white = new Paint();
        white.setTextSize(80);
        white.setColor(0xFFFFFFFF);
        white.setTypeface(Typeface.DEFAULT_BOLD);
        white.setShadowLayer(3, 5, 5, 0xFF000000);

        return white;
    }

    public static Paint getGameOverColor() {
        Paint red = new Paint();
        red.setColor(0xFFFF0000);
        red.setTextSize(150);
        red.setTypeface(Typeface.DEFAULT_BOLD);
        red.setShadowLayer(2, 3, 3, 0xFF000000);
        return red;
    }

    public static Paint getTryAgainColor() {
        Paint blue = new Paint();
        blue.setColor(0xFF0000EE);
        blue.setTextSize(80);
        blue.setTypeface(Typeface.DEFAULT_BOLD);
        blue.setShadowLayer(2, 3, 3, 0xFF000000);

        return blue;
    }

    public static Paint getPauseColor() {
        Paint white = new Paint();
        white.setTextSize(80);
        white.setColor(0xFFFFFFFF);
        white.setTypeface(Typeface.DEFAULT_BOLD);
        white.setShadowLayer(3, 5, 5, 0xFF000000);

        return white;
    }

    public static Rect getTextRect(String text, Paint color){
        Rect textRect = new Rect();
        color.getTextBounds(text, 0, text.length(), textRect);
        return textRect;
    }
}
