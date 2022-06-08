package com.example.socialmedia.adabtar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialmedia.R;
import com.example.socialmedia.model.product;
import com.example.socialmedia.screen.DetailsFragment;

import java.util.ArrayList;


public class ProductHolder extends RecyclerView.Adapter<ProductHolder.MyViewHolder> {
    public  static  String nameLL ="";
    public   static  String decLL ="";
    public   static  String priceLL="" ;
    public   static  String image ="";

      static int isFav = 0 ;
      static String  idFav = "" ;

    public static ArrayList<product> fav = new ArrayList<>();

     Context context;
        ArrayList<product> myArray;

    public ProductHolder(Context context, ArrayList<product> myArray) {
        this.context = context;
        this.myArray = myArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_only,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        product p = myArray.get(position);
          holder.tv.setText(p.getnameProduct());

                Glide
                .with(context)
                .load(p.getimageProduct())
                .into(holder.img);

               holder.img.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                        AppCompatActivity activity  = (AppCompatActivity)view.getContext();
                         nameLL = p.getnameProduct();
                        priceLL = p.getpriceProduct();
                        image=p.getimageProduct();
                        decLL = p.getdesProduct();


                        activity.getSupportFragmentManager().
                               beginTransaction().
                               replace(R.id.FrameLay,new DetailsFragment())
                               .addToBackStack(null).commit();
                    }
               });
               holder.love.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {


                                fav.add(p);
                                Toast.makeText(context,"The Add Product in Favorite", Toast.LENGTH_SHORT).show();




                    }
               });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fav.remove(p);
                Toast.makeText(context,"The remove Favorite",Toast.LENGTH_SHORT).show();




            }
        });


     }

    @Override
    public int getItemCount() {
        return myArray.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        TextView tv  ;
        CardView card ;
        Button love ,delete;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgRV);
            tv = itemView.findViewById(R.id.tvRV);
   love = itemView.findViewById(R.id.love);
            card = itemView.findViewById(R.id.card);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
