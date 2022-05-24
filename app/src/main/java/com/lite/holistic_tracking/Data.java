package com.lite.holistic_tracking;

public class Data {
    private String title;
    private String filmurl; // 영상 링크
    private Boolean star;
    private int resid; //이미지 값을 담을 변수

    public Data(String title, String filmurl, int resid){
        this.title = title;
        this.filmurl = filmurl;
        this.resid = resid;
        this.star = false;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getFilmUrl(){
        return filmurl;
    }
    public void setFilmUrl(String filmurl){
        this.filmurl = filmurl;
    }
    public Boolean getStar(){
        return star;
    }
    public void setStar(Boolean star){
        this.star = star;
    }
    public int getResId(){
        return resid;
    }
    public void setResId(int resid){
        this.resid = resid;
    }

}
