package com.inireksa.akademikakatek.AdapterMain;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inireksa.akademikakatek.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IniReksa on 5/2/2018.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<ItemGridMain> mItems;

    public GridAdapter() {
        super();
        mItems = new ArrayList<ItemGridMain>();
        ItemGridMain menu = new ItemGridMain();
        menu.setmName("Dosen");
        menu.setmThumnail(R.drawable.ic_mhs);
        mItems.add(menu);

        menu = new ItemGridMain();
        menu.setmName("Mahasiswa");
        menu.setmThumnail(R.drawable.ic_mhs);
        mItems.add(menu);

        menu = new ItemGridMain();
        menu.setmName("Jadwal");
        menu.setmThumnail(R.drawable.ic_mhs);
        mItems.add(menu);

        menu = new ItemGridMain();
        menu.setmName("Tentang");
        menu.setmThumnail(R.drawable.ic_mhs);
        mItems.add(menu);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kelas_main, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemGridMain nature = mItems.get(position);
        holder.imgThumnail.setImageResource(nature.getmThumnail());
//        holder.textView.setText(nature.getmName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgThumnail;
        TextView textView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumnail = (ImageView) itemView.findViewById(R.id.img_kelas_main);
//            textView = (TextView) itemView.findViewById(R.id.status);
//            cardView = (CardView) itemView.findViewById(R.id.cardviewmain);
        }
    }
}
