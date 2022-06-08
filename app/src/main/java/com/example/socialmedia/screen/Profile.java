package com.example.socialmedia.screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.R;
import com.example.socialmedia.auth.LoginScreen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.socialmedia.auth.SignupScreen.uId;


public class Profile extends Fragment {


    View view ;
    TextView pass ;
    TextView em;
    TextView na;
    TextView ph  ;
    Button logout;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        id =  sharedPref.getString("USER_ID",uId);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.fragment_profile, container, false);
       pass = view.findViewById(R.id.pass);
       em = view.findViewById(R.id.email);
       na = view.findViewById(R.id.name);
       ph = view.findViewById(R.id.phone);
       logout = view.findViewById(R.id.logout);
        firebaseFirestore.collection("User").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                pass.setText(documentSnapshot.getString("password"));
                ph.setText(documentSnapshot.getString("phone"));
                na.setText(documentSnapshot.getString("name"));
                em.setText(documentSnapshot.getString("email"));

                Toast.makeText(getActivity(),"The Get Data is Successfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"The Get Data is Error",Toast.LENGTH_LONG).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                firebaseAuth.signOut();
                Toast.makeText(getActivity(),"The LogOut Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), LoginScreen.class);
                getActivity().startActivity(intent);


            }
        });
        return view ;
    }
}
