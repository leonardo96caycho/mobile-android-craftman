package com.mucontactcraftmanapp.network;

import com.mucontactcraftmanapp.models.Craftman;
import com.mucontactcraftmanapp.models.Musician;
import com.mucontactcraftmanapp.models.Publication;
import com.mucontactcraftmanapp.models.User;

/**
 * Created by romer on 25/7/2017.
 */

public class MuContactService {
    public static String USERS_URL = "https://mucontact.herokuapp.com/api/user";
    public static String USERS_EDIT_URL = "https://mucontact.herokuapp.com/api/user/{user_id}";
    public static String SIGNUP_URL = "https://mucontact.herokuapp.com/api/signup";
    public static String SIGNIN_URL = "https://mucontact.herokuapp.com/api/signin";
    public static String MUSICIAN_USER_URL = "https://mucontact.herokuapp.com/api/musician/user/{user_id}";
    public static String PUBLICATION_URL = "https://mucontact.herokuapp.com/api/publication";
    public static String PUBLICATION_USER_URL = "https://mucontact.herokuapp.com/api/publication/user/{user_id}";
    public static String CRAFTTMAN_URL = "https://mucontact.herokuapp.com/api/craftman";
    public static String CRAFTMAN_USER_URL = "https://mucontact.herokuapp.com/api/craftman/user/{user_id}";
    public static String CONTRACT_URL = "https://mucontact.herokuapp.com/api/contract";

    private User currentUser;
    private String currentToken;
    private Publication currentPublication;
    private Musician currentMusician;
    private Craftman currentCraftman;


    public Craftman getCurrentCraftman() {
        return currentCraftman;
    }

    public MuContactService setCurrentCraftman(Craftman currentCraftman){
        this.currentCraftman = currentCraftman;
        return this;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public MuContactService setCurrentUser(User currentUser){
        this.currentUser = currentUser;
        return this;
    }

    public String getCurrentToken() {
        return currentToken;
    }

    public MuContactService setCurrentToken(String currentToken){
        this.currentToken = currentToken;
        return this;
    }

    public Publication getCurrentPublication() {
        return currentPublication;
    }

    public MuContactService setCurrentPublication(Publication currentPublication){
        this.currentPublication = currentPublication;
        return this;
    }

    public Musician getCurrentMusician() {
        return currentMusician;
    }

    public MuContactService setCurrentMusician(Musician currentMusician){
        this.currentMusician = currentMusician;
        return this;
    }
}
