package com.example.socialmedia.screen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentChange;
import com.example.socialmedia.R;
import com.example.socialmedia.adabtar.ProductHolder;
import com.example.socialmedia.model.product;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    View view ;

      RecyclerView recyclerView;
      ArrayList<product> products;
    ProgressDialog progressDialog;
      ProductHolder holder;
      FirebaseFirestore fire;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


                 view = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("get data");
        progressDialog.show();
        products = new ArrayList<product>();
        fire = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        holder = new ProductHolder(getActivity(),products);
        recyclerView.setAdapter(holder);


        holder.notifyDataSetChanged();
        FirebaseFirestore fire = FirebaseFirestore.getInstance();

 fire.collection("products").addSnapshotListener( new EventListener<QuerySnapshot>() {
     @Override
     public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


             if(error !=null){
                 if(progressDialog.isShowing())
                     progressDialog.dismiss();

                 return;
             }
             else {

                 for(DocumentChange qs: value.getDocumentChanges()){
                     if(qs.getType()== DocumentChange.Type.ADDED){
//                         product p = new product();
//                         p.setPrice(qs.getDocument().getString("priceProduct"));
                         products.add(qs.getDocument().toObject(product.class));
                     }
                     holder.notifyDataSetChanged();
                     if(progressDialog.isShowing())
                         progressDialog.dismiss();
                 }
             }

         }



        });


                 return view ;
    }


}
