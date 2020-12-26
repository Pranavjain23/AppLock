package com.example.applock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applock.R;
import com.example.applock.ViewHolder.AppViewHolder;
import com.example.applock.model.AppItem;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    private Context mContext;
    private List<AppItem> appItemList;

    public AppAdapter(Context nContext, List<AppItem> appItemList) {
        this.mContext = nContext;
        this.appItemList = appItemList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_apps,parent,false);

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

        holder.name_app.setText(appItemList.get(position).getName());
        holder.icon_app.setImageDrawable(appItemList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
