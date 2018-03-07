package com.chainsmokers.gjlee.elapse.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chainsmokers.gjlee.elapse.R;

import java.util.ArrayList;

/**
 * Created by GILJAE on 2018-03-06.
 */

public class ChattingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChattingData> chattingList;

    private Context context;

    public ChattingAdapter (ArrayList<ChattingData> chattingList, Context context) {
        this.chattingList = chattingList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userId;
        public TextView userMessage;

        public ViewHolder (View view) {
            super (view);
            userId = view.findViewById(R.id.userId);
            userMessage = view.findViewById(R.id.userMessage);
        }
    }

    public static class ViewHolder_Server extends RecyclerView.ViewHolder {
        public TextView userId;
        public TextView serverMessage;

        public ViewHolder_Server (View view) {
            super (view);
            userId = view.findViewById(R.id.userId);
            serverMessage = view.findViewById(R.id.serverMessage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (chattingList.get(position).getIsServerMessage()) {
            return 1;   // Server Message
        } else {
            return 0;   // User Message
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chats,parent,false);
                viewHolder = new ViewHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chats_server,parent,false);
                viewHolder = new ViewHolder_Server(view);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 0:
                ((ViewHolder)holder).userId.setText(chattingList.get(position).getUserId());
                ((ViewHolder)holder).userMessage.setText(chattingList.get(position).getUserMessage());
                break;
            case 1:
                ((ViewHolder_Server)holder).userId.setText(chattingList.get(position).getUserId());
                ((ViewHolder_Server)holder).serverMessage.setText(chattingList.get(position).getUserMessage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chattingList.size();
    }
}
