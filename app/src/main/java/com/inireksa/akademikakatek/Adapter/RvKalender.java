package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Model.Kalender;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/11/2018.
 */

public class RvKalender extends RecyclerView.Adapter<RvKalender.ViewHolder> {

    private Context context;
    private List<Kalender> kalenders;

    public RvKalender(Context context, List<Kalender> kalenders) {
        this.context = context;
        this.kalenders = kalenders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rvkalender, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kalender itemKalender = kalenders.get(position);
        holder.tanggal.setText(itemKalender.Waktu);
        holder.kegiatan.setText(itemKalender.Kegiatan);
        holder.ketmahasiswa.setText(itemKalender.Keterangan);
    }

    @Override
    public int getItemCount() {
        return kalenders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal;
        TextView kegiatan;
        TextView ketmahasiswa;

        public ViewHolder(View itemView) {
            super(itemView);

            tanggal = (TextView) itemView.findViewById(R.id.tanggalKalender);
            kegiatan = (TextView) itemView.findViewById(R.id.ketKalender);
            ketmahasiswa = (TextView) itemView.findViewById(R.id.ketMahasiswa);
        }
    }
}
