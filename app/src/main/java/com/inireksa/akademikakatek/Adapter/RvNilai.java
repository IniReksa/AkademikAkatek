package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.Model.Kalender;
import com.inireksa.akademikakatek.Model.Nilai;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/11/2018.
 */

public class RvNilai extends RecyclerView.Adapter<RvNilai.ViewHolder> {

    private Context context;

    List<Nilai> nilais;

    public RvNilai() {
        super();
        nilais = new ArrayList<>();
        nilais.add(new Nilai("Pemrograman Visual Basic", "9", "70", "75", "80", "80"));
        nilais.add(new Nilai("Pemrograman WEB", "7", "80", "80","80","85"));
        nilais.add(new Nilai("Web Desain", "7", "80", "80","80","85"));
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
        holder.matkul.setText(itemnilai.matkul);
    }

    @Override
    public int getItemCount() {
        return nilais.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView matkul, detailnilai;

        public ViewHolder(View itemView) {
            super(itemView);

            matkul = itemView.findViewById(R.id.matkulnilai);
            detailnilai = itemView.findViewById(R.id.detailNilai);
        }

    }
}
