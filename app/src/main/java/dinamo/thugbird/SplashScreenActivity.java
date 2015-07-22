package dinamo.thugbird;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dinamo.thugbird.R;

public class SplashScreenActivity extends Activity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        ImageView v= (ImageView) findViewById(R.id.splashScreenImage);
        v.setOnTouchListener(this);

        blink();
    }

    private void blink() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView txt = (TextView) findViewById(R.id.txtTouchToStart);
                        if (txt.getVisibility() == View.VISIBLE) {
                            txt.setVisibility(View.INVISIBLE);
                        } else {
                            txt.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

        return false;
    }

}
