package com.example.mynewsdaily.bean;


import java.io.Serializable;

public class NewsBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private String summary;
    private String icon;
    private String stamp;
    private String title;
    private int    nid;
    private String link;
    private int    type;

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setType(int type) {
        this.type = type;
    }
    //    public NewsBean(int type, int nid, String stamp,
//                String title, String icon, String summary, String link) {
//        super();
//        this.type = type;
//        this.nid = nid;
//        this.stamp = stamp;
//        this.title = title;
//        this.icon = icon;
//        this.summary = summary;
//        this.link = link;
//    }

    public String getIcon() {

        return icon;
    }

    public String getStamp() {
        return stamp;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public int getNid() {
        return nid;
    }

    public int getType() {
        return type;
    }
}
