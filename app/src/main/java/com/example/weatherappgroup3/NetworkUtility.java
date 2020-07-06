package com.example.weatherappgroup3;


import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtility {
    private static final String TAG = "NetworkUtility";
    private final static String weather_url =
            "https://dataservice.accuweather.com/forecasts/v1/daily/5day/349530";

    private final static String api_key = "dMN6U1Uu3a1tfoKkr3YnmKaSw5RKSyNt";

    private final static String para_api_key = "apikey";

    public static URL buildUrlForWeather() {
        Uri buildUri = Uri.parse(weather_url).buildUpon()
                .appendQueryParameter(para_api_key, api_key).build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "buildUrlForWeather: url: " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
