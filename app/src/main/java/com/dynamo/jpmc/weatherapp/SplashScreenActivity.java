package com.dynamo.jpmc.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by tanmaythakar on 3/13/23.
 */
public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
