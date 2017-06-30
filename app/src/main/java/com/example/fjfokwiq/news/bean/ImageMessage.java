package com.example.fjfokwiq.news.bean;

public class ImageMessage{
    private String path;
    private String name;
    private boolean isSelect;


    public ImageMessage(String path, String name,boolean isSelect) {
        this.path = path;
        this.name = name;
        this.isSelect=isSelect;
    }
    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
