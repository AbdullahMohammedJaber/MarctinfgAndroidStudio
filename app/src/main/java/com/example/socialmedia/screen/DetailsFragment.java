package com.example.socialmedia.screen ;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.socialmedia.R;

import static com.example.socialmedia.adabtar.ProductHolder.decLL;
import static com.example.socialmedia.adabtar.ProductHolder.image;
import static com.example.socialmedia.adabtar.ProductHolder.nameLL;
import static com.example.socialmedia.adabtar.ProductHolder.priceLL;




public class DetailsFragment extends Fragment {

 View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_details, container, false);
        ImageView img_detail=view.findViewById(R.id.img_detail);
        TextView name_detail=view.findViewById(R.id.name_detail);
        TextView desc_detail=view.findViewById(R.id.desc_detail);
        TextView price_detail=view.findViewById(R.id.price_detail);
        Button love = view.findViewById(R.id.love);
        Button url = (Button)view.findViewById(R.id.GO);
        name_detail.setText(nameLL);
        price_detail.setText( priceLL);
        desc_detail.setText( decLL);
        Glide
                .with(getActivity())
                .load( image)
                .into(img_detail);



        return  view ;

    }
    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrameLay,new HomeFragment()).addToBackStack(null).commit();

    }
}
