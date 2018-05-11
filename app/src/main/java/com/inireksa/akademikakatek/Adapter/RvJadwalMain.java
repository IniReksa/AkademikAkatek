package com.inireksa.akademikakatek.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Jadwal;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class RvJadwalMain extends RecyclerView.Adapter<RvJadwalMain.ViewHolder> {

    List<Jadwal> jadwals;

    public RvJadwalMain(){
        super();
        jadwals = new ArrayList<Jadwal>();
        Jadwal jadwal = new Jadwal();
        jadwal.setSesi("4");
        jadwal.setDosen("Muhamad Angga Reksa Purwana");
        jadwal.setMatkul("Pemrograman Visual Basic .NET 2010");
        jadwal.setRuangan("2.3.2");
        jadwals.add(jadwal);

        jadwal = new Jadwal();
        jadwal.setSesi("8");
        jadwal.setDosen("Muhamad Angga Reksa Purwana");
        jadwal.setMatkul("Pemrograman Java java java java java");
        jadwal.setRuangan("2.2.1");
        jadwals.add(jadwal);

        jadwal = new Jadwal();
        jadwal.setSesi("2");
        jadwal.setDosen("Muhamad Angga Reksa Purwana");
        jadwal.setMatkul("Pemrograman WEB");
        jadwal.setRuangan("2.2.2");
        jadwals.add(jadwal);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rvjadwal, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Jadwal itemjadwal = jadwals.get(position);
        holder.sesi.setText(itemjadwal.getSesi());
        holder.matkul.setText(itemjadwal.getMatkul());
        holder.dosen.setText(itemjadwal.getDosen());
        holder.ruangan.setText(itemjadwal.getRuangan());
    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView sesi, matkul, dosen, ruangan;

        public ViewHolder(View itemView) {
            super(itemView);

            sesi = (TextView) itemView.findViewById(R.id.sesi);
            matkul = (TextView) itemView.findViewById(R.id.matkul);
            dosen = (TextView) itemView.findViewById(R.id.dosen);
            ruangan = (TextView) itemView.findViewById(R.id.ruangan);

        }
    }
}
