package com.sarker.samplemvvmwithroomproject.viewmodels;

import android.util.Log;

import com.sarker.samplemvvmwithroomproject.models.NicePlace;
import com.sarker.samplemvvmwithroomproject.repositories.NicePlaceRepository;
import com.sarker.samplemvvmwithroomproject.requests.responses.NewsResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private MutableLiveData<NewsResponse> newsResponses;
    private NicePlaceRepository  mRepo;
    private final String API_KEY = "a17f7388dd5f47c8902cc1285fb0b6f0";

    public void init(){
        if(mNicePlaces != null){
            return;
        }else{
            mRepo = NicePlaceRepository.getInstance();
           // mNicePlaces = mRepo.getPlaces();
            newsResponses = mRepo.getNews("us", API_KEY);
          }

    }
//    public LiveData<List<NicePlace>> getPlaces(){
//        return mNicePlaces;
//    }

    public LiveData<NewsResponse> getNews(){
        return newsResponses;
    }

}
