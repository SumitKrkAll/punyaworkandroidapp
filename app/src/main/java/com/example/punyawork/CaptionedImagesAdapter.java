package com.example.punyawork;

import androidx.core.content.ContextCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

import android.view.View;


class CaptionedImagesAdapter extends
        RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>  {
        private String[] captions;
        private int[] imageIds;
        private Listener listener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
            private CardView cardView;
            public ViewHolder(CardView v){
                super(v);
                cardView=v;
            }

    }

    public CaptionedImagesAdapter (String[] captoins, int[] imageIds){
        this.captions=captoins;
        this.imageIds=imageIds;
    }

    public int getItemCount()
    {
        return captions.length;
    }

    public CaptionedImagesAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType){
            CardView cv= (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_captioned_image,parent,false);
     return  new ViewHolder(cv);
    }

    public void onBindViewHolder(ViewHolder holder, final int position){
            CardView cardView=holder.cardView;
            ImageView imageView= (ImageView) cardView.findViewById(R.id.info_image);
            Drawable drawable=
                    ContextCompat.getDrawable(cardView.getContext(),imageIds[position]);
         imageView.setImageDrawable(drawable);
         imageView.setContentDescription(captions[position]);
         TextView textView=(TextView)cardView.findViewById(R.id.text12);
         textView.setText(captions[position]);
         cardView.setOnClickListener(new View.OnClickListener(){
             public void onClick (View v){
                 if (listener !=null){
                     listener.onClick(position);
                 }
             }
         });
    }
    interface Listener {
        void onClick(int position);
        }
        public void setListener(Listener listener){
        this.listener=listener;
        }
    }


