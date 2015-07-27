package dinamo.thugbird.elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import dinamo.thugbird.MainActivity;
import dinamo.thugbird.R;
import dinamo.thugbird.engine.Sound;

public class Score{

    private final TextView txtScore;
    private int score = 0;
    private final Sound sound;
    private SharedPreferences settings;
    private int maxScore;
    private Context context;


    public Score(Sound sound, SharedPreferences settings, Context context){
        this.context = context;
        txtScore = (TextView) ((MainActivity) context).findViewById(R.id.txtScore);
        TextView txtHighScore = (TextView) ((MainActivity) context).findViewById(R.id.txtHighScore);

        this.sound = sound;
        this.settings = settings;
        this.maxScore =  settings.getInt(context.getString(R.string.maxScoreKey),0);
        txtHighScore.setText(context.getString(R.string.highScore) +String.valueOf(this.maxScore));
        txtScore.setText(context.getString(R.string.score)+String.valueOf(this.score));
    }

    public void add() {
        sound.playScore();
        score++;
        ((MainActivity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtScore.setText(context.getString(R.string.score) + String.valueOf(score));
            }
        });
    }

    public void endGame(){
        if (score > maxScore) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(context.getString(R.string.maxScoreKey), score);
            editor.commit();
        }
    }
}
