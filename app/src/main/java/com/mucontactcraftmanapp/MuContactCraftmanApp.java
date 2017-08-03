package com.mucontactcraftmanapp;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.mucontactcraftmanapp.models.Craftman;
import com.mucontactcraftmanapp.models.Musician;
import com.mucontactcraftmanapp.models.Publication;
import com.mucontactcraftmanapp.models.User;
import com.mucontactcraftmanapp.network.MuContactService;


/**
 * Created by romer on 25/7/2017.
 */

public class MuContactCraftmanApp extends Application {
    private static MuContactCraftmanApp instance;
    private MuContactService muContactService;

    public MuContactCraftmanApp() {
        super();
        instance = this;
    }

    public static MuContactCraftmanApp getInstance() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        muContactService = new MuContactService();
    }

    public MuContactCraftmanApp setCurrentCraftman(Craftman craftman){
        muContactService.setCurrentCraftman(craftman);
        return this;
    }

    public Craftman getCurrentCraftman() {
        return muContactService.getCurrentCraftman();
    }

    public MuContactCraftmanApp setCurrentUser(User user){
        muContactService.setCurrentUser(user);
        return this;
    }

    public User getCurrentUser() {
        return muContactService.getCurrentUser();
    }

    public MuContactCraftmanApp setCurrentToken(String token){
        muContactService.setCurrentToken(token);
        return this;
    }

    public String getCurrentToken() {
        return muContactService.getCurrentToken();
    }

    public MuContactCraftmanApp setCurrentPublication(Publication publication){
        muContactService.setCurrentPublication(publication);
        return this;
    }

    public Publication getCurrentPublication() {
        return muContactService.getCurrentPublication();
    }

    public MuContactCraftmanApp setCurrentMusician(Musician musician){
        muContactService.setCurrentMusician(musician);
        return this;
    }

    public Musician getCurrentMusician() {
        return muContactService.getCurrentMusician();
    }
}