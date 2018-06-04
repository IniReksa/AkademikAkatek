package com.inireksa.akademikakatek;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.support.design.widget.TextInputEditText;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Adapter.RvAdminMain;
import com.inireksa.akademikakatek.Model.SpinnerAngkatan;
import com.inireksa.akademikakatek.Model.ServerResponse;
import com.inireksa.akademikakatek.Model.SpinnerDosen;
import com.inireksa.akademikakatek.Model.SpinnerHari;
import com.inireksa.akademikakatek.Model.SpinnerJurusan;
import com.inireksa.akademikakatek.Model.SpinnerKelas;
import com.inireksa.akademikakatek.Model.SpinnerMatkul;
import com.inireksa.akademikakatek.Model.SpinnerRuangan;
import com.inireksa.akademikakatek.Model.SpinnerSesi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private final static String TAG = "AdminActivity";

    RecyclerView recyclerViewMain;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    String isiinfo, pilihangkatan;

    Spinner spinnerAngkatan;
    TextView namaAdmin;
    TextInputEditText info;
    Button btnKirimInfo;
    ProgressBar progressBar;
    RadioGroup radioGroup;
    boolean isSendAllChacked;

    SharedPref sharedPref;
    Dialog mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        sharedPref = new SharedPref(AdminActivity.this);

        namaAdmin = findViewById(R.id.namaAdmin);
        namaAdmin.setText(sharedPref.getNamaAdmin());
        recyclerViewMain = findViewById(R.id.rvHomeAdmin);
        recyclerViewMain.setHasFixedSize(true);

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
                        startActivity(new Intent(AdminActivity.this, LihatJadwalActivity.class));
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

        radioGroup = mydialog.findViewById(R.id.radioGroup);
        btnKirimInfo = mydialog.findViewById(R.id.btnInputInfo);
        progressBar = mydialog.findViewById(R.id.prograsbarinput);
        spinnerAngkatan = mydialog.findViewById(R.id.spinerkatergori);
        loadAngkatan();

        radioGroup.setOnCheckedChangeListener(this);
        btnKirimInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info = mydialog.findViewById(R.id.infoterbaru);
                isiinfo = info.getText().toString();
                btnKirimInfo.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(isiinfo)){
                    info.setError("Isi Info terlebih dahulu");
                    btnKirimInfo.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                
                kirimInfo();

            }
        });

        mydialog.show();

    }

    private void kirimInfo() {
        if(isSendAllChacked){
            sendMultiplePush();
        }else{
            sendSinglePush();
        }
        
    }

    private void sendSinglePush() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<ServerResponse> call = api.kiriminfoperkelas(pilihangkatan, "Akademik", isiinfo);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                String error = response.body().Error;
                String message = response.body().Message;

                if (error.equals("0")){
                    Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
                    info.setText("");
                    mydialog.dismiss();
                    btnKirimInfo.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                } if (error.equals("1")) {
                    btnKirimInfo.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                btnKirimInfo.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AdminActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMultiplePush() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<ServerResponse> call = api.kiriminfo("-1", "Akademik", isiinfo);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                String error = response.body().Error;
                String message = response.body().Message;

                if (error.equals("0")){
                    Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
                    info.setText("");
                    mydialog.dismiss();
                    btnKirimInfo.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                } if (error.equals("1")){
                    btnKirimInfo.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(AdminActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                btnKirimInfo.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(AdminActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radioButtonSemua:
                isSendAllChacked = true;
                spinnerAngkatan.setEnabled(false);
                break;

            case R.id.radioButtonPilihan:
                isSendAllChacked = false;
                spinnerAngkatan.setEnabled(true);
                break;
        }
    }


    private void loadAngkatan() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<List<SpinnerAngkatan>> call = api.ambilangkatan();
        call.enqueue(new Callback<List<SpinnerAngkatan>>() {
            @Override
            public void onResponse(Call<List<SpinnerAngkatan>> call, Response<List<SpinnerAngkatan>> response) {
                List<SpinnerAngkatan> dataresponse = response.body();
                Log.d(TAG, "data angkatan" +dataresponse);
                if (dataresponse != null) {
                    if (response.isSuccessful()) {
                        List<String> listspinner = new ArrayList<String>();
                        for (int i = 0; i < dataresponse.size(); i++) {
                            listspinner.add(dataresponse.get(i).angkatan);
                            Log.d(TAG, "Data spinner agkatan" + dataresponse.get(i).angkatan);
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminActivity.this,
                                android.R.layout.simple_spinner_item, listspinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerAngkatan.setAdapter(adapter);

                        spinnerAngkatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                pilihangkatan = adapter.getItem(i).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    } else {
                        Toast.makeText(AdminActivity.this, "Gagal mengambil angkatan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminActivity.this, "Tidak Ada Angkatan Lain", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SpinnerAngkatan>> call, Throwable t) {
                Toast.makeText(AdminActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
