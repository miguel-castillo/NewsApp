package com.example.rkjc.news_app_2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String JSONString){
        ArrayList<NewsItem> newsItemArrayList = new ArrayList<NewsItem>();
        try {
            JSONObject mainJSONObject = new JSONObject(JSONString);
            JSONArray articles = mainJSONObject.getJSONArray("articles");

            for(int i = 0; i < articles.length(); i++){
                JSONObject item = articles.getJSONObject(i);
                newsItemArrayList.add(new NewsItem(
                        item.getString("author"),
                        item.getString("title"),
                        item.getString("description"),
                        item.getString("url"),
                        item.getString("publishedAt")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItemArrayList;
    }

}


