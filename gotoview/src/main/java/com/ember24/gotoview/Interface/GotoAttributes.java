package com.ember24.gotoview.Interface;

import android.support.annotation.ColorInt;

/**
 * Created by minimac on 05/03/2017.
 */

public interface GotoAttributes {
    float getRadius();
    int getStroke();
    @ColorInt int getColor();
    @ColorInt  int getTextColor();
    @ColorInt int getSelectedColor();
    @ColorInt int getSelectedTextColor();
}
