package com.inireksa.akademikakatek.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Model.Jadwal;
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
        jadwals.add(new Jadwal("8", "Pemrograman Visual Basic .NET 2010", "Muhamad Angga Reksa", "2.3.3", "Selasa"));
        jadwals.add(new Jadwal("4", "Pemrograman Visual Basic .NET 2010", "Muhamad Angga Reksa", "2.3.3", "Rabu"));
        jadwals.add(new Jadwal("5", "Pemrograman Web ", "Muhamad Angga Reksa", "2.6.3", "kamis"));

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
        holder.sesi.setText(itemjadwal.Sesi);
        holder.matkul.setText(itemjadwal.Matkul);
        holder.dosen.setText(itemjadwal.Dosen);
        holder.ruangan.setText(itemjadwal.Ruangan);
        holder.hari.setText(itemjadwal.Hari);
    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView sesi, matkul, dosen, ruangan, hari;

        public ViewHolder(View itemView) {
            super(itemView);

            sesi = (TextView) itemView.findViewById(R.id.sesi);
            matkul = (TextView) itemView.findViewById(R.id.matkul);
            dosen = (TextView) itemView.findViewById(R.id.dosen);
            ruangan = (TextView) itemView.findViewById(R.id.ruangan);
            hari = (TextView) itemView.findViewById(R.id.jadwalHari);

        }
    }
}
