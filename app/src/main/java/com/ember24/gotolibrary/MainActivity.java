package com.ember24.gotolibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ember24.gotolibrary.adapter.AnimeAdapter;
import com.ember24.gotolibrary.adapter.AnimeData;
import com.ember24.gotoview.Adapter.SectionAdapter;
import com.ember24.gotoview.GoToView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private GoToView goToView;
    private AnimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        goToView = (GoToView) findViewById(R.id.gotoView);

        adapter = new AnimeAdapter(getDataList());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        goToView.setRecyclerView(rv);
    }

    private List<AnimeData> getDataList()
    {
        List<AnimeData> data = new ArrayList<>();

        data.add(new AnimeData("Tokyo Ghoul","Kaneki"));
        data.add(new AnimeData("Tokyo Ghoul","Amon"));
        data.add(new AnimeData("Tokyo Ghoul","Seido"));
        data.add(new AnimeData("Tokyo Ghoul","Mado"));

        data.add(new AnimeData("Dragon Ball","Son Goku"));
        data.add(new AnimeData("Dragon Ball","Vegeta"));
        data.add(new AnimeData("Dragon Ball","Gohan"));
        data.add(new AnimeData("Dragon Ball","Krillin"));
        data.add(new AnimeData("Dragon Ball","Picollo"));

        data.add(new AnimeData("One Piece","Luffy"));
        data.add(new AnimeData("One Piece","Zoro"));
        data.add(new AnimeData("One Piece","Sanji"));
        data.add(new AnimeData("One Piece","Chopper"));
        data.add(new AnimeData("One Piece","Nami"));
        data.add(new AnimeData("One Piece","Usoop"));
        data.add(new AnimeData("One Piece","Brook"));
        data.add(new AnimeData("One Piece","Nico Robin"));
        data.add(new AnimeData("One Piece","Franky"));
        data.add(new AnimeData("One Piece","Jimbei"));
        data.add(new AnimeData("One Piece","Portgaz D. Ace"));

        data.add(new AnimeData("Darker than black","Hei"));

        data.add(new AnimeData("Berserk","Guts"));
        data.add(new AnimeData("Berserk","Casca"));
        data.add(new AnimeData("Berserk","Zodd"));
        data.add(new AnimeData("Berserk","Rickert"));
        data.add(new AnimeData("Berserk","Puck"));
        data.add(new AnimeData("Berserk","Skull Knight"));
        data.add(new AnimeData("Berserk","Farnese de Vandimion"));
        data.add(new AnimeData("Berserk","Federico de Vandimion III"));
        data.add(new AnimeData("Berserk","Isidro"));
        data.add(new AnimeData("Berserk","Isma"));
        data.add(new AnimeData("Berserk","Jill"));
        data.add(new AnimeData("Berserk","Ivalera"));
        data.add(new AnimeData("Berserk","Moonlight Boy"));
        data.add(new AnimeData("Berserk","Pippin"));
        data.add(new AnimeData("Berserk","Schierke"));
        data.add(new AnimeData("Berserk","Serpico"));

        return data;
    }
}
