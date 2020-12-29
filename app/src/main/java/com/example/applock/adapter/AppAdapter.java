package com.example.applock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applock.Interface.AppOnClickListener;
import com.example.applock.R;
import com.example.applock.ViewHolder.AppViewHolder;
import com.example.applock.model.AppItem;
import com.example.applock.utils.Utils;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    private Context mContext;
    private List<AppItem> appItemList;
    private Utils utils;

    public AppAdapter(Context nContext, List<AppItem> appItemList) {
        this.mContext = nContext;
        this.appItemList = appItemList;
        this.utils = new Utils(mContext);
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_apps,parent,false);

        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppViewHolder holder, int position) {

        holder.name_app.setText(appItemList.get(position).getName());
        holder.icon_app.setImageDrawable(appItemList.get(position).getIcon());

        final String pk = appItemList.get(position).getPackageName();

        if (utils.isLock(pk)){
            holder.lock_app.setImageResource(R.drawable.ic_close_lock);
        }else{
            holder.lock_app.setImageResource(R.drawable.ic_open_lock);
        }

        holder.setListener(new AppOnClickListener() {
            @Override
            public void selectApp(int pos) {

                if(utils.isLock(pk)){

                    holder.lock_app.setImageResource(R.drawable.ic_open_lock);
                    utils.unlock(pk);

                }else{

                    holder.lock_app.setImageResource(R.drawable.ic_close_lock);
                    utils.lock(pk);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
