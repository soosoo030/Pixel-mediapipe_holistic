package com.lite.holistic_tracking;

import androidx.appcompat.app.AppCompatActivity;

public class Dict extends AppCompatActivity {
    String Word;
    String image;
    String videoURL;

    public String getWord(){return Word;}
    public void setWord(String s){Word = s;}
    public String getImage(){return image;}
    public void setImage(String im){image=im;}
    public String getVideoURL(){return videoURL;}
    public void setVideoURL(String vu){videoURL=vu;}

    public Dict(String Word, String image,String videoURL){
        this.Word=Word;
        this.image=image;
        this.videoURL=videoURL;
    }
}
