package com.example.android.medx;

public class ItemObjects {
    private String name;
    private String subname;
    private int photo;

    public ItemObjects(String name, int photo) {
        this.name = name;

        this.photo = photo;
    }

    public String getName() {
        return name;
    }
   // public String getSubname() {
     //   return subname;
    //}

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
