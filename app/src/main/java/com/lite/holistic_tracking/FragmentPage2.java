package com.lite.holistic_tracking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentPage2 extends Fragment {
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private View v;


    //ViewPager 변수 선언
    ViewPager2 vpHorizontal;
    String[] headers = {"끝말잇기","단어카드","단어사전"};
    int[] images = {R.drawable.endtalk_icon, R.drawable.wordcard_icon,R.drawable.worddict_icon};
    int[] backImages = {R.drawable.back_endtalk,R.drawable.back_wordcard,R.drawable.back_worddict};
    MainAdapter adapter;
    //Fragment 배경
    ImageView mainBackView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment2,container,false);
        //Fragment 배경
        mainBackView = v.findViewById(R.id.mainBack);
        //ViewPager 관련 코드
        vpHorizontal = v.findViewById(R.id.vp_horizontal);

        adapter = new MainAdapter(images,headers,getContext());

        //Set clip padding
        vpHorizontal.setClipToPadding(false);
        //Set clip children
        vpHorizontal.setClipChildren(false);
        //Set page limit
        vpHorizontal.setOffscreenPageLimit(3);
        //Set default start position
        vpHorizontal.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        //Set adapter on horizontal viewpager
        vpHorizontal.setAdapter(adapter);

        //Initialize composite page transformer
        CompositePageTransformer transformer = new CompositePageTransformer();
        //Add margin between page
        transformer.addTransformer(new MarginPageTransformer(8));
        //Increase selected page size
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float v = 1 - Math.abs(position);
                //Set scale y
                page.setScaleY(0.8f + v * 0.2f);
            }
        });
        // Set page transform
        vpHorizontal.setPageTransformer(transformer);
        //ViewPager의 position에 따라 배경 이미지 변경
        vpHorizontal.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){
                mainBackView.setBackgroundResource(backImages[position]);
                adapter.setposition(position);
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

}
