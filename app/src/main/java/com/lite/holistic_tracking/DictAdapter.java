package com.lite.holistic_tracking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class DictAdapter extends RecyclerView.Adapter<DictAdapter.ViewHolder>{
    private LayoutInflater inflater;

    int[] images;
    private Context context;
    private Dialog dialog;

    private ArrayList<Dict> dict;
    public DictAdapter(Context context, ArrayList<Dict> dict){
        this.inflater = LayoutInflater.from(context);
        this.dict=dict;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        this.context=parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_word,parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dict dict1=dict.get(position);
//        Log.e("받았나요?",dict1.getImage());
//        holder.imageView.setImageResource(dict1.getImage());

        holder.textView.setText(dict1.getWord());
        Glide.with(holder.imageView.getContext()).load(dict1.getImage()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return dict.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView save;
        ImageView showVideo;
        ImageView dictAct;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.dict_image);
            textView = itemView.findViewById(R.id.dict_name);
            save=itemView.findViewById(R.id.save_word);
            showVideo=itemView.findViewById(R.id.show_video);
            dictAct = itemView.findViewById(R.id.dict_act);
            dictAct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("아이템 클릭", String.valueOf(getAdapterPosition()));
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    custom_dialog(view);
                }
            });
            showVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    custom_dialog2(view,getAdapterPosition());
                    Log.d("비디오에서도 position이 나오나요?", String.valueOf(getAdapterPosition()));

                }
            });

            //카드 선택 시 이동
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MainActivity main = (MainActivity) DictAdapter.this.context;
                   // main.toMain(b);
                }
            });
        }
    }

    public void custom_dialog(View v){
        View dialogView = inflater.inflate(R.layout.dialog_wordadd,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView ok_btn = dialogView.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.dismiss();
            }
        });
    }
    public void custom_dialog2(View v,int position){
        Dict dict2=dict.get(position);
        VideoView vv;
        View dialogView = inflater.inflate(R.layout.dialog_video,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(dialogView);

        vv= dialogView.findViewById(R.id.videoV);
        //URL서ㅕㄹ정
        Uri videoUri= Uri.parse(dict2.getVideoURL());
        vv.setMediaController(new MediaController(context));
        vv.setVideoURI(videoUri);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //비디오 시작
                vv.start();
            }
        });

        TextView ok_btn = dialogView.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.dismiss();
            }
        });
    }
}

