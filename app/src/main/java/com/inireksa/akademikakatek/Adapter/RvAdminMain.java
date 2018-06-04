package com.inireksa.akademikakatek.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inireksa.akademikakatek.Model.ItemGridAdmin;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/2/2018.
 */

public class RvAdminMain extends RecyclerView.Adapter<RvAdminMain.ViewHolder> {

    List<ItemGridAdmin> mItems;

    public RvAdminMain() {
        super();
        mItems = new ArrayList<ItemGridAdmin>();
        mItems.add(new ItemGridAdmin("Input Info", R.drawable.ic_info));
        mItems.add(new ItemGridAdmin("Update Jadwal", R.drawable.ic_updatejadwal));

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_admin, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemGridAdmin itemGridAdmin = mItems.get(position);
        holder.imgThumnail.setImageResource(itemGridAdmin.mThumnail);
        holder.textView.setText(itemGridAdmin.mName);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgThumnail;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            textView = (TextView) itemView.findViewById(R.id.namaGrid);
        }
    }
}
