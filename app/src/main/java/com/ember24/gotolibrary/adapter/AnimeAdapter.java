package com.ember24.gotolibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ember24.gotolibrary.R;
import com.ember24.gotoview.Adapter.SectionItem;
import com.ember24.gotoview.Interface.GotoSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minimac on 04/03/2017.
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MovieCharacterViewHolder>
    implements GotoSection{
    private List<AnimeData> mDataArray;

    public AnimeAdapter(List<AnimeData> dataSet) {
        this.mDataArray = dataSet;
    }

    @Override
    public int getItemCount() {
        if (mDataArray == null)
            return 0;
        return mDataArray.size();
    }

    @Override
    public MovieCharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieCharacterViewHolder holder, int position) {
        holder.bind(mDataArray.get(position), position);
    }

    @Override
    public List<SectionItem> getSections()
    {
        List<SectionItem> sections = new ArrayList<>();
        String sectionName = "";
        for (int i = 0; i < mDataArray.size(); i++) {
            if(sectionName != (mDataArray.get(i).movie)) {
                sectionName = mDataArray.get(i).movie;
                sections.add(new SectionItem(sectionName, i, false));
            }
        }

        return sections;
    }

    @Override
    public String getCurrentSection(int pos) {
        if (pos < 0 || pos >= mDataArray.size())
            return null;

        AnimeData data = mDataArray.get(pos);
        if (data == null)
            return null;

        return mDataArray.get(pos).movie;
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
