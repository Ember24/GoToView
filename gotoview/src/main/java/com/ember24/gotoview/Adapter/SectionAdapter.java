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

            GradientDrawable bgShape = (GradientDrawable) txtSection.getBackground();
            bgShape.setCornerRadius(attr.getRadius());
            if (item.getColor() == -1) {
                bgShape.setColor(item.isActive() ? attr.getColor() : Color.TRANSPARENT);
                bgShape.setStroke(attr.getStroke(), attr.getColor());
                txtSection.setTextColor(item.isActive() ? Color.WHITE : attr.getTextColor());
            } else {
                bgShape.setColor(item.isActive() ? item.getColor() : Color.TRANSPARENT);
                bgShape.setStroke(attr.getStroke(), item.getColor());
                txtSection.setTextColor(item.isActive() ? Color.WHITE : item.getColor());
            }

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
