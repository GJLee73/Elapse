package com.chainsmokers.gjlee.elapse;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chainsmokers.gjlee.elapse.network.APIClient;
import com.chainsmokers.gjlee.elapse.ui.ErrorDialogFragment;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    private EditText account;
    private EditText password;

    private TextView loginButton;
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

        account = findViewById(R.id.textAccount);
        password = findViewById(R.id.textPassword);

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
                loginButton.setClickable(false);

                if (account.getText().toString().equals("")) {
                    ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Fill in your account.");
                    errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                    loginButton.setClickable(true);
                    return;
                } else if (password.getText().toString().equals("")) {
                    ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Fill in your password.");
                    errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                    loginButton.setClickable(true);
                    return;
                }

                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                RequestSignin requestSignin = new RequestSignin(account.getText().toString(),password.getText().toString());
                Call<ResponseSignin> call = apiInterface.signin(requestSignin);
                call.enqueue(new Callback<ResponseSignin>() {
                    @Override
                    public void onResponse(Call<ResponseSignin> call, Response<ResponseSignin> response) {
                        if (!response.isSuccessful()) {
                            ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Wrong account or password.");
                            errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                            loginButton.setClickable(true);
                            return;
                        }
                        SharedPreferences sharedPref = getSharedPreferences("Setting",Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("isLogin",true);
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, MyActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseSignin> call, Throwable t) {
                        ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Service error. Please try again.");
                        errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                        loginButton.setClickable(true);
                        return;
                    }
                });
            }
        });

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
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
