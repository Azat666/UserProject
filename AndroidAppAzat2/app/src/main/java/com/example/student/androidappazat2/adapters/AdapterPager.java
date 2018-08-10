package com.example.student.androidappazat2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.student.androidappazat2.R;
import com.example.student.androidappazat2.activitys.UserActivity;
import com.example.student.androidappazat2.models.ModelPager;

import java.util.List;

public class AdapterPager extends PagerAdapter {

    private final Context context;
    private final List<ModelPager> list;

    public AdapterPager(Context context, List<ModelPager> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);
        ImageButton imageButton = view.findViewById(R.id.image_buuton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(context, UserActivity.class);
                context.startActivity(intent);
            }
        });
        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setImageResource(list.get(position).getImageId());
        container.addView(view);
        return view;
    }

}
