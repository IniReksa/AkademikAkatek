package com.inireksa.akademikakatek;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.Adapter.RvAdminMain;

public class AdminActivity extends AppCompatActivity {

    private final static String TAG = "AdminActivity";

    RecyclerView recyclerViewMain;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    String Kelas, Matkul, Dosen, Sesi, Ruangan, Hari;
    Spinner spinnerKelas, spinnerMatkul, spinnerDosen, spinnerSesi, spinnerRuangan, spinnerHari;

    SharedPref sharedPref;
    Dialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerViewMain = findViewById(R.id.rvHomeAdmin);
        recyclerViewMain.setHasFixedSize(true);
        sharedPref = new SharedPref(AdminActivity.this);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerViewMain.setLayoutManager(layoutManager);
        adapter = new RvAdminMain();
        recyclerViewMain.setAdapter(adapter);

        recyclerViewMain.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = recyclerViewMain.findChildViewUnder(e.getX(), e.getY());
                if (child!= null && gestureDetector.onTouchEvent(e)) {
                    int position = recyclerViewMain.getChildAdapterPosition(child);
                    if (position == 0 ){
                        dialogInputInfo();
                    }
                    if (position == 1){
                        dialogUpdateJadwal();
                    }
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
    }

    private void dialogInputInfo() {
        mydialog = new Dialog(AdminActivity.this);
        mydialog.setContentView(R.layout.dialog_input_info);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView info = mydialog.findViewById(R.id.infoterbaru);
        final String isiinfo = info.getText().toString();
        Button btninfo = mydialog.findViewById(R.id.btnInputInfo);
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(isiinfo)){
                    info.setError("Isi Info terlebih dahulu");
                    return;
                }
                Toast.makeText(AdminActivity.this, "Info Terkirim", Toast.LENGTH_SHORT).show();
            }
        });
        mydialog.show();
    }

    private void dialogUpdateJadwal() {
        mydialog = new Dialog(AdminActivity.this);
        mydialog.setContentView(R.layout.dialog_update_jadwal);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        spinnerKelas = mydialog.findViewById(R.id.kategoriKelas);
        spinnerMatkul = mydialog.findViewById(R.id.kategoriMatkul);
        spinnerDosen = mydialog.findViewById(R.id.kategoriDosen);
        spinnerSesi = mydialog.findViewById(R.id.kategoriSesi);
        spinnerRuangan = mydialog.findViewById(R.id.kategoriRuangan);
        spinnerHari = mydialog.findViewById(R.id.kategoriHari);
        Button btnUpdate = mydialog.findViewById(R.id.btnUpdateJadwal);
        TextView btnLihatJadwal = mydialog.findViewById(R.id.lihatjadwal);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Kelas = spinnerKelas.getSelectedItem().toString();
                Matkul = spinnerMatkul.getSelectedItem().toString();
                Dosen = spinnerDosen.getSelectedItem().toString();
                Sesi = spinnerSesi.getSelectedItem().toString();
                Ruangan = spinnerRuangan.getSelectedItem().toString();
                Hari = spinnerHari.getSelectedItem().toString();

                Toast.makeText(AdminActivity.this, "Update Jadwal Kelas " +Kelas+ ", " +Matkul+ ", " +Dosen+ " Sukses", Toast.LENGTH_SHORT).show();
                mydialog.dismiss();
            }
        });

        btnLihatJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, LihatJadwalActivity.class));
            }
        });

        mydialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                        .setMessage("Yakin ingin Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPref.saveBooleanAdmin(SharedPref.SP_ADMIN_SUDAH_LOGIN, false);
                                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .setMessage("Yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
