package com.ember24.gotoview.Adapter;

import android.support.annotation.ColorInt;

/**
 * Created by Ikraam on 04/03/2017.
 */

public class SectionItem {
    public static final int NO_COLOR_OVERRIDE = -1;
    private String section;
    private int position;
    private boolean isActive;

    @ColorInt private int color;
    @ColorInt private int selectedColor;
    @ColorInt private int textColor;
    @ColorInt private int textSelectedColor;

    public SectionItem(String section, int position)
    {
        this.section = section;
        this.position = position;
        this.isActive = false;
        this.color = NO_COLOR_OVERRIDE;
        this.selectedColor = NO_COLOR_OVERRIDE;
        this.textColor = NO_COLOR_OVERRIDE;
        this.textSelectedColor = NO_COLOR_OVERRIDE;
    }

    public SectionItem(String section, int position,
                       @ColorInt int color,@ColorInt int selectedColor,
                       @ColorInt int textColor,@ColorInt int textSelectedColor)
    {
        this.section = section;
        this.position = position;
        this.color = color;
        this.selectedColor = selectedColor;
        this.textColor = textColor;
        this.textSelectedColor = textSelectedColor;
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

    @ColorInt
    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(@ColorInt int selectedColor) {
        this.selectedColor = selectedColor;
    }

    @ColorInt
    public int getTextSelectedColor() {
        return textSelectedColor;
    }

    public void setTextSelectedColor(@ColorInt int textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
    }

    @ColorInt
    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(@ColorInt int textColor) {
        this.textColor = textColor;
    }
}
