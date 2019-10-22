package com.sarker.samplemvvmwithroomproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sarker.samplemvvmwithroomproject.adapters.NewsAdapter;
import com.sarker.samplemvvmwithroomproject.adapters.RecyclerAdpter;
import com.sarker.samplemvvmwithroomproject.models.NicePlace;
import com.sarker.samplemvvmwithroomproject.requests.responses.NewsResponse;
import com.sarker.samplemvvmwithroomproject.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";



    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private RecyclerAdpter mAdapter;
    private NewsAdapter newsAdapter;
    private ProgressBar mProgressBar;
    public MainActivityViewModel mainActivityViewModel;
    ArrayList<NewsResponse.ArticlesBean> articleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();

//        mainActivityViewModel.getPlaces().observe(this, new Observer<List<NicePlace>>() {
//            @Override
//            public void onChanged(List<NicePlace> nicePlaces) {
//                mAdapter.notifyDataSetChanged();
//            }
//        });

        mainActivityViewModel.getNews().observe(this, new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                List<NewsResponse.ArticlesBean> newsArticles = newsResponse.getArticles();
                articleArrayList.addAll(newsArticles);
                newsAdapter.notifyDataSetChanged();
            }
        });
       // initRecyclerView();
        setupRecyclerView();
    }

//    private void initRecyclerView(){
//        mAdapter = new RecyclerAdpter(this, mainActivityViewModel.getPlaces().getValue());
//        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//    }
private void setupRecyclerView() {
    if (newsAdapter == null) {
        newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(newsAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);
    } else {
        newsAdapter.notifyDataSetChanged();
    }
}

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
