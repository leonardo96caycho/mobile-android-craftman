package com.mucontactcraftmanapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Contract {
    private String title;
    private String image;
    private String description;
    private String confirmation;

    public Contract(String title, String image, String description, String confirmation) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.confirmation = confirmation;
    }

    public Contract() {
    }

    public String getTitle() {
        return title;
    }

    public Contract setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Contract setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Contract setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public Contract setConfirmation(String confirmation) {
        this.confirmation = confirmation;
        return this;
    }

    public static Contract build(JSONObject jsonContract) {
        if(jsonContract == null) return null;
        Contract contract = new Contract();
        try {
            contract.setTitle(jsonContract.getString("title"))
                    .setImage(jsonContract.getString("image"))
                    .setDescription(jsonContract.getString("description"))
                    .setConfirmation(jsonContract.getString("confirmation"));
            return contract;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Contract> build(JSONArray jsonContract) {
        if(jsonContract == null) return null;
        int length = jsonContract.length();
        List<Contract> contracts = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                contracts.add(Contract.build(jsonContract.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return contracts;
    }
}
