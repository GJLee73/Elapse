package com.chainsmokers.gjlee.elapse.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chainsmokers.gjlee.elapse.R;
import com.chainsmokers.gjlee.elapse.page.ListPage;

import java.util.ArrayList;

/**
 * Created by GILJAE on 2018-03-04.
 */

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ViewHolder> {

    private ArrayList<RoomsData> listRooms;

    private ListPage listPage;

    public RoomsAdapter (ArrayList<RoomsData> listRooms, ListPage listPage) {
        this.listRooms = listRooms;
        this.listPage = listPage;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        public TextView streamName;
        public TextView streamerName;
        public TextView streamWatcher;

        public ViewHolder (View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
            streamName = view.findViewById(R.id.streamName);
            streamerName = view.findViewById(R.id.streamerName);
            streamWatcher = view.findViewById(R.id.streamWatcher);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rooms,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stream Room 입장 처리.
                listPage.EnterRoom();
                //Toast.makeText(view.getContext(),listRooms.get(viewHolder.getAdapterPosition()).getStreamerName(),Toast.LENGTH_LONG).show();
            }
        });*/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoomsAdapter.ViewHolder holder, int position) {
        holder.thumbnail.setImageResource(listRooms.get(position).getThumbnail());
        holder.streamName.setText(listRooms.get(position).getStreamName());
        holder.streamerName.setText(listRooms.get(position).getStreamerName());
        holder.streamWatcher.setText(listRooms.get(position).getStreamWatcher());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stream Room 입장 처리.
                listPage.EnterRoom();
                //Toast.makeText(view.getContext(),listRooms.get(viewHolder.getAdapterPosition()).getStreamerName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }
}
