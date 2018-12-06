package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private NewsItemRepository mRepository;

    private LiveData<List<NewsItem>> mAllNews;

    public NewsItemViewModel(Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mAllNews = mRepository.getAllNews();
    }

    public LiveData<List<NewsItem>> getmAllNews() {
        return mAllNews;
    }

    public void syncNews() {
        mRepository.syncNews();
    }

}
