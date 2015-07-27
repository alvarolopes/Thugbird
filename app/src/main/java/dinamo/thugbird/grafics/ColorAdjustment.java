package dinamo.thugbird.grafics;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ColorAdjustment {
    public static Bitmap updateHSV(Bitmap src, float settingHue, float settingSat) {

        int w = src.getWidth();
        int h = src.getHeight();
        int[] mapSrcColor = new int[w * h];
        int[] mapDestColor = new int[w * h];

        float[] pixelHSV = new float[3];

        src.getPixels(mapSrcColor, 0, w, 0, 0, w, h);

        int index = 0;
        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                if (mapSrcColor[index] == 0) {
                    index++;
                    continue;
                }
                // Convert from Color to HSV
                Color.colorToHSV(mapSrcColor[index], pixelHSV);

                // Adjust HSV
                pixelHSV[0] = pixelHSV[0] + settingHue;
                if (pixelHSV[0] < 0.0f) {
                    pixelHSV[0] = 0.0f;
                } else if (pixelHSV[0] > 360.0f) {
                    pixelHSV[0] = 360.0f;
                }

                pixelHSV[1] = pixelHSV[1] + settingSat;
                if (pixelHSV[1] < 0.0f) {
                    pixelHSV[1] = 0.0f;
                } else if (pixelHSV[1] > 1.0f) {
                    pixelHSV[1] = 1.0f;
                }

                // Convert back from HSV to Color
                mapDestColor[index] = Color.HSVToColor(pixelHSV);

                index++;
            }
        }

        return Bitmap.createBitmap(mapDestColor, w, h, Bitmap.Config.ARGB_8888);

    }
}
