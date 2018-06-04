package com.inireksa.akademikakatek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inireksa.akademikakatek.API.ApiUrl;
import com.inireksa.akademikakatek.API.InterfaceAPI;
import com.inireksa.akademikakatek.Model.ServerResponse;
import com.inireksa.akademikakatek.Model.SpinnerDosen;
import com.inireksa.akademikakatek.Model.SpinnerKelas;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateJadwalActivity extends AppCompatActivity {

    String pilihdosen, KodeDosen,matkul, dosen, sesi, ruangan, hari, id, kelas, jurusan, angkatan;
    TextView txtMatkul, txtId, txtKelas, txtJurusan, txtAngkatan;
    Spinner  spinSesi, spinRuangan, spinHari, spinDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jadwal);

        Intent intent = getIntent();
        matkul = intent.getStringExtra("matkul");
        dosen = intent.getStringExtra("dosen");
        sesi = intent.getStringExtra("sesi");
        ruangan = intent.getStringExtra("ruangan");
        hari = intent.getStringExtra("hari");
        id = intent.getStringExtra("id");
        kelas = intent.getStringExtra("kelas");
        jurusan = intent.getStringExtra("jurusan");
        angkatan = intent.getStringExtra("angkatan");

        txtMatkul = findViewById(R.id.matkulUpdate);
        txtKelas = findViewById(R.id.kelasUpdate);
        txtJurusan = findViewById(R.id.jurusanUpdate);
        txtAngkatan = findViewById(R.id.angkatanUpdate);
        txtId = findViewById(R.id.idJadwalUpdate);
        spinDosen = findViewById(R.id.spinDosen);
        spinRuangan = findViewById(R.id.spinRuangan);
        spinSesi = findViewById(R.id.spinSesi);
        spinHari = findViewById(R.id.spinHari);

        txtMatkul.setText(matkul);
        txtKelas.setText(kelas);
        txtJurusan.setText(jurusan);
        txtAngkatan.setText(angkatan);
        txtId.setText(id);
        spinnerDosen();

        Button btnUpdate = findViewById(R.id.btnUpdateJadwal);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatejadwal();
            }
        });
    }

    private void updatejadwal() {
        String ambilsesi = spinSesi.getSelectedItem().toString();
        String ambilruangan = spinRuangan.getSelectedItem().toString();
        String ambilhari = spinHari.getSelectedItem().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<ServerResponse> callDosen = api.updatejadwal(id, KodeDosen, ambilsesi,ambilhari, ambilruangan);
        callDosen.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                String error = response.body().Error;
                String message = response.body().Message;

                if (error != null){
                    if (error.equals("0")){
                        Toast.makeText(UpdateJadwalActivity.this, message, Toast.LENGTH_SHORT).show();

                    }
                    if (error.equals("1")){
                        Toast.makeText(UpdateJadwalActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateJadwalActivity.this, "Tidak Ada Respon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(UpdateJadwalActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spinnerDosen() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.URL_ROOT_LOCAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<List<SpinnerDosen>> callDosen = api.ambildosen();
        callDosen.enqueue(new Callback<List<SpinnerDosen>>() {
            @Override
            public void onResponse(Call<List<SpinnerDosen>> call, Response<List<SpinnerDosen>> response) {
                List<SpinnerDosen> dataDosen = response.body();
                Log.d("data ", "dosen" +dataDosen);
                if (dataDosen != null){
                    List<String> listspinner = new ArrayList<String>();
                    for (int i = 0; i < dataDosen.size(); i++){
                        listspinner.add(dataDosen.get(i).NamaDosen);
                        Log.d("data", "Spinner Dosen" +listspinner);
                    }
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateJadwalActivity.this,
                            android.R.layout.simple_spinner_item, listspinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinDosen.setAdapter(adapter);

                    spinDosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            pilihdosen = adapter.getItem(i).toString();
                            StringTokenizer kode = new StringTokenizer(pilihdosen, "-");
                            KodeDosen = kode.nextToken();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                } else {
                    Toast.makeText(UpdateJadwalActivity.this, "Gagal mengambil dosen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SpinnerDosen>> call, Throwable t) {

            }
        });
    }
}
