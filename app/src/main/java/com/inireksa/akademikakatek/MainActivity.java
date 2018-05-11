package com.inireksa.akademikakatek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.inireksa.akademikakatek.AdapterMain.GridAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewMain;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(MainActivity.this.getApplicationContext());

        recyclerViewMain = findViewById(R.id.recyclerView);
        recyclerViewMain.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerViewMain.setLayoutManager(layoutManager);

        recyclerViewMain.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewMain, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position == 0 ){
                    Toast.makeText(MainActivity.this, "sekarang di" + position, Toast.LENGTH_SHORT).show();
                }
                if (position == 1){
                    Toast.makeText(MainActivity.this, "sekarang di" +position, Toast.LENGTH_SHORT).show();
                }
            }
        }));

        adapter = new GridAdapter();
        recyclerViewMain.setAdapter(adapter);
    }

    public static interface ClickListener{
        void onClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){

            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child!= null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
//            case R.id.menuLogout:
//                if (session.loggedin()){
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                    finish();
//                }
//                session.setLoggedin(false);
//                finish();
//                return true;
            case R.id.menukalender:
                Toast.makeText(getApplicationContext(), "Menu Kalender", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
