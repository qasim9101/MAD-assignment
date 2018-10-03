package com.thebigq.recyclerview;

public class ListItem {

    private String head;
    private String desc;
    private String img;

    public ListItem() { }

    public ListItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public ListItem(String head, String desc, String img) {
        this.head = head;
        this.desc = desc;
        this.img = img;
    }

    public String getHead() {
        return head;
    }
    public String getDesc() {
        return desc;
    }
    public String getImg() { return img; }
    public void setHead(String head) {
        this.head = head;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setImg(String img) {
        this.img = img;
    }
}
