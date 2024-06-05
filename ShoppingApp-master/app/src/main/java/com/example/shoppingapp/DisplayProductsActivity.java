package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DisplayProductsActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    ImageView product_img;
    TextView product_name,Product_price,Product_discount,Product_brand,Product_description;
    Button add_to_cart;
    ImageButton btn_back;
    double priceAfter;
    ShoppingDatabase db;

    @SuppressLint({"SetTextI18n", "MissingInflatedId", "NonConstantResourceId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
        product_img = findViewById(R.id.display_iv_product);
        product_name = findViewById(R.id.display_tv_name);
        Product_price = findViewById(R.id.display_tv_price);
        Product_discount = findViewById(R.id.display_tv_discount);
        Product_brand = findViewById(R.id.display_tv_brand);
        Product_description = findViewById(R.id.display_tv_description);
        add_to_cart = findViewById(R.id.display_btn_cart);
        btn_back = findViewById(R.id.btn_back);

        int product_id;
        String table_name;
        Intent intent = getIntent();
        if(HomeActivity.flag){
            product_id = intent.getIntExtra(HomeActivity.PRODUCT_KEY,-1);
            table_name = intent.getStringExtra(HomeActivity.TABLE_NAME_KEY);
        }else{
            product_id = intent.getIntExtra(ProductsCardActivity.PRODUCT_ID_KEY,-1);
            table_name = intent.getStringExtra(ProductsCardActivity.TABLE_NAME_KEY);
        }

        db = new ShoppingDatabase(this);
        Products p = db.getProduct(product_id,table_name);

        if(p.getImage() != 0)
            product_img.setImageResource(p.getImage());
        product_name.setText(p.getName());
        if (p.getDiscount() > 0) {
            priceAfter = p.getPrice() - (p.getPrice() * (p.getDiscount() / 100));
            Product_discount.setText(priceAfter + "Р");
            Product_price.setText(p.getPrice()+"Р");
            Product_price.setPaintFlags(Product_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Product_price.setTextColor(Color.parseColor("#BFBFBF"));
        } else {
            priceAfter = p.getPrice();
            Product_discount.setText("");
            Product_price.setText(priceAfter + "Р");
            Product_price.setTextColor(Color.parseColor("#000000"));
        }
        Product_brand.setText(p.getBrand());
        Product_description.setText(p.getDescription());

        add_to_cart.setOnClickListener(view -> {
            int image = p.getImage();
            String name = product_name.getText().toString();
            String brand = Product_brand.getText().toString();
            Products ppp = new Products(image,name,priceAfter,brand);
            db.insertProductInPurchases(ppp);

            Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show();
        });

        bnv = findViewById(R.id.BottomNavigationView_products);
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