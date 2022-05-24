package com.lite.holistic_tracking;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_WordCard extends Fragment {
    private View v;
    WordCardAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Data> dataList=new ArrayList();

    String[] titles = {"하나","둘","셋"};
    String[] videos = {"비디오1","비디오2","비디오3"};
    int[] images = {R.drawable.test1,R.drawable.test1,R.drawable.test1 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        v= inflater.inflate(R.layout.f2_wordcard,container,false);
        RecyclerView recyclerView = v.findViewById(R.id.cardList);
        Context context = v.getContext();

        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new WordCardAdapter(context,dataList);

        dataList.add(new Data(titles[0],videos[0],images[0]));
        dataList.add(new Data(titles[1],videos[1],images[1]));
        dataList.add(new Data(titles[2],videos[2],images[2]));

        recyclerView.setAdapter(adapter);


        SearchView searchView = (SearchView) v.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String a_query) {
                // to do
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String a_newText) {
                // to do
                return false;
            }
        });

        return v;
    }
}
