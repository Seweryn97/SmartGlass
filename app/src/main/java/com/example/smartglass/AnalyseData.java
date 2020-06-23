package com.example.smartglass;

import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AnalyseData {

    private ArrayList <Integer> position;
    private  ArrayList <Integer> movement;


    public  AnalyseData(ArrayList<Integer> pos, ArrayList <Integer> mov){
        this.position = pos;
        this.movement = mov;
    }



    //Pobranie informacjii o używanej wersji androida
    public String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return  sdkVersion + " (" + release +")";
    }

    //Wyzanczenie mody w kolekcjach
    public  int [] get_data() {
        int [] tab= new int[2];

        Collections.sort(position);
        Collections.sort(movement);

        int max=0;
        int curr=0;
        Integer currkey=null;
        Set<Integer> unique = new HashSet<Integer>(position);

        for(Integer key : unique){
            curr= Collections.frequency(position, key);

            if(max<curr){
                max = curr;
                currkey = key;
            }
        }

        int max2=0;
        int curr2=0;
        Integer currkey2=null;
        Set<Integer> unique2 = new HashSet<Integer>(movement);

        for(Integer key2 : unique2){
            curr2= Collections.frequency(movement, key2);

            if(max2<curr2){
                max2 = curr2;
                currkey2 = key2;
            }
        }
        tab[0]=currkey;
        tab[1]=currkey2;

        return tab;
    }

    //Analiza danych
    public String check_activity(){
        String data;
        if(get_data()[0]==1 && get_data()[1]==1){
            data="Zasypianie na siedzaco";
        }
        else if(get_data()[0]==1 && get_data()[1]==2){
            data="Przebudzanie";
        }
        else if(get_data()[0]==1 && get_data()[1]==3){
            data="Sapnie na lewym boku";
        }
        else if(get_data()[0]==1 && get_data()[1]==4){
            data="Spanie na prawym boku";
        }
        else if(get_data()[0]==1 && get_data()[1]==5){
            data="Zasypianie na siedząco";
        }
        else if(get_data()[0]==2 && get_data()[1]==2){
            data="Przysypinie";
        }
        else if(get_data()[0]==2 && get_data()[1]==3){
            data="Spanie na lewym boku";
        }
        else if(get_data()[0]==2 && get_data()[1]==4){
            data="Spanie na prawym boku";
        }
        else if(get_data()[0]==2 && get_data()[1]==5){
            data="Spanie podczas parcy na komputerze";
        }
        else if(get_data()[0]==3 && get_data()[1]==1){
            data="Spanie na prawym boku";
        }
        else if(get_data()[0]==3 && get_data()[1]==2){
            data="Przysypianie";
        }
        else if(get_data()[0]==3 && get_data()[1]==4){
            data="Przekręcanie się na prawy bok";
        }
        else if(get_data()[0]==3 && get_data()[1]==5){
            data="Przekręcanie się na prawy bok";
        }
        else if(get_data()[0]==4 && get_data()[1]==1){
            data="Spanie na lewym boku";
        }
        else if(get_data()[0]==4 && get_data()[1]==2){
            data="Przysypianie";
        }
        else if(get_data()[0]==4 && get_data()[1]==3){
            data="Przekrecanie się na lewy bok";
        }
        else if(get_data()[0]==4 && get_data()[1]==5){
            data="Przekręcanie sie na lewy bok";
        }
        else if(get_data()[0]==5 && get_data()[1]==1){
            data="Sapanie na siedząco";
        }
        else if(get_data()[0]==5 && get_data()[1]==2){
            data="Przysypianie";
        }
        else if(get_data()[0]==5 && get_data()[1]==4){
            data="Przekrecanie się na prawy bok";
        }
        else if(get_data()[0]==5 && get_data()[1]==5){
            data="Stablilny sen";
        }
        else data="Niezarejestrowny ruch";
        Log.d("TAG","data "+get_data()[0]+" data2 "+get_data()[1] +" info "+data);

        return data ;
    }
}
