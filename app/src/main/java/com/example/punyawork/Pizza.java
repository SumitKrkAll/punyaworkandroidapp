package com.example.punyawork;

public class Pizza {
    private String name;
    private int imageResourceId;
    private String cap;
    private String vcap;
    private  String price;
    private String  vprice;
    private String weight;
    private String vweight;
    public static final Pizza[] pizzas={
            new Pizza("Ganga",R.drawable.ajpg5,"Capacity:","1 Litre",
                    "Price:","40 Rupees","Weight","250 Grams"),
            new Pizza("Aqua",R.drawable.apng5,"Capacity:","1 Litre",
                    "Price:","25 Rupees","Weight","200 Grams"),
            new Pizza("Simple",R.drawable.jag,"Capacity:","1 Litre",
                    "Price:","20 Rupees","Weight","150 Grams"),
            new Pizza("H2O",R.drawable.apng11,"Capacity:","1 Litre",
                    "Price:","13 Rupees","Weight","175 Grams"),
            new Pizza("Himalya",R.drawable.hard,"Capacity:","1 Litre",
                    "Price:","25 Rupees","Weight","100 Grams")
    };
    private Pizza(String name, int imageResourceId,String cap,String vcap
    ,String price,String vprice,String weight,String vweight){
    this.name=name;
    this.imageResourceId=imageResourceId;
    this.cap=cap;
    this.vcap=vcap;
    this.price=price;
    this.vprice=vprice;
    this.vweight=vweight;
    this.weight=weight;
    }
    public String getName(){
        return this.name;
    }
    public int getImageResourceId(){
        return this.imageResourceId;
    }
    public String getCap(){
        return this.cap;
    }
    public String getVcap(){
        return this.vcap;
    }
    public String getPrice(){
        return this.price;

    }
    public String getVprice(){
        return this.vprice;

    }
    public String getWeight(){
        return this.weight;

    }
    public String getVweight (){
        return this.vweight;
    }
}
