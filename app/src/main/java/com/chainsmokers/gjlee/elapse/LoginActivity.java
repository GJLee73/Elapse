package com.chainsmokers.gjlee.elapse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chainsmokers.gjlee.elapse.network.APIClient;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by GILJAE on 2018-02-18.
 */

public class LoginActivity extends AppCompatActivity {

    private ImageView buttonPrev;

    private Button loginButton;

    private TextView registerButton;

    // Debugging.
    private TextView result;
    private Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            socket = IO.socket("http://192.168.1.101:4000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        }).on("sendedMsg", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                // Debugging
                final JSONObject obj = (JSONObject)args[0];
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result = findViewById(R.id.result);
                        try {
                            result.setText(obj.getString("user_id") + ": " + obj.getString("text"));
                        } catch (JSONException e) {
                            e.printStackTrace();;
                        }
                    }
                });
            }
        });

        socket.connect();

        buttonPrev = findViewById(R.id.buttonPrev);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                 Call<TestClass> call = apiInterface.doTest();
                 call.enqueue(new Callback<TestClass>() {
                     @Override
                     public void onResponse(Call<TestClass> call, Response<TestClass> response) {
                         // Debugging.
                         result = findViewById(R.id.result);
                         TestClass tc = response.body();
                         result.setText(tc.sender);
                     }

                     @Override
                     public void onFailure(Call<TestClass> call, Throwable t) {

                     }
                 });
            }
        });

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("user_id", "Chainsmokers");
                    obj.put("room_name", "New");
                } catch (JSONException e) {

                }
                socket.emit ("new_join",obj);
            }
        });

    }

    interface APIInterface {
        @GET ("/test")
        Call<TestClass> doTest();
    }

    class TestClass {
        @SerializedName("sender")
        public String sender;
    }
}
