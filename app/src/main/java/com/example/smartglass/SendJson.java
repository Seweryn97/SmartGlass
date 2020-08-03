package com.example.smartglass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Send Json is responsible for create json format with data from sensor
 */

public class SendJson  {

    private JSONObject jsonParam = new JSONObject();
    private ArrayList<Integer> position;
    private ArrayList<Integer> movement;

    public SendJson(ArrayList <Integer> position, ArrayList <Integer> movement){
        this.position = position;
        this.movement = movement;

    }


    public void json(String currentDate, String android, String activity) throws JSONException{
        jsonParam.put("Imie", FillDataActivity.editText.getText().toString());
        jsonParam.put("Wiek", FillDataActivity.editText2.getText().toString());
        jsonParam.put("Miasto", FillDataActivity.editText3.getText().toString());
        jsonParam.put("Wersja_Androida",android);
        jsonParam.put("Data_i_Godzina",currentDate);
        jsonParam.put("Aktywnosc", activity);
        new SendDeviceDetails().execute("http://192.168.43.12:8080/MyWeb/json_response",jsonParam.toString());

        position.clear();
        movement.clear();
    }

}
