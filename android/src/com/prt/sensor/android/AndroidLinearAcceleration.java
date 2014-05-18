package com.prt.sensor.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.prt.sensor.LinearAcceleration;

public class AndroidLinearAcceleration implements LinearAcceleration, SensorEventListener {

	private float x, y, z;
	private long timestamp;
	private SensorManager mSensorManager;
	private Sensor mSensor;
	
	public AndroidLinearAcceleration(Context context) {
		mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
	}

	@Override
	public SensorData getSensorData() {
		return new SensorData(timestamp, x, y, z);
	}

	@Override
	public void register() {
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public void unregister() {
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];
		timestamp = event.timestamp;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	

}
