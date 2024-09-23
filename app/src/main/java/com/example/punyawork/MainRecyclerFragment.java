package com.example.punyawork;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import android.content.Intent;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainRecyclerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView serviceRecycler=
                (RecyclerView) inflater.inflate(R.layout.fragment_main_recycler,container,
                        false);
        String[] serviceNames = new String[Maindata.maindata.length];
        for(int i=0;i<serviceNames.length;i++){
            serviceNames[i] = Maindata.maindata[i].getName();
        }
        int[] serviceImages = new int[Maindata.maindata.length];
        for(int i=0; i<serviceImages.length; i++){
            serviceImages[i] =Maindata.maindata[i].getImageresourceId(); }
        MainImageAdapter adapter= new MainImageAdapter(serviceNames,serviceImages);
        serviceRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),3);
        serviceRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new MainImageAdapter.Listener() {
            @Override
            public void onClick(int position) {
                if(position==0){
                Intent intent= new Intent(getActivity(),WaterServiceActivity.class);
                getActivity().startActivity(intent);
                }
                if(position==1){
                    Intent intent= new Intent(getActivity(),BedServiceActivity.class);
                    getActivity().startActivity(intent);
                }
                if(position==2){
                    Intent intent= new Intent(getActivity(),PastaServiceActivity.class);
                    getActivity().startActivity(intent);
                }
                if(position==3){
                    Intent intent= new Intent(getActivity(),MedicineServiceActivity.class);
                    getActivity().startActivity(intent);
                }
                if(position==4){
                    Intent intent= new Intent(getActivity(),PizzaServiceActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });
        return serviceRecycler;

    }

}
