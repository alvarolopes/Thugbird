package dinamo.thugbird.engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import dinamo.thugbird.R;


public class Sound {

    private final SoundPool soundPool;
    private static int JUMP;
    private static int SCORE ;
    private static int BUMP ;

    private Sound(Context context) {
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        JUMP = soundPool.load(context, R.raw.jump, 0);
        SCORE = soundPool.load(context, R.raw.score, 0);
        BUMP = soundPool.load(context, R.raw.bump, 0);
    }

    private void play(int sound){
        soundPool.play(sound, 5, 5, 1, 0, 1);
    }

    public void playJump() {
        play(JUMP);
    }

    public void playScore() {
        play(SCORE);
    }

    public void playBump() {
        play(BUMP);
    }

    private static Sound instance = null;

    protected void Sound(){}

    public static Sound getInstance(Context context) {
        if(instance == null) {
            instance = new Sound(context);

            return instance;
        }
        return instance;
    }


}


