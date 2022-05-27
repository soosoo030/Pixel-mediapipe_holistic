package com.lite.holistic_tracking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentPage1 extends Fragment {
    private View v;
    private String checked;

    //권한
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;
    Context ct;

    FragmentManager manager;
    FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment1,container,false);

//        RadioGroup rg = (RadioGroup) v.findViewById(R.id.frag1_radio);
//        RadioButton rbhand = (RadioButton) v.findViewById(R.id.frag1_hand);
//        RadioButton rbstuff = (RadioButton) v.findViewById(R.id.frag1_stuff);
        Button handBtn = (Button) v.findViewById(R.id.handRecogBtn);
        Button stuffBtn = (Button) v.findViewById(R.id.stuffRecogBtn);

        manager = getChildFragmentManager();
        fragmentTransaction = manager.beginTransaction();

        handBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),holistic_activity.class);
                startActivity(intent);
            }
        });

//        rbhand.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                checked = "frag1_hand";
//                fragmentTransaction = manager.beginTransaction();
//                Fragment1Hand fragment1hand = new Fragment1Hand();
//                fragmentTransaction.replace(R.id.content,fragment1hand);
//                fragmentTransaction.commit();
//            }
//        });
//        rbstuff.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                checked = "frag1_stuff";
//                fragmentTransaction = manager.beginTransaction();
//                Fragment1Stuff fragment1stuff = new Fragment1Stuff();
//                fragmentTransaction.replace(R.id.content,fragment1stuff);
//                fragmentTransaction.commit();
//            }
//        });



//        checked = "frag1_hand";
//        if(checked == "frag1_hand"){
//            Fragment1Hand fragment1hand = new Fragment1Hand();
//            fragmentTransaction.replace(R.id.content,fragment1hand);
//        }else if(checked =="frag1_stuff"){
//            Fragment1Stuff fragment1stuff = new Fragment1Stuff();
//            fragmentTransaction.replace(R.id.content,fragment1stuff);
//        }fragmentTransaction.commit();
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }

}
