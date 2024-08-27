package com.example.punyawork;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class WaterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView waterRecycler=
                (RecyclerView) inflater.inflate(R.layout.fragment_water,container,
                        false);
        String[] waterNames = new String[Water.waters.length];
        for(int i=0;i<waterNames.length;i++){
            waterNames[i] = Water.waters[i].getName();
        }
        int[] waterImages = new int[Water.waters.length];
        for(int i=0; i<waterImages .length; i++){
            waterImages [i] = Water.waters[i].getImageResourceId(); }


        CaptionedImagesAdapter adapter= new CaptionedImagesAdapter(waterNames,waterImages);
        waterRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),1);
        waterRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent= new Intent(getActivity(),WaterDetailActivity.class);
                intent.putExtra(WaterDetailActivity.EXTRA_Id, position);
                getActivity().startActivity(intent);
            }
        });
        return waterRecycler;
    }

}
