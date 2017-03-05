package com.ember24.gotolibrary;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ember24.gotolibrary.adapter.AnimeAdapter;
import com.ember24.gotolibrary.adapter.AnimeData;
import com.ember24.gotoview.Adapter.SectionAdapter;
import com.ember24.gotoview.Adapter.SectionItem;
import com.ember24.gotoview.GoToView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private RecyclerView rv;
    private GoToView goToView;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabRemove;
    private AnimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
        goToView = (GoToView) findViewById(R.id.gotoView);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabRemove = (FloatingActionButton) findViewById(R.id.fabRemove);

        adapter = new AnimeAdapter(getDataList());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        goToView.setRecyclerView(rv);

        fabAdd.setOnClickListener(this);
        fabRemove.setOnClickListener(this);
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

    private List<AnimeData> addData() {
        List<AnimeData> data = null;
        int sectionsCount = 0;
        if (adapter != null) {
            data = adapter.getItems();
            sectionsCount = adapter.getSections().size();
        }

        if (data == null)
            data = new ArrayList<>();

        final int itemsToAddCount = 18;
        for (int i = 0; i < itemsToAddCount; i++) {
            data.add(new AnimeData("Anime " + sectionsCount, "Character " + i));
        }

        return data;
    }

    private List<AnimeData> removeSection(boolean removeLastSection) {
        List<AnimeData> data = null;
        if (adapter != null) {
            data = adapter.getItems();
        }

        if (data == null)
            data = new ArrayList<>();

        List<AnimeData> copyData = new ArrayList<>(data);
        int size = data.size();
        if (size > 0) {
            List<SectionItem> sections = adapter.getSections();
            String lastSection = sections.get(removeLastSection ? sections.size() - 1 : 0).getSection();

            int removeCount = 0;
            for(int i = 0; i < size;i++) {
                if (lastSection.equalsIgnoreCase(data.get(i).movie)) {
                    copyData.remove(i - removeCount);
                    removeCount++;
                }
            }
        }

        return copyData;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fabAdd:
                adapter.refreshAdapter(addData());
                goToView.refresh(adapter);

                Toast.makeText(this, "New section added", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabRemove:
                adapter.refreshAdapter(removeSection(true));
                goToView.refresh(adapter);

                Toast.makeText(this, "Last section removed", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
