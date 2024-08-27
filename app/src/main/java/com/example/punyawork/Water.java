package com.example.punyawork;
 public class Water {
     private String name;
     private String vname;
     private int imageResourceId;
     private String tds;
     private String vtds;
     private String ph;
     private String vph;
     private String temp;
     private String vtemp;
     private String rate;
     private String vrate;

     public static final Water [] waters={
             new Water("Aquaeasy",R.drawable.ajpg7,"Brand",
                     "TDS Range:","80 ppm to 100 ppm",
                     "PH Range:","6.8 to 7","Temperature Range:",
                     "10℃ to 15℃","Price:","Rs 3/Litre"),
             new Water("Aquaeasy",R.drawable.ajpg8,"Brand",
                     "TDS Range:","80 ppm to 100 ppm",
                     "PH Range:","6.8 to 7","Temperature Range:",
                     "10℃ to 15℃","Price:","Rs 3/Litre")
     };

     public Water(String name,int imageResourceId, String vname,  String tds, String vtds,
                  String ph, String vph, String temp, String vtemp,
                  String rate,String vrate) {
         this.name=name;
         this.vname=vname;
         this.imageResourceId=imageResourceId;
         this.tds=tds;
         this.vtds=vtds;
         this.ph=ph;
         this.vph=vph;
         this.temp=temp;
         this.vtemp=vtemp;
         this.rate=rate;
         this.vrate=vrate;
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
     public String getTds(){
         return  this.tds;
     }
     public  String getVtds(){
         return this.vtds;
     }
      public String getPh(){
         return this.ph;
      }
      public String getVph(){
         return this.vph;
      }
     public String getRate(){
         return this.rate;
     }
     public String getVrate(){
         return this.vrate;
     }
     public String getTemp(){
         return this.temp;
     }
     public String getVtemp(){
         return this.vtemp;
     }

 }
