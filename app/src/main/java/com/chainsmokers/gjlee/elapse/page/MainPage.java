package com.chainsmokers.gjlee.elapse.page;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chainsmokers.gjlee.elapse.R;

/**
 * Created by GILJAE on 2018-02-15.
 */

public class MainPage extends Fragment {

    // MainPage 객체 생성 함수.
    public static MainPage newInstance () {
        MainPage mainPage = new MainPage();
        Bundle args = new Bundle ();
        mainPage.setArguments(args);
        return mainPage;
    }

    public MainPage () {
        // Empty Constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_main, container, false);
        return view;
    }
}
