package com.example.shoppingapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class PurchasesActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    ListView lv;
    PurchasesAdapter pa;
    ShoppingDatabase db;
    ImageButton btn_clean;

    @SuppressLint({"NonConstantResourceId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);

        lv = findViewById(R.id.lv_purchases);

        db = new ShoppingDatabase(this);

        ArrayList<Products> p;
        p = db.getAllProductsInPurchases();
        System.out.println(p);
        pa = new PurchasesAdapter(p,this);
        pa.notifyDataSetChanged();
        lv.setAdapter(pa);

        bnv = findViewById(R.id.BottomNavigationView_basket);
        bnv.setSelectedItemId(R.id.basket);
        btn_clean = findViewById(R.id.clean_purchases);

        btn_clean.setOnClickListener(view -> {
            db.dropTables(ShoppingDatabase.TB_PURCHASES);
            db.createPurchases();
            Intent intent = new Intent(getBaseContext(),PurchasesActivity.class);
            startActivity(intent);
        });
        bnv.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent1 = new Intent(getBaseContext(),HomeActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.products:
                    Intent intent2 = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.profile:
                    Intent intent3 = new Intent(getBaseContext(),ProfileActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.basket:
                    Intent intent4 = new Intent(getBaseContext(),PurchasesActivity.class);
                    startActivity(intent4);
                    break;
            }
            return true;
        });
    }
}