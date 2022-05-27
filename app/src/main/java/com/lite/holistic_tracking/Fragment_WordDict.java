package com.lite.holistic_tracking;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_WordDict extends Fragment {
    //백엔드 연동
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private View v;
    private RecyclerView recyclerView;
    private LinearLayoutManager LinearLayoutManager;
    ArrayList<Dict> dataList=new ArrayList();
    String[] names;
    String[] images;
    String[] videoURLs;

    private String BASE_URL=LoginActivity.getBASE_URL();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.f2_worddict,container,false);
        Context context = v.getContext();

        // 리사이클
        recyclerView = v.findViewById(R.id.recyclerView1);

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
        //retrofit build
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<JsonElement> call = retrofitInterface.getDictAll();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonArray DictResponseArray = response.body().getAsJsonArray();
//                Log.e("넘어오는 갯수", String.valueOf(DictResponseArray.size()));
//                Log.e(":D",DictResponseArray.get(0).toString());
//                JsonElement jsonElement1 = DictResponseArray.get(0);
//                Log.e("name",jsonElement1.getAsJsonObject().get("Word").getAsString());

                //배열 선언
                names = new String[DictResponseArray.size()];
                images = new String[DictResponseArray.size()];
                videoURLs = new String[DictResponseArray.size()];
                for (int i=0; i<DictResponseArray.size();i++){
                    JsonElement jsonElement = DictResponseArray.get(i);
                    String name = jsonElement.getAsJsonObject().get("Word").getAsString();
                    String videoURL = jsonElement.getAsJsonObject().get("videoURL").getAsString();
                    String wordImg = jsonElement.getAsJsonObject().get("wordImg").getAsString();
                    names[i]=name;
                    images[i]=wordImg;
                    videoURLs[i] = videoURL;
                    dataList.add(new Dict(names[i], images[i], videoURLs[i]));
                }
                Log.e("dataList : ",dataList.get(0).getWord());
                DictAdapter adapter=new DictAdapter(context, dataList);

                LinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerView.setLayoutManager(LinearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("실패군","실패다");
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

}
