package com.mucontactcraftmanapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.mucontactcraftmanapp.MuContactCraftmanApp;
import com.mucontactcraftmanapp.R;
import com.mucontactcraftmanapp.models.Musician;
import com.mucontactcraftmanapp.models.Publication;
import com.mucontactcraftmanapp.models.User;
import com.mucontactcraftmanapp.network.MuContactService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AboutPublicationActivity extends AppCompatActivity {
    private ANImageView photoPublicationANImageView;
    private TextView instrumentEditText;
    private TextView descriptionEditText;
    private TextView locationAtEditText;
    private TextView displayNameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private List<Musician> musicians;
    public String TAG="MuContact";
    Publication publication;
    Musician musician;
    User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_publication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        photoPublicationANImageView = (ANImageView) findViewById(R.id.photoPublicationANImageView);
        photoPublicationANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        photoPublicationANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        instrumentEditText = (TextView) findViewById(R.id.instrumentTextView);
        descriptionEditText = (TextView) findViewById(R.id.descriptionTextView);
        locationAtEditText = (TextView) findViewById(R.id.locationReferenceTextView);
        displayNameTextView = (TextView) findViewById(R.id.diplayNameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);

        publication = MuContactCraftmanApp.getInstance().getCurrentPublication();
        musician = MuContactCraftmanApp.getInstance().getCurrentMusician();
        user = MuContactCraftmanApp.getInstance().getCurrentUser();
        instrumentEditText.setText(publication.getInstrument());
        descriptionEditText.setText(publication.getDescription());
        locationAtEditText.setText(publication.getLocationReference());
        //displayNameTextView.setText(musicianValues().);
        //emailTextView.setText(musician.getUser().getEmail());
        //phoneTextView.setText(musician.getPhone());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.createContractFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                }});
    }

    private void musicianValues() {
        AndroidNetworking
                .get(MuContactService.MUSICIAN_USER_URL)
                .addPathParameter("_id", publication.get_id())
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build();
    }}


