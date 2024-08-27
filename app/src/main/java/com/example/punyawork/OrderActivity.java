package com.example.punyawork;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    EditText  tnum,cnum,snum,pnrnum;
     Button Continue;
     public String category;
     public ContentValues contentValues= new ContentValues();
    public static final String WATER_ID="waterId";
    public static final String PIZZA_ID="pizzaID";
    public static final String PASTA_ID="pastaId";
    public static final String MEDICINE_ID="medicineID";
    public int TRAINWATER=1;
    public int TRAINMIX=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        // set the toolbar as activity appbar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // get the all edit view feiled value
        tnum=(EditText) findViewById(R.id.editText2);
        cnum=(EditText) findViewById(R.id.editText3);
        snum=(EditText) findViewById(R.id.editText4);
        pnrnum=(EditText)findViewById(R.id.editText5);
        Continue=(Button) findViewById(R.id.button3);
        Intent intent1= getIntent();
        // to show the detail of the selected water icon
        if(intent1.hasExtra(WATER_ID)) {
            int waterId=intent1.getIntExtra(WATER_ID,0);
            int waterimage=Water.waters[waterId].getImageResourceId();
            String watername=Water.waters[waterId].getName();
            String waterrate=Water.waters[waterId].getVrate();
            ImageView image= (ImageView) findViewById(R.id.imageView5);
            image.setImageDrawable(ContextCompat.getDrawable(this,waterimage));
            image.setContentDescription(watername);
            TextView text=(TextView) findViewById(R.id.textView);
            text.setText(watername);
             contentValues.put("Product_Name",watername);
             contentValues.put("Category","Water");
            contentValues.put("Rate",waterrate);}

        // to show the detail of the selected plastic water bottle icon
        if(intent1.hasExtra(PIZZA_ID)){
            int pizzaId=intent1.getIntExtra(PIZZA_ID,0);
            int pizzaimage=Pizza.pizzas[pizzaId].getImageResourceId();
            String pizzaname=Pizza.pizzas[pizzaId].getName();
            String pizzarate=Pizza.pizzas[pizzaId].getVprice();
            ImageView image= (ImageView) findViewById(R.id.imageView5);
            image.setImageDrawable(ContextCompat.getDrawable(this,pizzaimage));
            image.setContentDescription(pizzaname);
            TextView text=(TextView) findViewById(R.id.textView);
            text.setText(pizzaname);
            contentValues.put("Product_Name",pizzaname);
            contentValues.put("Category","Plastic Bottle");
            contentValues.put("Rate",pizzarate);

        }

        // to show the detail of the selected steel water bottle icon
        if(intent1.hasExtra(PASTA_ID)){
            int pastaId=intent1.getIntExtra(PASTA_ID,0);
            int pastaimage=Pasta.pastas[pastaId].getImageResourceId();
            String pastaname=Pasta.pastas[pastaId].getName();
            String pastarate=Pasta.pastas[pastaId].getVprice();
            ImageView image= (ImageView) findViewById(R.id.imageView5);
            image.setImageDrawable(ContextCompat.getDrawable(this,pastaimage));
            image.setContentDescription(pastaname);
            TextView text=(TextView) findViewById(R.id.textView);
            text.setText(pastaname);
            contentValues.put("Product_Name",pastaname);
            contentValues.put("Category","Steel Bottle");
            contentValues.put("Rate",pastarate);

            }
        // to show the detail of the selected medicine icon
        if(intent1.hasExtra(MEDICINE_ID)){
            int medicineId=intent1.getIntExtra(MEDICINE_ID,0);
            int medicineimage=Medicine.medicines[medicineId].getImageResourceId();
            String medicinename=Medicine.medicines[medicineId].getName();
            String medicinerate=Medicine.medicines[medicineId].getVprice();
            ImageView image= (ImageView) findViewById(R.id.imageView5);
            image.setImageDrawable(ContextCompat.getDrawable(this,medicineimage));
            image.setContentDescription(medicinename);
            TextView text=(TextView) findViewById(R.id.textView);
            text.setText(medicinename);
            contentValues.put("Product_Name",medicinename);
            contentValues.put("Category","Medicine");
            contentValues.put("Rate",medicinerate);
        } }
        // put the condition on the Editview field

    public void mycontinue(View view){
        if(tnum.length()==0){
            tnum.setError("Enter Train Number");
        }
        else if(cnum.length()==0){
            cnum.setError("Enter Coach Number");
        }
        else if(snum.length()==0){
            snum.setError("Enter Seat Number");
        }
        else if(pnrnum.length()==0){
            pnrnum.setError("Enter PNR Number");
        }
        else {
            tnum=(EditText) findViewById(R.id.editText2);
            cnum=(EditText) findViewById(R.id.editText3);
            snum=(EditText) findViewById(R.id.editText4);
            pnrnum=(EditText)findViewById(R.id.editText5);
            int trainno = Integer.parseInt(tnum.getText().toString());
            String coachno = cnum.getText().toString();
            int seatno=Integer.parseInt(snum.getText().toString());
            String pnrno=pnrnum.getText().toString();
            contentValues.put("Train_No",trainno);
            contentValues.put("Coach_No",coachno);
            contentValues.put("Seat_No",seatno);
            if(pnrno.length()!=0){
                contentValues.put("PNR_No",pnrno);
            }
            SQLiteOpenHelper aquaeasyDatabasehelper=new AquaeasyDatabaseHelper(this);
            try{
                SQLiteDatabase db = aquaeasyDatabasehelper.getWritableDatabase();
                long result= db.insert("TrainWaterOrder",null,contentValues);
                Cursor cursor= db.query("TrainWaterOrder",new String[]{"Id","Category"},null,
                        null,null,null,null);
                if(cursor.moveToLast()){
                    category=cursor.getString(1);
                }
                if(result==-1){
                    Toast toast2= Toast.makeText(OrderActivity.this,
                            "Data  is not inserted into the TrainWaterOrder", Toast.LENGTH_SHORT);
                    toast2.show();
                }else {
                    Toast toast3= Toast.makeText(OrderActivity.this,
                             "Data  is  inserted into the TrainWaterOrder", Toast.LENGTH_SHORT);
                    toast3.show();}
                cursor.close();
                db.close();
            } catch (SQLiteException e){
                Toast toast1= Toast.makeText(OrderActivity.this,
                        "Database is Unavailable", Toast.LENGTH_SHORT);
                toast1.show();
            }
            Toast toast= Toast.makeText(OrderActivity.this,
                    "Record is Loaded Successful", Toast.LENGTH_SHORT);
            toast.show();
            if(category.equals("Water")){
            Intent intent1=new Intent(this,WaterOrderActivity.class);
            intent1.putExtra(WaterOrderActivity.TRAIN_WATER,TRAINWATER);
            startActivity(intent1);
            } else {
                Intent intent=new Intent(this,MixOrderActivity.class);
                intent.putExtra(MixOrderActivity.TRAIN_MIX,TRAINMIX);
                startActivity(intent);
            }
        }
    }
}
