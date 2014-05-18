package com.prt.sensor;

public interface LinearAcceleration {
	public SensorData getSensorData();
	public void register();
	public void unregister();
	
	public class SensorData {
		public final float x, y, z;
		public final long timestamp;
		public SensorData(long timestamp, float x, float y, float z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.timestamp = timestamp;
		}		
	}
}
