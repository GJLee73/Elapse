package com.chainsmokers.gjlee.elapse.page;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chainsmokers.gjlee.elapse.R;
import com.chainsmokers.gjlee.elapse.list.GamesAdapter;
import com.chainsmokers.gjlee.elapse.list.GamesData;

import java.util.ArrayList;

/**
 * Created by GILJAE on 2018-02-15.
 */

public class GamesPage extends Fragment {

    //private static final String BUNDLE_RECYCLER_LAYOUT = "GamesPage.recycler.layout";

    // RecyclerView 관련 변수 선언.
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GamesAdapter gamesAdapter;
    private ArrayList<GamesData> gamesList;

    // GamesPage 객체 생성 함수.
    public static GamesPage newInstance () {
        GamesPage gamePage = new GamesPage();
        Bundle args = new Bundle ();
        gamePage.setArguments(args);
        return gamePage;
    }

    public GamesPage () {
        // Empty Constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        gamesList = new ArrayList<>();
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));
        gamesList.add(new GamesData("Hearthstone",R.drawable.hs,"Blizzard Entertainment"));
        gamesList.add(new GamesData("Circlt",R.drawable.circlt,"5437 INC"));

        View view = inflater.inflate(R.layout.fragment_page_games, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        gamesAdapter = new GamesAdapter(gamesList);
        recyclerView.setAdapter(gamesAdapter);

        return view;
    }

    /*@Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }*/

}
