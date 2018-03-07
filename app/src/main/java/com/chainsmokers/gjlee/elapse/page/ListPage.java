package com.chainsmokers.gjlee.elapse.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chainsmokers.gjlee.elapse.R;
import com.chainsmokers.gjlee.elapse.StreamingActivity;
import com.chainsmokers.gjlee.elapse.list.RoomsAdapter;
import com.chainsmokers.gjlee.elapse.list.RoomsData;

import java.util.ArrayList;

/**
 * Created by GILJAE on 2018-03-04.
 */

public class ListPage extends Fragment {

    // RecyclerView 관련 변수 선언.
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RoomsAdapter roomsAdapter;
    private ArrayList<RoomsData> listRooms;

    // GamesPage 객체 생성 함수.
    public static ListPage newInstance () {
        ListPage listPage = new ListPage();
        Bundle args = new Bundle ();
        listPage.setArguments(args);
        return listPage;
    }

    public ListPage () {
        // Empty Constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listRooms = new ArrayList<>();
        listRooms.add(new RoomsData(R.drawable.thumbnail_chainsmokers,"The Chainsmokers' VIENNA Live","The Chainsmokers","3000"));
        listRooms.add(new RoomsData(R.drawable.arsenal_barcelona,"UCL Final Arsenal vs Barcelona","Sky Sports","2000"));

        View view = inflater.inflate(R.layout.fragment_page_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        roomsAdapter = new RoomsAdapter(listRooms,this);
        recyclerView.setAdapter(roomsAdapter);

        return view;
    }

    public void EnterRoom () {
        Intent intent = new Intent(getActivity(), StreamingActivity.class);
        startActivity(intent);
    }
}
