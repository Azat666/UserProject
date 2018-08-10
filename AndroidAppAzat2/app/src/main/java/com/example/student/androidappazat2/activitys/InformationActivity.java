package com.example.student.androidappazat2.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.student.androidappazat2.Datas;
import com.example.student.androidappazat2.R;
import com.example.student.androidappazat2.models.Result;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class InformationActivity extends AppCompatActivity {

    private TextView nameText, usernameText,
            phoneText, emailText, genderText;
    private CircleImageView circleImageView;
    private ImageButton locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findItems();
        setViewDatas();


    }

    private void findItems() {
        nameText = findViewById(R.id.info_name);
        usernameText = findViewById(R.id.info_username);
        phoneText = findViewById(R.id.info_phone);
        emailText = findViewById(R.id.info_email);
        genderText = findViewById(R.id.info_gender);
        circleImageView = findViewById(R.id.info_image);
        locationButton = findViewById(R.id.info_location);
    }

    private void setViewDatas() {
        nameText.setText(getListItem().getName().getFirst());
        usernameText.setText(getListItem().getLogin().getUsername());
        phoneText.setText(getListItem().getPhone());
        emailText.setText(getListItem().getEmail());
        genderText.setText(getListItem().getGender());
        Picasso.get().load(getListItem().getPicture().getLarge()).into(circleImageView);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, MapsActivity.class);
                intent.putExtra(Datas.KEY_FOR_MAP, Objects.requireNonNull(getIntent().getExtras()).getInt(Datas.KEY_FOR_INFO));
                startActivity(intent);
            }
        });
    }

    private Result getListItem() {
        return Datas.list.get(Objects.requireNonNull(getIntent().getExtras()).getInt(Datas.KEY_FOR_INFO));
    }
}
