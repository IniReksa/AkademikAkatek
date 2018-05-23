package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
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

    private Context context;
    private List<Jadwal> jadwals;

    public RvJadwalMain(Context context, List<Jadwal> jadwals) {
        this.context = context;
        this.jadwals = jadwals;
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
        holder.matkul.setText(itemjadwal.NamaMk);
        holder.dosen.setText(itemjadwal.NamaDosen);
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

            sesi = itemView.findViewById(R.id.sesi);
            matkul = itemView.findViewById(R.id.matkul);
            dosen = itemView.findViewById(R.id.dosen);
            ruangan = itemView.findViewById(R.id.ruangan);
            hari = itemView.findViewById(R.id.jadwalHari);

        }
    }
}
