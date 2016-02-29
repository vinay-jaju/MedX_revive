package com.example.android.medx;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SolventViewHolders extends RecyclerView.ViewHolder  {

    public TextView countryName;
    //public TextView countrySubname;
    public ImageView countryPhoto;

    public SolventViewHolders(View itemView) {
        super(itemView);
        //itemView.setOnClickListener(this);
        countryName = (TextView) itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
        //countrySubname= (TextView) itemView.findViewById(R.id.country_subname);
    }


}
