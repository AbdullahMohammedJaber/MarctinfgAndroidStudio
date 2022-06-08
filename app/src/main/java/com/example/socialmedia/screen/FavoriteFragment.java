package com.example.socialmedia.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia.R;
import com.example.socialmedia.adabtar.ProductHolder;

import static com.example.socialmedia.adabtar.ProductHolder.fav;


public class FavoriteFragment extends Fragment {
    View view ;
    RecyclerView recyclerView;
    ProductHolder holder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_favorite, container, false);
            recyclerView = view.findViewById(R.id.rvFav);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            holder = new ProductHolder(getActivity(),fav);
            recyclerView.setAdapter(holder);



//        holder = new ProductHolder(getActivity(),fav);
//        recyclerView.setAdapter(holder);

            return view;
    }
}
