package com.chainsmokers.gjlee.elapse.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chainsmokers.gjlee.elapse.R;

import java.util.ArrayList;

/**
 * Created by GILJAE on 2018-02-15.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    // Games List 안에 들어갈 Games 정보.
    private ArrayList<GamesData> gamesList;

    // Constructor
    public GamesAdapter (ArrayList<GamesData> gamesList) {
        this.gamesList = gamesList;
    }

    // ViewHolder 및 Constructor 정의.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gameName;
        public ImageView gameImage;
        public TextView gameCompany;

        public ViewHolder (View view) {
            super (view);
            gameName = view.findViewById(R.id.textName_game);
            gameImage = view.findViewById(R.id.imageView_game);
            gameCompany = view.findViewById(R.id.textCompany_game);
        }
    }

    // Overriding: ViewHolder 반환.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_games,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Overriding: ViewHolder, List Item 실제 내용 설정.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.gameName.setText(gamesList.get(position).getName());
        holder.gameImage.setImageResource(gamesList.get(position).getImage());
        holder.gameCompany.setText(gamesList.get(position).getGameCompany());
    }

    // Overriding: Games 목록 개수 반환.
    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
