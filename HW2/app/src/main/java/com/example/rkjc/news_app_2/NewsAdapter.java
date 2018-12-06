package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {
    Context mContext;
    ArrayList<NewsItem> mNews;
    private NewsItemViewModel mViewModel;

    public NewsAdapter (Context context, ArrayList<NewsItem> mNews){
        this.mContext = context;
        this.mNews = mNews;
    }

    public void setNews(ArrayList<NewsItem> news){
        this.mNews=news;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view,mContext);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(null == mNews){
            return 0;
        }
        return mNews.size();
    }


    public class NewsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView description;
        public TextView publishedAt;
        public Context mcontext;
        public String url;


        public NewsItemViewHolder(View itemView, Context context) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            publishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
            this.mcontext = context;

        }

        void bind(final int listIndex) {
            title.setText("Title: " + mNews.get(listIndex).getTitle());
            description.setText("Description: "+mNews.get(listIndex).getDescription());
            publishedAt.setText("Date: "+mNews.get(listIndex).getPublishedAt());
            url = mNews.get(listIndex).getUrl();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
        }
    }
}
