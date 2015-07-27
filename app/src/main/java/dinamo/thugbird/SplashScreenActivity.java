package dinamo.thugbird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import dinamo.thugbird.R;

public class SplashScreenActivity extends Activity {

    private Context contex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contex = this;
        setContentView(R.layout.activity_splash_screen);

        Button btnPlay = (Button) findViewById(R.id.btnPlayGame);
        Button btnChangeColor = (Button) findViewById(R.id.btnChangeColor);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contex, MainActivity.class);

                startActivity(intent);
            }
        });

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contex, BirdColor.class);

                startActivity(intent);
            }
        });

    }
/*
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
    }*/


}
