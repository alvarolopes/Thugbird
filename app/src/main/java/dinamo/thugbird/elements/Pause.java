package dinamo.thugbird.elements;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
                            ((MainActivity)context).findViewById(R.id.txtPaused).setVisibility(View.INVISIBLE);
                            game.play();
                        }
                        else{
                            ((MainActivity)context).findViewById(R.id.txtPaused).setVisibility(View.VISIBLE);
                            game.pause();
                        }
                    }
                });
            }
        });

    }

}
