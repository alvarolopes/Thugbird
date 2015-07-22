package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import dinamo.thugbird.R;
import dinamo.thugbird.engine.Sound;
import dinamo.thugbird.grafics.Screen;

public class Bird extends Element{

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int X = 200;
    private static final int Y = 200;


    private final Bitmap skin;
    private final Matrix matrix;

    private final Sound sound;
    private Screen screen;
    private float verticalSpeed = 0;

    public Bird(Context context, Sound sound, Screen screen){
        super(Y, X, WIDTH, HEIGHT, screen);
        this.sound = sound;
        this.screen = screen;

        matrix = new Matrix();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        this.skin = Bitmap.createScaledBitmap(bp, getWidth(), getheight(), false);
    }

    public void jump(float pressure) {
        verticalSpeed = ((pressure*26*(Screen.BASE_RESOUTION/screen.getHeight()))*-1);

        sound.playJump();
    }

    public void move(Canvas canvas)
    {
        float deltaTime = 0.5f;
        float fallingConstant = 1.5f;

        top += (verticalSpeed * deltaTime) / (Screen.BASE_RESOUTION/screen.getHeight());

        matrix.reset();
        matrix.setRotate(verticalSpeed, skin.getWidth() / 2, skin.getHeight()/2);
        matrix.postTranslate(left, top);

        canvas.drawBitmap(skin, matrix, null);


        verticalSpeed += fallingConstant * deltaTime ;
    }
}
