package com.ember24.gotoview.Helper;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.Log;

/**
 * Created by Kam on 3/10/2017.
 */

public class ColorHelper {
    public static Boolean isColorDark(int color) {
        Double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return (darkness > 0.3);
    }
}
