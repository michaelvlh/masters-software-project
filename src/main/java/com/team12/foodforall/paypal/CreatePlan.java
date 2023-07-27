package com.team12.foodforall.paypal;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.JsonObject;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePlan{

    @Autowired
    private APIContext apiContext;

    public String desc;
    public String freq;
    public Integer interval;

    // Private class making the request to paypal
    private String getString(String productID, String name, String curr, String price, HttpURLConnection http) throws IOException {
        CreateJson jsonGen = new CreateJson();
        JsonObject objYear = jsonGen.makeJson(freq, interval, price, curr, name, desc, productID);

        System.out.println(objYear);

        byte[] out = objYear.toString().getBytes("utf-8");

        OutputStream stream = http.getOutputStream();
        stream.write(out);
        int responseCode = http.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = http.getInputStream();
        } else {
            inputStream = http.getErrorStream();
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));

        StringBuilder response = new StringBuilder();

        response.append(in.readLine());

        System.out.println("Create Plan");
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        in.close();

        String planID = response.substring(response.indexOf("{\"id\":\"")+7, response.indexOf("\","));
        System.out.println(planID);

        return planID;
    }

    //Makes the yearly billing plan
    public String makePlan(Long projectID, String productID, String curr, Float price, String pName) throws IOException, PayPalRESTException {
        /**Retrieve following from database**/
        String name = String.format("Yearly %s", pName);
        String pID = projectID.toString();
        System.out.println(productID);

        desc = "Yearly Donation";
        freq = "YEAR";
        interval = 1;

        URL url = new URL("https://api-m.sandbox.paypal.com/v1/billing/plans");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer {}".format(apiContext.fetchAccessToken()));
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("PayPal-Request-Id",  String.format("Yearly-%s", pID));

        return getString(productID, name, curr, price.toString(), http);

    }

    //Makes the monthly billing plan
    public String makeMonthly(Long projectID, String productID, String curr, Float price, String pName) throws IOException, PayPalRESTException {
        /**Retrieve following from database**/
        String name = String.format("%s Monthly", pName);
        String pID = projectID.toString();
        System.out.println(productID);
        desc = "Monthly Donation";
        freq = "MONTH";
        interval = 1;

        URL url = new URL("https://api-m.sandbox.paypal.com/v1/billing/plans");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer {}".format(apiContext.fetchAccessToken()));
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("PayPal-Request-Id",  String.format("Monthly-%s", pID));

        return getString(productID, name, curr, price.toString(), http);

    }

    //Makes the quarterly billing plan
    public String makeQuarterly(Long projectID, String productID, String curr, Float price, String pName) throws IOException, PayPalRESTException {
        /**Retrieve following from database**/
        String name = String.format("%s Quarterly", pName);
        String pID = projectID.toString();
        System.out.println(productID);
        desc = "Quarterly Donation";
        freq = "MONTH";
        interval = 3;

        URL url = new URL("https://api-m.sandbox.paypal.com/v1/billing/plans");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Bearer {}".format(apiContext.fetchAccessToken()));
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("PayPal-Request-Id", String.format("Quarterly-%s", pID));

        return getString(productID, name, curr, price.toString(), http);

    }

}