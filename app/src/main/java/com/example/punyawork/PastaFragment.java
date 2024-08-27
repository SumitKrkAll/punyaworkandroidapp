package com.example.punyawork;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;

public class PastaFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pastaRecycler=
                (RecyclerView) inflater.inflate(R.layout.fragment_pasta,container,
                        false);
        String[] pastaNames = new String[Pasta.pastas.length];
        for(int i=0;i<pastaNames.length;i++){
            pastaNames[i] = Pasta.pastas[i].getName();
        }
        int[] pastaImages = new int[Pasta.pastas.length];
        for(int i=0; i<pastaImages.length; i++){
            pastaImages[i] = Pasta.pastas[i].getImageResourceId(); }

        CaptionedImagesAdapter adapter= new CaptionedImagesAdapter(pastaNames,pastaImages);
        pastaRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),2);
        pastaRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent= new Intent(getActivity(),PastaDetailActivity.class);
                intent.putExtra(PastaDetailActivity.EXTRA_Id, position);
                getActivity().startActivity(intent);
            }
        });
        return pastaRecycler;
    }

}
