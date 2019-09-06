package com.lawrencekaul.scrollingshooter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

class GameEngine extends SurfaceView implements Runnable, GameStarter, GameEngineBroadcaster {
	UIController mUIController;
	HUD mHUD;
	Renderer mRenderer;
	private Thread mThread = null;
	private long mFPS;
	private ArrayList<InputObserver> inputObservers = new ArrayList();
	private GameState mGameState;
	private SoundEngine mSoundEngine;
	
	public GameEngine(Context context, Point size) {
		super(context);
		
		mUIController = new UIController(this);
		mGameState = new GameState(this, context);
		mSoundEngine = new SoundEngine(context);
		mHUD = new HUD(size);
		mRenderer = new Renderer(this);
	}
	
	@Override
	public void run() {
		while (mGameState.getThreadRunning()) {
			long frameStartTime = System.currentTimeMillis();
			if (!mGameState.getPaused()) {
				// Update all the game objects here
				
				
			}
			
			// Draw all the game objects here
			mRenderer.draw(mGameState, mHUD);
			
			
			// Measure the frames per second
			long timeThisFrame = System.currentTimeMillis() - frameStartTime;
			
			if (timeThisFrame >= 1) {
				final int MILLIS_IN_SECOND = 1000;
				mFPS = MILLIS_IN_SECOND / timeThisFrame;
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		// Handle the players input here
		for (InputObserver o : inputObservers) {
			o.handleInput(motionEvent, mGameState, mHUD.getControls());
		}
		
		
		return true;
	}
	
	public void stopThread() {
		
		mGameState.stopEverything();
		
		try {
			mThread.join();
		} catch (InterruptedException e) {
			Log.e("Exception", "stopThread" + e.getMessage());
		}
	}
	
	public void startThread() {
		mGameState.startThread();
		
		mThread = new Thread(this);
		mThread.start();
	}
	
	public void deSpawnReSpawn() {
	
	}
	
	// For the game engine broadcaster interface
	public void addObserver(InputObserver o) {
		inputObservers.add(o);
	}
	
	
}
