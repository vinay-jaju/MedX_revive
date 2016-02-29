package com.example.android.medx;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by abc on 24-Jan-16.
 */
public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.MyViewHolder> {
    int[] img = {0, 1, 2, 3};
    List<Information> data = Collections.emptyList();
    private LayoutInflater inflater;

    public YouTubeAdapter(List<Information> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.gen_card_view_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.hang_text);
            icon = (ImageView) itemView.findViewById(R.id.img_hangout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Class c;
            switch (getAdapterPosition()) {
                // JITNE CARDS UTNE CASES DAAL DENA
                case 0:
                    try {
                        //YAHAN  PE ACTIVITY KA NAAM DAALNA JOH OPEN KARNA HAI
                        c = Class.forName("com.example.android.medx.YouTubeActivity");
                        Intent i = new Intent(v.getContext(), c);
                        i.putExtra("x", img[0]);  //img[] array me bas numbers daal dena jitne bhi cards banayega upar declare kiya hai
                        v.getContext().startActivity(i);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        //YAHAN  PE ACTIVITY KA NAAM DAALNA JOH OPEN KARNA HAI
                        c = Class.forName("com.example.android.medx.YouTubeActivity");
                        Intent i = new Intent(v.getContext(), c);
                        i.putExtra("x", img[1]);
                        v.getContext().startActivity(i);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        //YAHAN  PE ACTIVITY KA NAAM DAALNA JOH OPEN KARNA HAI
                        c = Class.forName("com.example.android.medx.YouTubeActivity");
                        Intent i = new Intent(v.getContext(), c);
                        i.putExtra("x", img[2]);
                        v.getContext().startActivity(i);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        //YAHAN  PE ACTIVITY KA NAAM DAALNA JOH OPEN KARNA HAI
                        c = Class.forName("com.example.android.medx.YouTubeActivity");
                        Intent i = new Intent(v.getContext(), c);
                        i.putExtra("x", img[3]);
                        v.getContext().startActivity(i);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }
}

