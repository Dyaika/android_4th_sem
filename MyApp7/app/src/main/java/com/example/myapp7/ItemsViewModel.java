package com.example.myapp7;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemsViewModel extends ViewModel {
    private MutableLiveData<List<Item>> collection = new MutableLiveData<>(new ArrayList<>());

    public void loadCollection(String login){
        collection.setValue(Repository.getFumosByLogin(login));
    }
    public MutableLiveData<List<Item>> getCollection() {
        return collection;
    }
}
