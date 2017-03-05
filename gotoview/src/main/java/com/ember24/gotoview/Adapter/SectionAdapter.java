package com.ember24.gotoview.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ember24.gotoview.R;

import java.util.List;

/**
 * Created by Ikraam on 04/03/2017.
 */
public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {
    private List<SectionItem> mDataArray;
    private OnItemClickListener listener;

    public SectionAdapter(Context context, List<SectionItem> dataSet) {
        this.mDataArray = dataSet;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void refreshDataChange(List<SectionItem> newDataSet) {
        this.mDataArray = newDataSet;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDataArray == null)
            return 0;
        return mDataArray.size();
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.goto_text, parent, false));
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.bind(mDataArray.get(position), position);
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
            if (item.getColor() == -1) {
                bgShape.setColor(item.isActive() ? Color.RED : Color.TRANSPARENT);
                bgShape.setStroke(3, Color.RED);
                txtSection.setTextColor(item.isActive() ? Color.WHITE : Color.RED);
            } else {
                bgShape.setColor(item.isActive() ? item.getColor() : Color.TRANSPARENT);
                bgShape.setStroke(3, item.getColor());
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
