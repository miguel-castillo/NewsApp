package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsItemRepository {

    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNews;

    public NewsItemRepository(Application application) {
        NewsRoomDatabase db = NewsRoomDatabase.getsInstance(application.getApplicationContext());
        mNewsItemDao = db.newsItemDao();
        mAllNews = mNewsItemDao.loadAllNewsItem();
    }

    LiveData<List<NewsItem>> getAllNews() {
        return mAllNews;
    }

    public void syncNews(){
        new syncNewsAsyncTask(mNewsItemDao).execute();
    }

    private class syncNewsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao mAsyncTaskDao;

        syncNewsAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.clearAll();

            URL newsSearch = NetworkUtils.buildUrl();
            String newsSearchResults = null;
            try{
                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(newsSearch);
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<NewsItem> news = JsonUtils.parseNews(newsSearchResults);
            mAsyncTaskDao.insert(news);
            return null;
        }
    }
}
