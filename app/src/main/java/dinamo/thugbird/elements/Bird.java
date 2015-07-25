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
    private float verticalSpeed = 0.5f;

    public Bird(Context context, Sound sound, Screen screen){
        super( Element.responsiblePixels(Y,screen),  Element.responsiblePixels(X,screen), WIDTH, HEIGHT, screen);
        this.sound = sound;
        this.screen = screen;

        matrix = new Matrix();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird);
        this.skin = Bitmap.createScaledBitmap(bp, getWidth(), getheight(), false);
    }

    public void jump(float pressure) {

        verticalSpeed = ((screen.getHeight()*(0.8f+pressure))/100)*-1;


        sound.playJump();
    }

    public void move(Canvas canvas)
    {
        float deltaTime = 0.55f;//((screen.getHeight()*(0.03f))/100);
        float fallingConstant = ((screen.getHeight()*(0.088f))/100);

        top += (verticalSpeed * deltaTime);




        matrix.reset();
        matrix.setRotate(verticalSpeed, skin.getWidth() / 2, skin.getHeight()/2);
        matrix.postTranslate(left, top);

        canvas.drawBitmap(skin, matrix, null);


        verticalSpeed += fallingConstant * deltaTime ;
    }
}
