package com.mucontactcraftmanapp.models;

import com.orm.SugarRecord;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class ContractPublication extends SugarRecord{
    private String _id;
    private Craftman craftman;
    private Publication publication;

    public ContractPublication(String _id, Craftman craftman, Publication publication) {
        this._id = _id;
        this.craftman = craftman;
        this.publication = publication;
    }

    public ContractPublication() {
    }
    public String get_id(){
        return _id;
    }
    public ContractPublication set_id(String _id){
        this._id = _id;
        return this;
    }
    public Craftman getCraftman() {
        return craftman;
    }

    public ContractPublication setCraftman(Craftman craftman) {
        this.craftman = craftman;
        return this;
    }

    public Publication getPublication() {
        return publication;
    }

    public ContractPublication setPublication(Publication publication) {
        this.publication = publication;
        return this;
    }

    public static ContractPublication build(JSONObject jsonContractPublication, Craftman craftman, Publication publication) {
        if(jsonContractPublication == null) return null;
        ContractPublication contractPublication = new ContractPublication();
        try {
            contractPublication.set_id(jsonContractPublication.getString("_id"))
                                .setCraftman(craftman)
                                .setPublication(publication);
            return contractPublication;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ContractPublication> build(JSONArray jsonContractPublication, Craftman craftman, Publication publication) {
        if(jsonContractPublication == null) return null;
        int length = jsonContractPublication.length();
        List<ContractPublication> contractPublications = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                contractPublications.add(ContractPublication.build(jsonContractPublication.getJSONObject(i),craftman, publication));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return contractPublications;
    }
}



