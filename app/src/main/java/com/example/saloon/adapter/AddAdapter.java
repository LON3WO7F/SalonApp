package com.example.saloon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saloon.R;
import com.example.saloon.pojo.Retrieve;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.AddViewHolder> {

    private Context mcontext;
    private List<Retrieve> mretrieve;

    public AddAdapter(Context mcontext, List<Retrieve> mretrieve) {
        this.mcontext = mcontext;
        this.mretrieve = mretrieve;
    }

    @Override
    public AddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.add_card,parent,false);

        return new AddViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddViewHolder holder, int position) {

        Retrieve retrievePosition = mretrieve.get(position);
        Picasso.get().load(retrievePosition.getUrl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imageView) ;

    }

    @Override
    public int getItemCount() {
        return mretrieve.size();
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public AddViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.AddImage);

        }
    }
}
