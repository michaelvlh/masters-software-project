package com.team12.foodforall.paypal;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class CreateJson {
    public JsonObject makeJson(String freq, Integer interval, String price, String curr, String name, String desc, String productID){

        // json object declaration
        JsonObject obj = new JsonObject();
        JsonObject cycle = new JsonObject();
        JsonObject freqObj = new JsonObject();
        JsonObject priceObj = new JsonObject();
        JsonObject fixed = new JsonObject();
        JsonObject pPref = new JsonObject();
        JsonObject taxes = new JsonObject();
        JsonObject setup = new JsonObject();

        // frequency section
        freqObj.addProperty("interval_unit", freq);
        freqObj.addProperty("interval_count", interval);

        //inner pricing section
        fixed.addProperty("value", price);
        fixed.addProperty("currency_code", curr);

        //pricing section
        priceObj.add("fixed_price", fixed);

        //billing cycle section
        cycle.add("frequency", freqObj);
        cycle.addProperty("tenure_type", "REGULAR");
        cycle.addProperty("sequence", 1);
        cycle.addProperty("total_cycles", 24);
        cycle.add("pricing_scheme", priceObj);
        JsonArray wrapped = new JsonArray();
        wrapped.add(cycle);


        //setup fee section
        setup.addProperty("value", "0");
        setup.addProperty("currency_code", curr);

        //payment_pref
        pPref.addProperty("auto_bill_outstanding", true);
        pPref.add("setup_fee", setup);
        pPref.addProperty("setup_fee_failure_action", "CONTINUE");
        pPref.addProperty("payment_failure_threshold", 3);

        //taxes
        taxes.addProperty("percentage", 0);
        taxes.addProperty("inclusive", false);

        //Main json body
        obj.addProperty("product_id", productID);
        obj.addProperty("name", name);
        obj.addProperty("description", desc);
        obj.addProperty("status", "ACTIVE");
        obj.add("billing_cycles", wrapped);
        obj.add("payment_preferences", pPref);
        obj.add("taxes", taxes);

        return obj;
    }
}
