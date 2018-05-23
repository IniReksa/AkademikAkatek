package com.inireksa.akademikakatek.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inireksa.akademikakatek.Model.Mahasiswa;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/5/2018.
 */

public class RvKlsMain extends RecyclerView.Adapter<RvKlsMain.MyViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswas;

    public RvKlsMain(Context context, List<Mahasiswa> mahasiswas) {
        this.context = context;
        this.mahasiswas = mahasiswas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kelas_main, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mahasiswa itemKelas = mahasiswas.get(position);
        Glide.with(context)
                .load(itemKelas.FotoMhs)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_kelas_main);
        }
    }

}
