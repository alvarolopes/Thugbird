package dinamo.thugbird.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import dinamo.thugbird.grafics.Colors;
import dinamo.thugbird.grafics.Screen;

public class Pause extends Element {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 150;
    private static final int X = -150;
    private static final int Y = 0;
    private final String pause = "||";
    private final String paused = "PAUSED";
    private static final Paint WHITE = Colors.getPauseColor();
    private final Rect TEXT_RECT_PAUSE = Colors.getTextRect(pause, WHITE);
    private final Rect TEXT_RECT_PAUSED = Colors.getTextRect(paused,WHITE);
    private final Screen screen;

    public Pause(Screen screen){
        super(Y, screen.getWidth() + X, WIDTH, HEIGHT);
        this.screen = screen;
    }

    public void drawAt(Canvas canvas){
        canvas.drawText(String.valueOf(pause), screen.getWidth() + X+((TEXT_RECT_PAUSE.right - TEXT_RECT_PAUSE.left)/2), 100, WHITE);
    }

    public void drawPausedAt(Canvas canvas) {
        canvas.drawText(String.valueOf(paused), screen.getWidth() / 2 - (TEXT_RECT_PAUSED.right - TEXT_RECT_PAUSED.left) / 2, screen.getHeight()/2, WHITE);
    }
}
