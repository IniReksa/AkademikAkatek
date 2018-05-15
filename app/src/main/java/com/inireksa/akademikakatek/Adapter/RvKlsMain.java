package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inireksa.akademikakatek.Model.Mahasiswa;
import com.inireksa.akademikakatek.R;

import java.util.List;

/**
 * Created by IniReksa on 5/5/2018.
 */

public class RvKlsMain extends RecyclerView.Adapter<RvKlsMain.MyViewHolder> {

    Context mContext;
    List<Mahasiswa> mData;

    public RvKlsMain(Context mContext, List<Mahasiswa> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_kelas_main, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mahasiswa itemMahasiswa = mData.get(position);
        holder.img.setImageResource(itemMahasiswa.img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img_kelas_main);
        }
    }

}
