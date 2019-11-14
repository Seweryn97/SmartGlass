package com.example.smartglass;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    Sensor accelerometer;
    Sensor gyroscope;
    TextView xvalue, yvalue, gyrovalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xvalue = (TextView) findViewById(R.id.xvalue);
        yvalue = (TextView) findViewById(R.id.yvalue);
        gyrovalue = (TextView) findViewById(R.id.gyrovalue);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (gyroscope != null) {
            sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        } else xvalue.setText("Not connected");

        //if (accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
       // } else xvalue.setText("Not connected");



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xvalue.setText("X: " + sensorEvent.values[0]);
            yvalue.setText("Y: " + sensorEvent.values[1]);
            Log.d(TAG,"X :"+sensorEvent.values[0]);
            Log.d(TAG," Y :"+sensorEvent.values[1]);

        }
        /*if(sensorEvent.values[0]>0 && sensorEvent.values[2]>0) Log.d(TAG,"Góra, Prawo");
        else if (sensorEvent.values[0]>0 && sensorEvent.values[2]<0) Log.d(TAG,"Góra, Lewo");
        else if (sensorEvent.values[0]<0 && sensorEvent.values[2]>0) Log.d(TAG,"Dół, Prawo");
        else Log.d(TAG,"Dół,Lewo")*/


        else if(sensor.getType()==Sensor.TYPE_GYROSCOPE)
        {
            gyrovalue.setText("Gyro: " + sensorEvent.values[1]);
            Log.d(TAG," Gyro :"+sensorEvent.values[1]);
        }
    }
}

