package com.example.punyawork;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AquaeasyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME ="Aquaeasy21";
    public static final int DB_VERSION =9;
    AquaeasyDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db,0,DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion); }
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion,int newVersion){
        if (oldVersion<1){
            db.execSQL("CREATE TABLE AQUAEASY21 (Id INTEGER PRIMARY KEY, Product_Name TEXT," +
                    "Train_No INTEGER,Coach_No TEXT,Seat_No INTEGER,PNR_No TEXT,Quantity INTEGER," +
                    "Total_Price INTEGER,Payment_Mode TEXT);");
            ContentValues contentValues=new ContentValues();
            contentValues.put("Id",1);
            contentValues.put("Product_Name","waterpure");
            contentValues.put("Train_No",12553);
            contentValues.put("Coach_No","s6");
            contentValues.put("Seat_No",66);
            contentValues.put("PNR_No","spq1125");
            contentValues.put("Quantity",6);
            contentValues.put("Total_Price",18);
            contentValues.put("Payment_Mode","paytm");
            db.insert("AQUAEASY21",null,contentValues);}
        if(oldVersion<2){
            ContentValues ontentValues=new ContentValues();
            ontentValues.put("Id",2);
            ontentValues.put("Product_Name","bottle");
            ontentValues.put("Train_No",12653);
            ontentValues.put("Coach_No","s9");
            ontentValues.put("Seat_No",88);
            ontentValues.put("PNR_No","sprqr5598");
            ontentValues.put("Quantity",8);
            ontentValues.put("Total_Price",24);
            ontentValues.put("Payment_Mode","cash");
            db.insert("AQUAEASY21",null,ontentValues);
        }
        if (oldVersion<3){
            db.execSQL("ALTER TABLE AQUAEASY21 ADD COLUMN Category TEXT;");
        }
        if (oldVersion<4){
            db.execSQL("ALTER TABLE AQUAEASY21 ADD COLUMN Number_of_Bottle INTEGER;");
            db.execSQL("ALTER TABLE AQUAEASY21 ADD COLUMN Number_of_Packet INTEGER;");
            db.execSQL("ALTER TABLE AQUAEASY21 ADD COLUMN Rate INTEGER;");
        }
        if (oldVersion<5){
            db.execSQL("ALTER TABLE AQUAEASY21 ADD COLUMN BUS_NO INTEGET;");
            db.execSQL("ALTER TABLE AQUAEASY21 ADD COLUMN BUS_STOP_NO INTEGER ;");
        }
        if (oldVersion<6){
            db.execSQL("CREATE TABLE TrainWaterOrder (Id INTEGER PRIMARY KEY, Category TEXT,Product_Name TEXT," +
                    "Train_No INTEGER,Coach_No TEXT,Seat_No INTEGER,PNR_No TEXT,Quantity INTEGER," +
                    "Number_of_Bottle INTEGER,Number_of_Packet INTEGER,Total_Price INTEGER,Payment_Mode TEXT);");
        }

        if(oldVersion<7){
            ContentValues ontentValues=new ContentValues();
            ontentValues.put("Id",2);
            ontentValues.put("Category","Water");
            ontentValues.put("Product_Name","Aquaeasy");
            ontentValues.put("Train_No",12653);
            ontentValues.put("Coach_No","s9");
            ontentValues.put("Seat_No",88);
            ontentValues.put("PNR_No","sprqr5598");
            ontentValues.put("Quantity",8);
            ontentValues.put("Total_Price",24);
            ontentValues.put("Payment_Mode","cash");
            db.insert("TrainWaterOrder ",null,ontentValues);
        }
        if (oldVersion<8){
            db.execSQL("ALTER TABLE TrainWaterOrder ADD COLUMN Rate INTEGER;");
        }
        if (oldVersion<9){
            db.execSQL("CREATE TABLE BusWaterOrder (Id INTEGER PRIMARY KEY, Category TEXT,Product_Name TEXT," +
                    "BUS_NO INTEGET,Seat_No INTEGER,BUS_STOP_NO INTEGER,Quantity INTEGER," +
                    "Number_of_Bottle INTEGER,Number_of_Packet INTEGER,Rate INTEGER,Total_Price INTEGER,Payment_Mode TEXT);");
            ContentValues contentValues=new ContentValues();
            contentValues.put("Id",1);
            contentValues.put("Category","Water");
            contentValues.put("Product_Name","waterpure");
            contentValues.put("BUS_NO",12553);
            contentValues.put("Seat_No",66);
            contentValues.put("BUS_STOP_NO",12);
            contentValues.put("Quantity",6);
            contentValues.put("Rate",3);
            contentValues.put("Total_Price",18);
            contentValues.put("Payment_Mode","paytm");
            db.insert("BusWaterOrder",null,contentValues);
        }


    }
}
