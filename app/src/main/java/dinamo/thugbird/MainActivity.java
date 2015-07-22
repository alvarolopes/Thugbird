package dinamo.thugbird;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import dinamo.thugbird.engine.Game;

public class MainActivity extends Activity {

    private Game game;
    private FrameLayout container;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);

        this.game = new Game(this);
        container.addView(this.game);

        this.game.play();
        thread = new Thread(this.game);
        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (this.game.isPaused){
            this.game.play();
            thread.interrupt();
            new Thread(this.game).start();
        }
    }

    public void RestartGame() {
        container.removeView(this.game);

        this.game  = new Game(this);
        container.addView(this.game);

        this.game.play();
        new Thread(this.game).start();

    }

    @Override
    protected void onPause() {
        super.onPause();

        this.game.pause();
    }
}