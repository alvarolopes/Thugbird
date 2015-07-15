package dinamo.thugbird.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import dinamo.thugbird.grafics.Colors;
import dinamo.thugbird.grafics.Screen;

public class GameOver extends Element{

    private static final String gameOver = "BUSTED";
    private static final Paint RED = Colors.getGameOverColor();
    private static final Rect TEXT_RECT = Colors.getTextRect(gameOver,RED);
    public final TryAgain tryAgain;

    public GameOver(Screen screen) {
        super(screen.getHeight()/2,screen.getWidth()/2 - (TEXT_RECT.right - TEXT_RECT.left)/2,TEXT_RECT.width(),TEXT_RECT.height());

        tryAgain = new TryAgain(screen);
    }

    public void drawAt(Canvas canvas) {
        canvas.drawText(gameOver, left, top, RED);
        tryAgain.drawAt(canvas);
    }
}
