package com.example.punyawork;

public class Pasta {
    private String name;
    private int imageResourceId;
    private String cap;
    private String vcap;
    private  String price;
    private String  vprice;
    private String weight;
    private String vweight;
    public static final Pasta[] pastas={
            new Pasta("Milton",R.drawable.ajph4,"Capacity:","1 Litre",
                    "Price:","300 Rupees","Weight","150 Grams"),
            new Pasta("Stainless",R.drawable.steel,"Capacity:","1 Litre",
                    "Price:","250 Rupees","Weight","200 Grams"),
            new Pasta("Milton pro",R.drawable.steel2,"Capacity:","1 Litre",
                    "Price:","325 Rupees","Weight","175 Grams"),
            new Pasta("H2O",R.drawable.apng6,"Capacity:","1 Litre",
                    "Price:","175 Rupees","Weight","225 Grams"),
            new Pasta("Milton Steel",R.drawable.apng7,"Capacity:","1 Litre",
                    "Price:","150 Rupees","Weight","250 Grams")
    };
    private Pasta(String name, int imageResourceId,String cap,String vcap
    ,String price,String vprice ,String weight,String vweight){
        this.name=name;
        this.imageResourceId=imageResourceId;
        this.cap=cap;
        this.vcap=vcap;
        this.price=price;
        this.vprice=vprice;
        this.weight=weight;
        this.vweight=vweight;

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
