package com.ember24.gotoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;
import android.widget.FrameLayout;
import com.ember24.gotoview.Adapter.SectionAdapter;
import com.ember24.gotoview.Adapter.SectionItem;
import com.ember24.gotoview.Helper.RecyclerViewPositionHelper;
import com.ember24.gotoview.Helper.SavedState;
import com.ember24.gotoview.Interface.GotoAttributes;
import com.ember24.gotoview.Interface.GotoSection;

import java.util.List;

/**
 * Created by Ikraam on 04/03/2017.
 */
public class GoToView extends FrameLayout implements SectionAdapter.OnItemClickListener,GotoAttributes {

    private RecyclerView rvAttached;
    private RecyclerViewPositionHelper rvHelper;
    private int rvPosition = 0;
    private int gotoPosition = 0;
    private List<SectionItem> sections;
    private RecyclerView gotoRecyclerView;
    private SectionAdapter sectiondAdapter;
    private boolean isInitialized = false;

    private float mRadius;
    private int mTextSize;
    private int mStroke;
    private @ColorInt int mColor;
    private @ColorInt int mTextColor;
    private @ColorInt int mSelectedColor;
    private @ColorInt int mTextSelectedColor;

    public GoToView(Context context) {
        this(context, null);
    }

    public GoToView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoToView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GoToView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (!isInitialized) {
            setClipChildren(false);
            View.inflate(context, R.layout.goto_list, this);

            gotoRecyclerView = (RecyclerView) findViewById(R.id.gotoRecycler);
            gotoRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            isInitialized = true;
        }

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int colorPrimary = typedValue.data;

        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.GotoView, defStyleAttr, 0);
        mRadius = attr.getFloat(R.styleable.GotoView_goto_radius, 50f);
        mStroke = attr.getInteger(R.styleable.GotoView_goto_stroke, 3);
        mColor = attr.getColor(R.styleable.GotoView_goto_color, colorPrimary);
        mSelectedColor = attr.getColor(R.styleable.GotoView_goto_selectedColor, mColor);
        mTextColor = attr.getColor(R.styleable.GotoView_goto_textColor, mColor);
        mTextSelectedColor = attr.getColor(R.styleable.GotoView_goto_textSelectedColor, Color.WHITE);
        mTextSize = context.getResources().getDimensionPixelSize(R.dimen.material_text_body);

        attr.recycle();
    }

    @Override
    public float getRadius() {
        return mRadius;
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
    }

    @Override
    public int getStroke() {
        return mStroke;
    }

    public void setStroke(int stroke) {
        this.mStroke = stroke;
        if (this.sectiondAdapter != null) sectiondAdapter.notifyDataSetChanged();
    }

    @Override
    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
        if (this.sectiondAdapter != null) sectiondAdapter.notifyDataSetChanged();
    }

    @Override
    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        if (this.sectiondAdapter != null) sectiondAdapter.notifyDataSetChanged();
    }

    @Override
    public int getSelectedColor() {
        return mSelectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.mSelectedColor = selectedColor;
        if (this.sectiondAdapter != null) sectiondAdapter.notifyDataSetChanged();
    }

    @Override
    public int getTextSelectedColor() {
        return mTextSelectedColor;
    }

    public void setTextSelectedColor(int textSelectedColor) {
        this.mTextSelectedColor = textSelectedColor;
        if (this.sectiondAdapter != null && this.rvAttached != null) {
            sectiondAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getTextSize() {
        return mTextSize;
    }

    public int getPosition() {
        return gotoPosition;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.rvAttached = recyclerView;
        this.rvHelper = RecyclerViewPositionHelper.createHelper(this.rvAttached);
        this.rvAttached.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        setGotoSectionAdapter();
    }

    public void setGotoSectionAdapter() {
        if (rvAttached == null)
            return;
        RecyclerView.Adapter adapter = rvAttached.getAdapter();
        if (adapter instanceof GotoSection) {
            sections = ((GotoSection)adapter).getSections();
            sectiondAdapter = new SectionAdapter(sections, this);
            sectiondAdapter.setOnItemClickListener(this);
            gotoRecyclerView.setAdapter(sectiondAdapter);
            setRecyclerViewPosition(rvPosition);
        }
    }

    public void refresh() {
        if (rvAttached == null)
            return;
        RecyclerView.Adapter adapter = rvAttached.getAdapter();
        if (adapter instanceof GotoSection) {
            sections = ((GotoSection) adapter).getSections();
            sectiondAdapter.refreshDataChange(sections);
            setRecyclerViewPosition(rvPosition);
            gotoRecyclerView.refreshDrawableState();
        }
    }

    @Override
    public void OnItemClicked(int sectionPosition, int position) {
        this.gotoPosition = position;
        performSelectedSection();
        scrollToSectionPosition(sectionPosition);
    }

    private void setRecyclerViewPosition(int position) {
        if (rvAttached == null)
            return;

        this.rvPosition = position;
        final int itemsCount = (rvAttached.getAdapter()).getItemCount();
        if (itemsCount > 0 && this.rvPosition >= itemsCount)
            this.rvPosition = (itemsCount - 1);
        else if (itemsCount == 0)
            this.rvPosition = 0;

        final String sectionName = ((GotoSection) rvAttached.getAdapter()).getCurrentSection(this.rvPosition);
        setSectionSelected(sectionName);
    }

    private void performSelectedSection() {
        for (SectionItem item : sections) {
            item.setActive(false);
        }

        sections.get(gotoPosition).setActive(true);
        sectiondAdapter.refreshDataChange(sections);
    }

    private void scrollToSectionPosition(int position) {
        if (rvAttached == null || rvAttached.getAdapter() == null) {
            return;
        }

        int count = rvAttached.getAdapter().getItemCount();
        if (position < 0 || position > count) {
            return;
        }

        ((LinearLayoutManager) rvAttached.getLayoutManager()).scrollToPositionWithOffset(position, 0);
    }

    private void setSectionSelected(String sectionName) {
        if (sections == null && (sectionName == null || sectionName.trim().isEmpty())) {
            return;
        }

        for (int i = 0; i < sections.size(); i++) {
            SectionItem sectionItem = sections.get(i);
            if (sectionItem == null || sectionItem.getSection().trim().isEmpty()) {
                continue;
            }

            if (sectionItem.getSection().equalsIgnoreCase(sectionName)) {
                this.gotoPosition = i;
                performSelectedSection();
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