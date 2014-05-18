package com.prt.sensor.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.prt.sensor.TestSensor;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidLinearAcceleration androidLinearAcceleration = new AndroidLinearAcceleration(getApplicationContext());
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new TestSensor(androidLinearAcceleration), config);
	}
}
