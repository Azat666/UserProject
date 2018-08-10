package com.example.student.androidappazat2.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.student.androidappazat2.R;
import com.example.student.androidappazat2.adapters.AdapterPager;
import com.example.student.androidappazat2.models.ModelPager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<ModelPager> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list.add(new ModelPager(R.drawable.op1));
        list.add(new ModelPager(R.drawable.op5));

        AdapterPager adapter = new AdapterPager(this, list);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }


}
