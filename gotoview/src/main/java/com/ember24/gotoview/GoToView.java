package com.ember24.gotoview;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.ember24.gotoview.Adapter.SectionAdapter;
import com.ember24.gotoview.Adapter.SectionItem;
import com.ember24.gotoview.Helper.RecyclerViewPositionHelper;
import com.ember24.gotoview.Helper.SavedState;
import com.ember24.gotoview.Interface.GotoSection;

import java.util.List;

/**
 * Created by Ikraam on 04/03/2017.
 */
public class GoToView extends FrameLayout implements SectionAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewPositionHelper rvHelper;
    private List<SectionItem> sections;
    private RecyclerView gotoRecyclerView;
    private SectionAdapter sectiondAdapter;
    private boolean isInitialized = false;

    public GoToView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialiseView(context);
    }

    public GoToView(final Context context) {
        super(context);
        initialiseView(context);
    }

    public GoToView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initialiseView(context);
    }

    protected void initialiseView(Context context) {
        if (isInitialized) {
            return;
        }

        isInitialized = true;
        setClipChildren(false);
        View.inflate(context, R.layout.goto_list, this);

        gotoRecyclerView = (RecyclerView) findViewById(R.id.gotoRecycler);
        gotoRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void OnItemClicked(int sectionPosition, int position) {
        performSelectedSection(position);
        scrollToSectionPosition(sectionPosition);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.rvHelper = RecyclerViewPositionHelper.createHelper(this.recyclerView);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    return;
                }
                setRecyclerViewPosition(rvHelper.findFirstCompletelyVisibleItemPosition());
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        RecyclerView.Adapter adapter = this.recyclerView.getAdapter();
        if (adapter != null && adapter instanceof GotoSection) {
            setGotoSectionAdapter((GotoSection) adapter);
        }
    }

    public void setGotoSectionAdapter(GotoSection sectionAdapter) {
        List<SectionItem> items = sectionAdapter.getSections();
        if (items == null || items.size() <= 0)
            return;

        sections = items;
        sectiondAdapter = new SectionAdapter(getContext(), sections);
        sectiondAdapter.setOnItemClickListener(this);
        gotoRecyclerView.setAdapter(sectiondAdapter);
        setRecyclerViewPosition(0);
    }

    private void setRecyclerViewPosition(int position) {
        if (recyclerView != null) {
            final String sectionName = ((GotoSection) recyclerView.getAdapter()).getCurrentSection(position);
            setSectionSelected(sectionName);
        }
    }

    private void performSelectedSection(int position) {
        if (position < 0 || position >= sections.size()) {
            return;
        }

        for (SectionItem item : sections) {
            item.setActive(false);
        }

        sections.get(position).setActive(true);
        sectiondAdapter.refreshDataChange(sections);
    }

    private void scrollToSectionPosition(int position) {
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return;
        }

        int count = recyclerView.getAdapter().getItemCount();
        if (position < 0 || position > count) {
            return;
        }

        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
    }

    private void setSectionSelected(String sectionName) {
        if (sectionName == null || sectionName.trim().isEmpty()) {
            return;
        }

        for (int i = 0; i < sections.size(); i++) {
            SectionItem sectionItem = sections.get(i);
            if (sectionItem == null || sectionItem.getSection().trim().isEmpty()) {
                continue;
            }

            if (sectionItem.getSection().trim().equals(sectionName)) {
                performSelectedSection(i);
                gotoRecyclerView.smoothScrollToPosition(i);
                break;
            }
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState =  super.onSaveInstanceState();
        SavedState outState = new SavedState(superState);
        outState.mScrollPosition = rvHelper.findFirstVisibleItemPosition();
        return outState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if(state != null && state instanceof SavedState){
            int position = ((SavedState) state).mScrollPosition;
            setRecyclerViewPosition(position);
        }
    }

}