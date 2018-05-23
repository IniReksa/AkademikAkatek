package com.inireksa.akademikakatek.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inireksa.akademikakatek.Model.Info;
import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/7/2018.
 */

public class RvInfoMain extends RecyclerView.Adapter<RvInfoMain.ViewHolder> {

    private Context context;
    private List<Info> infos;

    public RvInfoMain(Context context, List<Info> infos) {
        this.context = context;
        this.infos = infos;
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
        holder.tanggal.setText(itemInfo.CreatedAt);
        holder.info.setText(itemInfo.IsiInfo);
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
