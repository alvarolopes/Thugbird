package dinamo.thugbird.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import dinamo.thugbird.MainActivity;
import dinamo.thugbird.elements.Bird;
import dinamo.thugbird.elements.Elements;
import dinamo.thugbird.elements.GameOver;
import dinamo.thugbird.elements.Pause;
import dinamo.thugbird.elements.Score;
import dinamo.thugbird.elements.SplashScreen;
import dinamo.thugbird.grafics.Screen;

public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private static final String PREFS_NAME = "ThugBirdScore";
    private boolean isStarting = false;
    private boolean isRunning = false;
    public boolean isPaused = false;
    private boolean isStopped = false;

    private final Screen screen;
    private final SurfaceHolder holder = this.getHolder();
    private Bird bird;

    private Elements elements;
    private Score score;
    private final Context context;
    private final Sound sound;
    private SplashScreen splashScreen;
    private GameOver gameOver;

    private Toast toast;
    private Pause pause;
    private Canvas canvas;
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
        while (isStarting){
            if(!holder.getSurface().isValid()) continue;
            canvas = holder.lockCanvas();

            this.splashScreen.drawAt(canvas);
            if (!toast.getView().isShown())
                toast.show();

            holder.unlockCanvasAndPost(canvas);
        }

        while (isRunning){
            if(!holder.getSurface().isValid()) continue;
            canvas = holder.lockCanvas();

            this.elements.drawAt(canvas);
            this.elements.move();

            this.bird.move(canvas);

            this.score.drawAt(canvas);

            if(this.elements.hasBumpWith(this.bird)) {
                this.stop();
            }

            if(this.elements.gotPrize(bird)) {
                score.add();
            }

            if (this.elements.isOutOfTheRoad(this.bird)){
                this.stop();
            }

            this.pause.drawAt(canvas);

            holder.unlockCanvasAndPost(canvas);
        }

        if (isPaused){
            if(!holder.getSurface().isValid()) return;
            canvas = holder.lockCanvas();

            this.pause.drawPausedAt(canvas);

             holder.unlockCanvasAndPost(canvas);

            //noinspection StatementWithEmptyBody this is while game is paused. so it will not take forever.
            while (isPaused) {}
        }



        if (isRunning) run();
    }

    public void start(){
        isStarting = true;
        isRunning = false;
        isPaused = false;
        isStopped = false;

        toast = Toast.makeText(context,"TOUCH TO START!!",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 350);
    }

    public void play(){
        isStarting = false;
        isRunning = true;
        isPaused = false;
        isStopped = false;

        if (toast!= null) toast.cancel();
    }

    public void pause() {
        isStarting = false;
        isRunning = false;
        isPaused = true;
        isStopped = false;
    }

    private void stop(){
        isStarting = false;
        isRunning = false;
        isPaused = false;
        isStopped = true;

        this.score.endGame();
        sound.playBump();
        this.gameOver.drawAt(canvas);
    }

    private void tryAgain() {
        MainActivity activity = (MainActivity) context;
        activity.RestartGame();
    }

    private void initializeElements() {
        this.splashScreen = new SplashScreen(0,0,screen.getWidth(),screen.getHeight(),context);
        this.pause = new Pause(screen);
        this.score = new Score(sound, settings,screen);
        this.bird = new Bird(context, sound);
        this.elements = new Elements(screen, context);
        this.gameOver = new GameOver(screen);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isRunning)
            this.bird.jump(event.getPressure());

        if (this.splashScreen.getClick(event.getY(),event.getX())) {
            if (isStarting) play();
        }

        if (this.pause.getClick(event.getY(),event.getX())) {
            if (isRunning) pause();
            else if (isPaused) play();
        }

        if (this.gameOver.tryAgain.getClick(event.getY(), event.getX())) {
            if (isStopped) tryAgain();
        }

        return false;
    }
}