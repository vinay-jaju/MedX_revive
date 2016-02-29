    package com.example.android.medx;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.medx.R;

import java.util.ArrayList;
import java.util.List;

    /**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String click_name;
    private int click_photo;

    //private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    //private ProductAdapter adapter;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= (RecyclerView) v.findViewById(R.id.recycler_view);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        final List<ItemObjects> gaggeredList = getListItemData();

        SolventRecyclerViewAdapter rcAdapter = new SolventRecyclerViewAdapter(v.getContext(), gaggeredList);
        recyclerView.setAdapter(rcAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(v.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                click_name=gaggeredList.get(position).getName();
                click_photo=gaggeredList.get(position).getPhoto();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                switch(position){
                   case 0: callIntent.setData(Uri.parse("tel:100"));
                      startActivity(callIntent);
                       break;
                    case 1: callIntent.setData(Uri.parse("tel:101"));
                        startActivity(callIntent);
                        break;

                    case 2: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;

                    case 3: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;
                    case 4: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;

                    case 5: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;
                    case 6: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;
                    case 7: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;
                    case 8: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;
                    case 9: callIntent.setData(Uri.parse("tel:9699973049"));
                        startActivity(callIntent);
                        break;



                }
            }


        }

        ));
        return v;
    }

        private List<ItemObjects> getListItemData() {
            List<ItemObjects> listViewItems = new ArrayList<ItemObjects>();
            listViewItems.add(new ItemObjects("Police", R.drawable.police));
            listViewItems.add(new ItemObjects("Fire", R.drawable.fire));
            listViewItems.add(new ItemObjects("Ambulance", R.drawable.ambulance));
            listViewItems.add(new ItemObjects("SOS", R.drawable.sos));
            listViewItems.add(new ItemObjects("Car Services", R.drawable.caraccident));
            listViewItems.add(new ItemObjects("Railway", R.drawable.railway));
            listViewItems.add(new ItemObjects("Women Safety", R.drawable.harrasment));
            listViewItems.add(new ItemObjects("Blood Banks", R.drawable.blood));
            listViewItems.add(new ItemObjects("Child Help", R.drawable.children));
            listViewItems.add(new ItemObjects("Airlines", R.drawable.arilines));


            return listViewItems;
        }

}
