package com.example.finalproject;

import java.io.Serializable;

public class Item implements Serializable {

    String image1_url;
    String image2_url;
    String image3_url;
    String title;
    String description;
    String price;
    String url;

    public Item(String image1_url,String image2_url,String image3_url, String title, String description,String price, String url){
        this.image1_url = image1_url;
        this.image2_url = image2_url;
        this.image3_url = image3_url;
        this.title = title;
        this.description = description;
        this.price =price;
        this.url = url;

    }

    public String getImage1_url(){
        return image1_url;
    }

    public String getImage2_url() {
        return image2_url;
    }

    public String getImage3_url() {
        return image3_url;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl(){
        return url;
    }



}
