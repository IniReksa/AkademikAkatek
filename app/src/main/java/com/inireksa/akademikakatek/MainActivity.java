package com.inireksa.akademikakatek;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.inireksa.akademikakatek.Fragment.HomeFragment;
import com.inireksa.akademikakatek.Fragment.InfoFragment;
import com.inireksa.akademikakatek.Fragment.JadwalFragment;
import com.inireksa.akademikakatek.Fragment.KalenderFragment;
import com.inireksa.akademikakatek.Fragment.NilaiFragment;
import com.inireksa.akademikakatek.Fragment.TentangFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
//  String ActiveFragment = "";
    HomeFragment homeFragment;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        sharedPref = new SharedPref(MainActivity.this);

        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, homeFragment);
        fragmentTransaction.commit();
//        ActiveFragment = "HOME";

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuInfo:
                InfoFragment infoFragment = new InfoFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, infoFragment);
                fragmentTransaction.commit();
//                ActiveFragment = "INFO";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menuHome) {
            homeFragment = new HomeFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, homeFragment);
            fragmentTransaction.commit();
//            ActiveFragment = "HOME";
        } else if (id == R.id.menuJadwal) {
            JadwalFragment jadwalFragment = new JadwalFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, jadwalFragment);
            fragmentTransaction.commit();
//            ActiveFragment = "JADWAL";
        } else if (id == R.id.menuNilai) {
            NilaiFragment nilaiFragment = new NilaiFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, nilaiFragment);
            fragmentTransaction.commit();
//            ActiveFragment = "NILAI";
        } else if (id == R.id.menukalender) {
            KalenderFragment kalenderFragment = new KalenderFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, kalenderFragment);
            fragmentTransaction.commit();
//            ActiveFragment = "KALENDER";

        }else if (id == R.id.menuTentang) {
            TentangFragment tentangFragment = new TentangFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, tentangFragment);
            fragmentTransaction.commit();
//            ActiveFragment = "TENTANG";

        }else if (id == R.id.logout){
            new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                    .setMessage("Yakin ingin Logout?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sharedPref.saveBoolean(SharedPref.SP_SUDAH_LOGIN, false);
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {

//        if (!ActiveFragment.equals("HOME")){
//            HomeFragment homeFragment = new HomeFragment();
//            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.framelayout, homeFragment);
//            fragmentTransaction.commit();
//        }else if (ActiveFragment.equals("HOME")) {

//        } else {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                new AlertDialog.Builder(this, R.style.CustomAlertDialog)
                        .setMessage("Yakin ingin keluar dari aplikasi?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
//                super.onBackPressed();
            }
        }

    }
//}
