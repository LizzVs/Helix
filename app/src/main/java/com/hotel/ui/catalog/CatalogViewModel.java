package com.hotel.ui.catalog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CatalogViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CatalogViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}