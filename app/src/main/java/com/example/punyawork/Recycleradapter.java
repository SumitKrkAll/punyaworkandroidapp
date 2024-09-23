
package com.example.punyawork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.myViewholder>{

    private ArrayList<Contacts> arrayList=new ArrayList<>();
    private Listener listener;

    public Recycleradapter(ArrayList<Contacts> arrayList){
        this.arrayList=arrayList;
    }

    public static class myViewholder extends RecyclerView.ViewHolder{
        private CardView cardView;
        public myViewholder(CardView v){
            super(v);
            cardView=v;
        }
    }


    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cv= (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view,viewGroup,false);
        return  new myViewholder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder viewHolder,final int i) {
        CardView cardView=viewHolder.cardView;
        int position=viewHolder.getAdapterPosition();
        TextView textView=(TextView)cardView.findViewById(R.id.mytext);
        textView.setText(arrayList.get(i).getCategory_Name());
        TextView myorderid=(TextView) cardView.findViewById(R.id.myorderid);
        myorderid.setText(""+arrayList.get(i).getOrderId());
        TextView myorderdate=(TextView) cardView.findViewById(R.id.myorderdate);
        myorderdate.setText(""+arrayList.get(i).getOrder_Date());
        int syncstattus=arrayList.get(i).getSync_status();
        ImageView imageView= (ImageView) cardView.findViewById(R.id.info_image);
        if(syncstattus==Dbcontacts.SYNC_STATUS_OK){
            imageView.setImageResource(R.drawable.check);
        }else{
            imageView.setImageResource(R.drawable.sync);
        }

        cardView.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                if (listener !=null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    interface Listener {
        void onClick(int position);
    }
    public void setListener(Listener listener){
        this.listener=listener;
    }
    public int ReturnOrderId(int positioninarraylist){
        return  arrayList.get(positioninarraylist).getOrderId();

    }


}

