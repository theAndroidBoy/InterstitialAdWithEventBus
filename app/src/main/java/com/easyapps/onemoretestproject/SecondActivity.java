package com.easyapps.onemoretestproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EventBus.getDefault().register(this);

    }
//-----------------------------------

    @Subscribe
    public void onEvent(CustomMessageEvent event) {
        finish();
    }
//--------------------------------------------------------------------
    @Override
    public void finish() {
        super.finish();
        Log.i("flow", "finish: SecondActivity's finish() method is called");
    }
}
