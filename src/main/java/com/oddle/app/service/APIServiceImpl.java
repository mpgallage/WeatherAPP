package com.oddle.app.service;

import com.oddle.app.model.Report;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @author : maal (Malaka Gallage)
 * @since : 10/12/17
 */
@Service
@PropertySource(value = { "classpath:application.properties" })
public class APIServiceImpl implements APIService {

    @Autowired
    private Environment environment;

    public Report callAPI(String city) throws IOException, ParseException {

        String url = environment.getProperty("openweather.api.url");
        String appID = environment.getProperty("openweather.api.app_id");

        StringBuffer bf = new StringBuffer(url);
        bf.append("?q=").append(city).append("&appid=").append(appID);

        URL address = new URL(bf.toString());
        HttpURLConnection conn = (HttpURLConnection) address.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IllegalStateException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(br);

        return Report.unmarshalJson(json);
    }
}
