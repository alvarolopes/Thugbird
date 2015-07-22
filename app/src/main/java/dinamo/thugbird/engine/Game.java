package dinamo.thugbird.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import dinamo.thugbird.MainActivity;
import dinamo.thugbird.R;
import dinamo.thugbird.elements.Bird;
import dinamo.thugbird.elements.Elements;
import dinamo.thugbird.elements.GameOver;
import dinamo.thugbird.elements.Pause;
import dinamo.thugbird.elements.Score;
import dinamo.thugbird.grafics.Screen;

public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private static final String PREFS_NAME = "ThugBirdScore";
    private boolean isRunning = false;
    public boolean isPaused = false;

    private final Screen screen;
    private final SurfaceHolder holder = this.getHolder();
    private Bird bird;

    private Elements elements;
    private Score score;
    private final Context context;
    private final Sound sound;

    private SharedPreferences settings;

    public Game(Context context) {
        super(context);
        this.context = context;
        setOnTouchListener(this);

        screen = new Screen(context);
        sound = new Sound(context);

        settings = context.getSharedPreferences(PREFS_NAME, 0);
        initializeElements();
    }

    @Override
    public void run() {
        Canvas canvas;
        while (isRunning){
            if(!holder.getSurface().isValid()) continue;

            canvas = holder.lockCanvas();

            this.elements.drawAt(canvas);
            this.elements.move();

            this.bird.move(canvas);

            if(this.elements.hasBumpWith(this.bird)) {
                this.stop();
            }

            if(this.elements.gotPrize(bird)) {
                score.add();
            }

            if (this.elements.isOutOfTheRoad(this.bird)){
                this.stop();
            }

            holder.unlockCanvasAndPost(canvas);
        }

        if (isPaused){
            if(!holder.getSurface().isValid()) return;
            canvas = holder.lockCanvas();

             holder.unlockCanvasAndPost(canvas);

            //noinspection StatementWithEmptyBody this is while game is paused. so it will not take forever.
            while (isPaused) {}
        }

        if (isRunning) run();
    }

    public void play(){
        isRunning = true;
        isPaused = false;
    }

    public void pause() {
        isRunning = false;
        isPaused = true;
    }

    private void stop(){
        isRunning = false;
        isPaused = false;

        this.score.endGame();
        sound.playBump();
        new GameOver(context);
    }

    private void initializeElements() {
        ((MainActivity)context).findViewById(R.id.mainPanel).setVisibility(VISIBLE);

        new Pause(context,this);
        this.score = new Score(sound, settings,context);
        this.bird = new Bird(context, sound, screen);
        this.elements = new Elements(screen, context);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.bird.jump((event.getSize()+event.getPressure())/2);

        return false;
    }
}