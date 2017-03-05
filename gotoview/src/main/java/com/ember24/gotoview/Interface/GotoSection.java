package com.ember24.gotoview.Interface;

import com.ember24.gotoview.Adapter.SectionItem;

import java.util.List;

/**
 * Created by Ikraam on 04/03/2017.
 */
public interface GotoSection {
    List<SectionItem> getSections();
    String getCurrentSection(int pos);
}