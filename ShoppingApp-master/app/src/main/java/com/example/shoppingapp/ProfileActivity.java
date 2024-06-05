package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Button logoutButton;
    EditText et_name,et_gmail,et_phone;
    ImageView iv_edit_data,iv_edit_photo,iv_photo;
    Button btn_save;
    TextView tv_full_name,tv_user_name;
    LinearLayout layout_name;
    Uri imageUri;
    SharedPreferences shp_id;
    private static final int PICK_IMAGE_REQ_COD = 1;
    ShoppingDatabase db;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        et_name = findViewById(R.id.et_full_name_profile);
        et_gmail = findViewById(R.id.et_gmail_user_profile);
        et_phone = findViewById(R.id.et_phone_user_profile);
        iv_edit_data = findViewById(R.id.iv_edit_data);
        iv_edit_photo = findViewById(R.id.iv_edit_photo);
        iv_photo = findViewById(R.id.ri_profile_image);
        btn_save = findViewById(R.id.btn_save_data);
        tv_full_name = findViewById(R.id.tv_full_name_profile);
        tv_user_name = findViewById(R.id.tv_user_name_profile);
        layout_name = findViewById(R.id.enter_name);

        shp_id = getSharedPreferences("Preferences_id", MODE_PRIVATE);

        int user_id = shp_id.getInt("user_id",0);

        db = new ShoppingDatabase(this);

        Users users = db.getUser(user_id);
        et_name.setText(users.getFullName());
        et_gmail.setText(users.getEmail());
        et_phone.setText(users.getPhone());
        if(users.getUserImage()!=null && !users.getUserImage().isEmpty()){
            imageUri = Uri.parse(users.getUserImage());
            iv_photo.setImageURI(imageUri);
        }else
            iv_photo.setImageResource(R.drawable.man);
        tv_full_name.setText(users.getFullName());
        tv_user_name.setText(users.getUserName());

        iv_edit_data.setOnClickListener(view -> {
            iv_edit_data.setVisibility(View.INVISIBLE);
            btn_save.setVisibility(View.VISIBLE);
            et_name.setEnabled(true);
            et_gmail.setEnabled(true);
            et_phone.setEnabled(true);
            iv_edit_photo.setVisibility(View.VISIBLE);
            layout_name.setVisibility(View.VISIBLE);
        });

        iv_edit_photo.setOnClickListener(view -> {
            Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(in,PICK_IMAGE_REQ_COD);
        });

        btn_save.setOnClickListener(view -> {
            String name = et_name.getText().toString();
            String gmail = et_gmail.getText().toString();
            String phone = et_phone.getText().toString();
            String image = "";
            if(imageUri != null)
                image = imageUri.toString();

            db.upDataUser(new Users(user_id,name,image,gmail,phone));

            Users user = db.getUser(user_id);
            et_name.setText(user.getFullName());
            et_gmail.setText(user.getEmail());
            et_phone.setText(user.getPhone());
            if(user.getUserImage()!=null && !user.getUserImage().isEmpty())
                iv_photo.setImageURI(Uri.parse(user.getUserImage()));
            else
                iv_photo.setImageResource(R.drawable.man);
            tv_full_name.setText(user.getFullName());

            iv_edit_data.setVisibility(View.VISIBLE);
            btn_save.setVisibility(View.GONE);
            et_name.setEnabled(false);
            et_gmail.setEnabled(false);
            et_phone.setEnabled(false);
            iv_edit_photo.setVisibility(View.GONE);
            layout_name.setVisibility(View.GONE);
        });

        logoutButton = findViewById(R.id.button_exit);
        logoutButton.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        });

        bnv = findViewById(R.id.BottomNavigationView_profile);
        bnv.setSelectedItemId(R.id.profile);
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