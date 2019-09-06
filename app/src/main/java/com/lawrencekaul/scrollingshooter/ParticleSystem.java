package com.lawrencekaul.scrollingshooter;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Random;

class ParticleSystem {
	
	float mDuration;
	ArrayList<Particle> mParticles;
	Random random = new Random();
	boolean mIsRunning = false;
	
	void init(int numParticles) {
		mParticles = new ArrayList<>();
		// Create the particles
		
		for (int i = 0; i < numParticles; i++) {
			float angle = (random.nextInt(360));
			angle = angle * 3.14f / 180.f;
			float speed = (random.nextInt(20) + 1);
			
			PointF direction;
			
			direction = new PointF((float)Math.cos(angle) * speed,
					(float)Math.sin(angle) * speed);
			
			mParticles.add(new Particle(direction));
		}
	}
	
	void update(long fps) {
		mDuration -= (1f/fps);
		
		for (Particle p : mParticles) {
			p.update();
		}
		
		if (mDuration < 0){
			mIsRunning = false;
		}
	}
}
