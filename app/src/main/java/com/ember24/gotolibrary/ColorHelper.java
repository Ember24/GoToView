package com.ember24.gotolibrary;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Kam on 3/5/2017.
 */

public abstract class ColorHelper {

    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
