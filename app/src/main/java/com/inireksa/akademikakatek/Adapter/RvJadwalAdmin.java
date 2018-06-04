package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Model.Jadwal;
import com.inireksa.akademikakatek.R;
import com.inireksa.akademikakatek.UpdateJadwalActivity;

import java.util.List;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class RvJadwalAdmin extends RecyclerView.Adapter<RvJadwalAdmin.ViewHolder> {

    private Context context;
    private List<Jadwal> jadwals;

    public RvJadwalAdmin(Context context, List<Jadwal> jadwals) {
        this.context = context;
        this.jadwals = jadwals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rvjadwaladmin, parent, false);
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
        holder.idjadwal.setText(itemjadwal.IdJadwal);
        holder.kelas.setText(itemjadwal.Kelas);
        holder.angkatan.setText(itemjadwal.Angkatan);
        holder.jurusan.setText(itemjadwal.Jurusan);
    }

    @Override
    public int getItemCount() {
        return jadwals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView sesi, matkul, dosen, ruangan, hari, idjadwal, jurusan, angkatan, kelas;

        public ViewHolder(View itemView) {
            super(itemView);

            kelas = itemView.findViewById(R.id.kelasAdmin);
            angkatan = itemView.findViewById(R.id.angkatanAdmin);
            jurusan = itemView.findViewById(R.id.jurusanAdmin);
            sesi = itemView.findViewById(R.id.sesi);
            matkul = itemView.findViewById(R.id.matkul);
            dosen = itemView.findViewById(R.id.dosen);
            ruangan = itemView.findViewById(R.id.ruangan);
            hari = itemView.findViewById(R.id.jadwalHari);
            idjadwal = itemView.findViewById(R.id.idJadwal);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            String kirimmatkul = matkul.getText().toString();
            Log.d("data", "matkul" +kirimmatkul);
            String kirimdosen = dosen.getText().toString();
            Log.d("data", "dosen" +kirimdosen);
            String kirimsesi = sesi.getText().toString();
            Log.d("data", "sesi" +kirimsesi);
            String kirimruangan = ruangan.getText().toString();
            Log.d("data", "ruangan" +kirimruangan);
            String kirimhari = hari.getText().toString();
            Log.d("data", "hari" +kirimhari);
            String kirimid  = idjadwal.getText().toString();
            Log.d("data", "id" +kirimid);
            String kirimkelas = kelas.getText().toString();
            Log.d("data", "kelas" +kirimkelas);
            String kirimangkatan  = angkatan.getText().toString();
            Log.d("data", "angkatan" +kirimangkatan);
            String kirimjurusan = jurusan.getText().toString();
            Log.d("data", "jurusan" +kirimjurusan);

            Intent update = new Intent(context, UpdateJadwalActivity.class);
            update.putExtra("matkul", kirimmatkul);
            update.putExtra("dosen", kirimdosen);
            update.putExtra("sesi", kirimsesi);
            update.putExtra("ruangan", kirimruangan);
            update.putExtra("hari", kirimhari);
            update.putExtra("id", kirimid);
            update.putExtra("kelas", kirimkelas);
            update.putExtra("jurusan", kirimjurusan);
            update.putExtra("angkatan", kirimangkatan);
            context.startActivity(update);
        }
    }
}
