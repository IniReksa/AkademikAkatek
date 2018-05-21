package com.inireksa.akademikakatek;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.inireksa.akademikakatek.Fragment.LoginAdminFragment;
import com.inireksa.akademikakatek.Fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = new SharedPref(LoginActivity.this);
        if (sharedPref.getSudahLogin()){
            startActivity(new Intent(LoginActivity.this, Main2Activity.class));
            finish();
        }
        if (sharedPref.getAdminSudahLogin()){
            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            finish();
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout =findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           switch (position){
               case 0 :
                   LoginFragment tab1 = new LoginFragment();
                   return tab1;
               case 1 :
                   LoginAdminFragment tab2 = new LoginAdminFragment();
                   return tab2;
               default :
                   return null;
           }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
