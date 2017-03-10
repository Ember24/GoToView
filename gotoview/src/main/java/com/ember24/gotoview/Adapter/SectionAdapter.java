package com.ember24.gotoview.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ember24.gotoview.Interface.GotoAttributes;
import com.ember24.gotoview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ikraam on 04/03/2017.
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {
    private List<SectionItem> sections;
    private OnItemClickListener listener;
    private GotoAttributes attr;

    public SectionAdapter(List<SectionItem> dataSet,GotoAttributes attr) {
        this.sections =  dataSet == null ? new ArrayList<SectionItem>() : dataSet;
        this.attr = attr;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refreshDataChange(List<SectionItem> dataSet) {
        if (dataSet == null)
            sections.clear();
        else
            sections = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (sections == null)
            return 0;
        return sections.size();
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.goto_text, parent, false));
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.bind(sections.get(position), position);
    }

    public interface OnItemClickListener {
        void OnItemClicked(int alphabetPosition, int position);
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSection;

        SectionViewHolder(View itemView) {
            super(itemView);
            txtSection = (TextView) itemView.findViewById(R.id.section_name);
        }

        void bind(final SectionItem item, final int position) {
            if (item == null || item.getSection() == null)
                return;

            int color = item.getColor() != SectionItem.NO_COLOR_OVERRIDE ? item.getColor() : attr.getColor();
            int selectedColor = item.getSelectedColor() != SectionItem.NO_COLOR_OVERRIDE ? item.getSelectedColor() : attr.getSelectedColor();
            int textColor = item.getTextColor() != SectionItem.NO_COLOR_OVERRIDE ? item.getTextColor() : attr.getTextColor();
            int textSelectedColor = item.getTextSelectedColor() != SectionItem.NO_COLOR_OVERRIDE ? item.getTextSelectedColor() : attr.getTextSelectedColor();

            GradientDrawable bgShape = (GradientDrawable) txtSection.getBackground();
            if (attr.getFillColor())
                bgShape.setColor(item.isActive() ? selectedColor : color);
            else
                bgShape.setColor(item.isActive() ? selectedColor : Color.TRANSPARENT);

            bgShape.setCornerRadius(attr.getRadius());
            bgShape.setStroke(attr.getStroke(), item.isActive() ? selectedColor : color);
            txtSection.setTextColor(item.isActive() ? textSelectedColor : textColor);
            txtSection.setTextSize(TypedValue.COMPLEX_UNIT_PX, attr.getTextSize() + (item.isActive() ? 10 : 0));

            txtSection.setText(item.getSection());
            txtSection.setTypeface(null, item.isActive() ? Typeface.BOLD : Typeface.NORMAL);
            txtSection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.OnItemClicked(item.getPosition(), position);
                    }
                }
            });
        }
    }
}
