package com.prt.sensor;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
//import com.badlogic.gdx.input.GestureDetector;
import com.prt.sensor.LinearAcceleration.SensorData;

public class TestSensor extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private boolean available;
	private FileWriter writer;
	private boolean opened = false;
	private static final String newLine = System.getProperty("line.separator");
	private LinearAcceleration sensor = null;
	
	public TestSensor(LinearAcceleration sensor) {
		this.sensor = sensor;
	}
	
	@Override
	public void create() {
//		Gdx.input
//				.setInputProcessor(new GestureDetector(new MyGestureListener(this)));

		Gdx.input.setInputProcessor(new InputHandler(this));
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		available = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);
	}

	public void toggleFile() {
		if (!opened) {
			sensor.register();
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");
			Date date = new Date();
			try {
				writer = new FileWriter("sdcard/Download/accelerometer_data"
						+ dateFormat.format(date) + ".csv");
				opened = true;
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		} else {
			sensor.unregister();
			try {
				writer.close();
				opened = false;
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render() {
		if(opened)
			Gdx.gl.glClearColor(1, 0, 0, 1);
		else
			Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		if (available && opened) {
			float accelX = Gdx.input.getAccelerometerX();
			float accelY = Gdx.input.getAccelerometerY();
			float accelZ = Gdx.input.getAccelerometerZ();
			System.out.println("x " + accelX + " y " + accelY + " z " + accelZ + "\n");
			try {
				SensorData data = sensor.getSensorData();
				if(data != null)
					writer.write(System.currentTimeMillis() + ", " + accelX + ", " + accelY + ", " + accelZ 
						+ ", " + data.x + ", " + data.y + ", " + data.z + newLine);
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.ApplicationAdapter#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
		if (opened)
			try {
				writer.close();
				opened = false;
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
	}

}
