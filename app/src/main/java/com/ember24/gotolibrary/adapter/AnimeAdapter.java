package com.ember24.gotolibrary.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ember24.gotolibrary.R;
import com.ember24.gotoview.Adapter.SectionItem;
import com.ember24.gotoview.Interface.GotoSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by minimac on 04/03/2017.
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MovieCharacterViewHolder>
    implements GotoSection{
    private List<AnimeData> dataArray;

    public AnimeAdapter(List<AnimeData> dataSet) {
        this.dataArray = dataSet;
    }

    public void refreshAdapter(List<AnimeData> data) {
        this.dataArray = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (dataArray == null)
            return 0;
        return dataArray.size();
    }

    public List<AnimeData> getItems() {
        return dataArray;
    }

    @Override
    public MovieCharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieCharacterViewHolder holder, int position) {
        holder.bind(dataArray.get(position), position);
    }

    @Override
    public List<SectionItem> getSections()
    {
        List<SectionItem> sections = new ArrayList<>();
        String sectionName = "";
        for (int i = 0; i < dataArray.size(); i++) {
            if(!sectionName.equalsIgnoreCase(dataArray.get(i).movie)) {
                sectionName = dataArray.get(i).movie;
                sections.add(new SectionItem(sectionName, i));
                //Get Random Color for each sections
                //sections.add(new SectionItem(sectionName, i,getRandomColor()));
            }
        }

        return sections;
    }

    @Override
    public String getCurrentSection(int pos) {
        if (pos < 0 || pos >= dataArray.size())
            return null;

        AnimeData data = dataArray.get(pos);
        if (data == null)
            return null;

        return dataArray.get(pos).movie;
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    class MovieCharacterViewHolder extends RecyclerView.ViewHolder {
        TextView txtView;

        MovieCharacterViewHolder(View itemView) {
            super(itemView);
            txtView = (TextView) itemView.findViewById(R.id.txtView);
        }

        void bind(final AnimeData item, final int position) {
            txtView.setText(item.movie + ": " + item.characterName);
        }
    }
}
