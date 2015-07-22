package dinamo.thugbird.elements;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import dinamo.thugbird.MainActivity;
import dinamo.thugbird.R;
import dinamo.thugbird.engine.Game;

public class Pause {

    public Pause(final Context context, final Game game){

        final Button btnPause = (Button) ((MainActivity)context).findViewById(R.id.btnPause);
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
