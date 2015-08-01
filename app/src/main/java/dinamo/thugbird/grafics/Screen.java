package dinamo.thugbird.grafics;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Screen {

    private final DisplayMetrics metrics;
    public static final int BASE_HEIGHT_REVOLUTION = 1776;
    public static final int BASE_WIDTH_REVOLUTION = 1080;

    private Context context;

    public Screen(Context context){
        this.context = context;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public int getHeight(){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public int getWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
