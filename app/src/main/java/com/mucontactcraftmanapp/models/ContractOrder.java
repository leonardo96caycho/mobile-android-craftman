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

public class ContractOrder extends SugarRecord {
    private String _id;
    private Craftman craftman;
    private Order order;
    private String state;

    public ContractOrder(String _id, Craftman craftman, Order order, String state) {
        this._id = _id;
        this.craftman = craftman;
        this.order = order;
        this.state = state;
    }

    public ContractOrder() {
    }

    public String get_id() {
        return _id;
    }

    public ContractOrder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public Craftman getCraftman() {
        return craftman;
    }

    public ContractOrder setCraftman(Craftman craftman) {
        this.craftman = craftman;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public ContractOrder setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getState() {
        return state;
    }

    public ContractOrder setState(String state) {
        this.state = state;
        return this;
    }

    public static ContractOrder build(JSONObject jsonContractOrder, Craftman craftman, Order order) {
        if(jsonContractOrder == null) return null;
        ContractOrder contractOrder = new ContractOrder();
        try {
            contractOrder.set_id(jsonContractOrder.getString("_id"))
                    .setState(jsonContractOrder.getString("state"))
                    .setCraftman(craftman)
                    .setOrder(order);
            return contractOrder;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ContractOrder> build(JSONArray jsonContractOrder, Craftman craftman, Order order) {
        if(jsonContractOrder == null) return null;
        int length = jsonContractOrder.length();
        List<ContractOrder> contractOrders = new ArrayList<>();
        for(int i = 0; i < length; i++)
            try {
                contractOrders.add(ContractOrder.build(jsonContractOrder.getJSONObject(i), craftman, order));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return contractOrders;
    }
}
