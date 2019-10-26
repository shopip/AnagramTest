package com.shofiq.anagramsdetect.model;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.shofiq.anagramsdetect.R;
import com.shofiq.anagramsdetect.Util.AnagramUtil;

import java.util.Arrays;


public class MainViewModel extends AndroidViewModel {

    /* firstText will be the value of first string that user inputs */
    private MutableLiveData<String> firstText = new MutableLiveData<>() ;
    /* secondText will be the value of second string that user inputs */
    private MutableLiveData<String> secondText = new MutableLiveData<>();
    /* resultText will be the value of result of the anagram test of the two strings that user inputs */
    private MutableLiveData<String> resultText = new MutableLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    // getter method for first text mutable data
    public MutableLiveData<String> getFirstText() {
        return firstText;
    }

    // getter method for second text mutable data
    public MutableLiveData<String> getSecondText() {
        return secondText;
    }

    // getter method for result text mutable data
    public MutableLiveData<String> getResultText() {
        return resultText;
    }

    // Check if first string of this class is anagram with second string of this class

    public void checkAnagram () {

        // checking if two string are anagram and setting result
        resultText.postValue(AnagramUtil.checkAnagram(firstText.getValue(),secondText.getValue()) ? this.getApplication().getString(R.string.anagram_pass) : this.getApplication().getString(R.string.anagram_fail) );

    }



}
