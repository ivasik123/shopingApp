package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    CardView fashion_card,book_card,beauty_card,electrics_card,game_card,homeCooker_card,laptop_card,mobile_card,sports_card,carTools_card;
    TextView tv_fashion,tv_book,tv_beauty,tv_electrics,tv_game,tv_home,tv_laptop,tv_mobile,tv_sports,tv_car;
    public static final String FASHION_KEY = "fashion_key";
    public static final String BOOK_KEY = "book_key";
    public static final String BEAUTY_KEY = "beauty_key";
    public static final String ELECTRICS_KEY = "electrics_key";
    public static final String GAME_KEY = "game_key";
    public static final String HOME_KEY = "home_key";
    public static final String LAPTOP_KEY = "laptop_key";
    public static final String MOBILE_KEY = "mobile_key";
    public static final String SPORTS_KEY = "sports_key";
    public static final String CAR_KEY = "car_key";
    public static String name_data ="";

    ShoppingDatabase db;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fashion_card = findViewById(R.id.fashion_card);
        book_card = findViewById(R.id.book_card);
        beauty_card = findViewById(R.id.beauty_card);
        electrics_card = findViewById(R.id.electronics_card);
        game_card = findViewById(R.id.games_card);
        homeCooker_card = findViewById(R.id.home_cooker_card);
        laptop_card = findViewById(R.id.laptop_card);
        mobile_card = findViewById(R.id.mobiles_card);
        sports_card = findViewById(R.id.sports_card);
        carTools_card = findViewById(R.id.car_card);
        tv_fashion = findViewById(R.id.tv_fashion_card);
        tv_book = findViewById(R.id.tv_books_card);
        tv_beauty = findViewById(R.id.tv_beauty_card);
        tv_electrics = findViewById(R.id.tv_electronics_card);
        tv_game = findViewById(R.id.tv_game_card);
        tv_home = findViewById(R.id.tv_home_card);
        tv_laptop = findViewById(R.id.tv_laptop_card);
        tv_mobile = findViewById(R.id.tv_mobile_card);
        tv_sports = findViewById(R.id.tv_sports_card);
        tv_car = findViewById(R.id.tv_car_card);


        db = new ShoppingDatabase(this);


        fashion_card.setOnClickListener(view -> {
            name_data = "Fashion";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(FASHION_KEY, name_data);
            startActivity(intent);
        });

        electrics_card.setOnClickListener(view -> {
            name_data = "Electronics";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(ELECTRICS_KEY, name_data);
            startActivity(intent);
        });

        mobile_card.setOnClickListener(view -> {
            name_data = "Mobile";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(MOBILE_KEY, name_data);
            startActivity(intent);
        });

        laptop_card.setOnClickListener(view -> {
            name_data = "Laptop";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(LAPTOP_KEY, name_data);
            startActivity(intent);
        });

        game_card.setOnClickListener(view -> {
            name_data = "Video Game";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(GAME_KEY, name_data);
            startActivity(intent);
        });

        sports_card.setOnClickListener(view -> {
            name_data = "Sports";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(SPORTS_KEY, name_data);
            startActivity(intent);
        });

        book_card.setOnClickListener(view -> {
            name_data = "Books";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(BOOK_KEY, name_data);
            startActivity(intent);
        });

        homeCooker_card.setOnClickListener(view -> {
            name_data = "Home and Cooker";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(HOME_KEY, name_data);
            startActivity(intent);
        });

        beauty_card.setOnClickListener(view -> {
            name_data = "Beauty Tools";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(BEAUTY_KEY, name_data);
            startActivity(intent);
        });

        carTools_card.setOnClickListener(view -> {
            name_data = "Car Tools";
            Intent intent = new Intent(getBaseContext(),ProductsCardActivity.class);
            intent.putExtra(CAR_KEY, name_data);
            startActivity(intent);
        });

        bnv = findViewById(R.id.BottomNavigationView_categories);
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

    }
}