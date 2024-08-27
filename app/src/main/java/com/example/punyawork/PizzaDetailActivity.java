package com.example.punyawork;

import android.content.Intent;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.View;


public class PizzaDetailActivity extends AppCompatActivity {
    public static final String EXTRA_Id = "pizzaId";
    public int pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);
        // set the toolbar as activity appbar
        Toolbar toolbar12 = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar12);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // display the details of the pizza
        int pizzaId= (Integer)getIntent().getExtras().get(EXTRA_Id);
        String pizzaname= Pizza.pizzas[pizzaId].getName();
        TextView text1= (TextView) findViewById(R.id.textp);
        text1.setText(pizzaname);
        int pizzaimage= Pizza.pizzas[pizzaId].getImageResourceId();
        ImageView image1= (ImageView) findViewById(R.id.pizza_image);
        image1.setImageDrawable(ContextCompat.getDrawable(this,pizzaimage));
        image1.setContentDescription(pizzaname);
        String capacity= Pizza.pizzas[pizzaId].getCap();
        TextView text6= (TextView) findViewById(R.id.textView6);
        text6.setText(capacity);
        String vcapacity=Pizza.pizzas[pizzaId].getVcap();
        TextView text7=(TextView) findViewById(R.id.textView7);
        text7.setText(vcapacity);
        String weight=Pizza.pizzas[pizzaId].getWeight();
        TextView text8=(TextView) findViewById(R.id.textView8);
        text8.setText(weight);
        String vweight=Pizza.pizzas[pizzaId].getVweight();
        TextView text9=(TextView) findViewById(R.id.textView9);
        text9.setText(vweight);
        String price=Pizza.pizzas[pizzaId].getPrice();
        TextView text10=(TextView) findViewById(R.id.textView10);
        text10.setText(price);
        String vprice=Pizza.pizzas[pizzaId].getVprice();
        TextView text11=(TextView) findViewById(R.id.textView11);
        text11.setText(vprice);
        pizza=pizzaId;
    }
    public void myorder(View view){
        Intent intent=new Intent(this,Create_Order.class);
        intent.putExtra(Create_Order.PIZZAID,pizza);
        startActivity(intent);

    }
}
