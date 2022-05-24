package com.lite.holistic_tracking;

import static com.lite.holistic_tracking.MainActivity.p_email;
import static com.lite.holistic_tracking.MainActivity.p_name;
import static com.lite.holistic_tracking.MainActivity.p_password;
import static com.lite.holistic_tracking.MainActivity.p_userID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPage3 extends Fragment {
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private View v;
    private Context ct;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment3,container,false);

        ct=container.getContext();
        Button logout=v.findViewById(R.id.setLogout);
        TextView notice=v.findViewById(R.id.setNotice);
        TextView ask=v.findViewById(R.id.setAsk);

        //로그아웃 시 로그인 페이지로
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent_변수들 빈 값으로 만들기
                p_name = "";
                p_userID = -1;
                p_email = "";
                p_password = "";

//                Intent intent=new Intent(getActivity(),LoginActivity.class);
//                startActivity(intent);
                Intent intent=new Intent(getActivity(),holistic_activity.class);
                startActivity(intent);
            }
        });

        //공지사항 토스트
        notice.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(ct,"공지사항.", Toast.LENGTH_LONG).show();
            }
        });

        //문의사항 토스트
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ct,"문의사항이 없습니다.", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
}
