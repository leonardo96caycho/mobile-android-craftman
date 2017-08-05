package com.mucontactcraftmanapp.models;

import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franklin on 04/08/2017.
 */

public class Order extends SugarRecord{

    private String _id;
    private String instrument;
    private String description;
    private String state;
    private Craftman craftman;

    public Musician getMusician() {
        return musician;
    }

    public Order setMusician(Musician musician) {
        this.musician = musician;
        return this;
    }

    private Musician musician;

    public Order(String _id,String instrument, String description, String state,Craftman craftman,Musician musician) {
        this._id = _id;
        this.instrument = instrument;
        this.description = description;
        this.state = state;
        this.craftman = craftman;
        this.musician = musician;
    }

    public Order() {
    }

    public String get_id() {
        return _id;
    }

    public Order set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getInstrument() {
        return instrument;
    }

    public Order setInstrument(String instrument) {
        this.instrument = instrument;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }

    public Order setState(String state) {
        this.state = state;
        return this;
    }

    public Craftman getCraftman() {
        return craftman;
    }

    public Order setCraftman(Craftman craftman) {
        this.craftman = craftman;
        return this;
    }

    public static Order build(JSONObject jsonOrder, Craftman craftman,Musician musician) {
        if(jsonOrder == null) return null;
        Order order = new Order();
        try {
            order.set_id(jsonOrder.getString("_id"))
                    .setInstrument(jsonOrder.getString("instrument"))
                    .setDescription(jsonOrder.getString("description"))
                    .setState(jsonOrder.getString("state"))
                    .setCraftman(craftman)
                    .setMusician(musician);
            return order;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> build(JSONArray jsonOrder, Craftman craftman, Musician musician) {
        if(jsonOrder == null) return null;
        int length = jsonOrder.length();
        List<Order> orders = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                orders.add(Order.build(jsonOrder.getJSONObject(i),craftman,musician));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return orders;
    }
}

