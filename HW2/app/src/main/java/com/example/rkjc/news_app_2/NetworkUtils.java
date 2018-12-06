package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String BASE_URL = "https://newsapi.org/v1/articles?";

    private final static String QUERY_PARAMETERS = "source";
    private final static String queryParameters = "the-next-web";

    private final static String PARAM_SORT = "sortBy";
    private final static String sortBy = "latest";

    private final static String API_KEY = "apiKey";
    private final static String apiKey = "9a69afc83ae24657984c2d25f118577c";

    public static URL buildUrl(){
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETERS,queryParameters)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .appendQueryParameter(API_KEY, apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
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
