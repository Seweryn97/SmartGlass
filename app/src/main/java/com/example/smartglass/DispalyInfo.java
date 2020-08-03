package com.example.smartglass;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * DisplayInfo is responsible for diplaying informations from gyroscope and accelerometer on app GUI
 * @params xAcc, yAcc, zAcc ... sets values from accelerometer and gyroscope
 * @params xAccValue, yAccValue - TextViews from GUI
 * @params position and movements are arraylists which contain values from accelerometer and gyroscope
 */

public class DispalyInfo {

    private double xAcc, yAcc, zAcc, xGyro, yGyro, zGyro;
    private TextView xAccValue, yAccValue, zAccValue, xGyroValue, yGyroValue, zGyroValue ,
            gyroVlue, accValue;
    private  ArrayList<Integer> position;
    private  ArrayList<Integer> movement ;
    

    public DispalyInfo(double xa, double ya, double za, double xg, double yg, double zg,
                       TextView xavalue,TextView yavalue, TextView zavalue,TextView xgvalue,
                       TextView ygvalue,TextView zgvalue,TextView wynvalue,TextView wyn_value,
                       ArrayList <Integer> position, ArrayList<Integer> movement){

        this.xAcc = xa;
        this.yAcc = ya;
        this.zAcc = za;
        this.xGyro = xg;
        this.yGyro = yg;
        this.zGyro = zg;
        this.xAccValue = xavalue;
        this.yAccValue = yavalue;
        this.zAccValue = zavalue;
        this.xGyroValue = xgvalue;
        this.yGyroValue = ygvalue;
        this.zGyroValue = zgvalue;
        this.gyroVlue = wynvalue;
        this.accValue = wyn_value;
        this.position = position;
        this.movement = movement;

    }

    // Displaying informations from acceleroemter and gyroscope
    public void displaySensorsValues (){
        xAccValue.setText("X: " + xAcc);
        yAccValue.setText("Y: " + yAcc);
        zAccValue.setText("Z: " + zAcc);
        xGyroValue.setText("Xg: " + xGyro);
        yGyroValue.setText("Yg: " + yGyro);
        zGyroValue.setText("Zg: " + zGyro);
        
    }

    //Adding informations to arraylists

    public void changeOnParticularValues(){

        if (yGyro < 0 && zGyro > 0 && xGyro > 0.1) {
            gyroVlue.setText("Ruch: Góra");
            movement.add(1);
        } else if (xGyro < -0.1) {
            gyroVlue.setText("Ruch: Dół");
            movement.add(2);
        } else if (yGyro < -0.1) {
            gyroVlue.setText("Ruch: Prawo");
            movement.add(3);
        } else if (yGyro > 0.1) {
            gyroVlue.setText("Ruch: Lewo");
            movement.add(4);
        } else if (xGyro < 0.05 && yGyro < 0.05 && zGyro < 0.05) {
            gyroVlue.setText("Ruch: Bez Ruchu");
            movement.add(5);
        }

        if (zAcc < -2 && xAcc < 2 && xAcc > -2) {
            accValue.setText("Położenie: Góra");
            position.add(1);
        } else if (zAcc > 2 && xAcc < 2 && xAcc > -2) {
            accValue.setText("Położenie: Dół");
            position.add(2);
        } else if (zAcc < -2 && xAcc > 2 && yAcc > 5 && yAcc < 10) {
            accValue.setText("Położenie: Pochylony w Lewo");
            position.add(3);
        } else if (zAcc < -2 && xAcc < -2 && yAcc > 5 && yAcc < 10) {
            accValue.setText("Położenie: Pochylony w Prawo");
            position.add(4);
        }
        else if (yAcc > 9 && zAcc < 2 && zAcc > -2 && xAcc < 2 && xAcc > -2) {
            accValue.setText("Położenie: Normalne");
            position.add(5);
        }

    }


}
