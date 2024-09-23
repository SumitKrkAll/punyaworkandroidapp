package com.example.punyawork;
public class Bed {
    private String name;
    private String vname;
    private int imageResourceId;
    private String categori;
    private String vcategory;
    private String timeforuse;
    private String vtimefortheuse;
    private String servicecharge;
    private String vservicecharge;
    private String durationforuse;
    private String vdurationforuse;
    public static final Bed [] beds={
            new Bed("Bedeasy",R.drawable.abedjpg1,"Brand","Category",
                    "Single Bed","Time for Use","7 Hours"
            ,"Service Charge","Rs 70/Bed","Duration For Use","10 Pm to 5 Am"),
            new Bed("Bedeasy",R.drawable.abedjpg2,"Brand","Category",
                    "Single Bed","Time for Use","7 Hours"
                    ,"Service Charge","Rs 70/Bed","Duration For Use","10 Pm to 5 Am"),
            new Bed("Bedeasy",R.drawable.abedjpg3,"Brand","Category",
                    "Single Bed","Time for Use","7 Hours"
                    ,"Service Charge","Rs 70/Bed","Duration For Use","10 Pm to 5 Am"),
            new Bed("Bedeasy",R.drawable.abedjpg4,"Brand","Category",
            "Single Bed","Time for Use","7 Hours"
            ,"Service Charge","Rs 70/Bed","Duration For Use","10 Pm to 5 Am"),
             new Bed("Bedeasy",R.drawable.abedjpg5,"Brand","Category",
            "Single Bed","Time for Use","7 Hours"
            ,"Service Charge","Rs 70/Bed","Duration For Use","10 Pm to 5 Am")

    };

    public Bed(String name,int imageResourceId, String vname,  String categori, String vcategory,
                 String timeforuse, String vtimefortheuse, String servicecharge, String vservicecharge,
                 String durationforuse,String vdurationforuse) {
        this.name=name;
        this.vname=vname;
        this.imageResourceId=imageResourceId;
        this.categori=categori;
        this.vcategory=vcategory;
        this.timeforuse=timeforuse;
        this.vtimefortheuse=vtimefortheuse;
        this.servicecharge=servicecharge;
        this.vservicecharge=vservicecharge;
        this.durationforuse=durationforuse;
        this.vdurationforuse=vdurationforuse;
    }
    public String getName(){
        return this.name;
    }
    public String getVname(){
        return this.vname;
    }
    public  int getImageResourceId(){
        return this.imageResourceId;
    }
    public String getCategori(){
        return  this.categori;
    }
    public  String getVcategory(){
        return this.vcategory;
    }
    public String getTimeforuse(){
        return this.timeforuse;
    }
    public String getVtimefortheuse(){
        return this.vtimefortheuse;
    }
    public String getServicecharge(){
        return this.servicecharge;
    }
    public String getVservicecharge(){
        return this.vservicecharge;
    }
    public String getDurationforuse(){
        return this.durationforuse;
    }
    public String getVdurationforuse(){
        return this.vdurationforuse;
    }
}
