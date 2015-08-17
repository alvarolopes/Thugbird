package dinamo.thugbird.elements;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import dinamo.thugbird.MainActivity;
import dinamo.thugbird.R;
import dinamo.thugbird.engine.Game;

public class Pause {

    public Pause(final Context context, final Game game){

        final ImageButton btnPause = (ImageButton) ((MainActivity)context).findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ((MainActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (game.isPaused){
                            ((MainActivity)context).findViewById(R.id.pause).setVisibility(View.INVISIBLE);
                            game.play();
                        }
                        else{
                            ((MainActivity)context).findViewById(R.id.pause).setVisibility(View.VISIBLE);
                            ((TextView)((MainActivity)context).findViewById(R.id.txtPaused)).setText(((TextView)((MainActivity)context).findViewById(R.id.txtPaused)).getText());
                            game.pause();
                        }

                        final Button btnPlay = (Button) ((MainActivity)context).findViewById(R.id.btnPlay);
                        btnPlay.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                ((MainActivity)context).findViewById(R.id.pause).setVisibility(View.INVISIBLE);
                                game.play();
                            }
                        });
                    }
                });
            }
        });

    }

}
