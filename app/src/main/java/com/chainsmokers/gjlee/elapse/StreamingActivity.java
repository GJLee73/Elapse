package com.chainsmokers.gjlee.elapse;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chainsmokers.gjlee.elapse.list.ChattingAdapter;
import com.chainsmokers.gjlee.elapse.list.ChattingData;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class StreamingActivity extends AppCompatActivity {

    private EditText textMessage;
    private TextView buttonSend;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ChattingAdapter chattingAdapter;
    private ArrayList<ChattingData> chattingList;

    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_streaming);

        textMessage = findViewById(R.id.textMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // "send:message" Event 발생.
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject msg = new JSONObject();
                try {
                    msg.put("user","gjlee");
                    msg.put("text",textMessage.getText().toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }

                JSONObject data = new JSONObject();
                try {
                    data.put("room","chainsmokers");
                    data.put("msg",msg);
                } catch (JSONException e){
                    e.printStackTrace();
                }
                socket.emit("send:message",data);
                textMessage.setText("");
            }
        });

       chattingList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        chattingAdapter = new ChattingAdapter(chattingList, this);
        recyclerView.setAdapter(chattingAdapter);
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if ( i3 < i7) {
                    recyclerView.smoothScrollToPosition(chattingAdapter.getItemCount());
                }
            }
        });

        // Socket IO -------------------------------------------------------------------------------

        try {
            socket = IO.socket("http://35.190.183.146:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //
            }
        });

        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                //
            }
        });

        // "user:join" Event 전달 받을 시.
        socket.on("user:join", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                final JSONObject obj = (JSONObject)args[0];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    chattingList.add (new ChattingData(obj.getString("userId"),"has joined",true));
                                    chattingAdapter.notifyDataSetChanged();
                                    recyclerView.smoothScrollToPosition(chattingAdapter.getItemCount());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        // "send:message" Event 전달 받을 시.
        socket.on("send:message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                final JSONObject obj = (JSONObject)args[0];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    chattingList.add (new ChattingData(obj.getString("user"),obj.getString("text"),false));
                                    chattingAdapter.notifyDataSetChanged();
                                    recyclerView.smoothScrollToPosition(chattingAdapter.getItemCount());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        // "user:left" Event 전달 받을 시.
        socket.on("user:left", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                final JSONObject obj = (JSONObject)args[0];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    chattingList.add (new ChattingData(obj.getString("userId"),"has left",true));
                                    chattingAdapter.notifyDataSetChanged();
                                    recyclerView.smoothScrollToPosition(chattingAdapter.getItemCount());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        // Socket IO -------------------------------------------------------------------------------
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Socket 연결.
        socket.connect();

        JSONObject data = new JSONObject();
        try {
            data.put ("userId","gjlee");
            data.put ("room","chainsmokers");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit ("user:join",data);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Socket 연결 끊기.
        socket.disconnect();
    }
}
