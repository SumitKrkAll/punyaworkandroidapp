package com.example.punyawork;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class StoresFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView medicineRecycler=
                (RecyclerView) inflater.inflate(R.layout.fragment_stores,container,
                        false);
        String[] medicineNames = new String[Medicine.medicines.length];
        for(int i=0;i<medicineNames.length;i++){
            medicineNames[i] = Medicine.medicines[i].getName();
        }
        int[] medicineImages = new int[Medicine.medicines.length];
        for(int i=0; i<medicineImages.length; i++){
            medicineImages[i] = Medicine.medicines[i].getImageResourceId(); }
        CaptionedImagesAdapter adapter= new CaptionedImagesAdapter(medicineNames,medicineImages);
        medicineRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager= new GridLayoutManager(getActivity(),2);
        medicineRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent= new Intent(getActivity(),MedicineDetailActivity.class);
                intent.putExtra(MedicineDetailActivity.EXTRA_Id, position);
                getActivity().startActivity(intent);
            }
        });
        return medicineRecycler;
    }
    }


