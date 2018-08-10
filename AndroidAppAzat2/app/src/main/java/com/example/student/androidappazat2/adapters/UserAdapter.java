package com.example.student.androidappazat2.adapters;


import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.androidappazat2.Datas;
import com.example.student.androidappazat2.R;
import com.example.student.androidappazat2.activitys.InformationActivity;
import com.example.student.androidappazat2.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Result> list = Datas.list;

    public UserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_girl_item, parent, false);
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.user_boy_item, parent, false);
            return new MyBoyViewHolder(view);
        }
        return new MyGirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (list.get(position).getGender().equals("male")) {
            Picasso.get().load(list.get(position).getPicture().getMedium()).into(((MyBoyViewHolder) holder).imageId);
            ((MyBoyViewHolder) holder).textname.setText(list.get(position).getName().getFirst());
        } else {
            Picasso.get().load(list.get(position).getPicture().getMedium()).into(((MyGirlViewHolder) holder).imageId);
            ((MyGirlViewHolder) holder).textname.setText(list.get(position).getName().getFirst());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyGirlViewHolder extends RecyclerView.ViewHolder {

        TextView textname;
        CircleImageView imageId;
        ImageButton callButton;

        public MyGirlViewHolder(View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.name_girl);
            imageId = itemView.findViewById(R.id.imageview_girl);
            callButton = itemView.findViewById(R.id.call_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InformationActivity.class);
                    intent.putExtra(Datas.KEY_FOR_INFO, getAdapterPosition());
                    context.startActivity(intent);
                }

            });
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String uri = "tel:" + list.get(getAdapterPosition()).getPhone();
                    final Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse(uri));
                    context.startActivity(callIntent);
                }
            });
        }
    }

    class MyBoyViewHolder extends RecyclerView.ViewHolder {

        TextView textname;
        CircleImageView imageId;
        ImageButton emailButton;

        public MyBoyViewHolder(View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.name);
            imageId = itemView.findViewById(R.id.imageview);
            emailButton = itemView.findViewById(R.id.email_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InformationActivity.class);
                    intent.putExtra(Datas.KEY_FOR_INFO, getAdapterPosition());
                    context.startActivity(intent);
                }

            });
            emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + list.get(getAdapterPosition()).getEmail()));
                    context.startActivity(Intent.createChooser(emailIntent, "Send Mail"));
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getGender().equals("male")) {
            return 1;
        }
        return 0;
    }
}