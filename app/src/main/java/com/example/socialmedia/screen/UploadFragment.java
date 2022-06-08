package com.example.socialmedia.screen;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.socialmedia.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;


public class UploadFragment extends Fragment {
    View view ;
    ImageView imageView;
    ImageView openImage;
    EditText nameProduct;
    EditText priceProduct;
    EditText desProduct;
    Button upload;
    Uri filePath;
    String name , price,des;

    // request code
    final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          view =  inflater.inflate(R.layout.fragment_upload, container, false);
          imageView = view.findViewById(R.id.imageProd);
          openImage = view.findViewById(R.id.openCamera);
          nameProduct = view.findViewById(R.id.nameProduct);
        priceProduct = view.findViewById(R.id.priceProduct);
        desProduct = view.findViewById(R.id.desProduct);
        upload = view.findViewById(R.id.addProd);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        openImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        return view;
    }

    private void uploadImage() {
        name = nameProduct.getText().toString();
        des = desProduct.getText().toString();
        price = priceProduct.getText().toString();
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                     ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                         @Override
                                         public void onSuccess(Uri uri) {
                                             HashMap<String,String> toMap = new HashMap<>();
                                             toMap.put("nameProduct",name);
                                             toMap.put("priceProduct",price);
                                             toMap.put("desProduct",des);
                                             toMap.put("imageProduct",uri.toString());
                                             FirebaseFirestore.getInstance().collection("products").add(toMap);
                                             // Image uploaded successfully
                                             // Dismiss dialog
                                             progressDialog.dismiss();
                                             Toast
                                                     .makeText(getActivity(),
                                                             "Product Uploaded Done",
                                                             Toast.LENGTH_SHORT)
                                                     .show();
                                         }
                                     });

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getActivity(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }

    private void SelectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            filePath = data.getData();
            imageView.setImageURI(filePath);


        }
    }

}
