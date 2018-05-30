package com.inireksa.akademikakatek.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Model.Nilai;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/11/2018.
 */

public class RvNilai extends RecyclerView.Adapter<RvNilai.ViewHolder> {

    private Context context;
    private List<Nilai> nilais;

    public RvNilai(Context context, List<Nilai> nilais) {
        this.context = context;
        this.nilais = nilais;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rvnilai, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Nilai itemnilai = nilais.get(position);
        holder.matkul.setText(itemnilai.NamaMk);
        holder.hadir.setText(itemnilai.Kehadiran);
        holder.hadir.setText(itemnilai.Kehadiran);
        holder.tugas.setText(itemnilai.Tugas);
        holder.uts.setText(itemnilai.Uts);
        holder.uas.setText(itemnilai.Uas);
        holder.nilaiAkhir.setText(itemnilai.NilaiAkhir);
    }

    @Override
    public int getItemCount() {
        return nilais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView matkul, hadir, tugas, uts, uas, nilaiAkhir;

        public ViewHolder(View itemView) {
            super(itemView);

            matkul = itemView.findViewById(R.id.matkulnilai);
            hadir = itemView.findViewById(R.id.nilaihadir);
            tugas = itemView.findViewById(R.id.nilaitugas);
            uts = itemView.findViewById(R.id.nilaiuts);
            uas = itemView.findViewById(R.id.nilaiuas);
            nilaiAkhir = itemView.findViewById(R.id.nilaiakhir);
        }
    }
}
