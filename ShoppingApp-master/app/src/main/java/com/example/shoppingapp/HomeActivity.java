package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    RecyclerView rv;
    public static final String PRODUCT_KEY = "product_key";
    private static final int PERMISSION_REQ_COD = 1;
    public static final String TABLE_NAME_KEY = "table_key";
    SharedPreferences shp;
    SharedPreferences shp_id;
    public static boolean flag;
    ShoppingDatabase db;

    @SuppressLint({"NonConstantResourceId", "WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        flag = false;
        db = new ShoppingDatabase(this);

        db.dropTables(ShoppingDatabase.TB_PRODUCT_DISCOUNT);
        db.dropTables(ShoppingDatabase.TB_LAPTOP);
        db.dropTables(ShoppingDatabase.TB_BEAUTY);
        db.dropTables(ShoppingDatabase.TB_BOOK);
        db.dropTables(ShoppingDatabase.TB_CAR_TOOL);
        db.dropTables(ShoppingDatabase.TB_ELECTRICS);
        db.dropTables(ShoppingDatabase.TB_FASHION);
        db.dropTables(ShoppingDatabase.TB_SPORTS);
        db.dropTables(ShoppingDatabase.TB_MOBILE);
        db.dropTables(ShoppingDatabase.TB_HOME_COOKER);
        db.dropTables(ShoppingDatabase.TB_GAME);

        db.createProductDiscount();
        db.createTables(ShoppingDatabase.TB_LAPTOP);
        db.createTables(ShoppingDatabase.TB_BEAUTY);
        db.createTables(ShoppingDatabase.TB_BOOK);
        db.createTables(ShoppingDatabase.TB_CAR_TOOL);
        db.createTables(ShoppingDatabase.TB_ELECTRICS);
        db.createTables(ShoppingDatabase.TB_FASHION);
        db.createTables(ShoppingDatabase.TB_SPORTS);
        db.createTables(ShoppingDatabase.TB_MOBILE);
        db.createTables(ShoppingDatabase.TB_HOME_COOKER);
        db.createTables(ShoppingDatabase.TB_GAME);


        Products p = new Products(R.drawable.playstation4,"PlayStation 4",25000,"Sony","PlayStation 4 (PS4) — домашняя игровая приставка, разработанная Sony Interactive Entertainment.",10);
        db.insertProduct(p,ShoppingDatabase.TB_GAME);

        Products p1 = new Products(R.drawable.playstation5,"PlayStation 5",76000,"Sony","Консоль Sony PlayStation 5 + 2 беспроводных контроллера DualSense + FIFA 21",25);
        db.insertProduct(p1,ShoppingDatabase.TB_GAME);

        Products p2 = new Products(R.drawable.pes2012,"PES 2012",4900,"Konami","«Pro Evolution Soccer 2012» — мультиплатформенная видеоигра в жанре спортивного симулятора из серии «Pro Evolution Soccer» от компании «Konami»",0);
        db.insertProduct(p2,ShoppingDatabase.TB_GAME);

        Products p3 = new Products(R.drawable.menacing,"menacing",58500,"ASER","Acer Predator Thronos Air, Gaming Throne with Massage Pad and Gaming Chair, Up to 3 Displays, 130 Degrees Recline, LED Lighting, PC Landing Pad, Stabilizing Arm (PC and Monitors Sold Separately)",0);
        db.insertProduct(p3,ShoppingDatabase.TB_GAME);

        Products p4 = new Products(R.drawable.steelchair3,"Игровое кресло",60000,"ASER","different colors gaming Thermaltake U-Fit Black-Red Gaming Chair GGC-UFT-BRMWDS-01 GGC-UFT-BRMWDS-01",0);
        db.insertProduct(p4,ShoppingDatabase.TB_GAME);

        Products p5 = new Products(R.drawable.gamecontroller5,"Геймпад",800," Powerextra","Powerextra Контроллеры Xbox 360, USB-геймпад Проводной контроллер Улучшенный эргономичный джойстик Двойная вибрация, совместимый с Xbox 360 Slim / Xbox 360 / ПК (Windows / 7 / 8.1 / 10)",0);
        db.insertProduct(p5,ShoppingDatabase.TB_GAME);

        Products p6 = new Products(R.drawable.microwave,"Микроволновая печь",2700,"TOSHIBA","Микроволновая печь Toshiba - это надежное и удобное устройство для приготовления пищи. Она обладает стильным дизайном и компактными размерами, что делает ее отличным выбором для любой кухни.",0);
        db.insertProduct(p6,ShoppingDatabase.TB_ELECTRICS);

        Products p7 = new Products(R.drawable.vacuumcleaner,"Пылесос",2100,"TOSHIBA","Пылесос Toshiba - это надежное и мощное устройство для уборки вашего дома. Он обладает эффективным всасыванием и фильтрацией, что позволяет быстро и эффективно убирать пыль, грязь и волосы с различных поверхностей.",10);
        db.insertProduct(p7,ShoppingDatabase.TB_ELECTRICS);

        Products p8 = new Products(R.drawable.washer,"Посудомоечная машина",69000,"TOSHIBA","Посудомоечная машина Toshiba оснащена технологией УФ-светодиодного стерилизатора, которая стерилизует вашу кухонную утварь ультрафиолетовым светом после цикла стирки",0);
        db.insertProduct(p8,ShoppingDatabase.TB_ELECTRICS);

        Products p9 = new Products(R.drawable.teshert,"Толстовка",4500,"Zara","Черная толстовка с модным принтом",0);
        db.insertProduct(p9,ShoppingDatabase.TB_FASHION);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_COD);

        rv = findViewById(R.id.rv_home);
        shp = getSharedPreferences("myPreferences", MODE_PRIVATE);
        shp_id = getSharedPreferences("Preferences_id", MODE_PRIVATE);

        ArrayList<Products> products1;
        products1 = db.getAllProducts(ShoppingDatabase.TB_PRODUCT_DISCOUNT);
        HomeAdapter adapter = new HomeAdapter(products1, productId -> {
            Intent i = new Intent(getBaseContext(),DisplayProductsActivity.class);
            i.putExtra(PRODUCT_KEY,productId);
            i.putExtra(TABLE_NAME_KEY,ShoppingDatabase.TB_PRODUCT_DISCOUNT);
            flag = true;
            startActivity(i);
        });
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        bnv = findViewById(R.id.BottomNavigationView);
        bnv.setSelectedItemId(R.id.home);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}