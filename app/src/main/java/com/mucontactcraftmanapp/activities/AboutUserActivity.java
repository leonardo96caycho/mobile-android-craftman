package com.mucontactcraftmanapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.mucontactcraftmanapp.MuContactCraftmanApp;
import com.mucontactcraftmanapp.R;
import com.mucontactcraftmanapp.models.Craftman;
import com.mucontactcraftmanapp.models.User;

public class AboutUserActivity extends AppCompatActivity {
    private ANImageView photoANImageView;
    private TextView displayNameTextView;
    private TextView emailTextView;
    private TextView userTypeTextView;
    private TextView phoneTextView;
    private TextView descriptionTextView;
    private TextView levelTextView;
    User user;
    Craftman craftman;
    String TAG = "MuContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = MuContactCraftmanApp.getInstance().getCurrentUser();

        craftman = MuContactCraftmanApp.getInstance().getCurrentCraftman();

        photoANImageView = (ANImageView) findViewById(R.id.photoANImageView);
        displayNameTextView = (TextView) findViewById(R.id.displayNameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        userTypeTextView = (TextView) findViewById(R.id.userTypeTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        levelTextView = (TextView) findViewById(R.id.levelTextView);

        photoANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        photoANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        //photoANImageView.setImageUrl(sources.get(position).getUrlToSmallLogo());
        displayNameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        userTypeTextView.setText(user.getUserType());
        phoneTextView.setText((craftman.getPhone()));
        descriptionTextView.setText(craftman.getDescription());
        levelTextView.setText(craftman.getLevel());
    }

}
