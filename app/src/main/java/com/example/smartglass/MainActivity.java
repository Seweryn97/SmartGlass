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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.view.KeyCharacterMap.ALPHA;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    Sensor accelerometer;
    Sensor gyroscope;
    TextView xavalue, yavalue,zavalue, xgvalue,ygvalue,zgvalue, wynvalue, wyn_value;
    double xa;
    double ya;
    double za;
    double xg;
    double yg;
    double zg;
    double sen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xavalue = (TextView) findViewById(R.id.xavalue);
        yavalue = (TextView) findViewById(R.id.yavalue);
        zavalue = (TextView) findViewById(R.id.zavalue);
        xgvalue = (TextView) findViewById(R.id.xgvalue);
        ygvalue = (TextView) findViewById(R.id.ygvalue);
        zgvalue = (TextView) findViewById(R.id.zgvalue);
        wynvalue = (TextView) findViewById(R.id.wynvalue);
        wyn_value = (TextView) findViewById(R.id.wyn_value);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (gyroscope != null) {
            sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        } else xavalue.setText("Not connected");

        if (accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else xavalue.setText("Not connected");
    }

    protected float[] lowPass(float[] input, float[] output){
        if(output==null) return input;
        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }
    public static DecimalFormat df = new DecimalFormat("0.00");


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Date currenttime= Calendar.getInstance().getTime();
        DateFormat dateFormat= new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strdate= dateFormat.format(currenttime);

        Sensor sensor = sensorEvent.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            xa= Math.round((double)sensorEvent.values[0]*100.0)/100.0;
            ya= Math.round((double)sensorEvent.values[1]*100.0)/100.0;
            za= Math.round((double)sensorEvent.values[2]*100.0)/100.0;
        }
        else if(sensor.getType()==Sensor.TYPE_GYROSCOPE) {

            xg= Math.round((double)sensorEvent.values[0]*100.0)/100.0;
            yg= Math.round((double)sensorEvent.values[1]*100.0)/100.0;
            zg= Math.round((double)sensorEvent.values[2]*100.0)/100.0;

        }
        xavalue.setText("X: "+xa);
        yavalue.setText("Y: "+ya);
        zavalue.setText("Z: "+za);
        xgvalue.setText("Xg: "+xg);
        ygvalue.setText("Yg: "+yg);
        zgvalue.setText("Zg: "+zg);

        if(yg<0 && zg>0 && xg>0.1){
            wynvalue.setText("Ruch: Góra");

        }
        else if(xg <-0.1){
            wynvalue.setText("Ruch: Dół");

        }
        else if(yg<-0.1){
            wynvalue.setText("Ruch: Prawo");

        }
        else if(yg>0.1){
            wynvalue.setText("Ruch: Lewo");

        }
        else if(xg<0.05 && yg<0.05 && zg < 0.05){
            wynvalue.setText("Ruch: Bez Ruchu");
        }

        if(ya>9 && za<2 && za>-2 && xa<2 && xa>-2){
            wyn_value.setText("Położenie: Normalne");

        }
        else if(za<-2 && xa<2 && xa>-2){
            wyn_value.setText("Położenie: Góra");

        }
        else if(za>2 && xa<2 && xa>-2){
            wyn_value.setText("Położenie: Dół");

        }
        else if(za<-2 && xa>2 && ya>5 &&ya<10){
            wyn_value.setText("Położenie: Pochylony w Lewo");

        }
        else if(za<-2 && xa<-2 && ya>5 &&ya<10){
            wyn_value.setText("Położenie: Pochylony w Prawo");

        }


    }
}

