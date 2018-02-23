package com.chainsmokers.gjlee.elapse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chainsmokers.gjlee.elapse.network.APIClient;
import com.chainsmokers.gjlee.elapse.ui.ErrorDialogFragment;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RegisterActivity extends AppCompatActivity {

    private ImageView buttonPrev;

    private EditText account;
    private EditText password;

    private TextView registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonPrev = findViewById(R.id.buttonPrev_register);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Register Canceled",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        account = findViewById(R.id.textAccount_register);
        password = findViewById(R.id.textPassword_register);

        registerButton = findViewById(R.id.registerButton_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButton.setClickable(false);

                if (account.getText().toString().equals("")) {
                    ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Fill in your account.");
                    errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                    registerButton.setClickable(true);
                    return;
                } else if (password.getText().toString().equals("")) {
                    ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance("Fill in your password.");
                    errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                    registerButton.setClickable(true);
                    return;
                }

                RegisterActivity.APIInterface apiInterface = APIClient.getClient().create(RegisterActivity.APIInterface.class);
                RegisterActivity.RequestSignup requestSignin = new RegisterActivity.RequestSignup(account.getText().toString(),password.getText().toString());
                Call<RegisterActivity.ResponseSignup> call = apiInterface.signup(requestSignin);
                call.enqueue(new Callback<RegisterActivity.ResponseSignup>() {
                    @Override
                    public void onResponse(Call<RegisterActivity.ResponseSignup> call, Response<RegisterActivity.ResponseSignup> response) {
                        if (!response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                ErrorDialogFragment errorDialogFragment = ErrorDialogFragment.newInstance(jsonObject.getString("error"));
                                errorDialogFragment.show(getSupportFragmentManager(),"error_message");
                                registerButton.setClickable(true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return;
                        }
                        Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<RegisterActivity.ResponseSignup> call, Throwable t) {

                    }
                });
            }
        });
    }

    interface APIInterface {
        @POST("/api/account/signup")
        Call<RegisterActivity.ResponseSignup> signup (@Body RegisterActivity.RequestSignup requestSignin);
    }

    class RequestSignup {
        private String userId;
        private String password;

        public RequestSignup (String userId, String password) {
            this.userId = userId;
            this.password = password;
        }
    }

    class ResponseSignup {
        @SerializedName("success")
        public boolean success;
    }
}
