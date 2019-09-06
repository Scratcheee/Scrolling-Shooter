package com.lawrencekaul.scrollingshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    GameEngine mGameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        mGameEngine = new GameEngine(this, size);
        setContentView(mGameEngine);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGameEngine.startThread();
    }

    @Override
    protected void onPause() {
        super.onPause();;
        mGameEngine.stopThread();
    }
}
