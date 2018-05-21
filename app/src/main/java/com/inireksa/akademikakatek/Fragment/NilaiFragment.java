package com.inireksa.akademikakatek.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.Adapter.RvNilai;
import com.inireksa.akademikakatek.Model.Nilai;
import com.inireksa.akademikakatek.R;


public class NilaiFragment extends Fragment {

    private RecyclerView rvNilai;
    private RecyclerView.Adapter adapter;
    private String[] semester={"1", "2", "3", "4", "5", "6"};
    private Dialog mydialog;
    public String ActiveFragment = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ActiveFragment = "NILAI";

        View v = inflater.inflate(R.layout.fragment_nilai, container, false);
        rvNilai = v.findViewById(R.id.rvNilai);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvNilai.setLayoutManager(layoutManager);
        adapter = new RvNilai();
        rvNilai.setAdapter(adapter);

        //klik recyclerview
        rvNilai.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rvNilai.findChildViewUnder(e.getX(), e.getY());
                if (child!= null && gestureDetector.onTouchEvent(e)){
                    int position = rvNilai.getChildAdapterPosition(child);

                    mydialog = new Dialog(getContext());
                    mydialog.setContentView(R.layout.dialog_nilai);
                    mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    TextView dialogHadir = (TextView) mydialog.findViewById(R.id.nilaihadir);
                    TextView dialogTugas = (TextView) mydialog.findViewById(R.id.nilaitugas);
                    TextView dialogUts = (TextView) mydialog.findViewById(R.id.nilaiuts);
                    TextView dialogUas = (TextView) mydialog.findViewById(R.id.nilaiuas);
                    TextView dialogNilaiAkhir = (TextView) mydialog.findViewById(R.id.nilaiakhir);
                    mydialog.show();
                    Toast.makeText(getContext(), "Matkul " +position, Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        Spinner spinner = (Spinner) v.findViewById(R.id.spSemester);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, semester);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Semester" +adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return v;
    }
}
