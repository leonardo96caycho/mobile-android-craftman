package com.mucontactcraftmanapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.mucontactcraftmanapp.MuContactCraftmanApp;
import com.mucontactcraftmanapp.R;
import com.mucontactcraftmanapp.adapters.PublicationsAdapter;
import com.mucontactcraftmanapp.models.Publication;
import com.mucontactcraftmanapp.models.User;
import com.mucontactcraftmanapp.network.MuContactService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {
    private RecyclerView publicationsRecyclerView;
    private PublicationsAdapter publicationsAdapter;
    private RecyclerView.LayoutManager publicationsLayoutManager;
    private List<Publication> publications;
    private User user;
    String TAG = "MuContact";
    public HomeFragment(){

    }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_home, container, false);
            publicationsRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
            publications = new ArrayList<>();
            publicationsAdapter = (new PublicationsAdapter()).setPublications(publications);
            publicationsLayoutManager = new LinearLayoutManager(view.getContext());
            publicationsRecyclerView.setAdapter(publicationsAdapter);
            publicationsRecyclerView.setLayoutManager(publicationsLayoutManager);
            user = MuContactCraftmanApp.getInstance().getCurrentUser();
            updatePublications();
            return view;
        }

        private void updatePublications() {
        AndroidNetworking
                .get(MuContactService.PUBLICATION_STATE_URL)
                .addPathParameter("state_publication", "A")
                    .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            publications = Publication.build(response.getJSONArray("publications"), user);
                            Log.d(TAG, "Found Publications: " + String.valueOf(publications.size()));
                            publicationsAdapter.setPublications(publications);
                            publicationsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getMessage());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        updatePublications();
    }
}
