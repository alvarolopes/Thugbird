package dinamo.thugbird.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import dinamo.thugbird.grafics.Colors;
import dinamo.thugbird.grafics.Screen;

public class TryAgain extends Element{

    private static final int GAME_OVER_HEIGHT = 250;
    private static String tryAgain = "Try Again!";
    private static final Paint BLUE = Colors.getTryAgainColor();
    private static final Rect TEXT_RECT = Colors.getTextRect(tryAgain,BLUE);

    public TryAgain(Screen screen) {
        super((screen.getHeight() / 2) + GAME_OVER_HEIGHT,
                ((screen.getWidth()/2) - (TEXT_RECT.right - TEXT_RECT.left)/2),
                TEXT_RECT.width(),
                TEXT_RECT.height());
    }

    public void drawAt(Canvas canvas) {
        canvas.drawText(tryAgain, left, top, BLUE);
    }

}
