package dinamo.thugbird.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import dinamo.thugbird.engine.Sound;
import dinamo.thugbird.grafics.Colors;

public class Score {

    private static final Paint WHITE = Colors.getScoreColor();
    private int score = 0;
    private final Sound sound;

    public Score(Sound sound){
        this.sound = sound;
    }

    public void drawAt(Canvas canvas) {
        canvas.drawText(String.valueOf(score), 100, 100, WHITE);
    }

    public void add() {
        sound.playScore();
        score++;
    }
}
