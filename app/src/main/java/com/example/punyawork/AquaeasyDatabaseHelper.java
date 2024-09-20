package com.example.punyawork;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AquaeasyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME ="Aquaeasy21";
    public static final int DB_VERSION =14;
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
                    "Total_Price INTEGER,Payment_Mode TEXT);");}
        if(oldVersion<2){
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
        }
        if (oldVersion<8){
            db.execSQL("ALTER TABLE TrainWaterOrder ADD COLUMN Rate INTEGER;");
        }
        if (oldVersion<9){
            db.execSQL("CREATE TABLE BusWaterOrder (Id INTEGER PRIMARY KEY, Category TEXT,Product_Name TEXT," +
                    "BUS_NO INTEGET,Seat_No INTEGER,BUS_STOP_NO INTEGER,Quantity INTEGER," +
                    "Number_of_Bottle INTEGER,Number_of_Packet INTEGER,Rate INTEGER,Total_Price INTEGER,Payment_Mode TEXT);");
        }
        if (oldVersion<10){
            db.execSQL("CREATE TABLE PlatformWaterOrder (Id INTEGER PRIMARY KEY, Category TEXT,Product_Name TEXT," +
                    "Passenger_Name TEXT,PLATFORM_No INTEGER,ADN_No TEXT,Mobile_No TEXT,Quantity INTEGER," +
                    "Number_of_Bottle INTEGER,Number_of_Packet INTEGER,Number_of_Bed INTEGER,Rate INTEGER,Total_Price INTEGER,Payment_Mode TEXT);");
        }

        if (oldVersion<11){
            db.execSQL("CREATE TABLE UserDetail (Id INTEGER PRIMARY KEY,USER_NAME TEXT," +
                    "USER_EMAIL TEXT,USER_Mobile_No TEXT,USER_ADDRESS TEXT);");
            ContentValues contentValues=new ContentValues();
            contentValues.put("Id",1);
            contentValues.put("USER_NAME","Aquaeasy");
            contentValues.put("USER_EMAIL","aquaeasy786@gmail.com");
            contentValues.put("USER_Mobile_No","8507064152");
            contentValues.put("USER_ADDRESS","New Delhi sector 4");
            db.insert("UserDetail",null,contentValues);
        }
        if (oldVersion<12){
            db.execSQL("ALTER TABLE TrainWaterOrder ADD COLUMN Sync_Status INTEGER;");
            db.execSQL("ALTER TABLE PlatformWaterOrder ADD COLUMN Sync_Status INTEGER;");
            db.execSQL("ALTER TABLE BusWaterOrder ADD COLUMN Sync_Status INTEGER;");
        }
        if (oldVersion<13){
            db.execSQL("ALTER TABLE TrainWaterOrder ADD COLUMN Order_Date TEXT;");
            db.execSQL("ALTER TABLE PlatformWaterOrder ADD COLUMN Order_Date TEXT;");
            db.execSQL("ALTER TABLE BusWaterOrder ADD COLUMN Order_Date TEXT;");
        }
        if (oldVersion<14){
            db.execSQL("ALTER TABLE TrainWaterOrder ADD COLUMN Order_Time TEXT;");
            db.execSQL("ALTER TABLE PlatformWaterOrder ADD COLUMN Order_Time TEXT;");
            db.execSQL("ALTER TABLE BusWaterOrder ADD COLUMN Order_Time TEXT;");
        }

    }

}
