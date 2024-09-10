package com.example.punyawork;
public class Maindata {
    private String name;
    private int imageresourceId;
    public static final Maindata[] maindata={
            new Maindata("Water",R.drawable.ajpg8),
            new Maindata("Bed",R.drawable.abedjpg1),
            new Maindata("Steel",R.drawable.apng7),
            new Maindata("Medicine",R.drawable.apngm5),
            new Maindata("Plastic",R.drawable.ajpg5)
    };
    private Maindata(String name, int imageresourceId){
        this.name=name;
        this.imageresourceId=imageresourceId;
    }
    public String getName(){
        return name;
    }
    public int getImageresourceId(){
        return imageresourceId;
    }
}
