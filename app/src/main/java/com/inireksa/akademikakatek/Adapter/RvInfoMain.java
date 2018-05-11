package com.inireksa.akademikakatek.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Info;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class RvInfoMain extends RecyclerView.Adapter<RvInfoMain.ViewHolder> {

    List<Info> infos;

    public RvInfoMain(){
        super();
        infos = new ArrayList<Info>();
        Info info = new Info();
        info.setTanggal("2-3-2018");
        info.setInfo("uts akan dilaksanakan pada diharapkan semua mahasiswa sudah memenuhi persyarakat admin");
        infos.add(info);

        info = new Info();
        info.setTanggal("31-3-2018");
        info.setInfo("Cek sinyal");
        infos.add(info);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rvinfo, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Info itemInfo = infos.get(position);
        holder.tanggal.setText(itemInfo.getTanggal());
        holder.info.setText(itemInfo.getInfo());
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tanggal, info;

        public ViewHolder(View itemView) {
            super(itemView);

            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
            info = (TextView) itemView.findViewById(R.id.infoakademik);

        }
    }
}