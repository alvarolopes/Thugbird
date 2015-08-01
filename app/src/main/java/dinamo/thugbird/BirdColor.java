package dinamo.thugbird;

    import android.content.Context;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.app.Activity;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.Button;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.SeekBar;
    import android.widget.SeekBar.OnSeekBarChangeListener;
    import android.widget.TextView;
    import android.widget.Toast;

    import dinamo.thugbird.grafics.ColorAdjustment;

public class BirdColor extends Activity {

    TextView textSource;
    ImageView imageResult;
    private SeekBar hueBar, satBar;
    TextView hueText, satText, valText;
    Button btnResetHSV;
    Bitmap bitmapMaster;
    private SharedPreferences settings;
    private Context context;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            context = this;
            setContentView(R.layout.activity_bird_color);
            settings = getSharedPreferences(getString(R.string.PrefsName), 0);

            textSource = (TextView) findViewById(R.id.sourceuri);
            imageResult = (ImageView) findViewById(R.id.result);

            hueText = (TextView) findViewById(R.id.texthue);
            satText = (TextView) findViewById(R.id.textsat);
            hueBar = (SeekBar) findViewById(R.id.huebar);
            satBar = (SeekBar) findViewById(R.id.satbar);
            hueBar.setOnSeekBarChangeListener(seekBarChangeListener);
            satBar.setOnSeekBarChangeListener(seekBarChangeListener);
            btnResetHSV = (Button)findViewById(R.id.resethsv);
            btnResetHSV.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    hueBar.setProgress(256);
                    satBar.setProgress(256);

                    loadBitmapHSV();
                }
            });

            bitmapMaster = BitmapFactory.decodeResource(getResources(), R.drawable.game_bird);

            // reset SeekBars
            setHueBar(settings.getFloat(context.getString(R.string.hueKey), 256));
            setSatBar(settings.getFloat(context.getString(R.string.satKey), 256));

            loadBitmapHSV();

            Button btnSalvar = (Button) findViewById(R.id.btnSaveColor);
            btnSalvar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putFloat(getString(R.string.hueKey), getHue());
                    editor.putFloat(getString(R.string.satKey), getSat());
                    editor.commit();
                    Toast.makeText(context,getString(R.string.colorSaved),Toast.LENGTH_LONG).show();
                }
            });
        }

        OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                loadBitmapHSV();
            }

        };

        private void setHueBar(float hue){
            int hueBarVal = (int)((hue*256/360)+256);

            hueBar.setProgress(hueBarVal);
        }

        private void setSatBar(float hue){
            int satBarVal = (int)((hue*256)+256);

            satBar.setProgress(satBarVal);
        }

        private float getHue(){
            int progressHue = hueBar.getProgress() - 256;
            float hue = (float) progressHue * 360 / 256;
            return hue;
        }

        private float getSat(){
            int progressSat = satBar.getProgress() - 256;
            float sat = (float) progressSat / 256;
            return sat;
        }

        private void loadBitmapHSV() {
            if (bitmapMaster != null) {

                float hue = getHue();
                float sat = getSat();

                hueText.setText("Hue: " + String.valueOf(hue));
                satText.setText("Saturation: " + String.valueOf(sat));

                imageResult.setImageBitmap(ColorAdjustment.updateHSV(bitmapMaster, hue, sat));
            }
        }



    }