package dinamo.thugbird;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import dinamo.thugbird.engine.Game;

public class MainActivity extends Activity {

    private Game game;
    private FrameLayout container;
    private Thread thread;
    private RelativeLayout mainPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);
        mainPanel = (RelativeLayout) findViewById(R.id.mainPanel);

        this.game = new Game(this);
        container.addView(this.game);

        this.game.play();
        thread = new Thread(this.game);
        thread.start();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/RosewoodStd-Regular.otf");
        TextView score = (TextView) findViewById(R.id.txtScore);
        TextView highScore = (TextView) findViewById(R.id.txtHighScore);
        TextView busted = (TextView) findViewById(R.id.txtGameOver);
        TextView paused = (TextView) findViewById(R.id.txtPaused);

        score.setTypeface(font);
        highScore.setTypeface(font);
        busted.setTypeface(font);
        paused.setTypeface(font);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.game.isPaused){
            this.game.play();
            thread.interrupt();
            thread = new Thread(this.game);
            thread.start();
        }
    }

    public void RestartGame() {
        mainPanel.setBackground(getResources().getDrawable((R.drawable.background)));
        container.removeView(this.game);

        this.game  = null;
        this.game  = new Game(this);
        container.addView(this.game);

        this.game.play();
        thread.interrupt();
        thread = new Thread(this.game);
        thread.start();
        mainPanel.setBackground(null);
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.game.pause();
    }
}