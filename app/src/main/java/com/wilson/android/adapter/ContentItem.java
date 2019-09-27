package com.wilson.android.adapter;

public class ContentItem {

    private String name , phone, title;
    private boolean isHeader, isFavorite;

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setHeader(String header){
        this.title = header;
    }

    public void setIsHeader(boolean isHeader){
        this.isHeader = isHeader;
    }

    public void setIsFavorite(boolean isFavorite){
        this.isFavorite = isFavorite;
    }

    public String getHeader(){
        return this.title;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public boolean getIsHeader(){
        return this.isHeader;
    }

    public boolean getIsFavorite(){
        return this.isFavorite;
    }
}
