package com.chainsmokers.gjlee.elapse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chainsmokers.gjlee.elapse.network.APIClient;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by GILJAE on 2018-02-18.
 */

public class LoginActivity extends AppCompatActivity {

    private ImageView buttonPrev;

    private Button loginButton;

    private TextView registerButton;

    //private Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Socket Programming
        /*try {
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

        socket.connect();*/

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
                EditText account = findViewById(R.id.textAccount);
                EditText password = findViewById(R.id.textPassword);
                RequestSignin requestSignin = new RequestSignin(account.getText().toString(),password.getText().toString());
                 Call<ResponseSignin> call = apiInterface.signin(requestSignin);
                 call.enqueue(new Callback<ResponseSignin>() {
                     @Override
                     public void onResponse(Call<ResponseSignin> call, Response<ResponseSignin> response) {
                         // Debugging.
                         if (!response.isSuccessful()) {
                             TextView result = findViewById(R.id.result);
                             try {
                                 JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                 result.setText(jsonObject.getString("error"));
                             } catch (IOException e) {
                                 e.printStackTrace();
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                             return;
                         }
                         TextView result = findViewById(R.id.result);
                         ResponseSignin responseSignin = response.body();
                         result.setText(String.valueOf(responseSignin.success));
                     }

                     @Override
                     public void onFailure(Call<ResponseSignin> call, Throwable t) {

                     }
                 });
            }
        });

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*JSONObject obj = new JSONObject();
                try {
                    obj.put("user_id", "Chainsmokers");
                    obj.put("room_name", "New");
                } catch (JSONException e) {

                }
                socket.emit ("new_join",obj);*/
            }
        });

    }

    // Networking 관련 내부 Class 및 Interface 정의.
    interface APIInterface {
        @POST ("/api/account/signin")
        Call<ResponseSignin> signin (@Body RequestSignin requestSignin);
    }

    class RequestSignin {
        private String userId;
        private String password;

        public RequestSignin (String userId, String password) {
            this.userId = userId;
            this.password = password;
        }
    }

    class ResponseSignin {
        @SerializedName("success")
        public boolean success;
    }
}
