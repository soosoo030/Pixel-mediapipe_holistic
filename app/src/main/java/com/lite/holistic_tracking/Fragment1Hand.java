package com.lite.holistic_tracking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment1Hand extends Fragment {
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private View v;

    //카메라
    com.lite.holistic_tracking.CameraSurfaceView surfaceView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment1_hand,container,false);
        surfaceView=v.findViewById(R.id.surfacaview);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

}
