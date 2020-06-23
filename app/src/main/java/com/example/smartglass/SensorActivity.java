package com.example.smartglass;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "FillDataActivity";

    public   TextView xAccValue, yAccValue, zAccValue, xGyroValue, yGyroValue, zGyroValue, accValue
            , gyroValue;
    private double xAcc, yAcc, zAcc, xGyro, yGyro, zGyro;
    private  ArrayList<Integer> position = new ArrayList<>();
    private  ArrayList<Integer> movement = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    private Date currenttime = Calendar.getInstance().getTime();
    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private String strdate = dateFormat.format(currenttime);




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Layout
        xAccValue = (TextView) findViewById(R.id.xavalue);
        yAccValue = (TextView) findViewById(R.id.yavalue);
        zAccValue = (TextView) findViewById(R.id.zavalue);
        xGyroValue = (TextView) findViewById(R.id.xgvalue);
        yGyroValue = (TextView) findViewById(R.id.ygvalue);
        zGyroValue = (TextView) findViewById(R.id.zgvalue);
        gyroValue = (TextView) findViewById(R.id.wynvalue);
        accValue = (TextView) findViewById(R.id.wyn_value);
        Button button2 = findViewById(R.id.button2);

        final AnalyseData analyseData = new AnalyseData(position,movement);
        final SendJson sendJson = new SendJson(position,movement);

        //Obsługa przycisku (tworzenie i wysyłanie jsona na serwer)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendJson.json(currentDate,analyseData.getAndroidVersion(),
                            analyseData.check_activity());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //Obsługa sensorów
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (gyroscope != null) {
            sensorManager.registerListener(SensorActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        } else xAccValue.setText("Not connected");

        if (accelerometer != null) {
            sensorManager.registerListener(SensorActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else xAccValue.setText("Not connected");
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        DispalyInfo dispalyInfo = new DispalyInfo(xAcc,yAcc,zAcc,xGyro,yGyro,zGyro,xAccValue,
                yAccValue,zAccValue,xGyroValue,yGyroValue,zGyroValue,gyroValue,accValue,position
                ,movement);

        //Zaokrąglenie wartośći z sensorów do dwóch miejs po przecinku
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            xAcc = Math.round((double) sensorEvent.values[0] * 100.0) / 100.0;
            yAcc = Math.round((double) sensorEvent.values[1] * 100.0) / 100.0;
            zAcc = Math.round((double) sensorEvent.values[2] * 100.0) / 100.0;
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {

            xGyro = Math.round((double) sensorEvent.values[0] * 100.0) / 100.0;
            yGyro = Math.round((double) sensorEvent.values[1] * 100.0) / 100.0;
            zGyro = Math.round((double) sensorEvent.values[2] * 100.0) / 100.0;

        }
        //Wyświetlanie wartości z sensorów
        dispalyInfo.displaySensorsValues();

        //Wyświetlanie konkretnego położenia i przemieszczenia
        dispalyInfo.changeOnParticularValues();

    }

}


