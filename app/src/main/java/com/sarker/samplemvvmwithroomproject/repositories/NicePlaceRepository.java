package com.sarker.samplemvvmwithroomproject.repositories;

import android.util.Log;

import com.sarker.samplemvvmwithroomproject.models.NicePlace;
import com.sarker.samplemvvmwithroomproject.requests.NewsApiInterface;
import com.sarker.samplemvvmwithroomproject.requests.RetrofitService;
import com.sarker.samplemvvmwithroomproject.requests.responses.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NicePlaceRepository {
    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    private NewsApiInterface newsApi;

    public NicePlaceRepository(){
        newsApi = RetrofitService.cteateService(NewsApiInterface.class);
    }

    public static NicePlaceRepository getInstance(){
        if(instance == null)
        {
            instance = new NicePlaceRepository();
        }
        return instance;
    }

    public MutableLiveData<List<NicePlace>> getPlaces(){
        setNicePlaces();
        MutableLiveData <List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces(){
        dataSet.add(
                new NicePlace("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/tpsnoz5bzo501.jpg",
                        "Trondheim")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/qn7f9oqu7o501.jpg",
                        "Portugal")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/j6myfqglup501.jpg",
                        "Rocky Mountain National Park")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/0h2gm1ix6p501.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/k98uzl68eh501.jpg",
                        "Mahahual")
        );
        dataSet.add(
                new NicePlace("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                        "Frozen Lake")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/obx4zydshg601.jpg",
                        "Austrailia")
        );
    }


    public MutableLiveData<NewsResponse> getNews(String source, String key){
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();

        newsApi.getNewsList(source,key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                    Log.i("onResponse", "onResponse:.................. "+response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.i("onResponse", "onResponse:.................. "+"hoi nai");
                newsData.setValue(null);
            }
        });

        return newsData;
    }

}
