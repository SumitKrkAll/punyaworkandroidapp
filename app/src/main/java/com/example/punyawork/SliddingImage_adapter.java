package com.example.punyawork;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class SliddingImage_adapter extends PagerAdapter {
    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    public String[] stringArray;
    public SliddingImage_adapter(Context context, ArrayList<Integer> IMAGES,String[] stringarray){
        this.IMAGES=IMAGES;
        this.context=context;
        this.stringArray=stringarray;
        inflater=LayoutInflater.from(context);
    }

    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView((View)object);

    }
    @Override
    public int getCount() {
        return IMAGES.size();
    }
    public Object instantiateItem(ViewGroup view,int position){
        View imagelayout=inflater.inflate(R.layout.mysliding,view,false);
        assert imagelayout !=null;
        final ImageView imageView=(ImageView)imagelayout.findViewById(R.id.myslideimage);
        imageView.setImageResource(IMAGES.get(position));
        view.addView(imagelayout,0);
        TextView text1=(TextView)imagelayout.findViewById(R.id.mytext);
        text1.setText(stringArray[position]);
        return imagelayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }
    public void restoreState(Parcelable state,ClassLoader loader){

    }


    @Override
    public Parcelable saveState() {
        return null;
    }
}
