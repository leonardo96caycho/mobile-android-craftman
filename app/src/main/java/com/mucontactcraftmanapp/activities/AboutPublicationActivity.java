package com.mucontactcraftmanapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.mucontactcraftmanapp.MuContactCraftmanApp;
import com.mucontactcraftmanapp.R;
import com.mucontactcraftmanapp.models.Craftman;
import com.mucontactcraftmanapp.models.Musician;
import com.mucontactcraftmanapp.models.Publication;
import com.mucontactcraftmanapp.models.User;
import com.mucontactcraftmanapp.network.MuContactService;

import org.json.JSONException;
import org.json.JSONObject;

public class AboutPublicationActivity extends AppCompatActivity {
    private ANImageView photoPublicationANImageView;
    private TextView instrumentEditText;
    private TextView descriptionEditText;
    private TextView locationAtEditText;
    private TextView displayNameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    public String TAG="MuContact";
    Publication publication;
    Craftman craftman;
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

        craftman = MuContactCraftmanApp.getInstance().getCurrentCraftman();
        publication = MuContactCraftmanApp.getInstance().getCurrentPublication();
        instrumentEditText.setText(publication.getInstrument());
        descriptionEditText.setText(publication.getDescription());
        locationAtEditText.setText(publication.getLocationReference());
        displayNameTextView.setText(publication.getUser().getDisplayName());
        emailTextView.setText(publication.getUser().getEmail());
        //phoneTextView.setText(publication.getUser()getPhone());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.createContractFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    createContract();
                }});
    }
    public void createContract(){
        AndroidNetworking.post(MuContactService.CONTRACT_URL)
                .addBodyParameter("publication", publication.get_id().toString())
                .addBodyParameter("craftman", craftman.get_id().toString())
                .addBodyParameter("user", publication.getUser().get_id().toString())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                        updatePublication();
                        finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Failed to save contract", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updatePublication() {
        AndroidNetworking
                .put(MuContactService.PUBLICATION_ID_URL)
                .addPathParameter("publication_id", publication.get_id().toString())
                .addBodyParameter("state", "N")
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getMessage());
                    }
                });
    }
}


