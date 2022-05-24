package com.lite.holistic_tracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    int[] images;
    String[] headers;
    private Context context;
    int a;

    public MainAdapter(int[] images, String[] headers, Context context){
        this.images = images;
        this.headers = headers;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setBackgroundResource(images[position]);
        holder.textView.setText(headers[position]);

    }

    //카드 선택 시 이동
    @Override
    public int getItemCount() {
        return images.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.MenuHeader);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MainActivity main = (MainActivity) context;
                    int b= sendposition();
                    main.toMain(b);
                }
            });
        }
    }
    public void setposition(int position){a=position;}
    public int sendposition(){return a;}
}
