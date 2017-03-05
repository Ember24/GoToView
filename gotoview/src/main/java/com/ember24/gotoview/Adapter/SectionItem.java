package com.ember24.gotoview.Adapter;

import android.support.annotation.ColorInt;

/**
 * Created by Ikraam on 04/03/2017.
 */

public class SectionItem {
    private String section;
    private int position;
    private boolean isActive;
    @ColorInt
    private int color;

    public SectionItem(String section, int position)
    {
        this.section = section;
        this.position = position;
        this.isActive = false;
        this.color = -1;
    }

    public SectionItem(String section, int position, @ColorInt int color)
    {
        this.section = section;
        this.position = position;
        this.color = color;
        this.isActive = false;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @ColorInt
    public int getColor() {
        return color;
    }

    public void setColor(@ColorInt int color) {
        this.color = color;
    }
}
