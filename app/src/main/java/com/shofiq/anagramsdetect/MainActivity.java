package com.shofiq.anagramsdetect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.shofiq.anagramsdetect.databinding.ActivityMainBinding;
import com.shofiq.anagramsdetect.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    //viewModel
    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting ViewModel to activity
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // This will bind the data and will set the activity layout
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // Setting viewmodel to binding object, this will create a two way data binding between layout and viewmodel
        binding.setViewModel(mainViewModel);
        //Setting lifecycle owner to activity , the livedatas of viewmodel and bindings will be activity lifecycle aware
        binding.setLifecycleOwner(this);

        //Setting observer for text change to remove previous result string if there is any
        mainViewModel.getFirstText().observe(this,textObserver);
        mainViewModel.getSecondText().observe(this,textObserver);

        //submmit button click handling
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking for anagram result
                mainViewModel.checkAnagram();

            }
        });


    }
    //Text observer of livedata
    Observer<String> textObserver = new Observer<String>() {
        @Override
        public void onChanged(String s) {

            //text changes so setting previous result empty
            mainViewModel.getResultText().setValue("");

        }
    };



}
