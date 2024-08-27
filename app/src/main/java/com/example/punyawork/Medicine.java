package com.example.punyawork;
class Medicine {
    private String name;
    private int imageResourceId;
    private String pack;
    private String vpack;
    private String appl;
    private String vappl;
    private  String price;
    private String  vprice;

   public static final Medicine[] medicines={
            new Medicine("Paracetamol",R.drawable.ajpgm3,"Packaging Size:"," 10 X 10 Tablets",
                    "Application/Uses","Fever","Price","Rs 60/Pack"),
            new Medicine("Ondensetron",R.drawable.ajpgm2,"Packaging Size:"," 10 X 10 Tablets",
                    "Application/Uses","Voimiting","Price","Rs 60/Pack"),
            new Medicine("Benadryl",R.drawable.apngm4,"Packaging Size:"," 10 X 10 Tablets",
                    "Application/Uses","Dry Cough","Price","Rs 170/Pack"),
           new Medicine("Fioricet",R.drawable.apngm5,"Packaging Size:"," 10 X 10 Tablets",
                   "Application/Uses","Headache","Price","Rs 320/Pack"),
           new Medicine("Benadryl Syrup",R.drawable.ajpgm1,"Bottle Size:","100 ml",
                   "Application/Uses","Dry Cough","Price"," Rs 60/Pack")
   };
   private Medicine(String name, int imageResourceId,String pack,String vpack,String appl,String vappl,
                    String price,String vprice){
       this.name=name;
       this.imageResourceId=imageResourceId;
       this.pack=pack;
       this.vpack=vpack;
       this.appl=appl;
       this.vappl=vappl;
       this.price=price;
       this.vprice=vprice;
   }

   public String getName(){
       return this.name;
   }
    public int getImageResourceId(){
       return this.imageResourceId;
   }
    public  String getPack(){
       return this.pack;

   }
    public  String getVpack(){
       return  this.vpack;
   }
    public String getAppl(){
       return this.appl;
   }
    public String getVappl(){
       return this.vappl;
   }
    public String getPrice(){
       return this.price;
   }
    public String getVprice(){
       return this.vprice;
   }


}
