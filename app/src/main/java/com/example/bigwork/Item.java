package com.example.bigwork;

public class Item {

    private int id;
    private String curTitle;
    private String curUrl;
    private int tag;

    public Item() {
        this.curTitle = "";
        this.curUrl = "";
        this.tag = 0;
    }

    public Item(String curTitle, String curUrl, int curTag) {
        this.curTitle = curTitle;
        this.curUrl = curUrl;
        this.tag = curTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurTitle() {
        return curTitle;
    }

    public void setCurTitle(String curTitle) {
        this.curTitle = curTitle;
    }

    public String getCurUrl() {
        return curUrl;
    }

    public void setCurUrl(String curUrl) {
        this.curUrl = curUrl;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
