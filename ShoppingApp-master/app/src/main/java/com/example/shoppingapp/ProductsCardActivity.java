package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProductsCardActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    RecyclerView rv;
    public static final String PRODUCT_ID_KEY = "product_key";
    public static final String TABLE_NAME_KEY = "table_name_key";
    TextView tv_product_name;
    ShoppingDatabase db;
    ImageButton btn_back;
    private String table_name;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_card);

        rv = findViewById(R.id.rv_products);
        tv_product_name = findViewById(R.id.tv_product_name);
        btn_back = findViewById(R.id.btn_back);


        db = new ShoppingDatabase(this);

        switch (MainActivity.name_data){
            case "Fashion":
                tv_product_name.setText("Одежда");
                table_name = ShoppingDatabase.TB_FASHION;
                break;
            case "Books":
                tv_product_name.setText("Книги");
                table_name = ShoppingDatabase.TB_BOOK;
                break;
            case "Beauty Tools":
                tv_product_name.setText("Косметика");
                table_name = ShoppingDatabase.TB_BEAUTY;
                break;
            case "Electronics":
                tv_product_name.setText("Электроника");
                table_name = ShoppingDatabase.TB_ELECTRICS;
                break;
            case "Video Game":
                tv_product_name.setText("Видеоигры");
                table_name = ShoppingDatabase.TB_GAME;
                break;
            case "Home and Cooker":
                tv_product_name.setText("Всё для дома");
                table_name = ShoppingDatabase.TB_HOME_COOKER;
                break;
            case "Laptop":
                tv_product_name.setText("Ноутбуки");
                table_name = ShoppingDatabase.TB_LAPTOP;
                break;
            case "Mobile":
                tv_product_name.setText("Смартфоны");
                table_name = ShoppingDatabase.TB_MOBILE;
                break;
            case "Sports":
                tv_product_name.setText("Спорт");
                table_name = ShoppingDatabase.TB_SPORTS;
                break;
            case "Car Tools":
                tv_product_name.setText("Автотовары");
                table_name = ShoppingDatabase.TB_CAR_TOOL;
                break;
        }
        MainActivity.name_data = "";

        ArrayList<Products> products;
        products = db.getAllProducts(table_name);
        ProductAdapter adapter = new ProductAdapter(products, productId -> {
            Intent i = new Intent(getBaseContext(), DisplayProductsActivity.class);
            i.putExtra(PRODUCT_ID_KEY, productId);
            i.putExtra(TABLE_NAME_KEY, table_name);
            HomeActivity.flag = false;
            startActivity(i);
        });
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,2);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        bnv = findViewById(R.id.BottomNavigationView_productsCards);
        bnv.setSelectedItemId(R.id.products);
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
        btn_back.setOnClickListener(v -> finish());
    }
}