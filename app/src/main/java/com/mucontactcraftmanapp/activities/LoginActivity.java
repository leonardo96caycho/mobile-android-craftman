package com.mucontactcraftmanapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mucontactcraftmanapp.MuContactCraftmanApp;
import com.mucontactcraftmanapp.R;
import com.mucontactcraftmanapp.models.Craftman;
import com.mucontactcraftmanapp.models.User;
import com.mucontactcraftmanapp.network.MuContactService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    String TAG = "MuContact";
    EditText emailEditText;
    EditText passwordEditText;
    TextView signUpTextView;
    boolean correctEmail = false;
    boolean correctPassword = false;
    String email;
    String password;
    ProgressBar loginProgressBar;
    User user;
    String token;
    List<Craftman> craftmen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = (EditText) findViewById(R.id.emailTextInputEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordInputEditText);
        loginProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
        loginProgressBar.setVisibility(View.GONE);
        ((Button) findViewById(R.id.loginButton))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginProgressBar.setVisibility(View.VISIBLE);
                        intent = new Intent (v.getContext(), MainActivity.class);
                        if(Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()==false){
                            emailEditText.setError("Invalid email");
                            correctEmail = false;
                            loginProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            emailEditText.setError(null);
                            correctEmail = true;
                        }
                        if(passwordEditText.getText().toString().length() == 0) {
                            passwordEditText.setError("Invalid password");
                            correctPassword = false;
                            loginProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            passwordEditText.setError(null);
                            correctPassword = true;
                        }
                        if(correctEmail == true && correctPassword == true) {
                            email = emailEditText.getText().toString();
                            password = passwordEditText.getText().toString();
                            login();
                        }
                    }
                });
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext()
                        .startActivity(new Intent(v.getContext(),
                                RegisterActivity.class));
            }
        });
    }

    private void login() {
        AndroidNetworking.post(MuContactService.SIGNIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setTag(TAG)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            token = response.getString("token");
                            user = User.build(response.getJSONObject("user"));
                            if(user.getUserType().equals("Craftman")) {
                                MuContactCraftmanApp.getInstance().setCurrentToken(token);
                                MuContactCraftmanApp.getInstance().setCurrentUser(user);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                                updateCraftman();
                            } else {
                                Toast.makeText(getApplicationContext(), "You need a craftman count", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loginProgressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "User or password incorrect", Toast.LENGTH_SHORT).show();
                        loginProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }
    private void updateCraftman() {
        AndroidNetworking.get(MuContactService.CRAFTMAN_USER_URL)
                .addPathParameter("user_id", user.get_id())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            craftmen = Craftman.build(response.getJSONArray("craftman"), user);
                            MuContactCraftmanApp.getInstance().setCurrentCraftman(craftmen.get(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Error in craftman profile", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}


