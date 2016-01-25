package com.majie.stugrade.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 天气接口
 * Created by Maniac on 16/1/16.
 */

public class WeatherApi {

    public static final String key = "2ee93db126ca4027b068036c2f92e57c";

    public WeatherApi getInstance(String cityid){

        return new WeatherApi(cityid);
    }

    public WeatherApi(String cityid){
        String httpUrl = "https://api.heweather.com/x3/weather?cityid=" + cityid+ "&key=" + key;
    }

    public static String request(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
