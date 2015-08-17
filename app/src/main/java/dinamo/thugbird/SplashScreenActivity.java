package dinamo.thugbird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_splash_screen);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/RosewoodStd-Regular.otf");
        TextView highScore = (TextView) findViewById(R.id.txtHighScore);
        highScore.setTypeface(font);

        Button btnPlay = (Button) findViewById(R.id.btnPlayGame);
        Button btnChangeColor = (Button) findViewById(R.id.btnChangeColor);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);

                startActivity(intent);
            }
        });

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BirdChooserActivity.class);

                startActivity(intent);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences settings = getSharedPreferences(getString(R.string.PrefsName), 0);
        int maxScore =  settings.getInt(this.getString(R.string.maxScoreKey),0);
        TextView txtHighScore = (TextView) this.findViewById(R.id.txtHighScore);
        txtHighScore.setText(getString(R.string.highScore) + String.valueOf(maxScore));
    }
}
