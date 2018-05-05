package com.easyapps.onemoretestproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void finish() {
        super.finish();
        Log.i("flow", "finish: SecondActivity's finish() method is called");
    }
}
