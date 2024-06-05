package com.example.shoppingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PurchasesAdapter extends BaseAdapter {
    ArrayList<Products> purchases;
    Context context;

    public PurchasesAdapter(ArrayList<Products> purchases, Context context) {
        this.purchases = purchases;
        this.context = context;
    }

    @Override
    public int getCount() {
        return purchases.size();
    }
    @Override
    public Products getItem(int i) {
        return purchases.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @SuppressLint({"ResourceType", "SetTextI18n", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if(v==null)
            v = LayoutInflater.from(context).inflate(R.layout.custome_purchases_products,null,false);

        ImageView img = v.findViewById(R.id.img_products_purchases);
        TextView tv_name = v.findViewById(R.id.tv_name_purchases);
        TextView tv_price = v.findViewById(R.id.tv_price_purchases);
        TextView tv_brand = v.findViewById(R.id.tv_brand_purchases);

        Products p = getItem(i);
        if(p.getImage() != 0)
            img.setImageResource(p.getImage());
        else
            img.setImageResource(R.drawable.products);
        tv_name.setText(p.getName());
        tv_price.setText(p.getPrice()+"Р");
        tv_brand.setText(p.getBrand());
        return v;
    }
}
