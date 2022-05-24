package com.lite.holistic_tracking;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordCardAdapter extends RecyclerView.Adapter<WordCardAdapter.ItemViewHolder> {
    private LayoutInflater inflater;
    //adapter에 들어갈 list
    private ArrayList<Data> listData = new ArrayList<>();
    String[] titles;
    int[] images;
    String[] videos;
    //Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    //직전에 클릭했던 item의 position
    private int prePosition = -1;
    private ArrayList<Data> data;
    public WordCardAdapter(Context context, ArrayList<Data> data){
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordcard_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Data data1 = data.get(position);
        holder.wordTitle.setText(data1.getTitle());
        holder.wordImage.setImageResource(data1.getResId());
//        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void addItem(Data data){
        listData.add(data);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private Data data;
        public final View mView;
        private TextView wordTitle;
        private ImageView wordImage;
        private LinearLayout expanded;
//        private ImageButton wordStarBtn;
//        private ImageButton wordFilmBtn;
        private ImageButton wordDeleteBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            wordTitle = itemView.findViewById(R.id.wordTitle);
            wordImage = itemView.findViewById(R.id.wordImage);
            expanded = itemView.findViewById(R.id.expandedLayout);

            wordDeleteBtn = itemView.findViewById(R.id.wordDeleteBtn);

            mView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int isVisible = expanded.getVisibility();

                    if(isVisible==8){
                        expanded.setVisibility(v.VISIBLE);
                    }else if(isVisible==0){
                        expanded.setVisibility(v.GONE);
                    }

                }
            });

            wordDeleteBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    custom_dialog(v);
                }
            });
        }

//        void onBind(Data data){
//            wordTitle.setText(data.getTitle());
//            wordImage.setImageResource(data.getResId());

//        }

    }
    public void custom_dialog(View v){
        View dialogView = inflater.inflate(R.layout.dialog_delete,null);

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
        TextView cancel_btn = dialogView.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.dismiss();
            }
        });
    }
}

