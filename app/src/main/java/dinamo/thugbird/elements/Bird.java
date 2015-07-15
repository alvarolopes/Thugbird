package dinamo.thugbird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import dinamo.thugbird.R;
import dinamo.thugbird.engine.Sound;

public class Bird extends Element{

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int X = 300;
    private static final int Y = 800;

    private final Bitmap skin;
    private final Matrix matrix;

    private final Sound sound;
    private float verticalSpeed = 0;

    public Bird(Context context, Sound sound){
        super(Y, X, WIDTH, HEIGHT);
        this.sound = sound;

        matrix = new Matrix();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        this.skin = Bitmap.createScaledBitmap(bp, WIDTH, HEIGHT, false);
    }

    public void jump(float pressure) {
        verticalSpeed = ((pressure*26)*-1);

        sound.playJump();
    }

    public void move(Canvas canvas)
    {
        float deltaTime = 0.5f;
        float fallingConstant = 1.5f;

        top += verticalSpeed * deltaTime;

        matrix.reset();
        matrix.setRotate(verticalSpeed, skin.getWidth() / 2, skin.getHeight()/2);
        matrix.postTranslate(left, top);

        canvas.drawBitmap(skin, matrix, null);


        verticalSpeed += fallingConstant * deltaTime;
    }
}
