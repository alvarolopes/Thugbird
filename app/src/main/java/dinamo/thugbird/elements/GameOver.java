package dinamo.thugbird.elements;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import dinamo.thugbird.MainActivity;
import dinamo.thugbird.R;

public class GameOver{

    private final Context context;

    public GameOver(final Context context) {
        this.context = context;

        ((MainActivity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)context).findViewById(R.id.btnPause).setEnabled(false);
                ((MainActivity)context).findViewById(R.id.gameOver).setVisibility(View.VISIBLE);

                final ImageButton btnTryAgain = (ImageButton) ((MainActivity)context).findViewById(R.id.btnTryAgain);
                btnTryAgain.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        ((MainActivity)context).findViewById(R.id.btnPause).setEnabled(true);
                        ((MainActivity)context).findViewById(R.id.gameOver).setVisibility(View.INVISIBLE);
                        ((MainActivity)context).RestartGame();
                    }
                });
            }
        });
    }
}
