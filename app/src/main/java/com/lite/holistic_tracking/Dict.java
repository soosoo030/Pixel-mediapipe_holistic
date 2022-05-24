package com.lite.holistic_tracking;

import androidx.appcompat.app.AppCompatActivity;

public class Dict extends AppCompatActivity {
    String sub;
    int image;

    public String getSub(){return sub;}
    public void setSub(String s){sub=s;}
    public int getImage(){return image;}
    public void setImage(int im){image=im;}

    public Dict(String sub, int image){
        this.sub=sub;
        this.image=image;
    }
}
