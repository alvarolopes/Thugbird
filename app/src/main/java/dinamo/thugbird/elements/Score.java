package dinamo.thugbird.elements;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;

import dinamo.thugbird.engine.Sound;
import dinamo.thugbird.grafics.Colors;
import dinamo.thugbird.grafics.Screen;

public class Score {

    private static final Paint WHITE = Colors.getScoreColor();
    private static final Paint WHITE1 = Colors.getHighScoreColor();
    private int score = 0;
    private final Sound sound;
    private SharedPreferences settings;
    private Screen screen;
    private int maxScore;


    public Score(Sound sound, SharedPreferences settings, Screen screen){
        this.sound = sound;
        this.settings = settings;
        this.screen = screen;
        this.maxScore =  settings.getInt("maxScore",0);
    }

    public void drawAt(Canvas canvas) {
        canvas.drawText("$"+String.valueOf(score), 100, 100, WHITE);
        canvas.drawText("High score: $"+String.valueOf(maxScore), 100, screen.getHeight()-50, WHITE1);
    }

    public void add() {
        sound.playScore();
        score++;
    }

    public void endGame(){
        if (score > maxScore) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("maxScore", score);
            editor.commit();
        }
    }
}
