package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inireksa.akademikakatek.Model.Mahasiswa;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/18/2018.
 */

public class RvKlsFragment extends RecyclerView.Adapter<RvKlsFragment.MyViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswas;

    public RvKlsFragment(Context context, List<Mahasiswa> mahasiswas) {
        this.context = context;
        this.mahasiswas = mahasiswas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rvkelasfragment, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Mahasiswa itemMhs = mahasiswas.get(position);
        holder.namaMhs.setText(itemMhs.NamaMhs);
        holder.tlpMhs.setText(toString().valueOf(itemMhs.NoTlp));
        holder.alamatMhs.setText(itemMhs.Alamat);
        Glide.with(context)
                .load(itemMhs.FotoMhs)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ftMhs);
    }

    @Override
    public int getItemCount() {
        return mahasiswas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ftMhs;
        TextView namaMhs, alamatMhs, tlpMhs;

        public MyViewHolder(View itemView) {
            super(itemView);

            ftMhs = itemView.findViewById(R.id.fotomhs);
            namaMhs = itemView.findViewById(R.id.namamhs);
            alamatMhs = itemView.findViewById(R.id.alamatmhs);
            tlpMhs = itemView.findViewById(R.id.tlpmhs);
        }
    }
}
