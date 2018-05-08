package com.easyapps.onemoretestproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.greenrobot.eventbus.EventBus;


public class FirstActivity extends AppCompatActivity {

    Runnable runnable;
    Handler handler;
    private InterstitialAd mInterstitialAd;
    private ImageView splash1, splash2;
    private TextView splashTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //---------------------------------------------------
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        //------------------------------------------------------------
        splash1 = findViewById(R.id.splash1);
        splash2 = findViewById(R.id.splash2);
        splashTextView = findViewById(R.id.splashTextView);

        //----------------------------------------------------
        runnable = new Runnable() {
            @Override
            public void run() {
                splash1.setVisibility(View.GONE);
                splash2.setVisibility(View.GONE);
                splashTextView.setVisibility(View.GONE);
//                Intent i = new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(i);
//                finish();
            }
        };
        //-------------------------------------------------------------------------

        handler = new Handler();
        handler.postDelayed(runnable, 6000);
        //----------------------------------------------------------

    }

    public void connectButton(View view) {

    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
                showInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                Log.i("flow", "onAdClosed: ");
                CustomMessageEvent event = new CustomMessageEvent();
                EventBus.getDefault().post(event);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadInterstitial();
                    }
                }, 18000);

            }
        });

        return interstitialAd;
    }


    private void showInterstitial() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void loadInterstitial() {

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }
    //--------------------------------------------------
    @Override
    protected void onPause() {
        super.onPause();
        cancelSplash();
    }

    //------------------------------------------------------
    private void cancelSplash() {
        if (splash1.getVisibility() == View.VISIBLE) {
            handler.removeCallbacks(runnable);
            splash1.setVisibility(View.GONE);
            splash2.setVisibility(View.GONE);
            splashTextView.setVisibility(View.GONE);
        }
    }
    //----------------------------------------------------------------------
}
