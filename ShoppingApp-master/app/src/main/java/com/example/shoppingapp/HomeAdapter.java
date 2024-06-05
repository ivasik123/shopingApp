package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ProductInHomeViewHolder> {
    ArrayList<Products> products;
    OnRecyclerViewClickListener listener;
    public HomeAdapter(ArrayList<Products> products, OnRecyclerViewClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductInHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_card_home,null,false);
        return new ProductInHomeViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductInHomeViewHolder holder, int position) {
        Products p = products.get(position);

        if(p.getImage() != 0)
            holder.img.setImageResource(p.getImage());
        else
            holder.img.setImageResource(R.drawable.products);
        holder.name.setText(p.getName());
        holder.price.setText(p.getPrice()+"Р");
        if(p.getDiscount()>0){
            holder.priceAfter.setText(p.getPrice()-(p.getPrice()*(p.getDiscount()/100))+"Р");
            holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.price.setTextColor(Color.parseColor("#BFBFBF"));
        }else{
            holder.priceAfter.setText("");
            holder.price.setTextColor(Color.parseColor("#000000"));
        }
        holder.img.setTag(position+1);
    }

    @Override
    public int getItemCount() {return products.size();}

    class ProductInHomeViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,price,priceAfter;

        public ProductInHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_car_home);
            name = itemView.findViewById(R.id.tv_name_card_home);
            price = itemView.findViewById(R.id.tv_price_card_home);
            priceAfter = itemView.findViewById(R.id.tv_priceafter_card_home);

            itemView.setOnClickListener(view -> {
                int id = (int)img.getTag();
                listener.OnItemClick(id);
            });
        }
    }
}
