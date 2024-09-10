package com.example.punyawork;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.view.View;

public class MainImageAdapter extends
        RecyclerView.Adapter<MainImageAdapter .ViewHolder>  {
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
    public MainImageAdapter (String[] captoins, int[] imageIds){
        this.captions=captoins;
        this.imageIds=imageIds;
    }
    public int getItemCount()
    {
        return captions.length;
    }
    public MainImageAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv= (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.maincarddata,parent,false);
        return  new MainImageAdapter.ViewHolder(cv);
    }
    public void onBindViewHolder(MainImageAdapter.ViewHolder holder,int position1){
        CardView cardView=holder.cardView;

        int position=holder.getAdapterPosition();
        ImageView imageView= (ImageView) cardView.findViewById(R.id.info_image);
        Drawable drawable=
                ContextCompat.getDrawable(cardView.getContext(),imageIds[position1]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position1]);
        TextView textView=(TextView)cardView.findViewById(R.id.text12);
        textView.setText(captions[position1]);
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
    public void setListener(MainImageAdapter.Listener listener){
        this.listener= (MainImageAdapter.Listener) listener;
    }

}


