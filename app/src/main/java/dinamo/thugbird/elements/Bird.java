package dinamo.thugbird.elements;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import dinamo.thugbird.R;
import dinamo.thugbird.engine.Sound;
import dinamo.thugbird.grafics.Screen;

public class Bird extends Element{

    private static final int WIDTH = 119;
    private static final int HEIGHT = 107;
    private static final int X = 200;
    private static final int Y = 200;

    private final Bitmap skin;
    private final Matrix matrix;

    private final Sound sound;
    private final Screen screen;
    private float verticalSpeed = 0.5f;

    public Bird(Context context, Sound sound, Screen screen, SharedPreferences settings){
        super( Element.responsibleHeight(Y, screen),  Element.responsibleHeight(X, screen), WIDTH, HEIGHT, screen);
        this.sound = sound;
        this.screen = screen;

        matrix = new Matrix();

        this.skin = SkinFactory.getBirdSkin(context, this, settings);
    }

    public void jump(float pressure) {

        verticalSpeed = ((screen.getHeight()*(0.8f+pressure))/100)*-1;


        sound.playJump();
    }

    public void move(Canvas canvas)
    {
        float deltaTime = 0.55f;
        float fallingConstant = ((screen.getHeight()*(0.088f))/100);

        top += (verticalSpeed * deltaTime);

        matrix.reset();
        matrix.setRotate(verticalSpeed, skin.getWidth() / 2, skin.getHeight()/2);
        matrix.postTranslate(left, top);

        canvas.drawBitmap(skin, matrix, null);


        verticalSpeed += fallingConstant * deltaTime ;
    }

    public int getThugBirg(int thugNumber){

        switch (thugNumber){
            case 0:
                return R.drawable.thug_zero;
            case 1:
                return R.drawable.thug_one;
            case 2:
                return R.drawable.thug_two;
            case 3:
                return R.drawable.thug_three;
            case 4:
                return R.drawable.thug_four;
            default:
                return R.drawable.thug_two;
        }
    }

    public static String getThugName(int thugNumber) {
        switch (thugNumber){
            case 0:
                return "Red";
            case 1:
                return "Blue";
            case 2:
                return "Brown";
            case 3:
                return "Green";
            case 4:
                return "Yellow";
            default:
                return "Brown";
        }
    }
}
