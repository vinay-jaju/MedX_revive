package com.example.android.medx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class YouTubeList extends AppCompatActivity {

    private RecyclerView recyclerView;
    public YouTubeAdapter adapter;
    int icons[]={R.drawable.repairtyre,R.drawable.fireextingusher,R.drawable.hurricane,R.drawable.carsiking};
    public static List<Information> getData(int[] icons,String[] titles){
        List<Information> data=new ArrayList<>();
        for(int i=0;i<titles.length&&i<icons.length;i++){
            Information current=new Information();
            current.iconId=icons[i% icons.length];
            current.title=titles[i%titles.length];
            data.add(current);
        }
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.hang_recycler_view);
        adapter = new YouTubeAdapter(getData(icons, getResources().getStringArray(R.array.list)));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
