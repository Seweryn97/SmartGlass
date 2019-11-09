package com.example.smartglass;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG= "MainActivity";

    private SensorManager sensorManager;
    Sensor accelerometr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometr = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometr, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registered accelerometer listener");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent ) {
        if(sensorEvent.values[0]>0 && sensorEvent.values[2]>0) Log.d(TAG,"Góra, Prawo");
        else if (sensorEvent.values[0]>0 && sensorEvent.values[2]<0) Log.d(TAG,"Góra, Lewo");
        else if (sensorEvent.values[0]<0 && sensorEvent.values[2]>0) Log.d(TAG,"Dół, Prawo");
        else Log.d(TAG,"Dół,Lewo");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
