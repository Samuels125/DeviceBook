package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.devicebook.devicebook.adapter_class.AdminAdapter;
import com.devicebook.devicebook.adapter_class.DisplayOrderAdapter;
import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    MyDatabase dbhelper;
    SQLiteDatabase mDatabase;
    TextView mobileorder;
    TextView tabletorder;
    TextView totalorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mobileorder = findViewById(R.id.mobileOrder);
       tabletorder = findViewById(R.id.tabletorder);
       totalorder = findViewById(R.id.totalrepair);

        ListView total_order_listview;

        dbhelper = new MyDatabase(AdminActivity.this);
        mDatabase = dbhelper.getWritableDatabase();

        String devicecounter;
       String tabletcounter;
       String totalcounter;
        devicecounter = String.valueOf(dbhelper.mobilecount());
        tabletcounter = String.valueOf(dbhelper.tabletcount());
        totalcounter = String.valueOf(dbhelper.totalrepaircount());
        mobileorder.setText(devicecounter);
        tabletorder.setText(tabletcounter);
       totalorder.setText(totalcounter);




    }

    public void mobilebutton(View view){
        Intent LogIntent = new Intent(AdminActivity.this, AdminListOrders.class);


        startActivity(LogIntent);
    }
    public void tabletbutton(View view){
        Intent LogIntent = new Intent(AdminActivity.this, AdminListOrders.class);


        startActivity(LogIntent);
    }
    public void totalbutton(View view){
        Intent LogIntent = new Intent(AdminActivity.this, AdminListOrders.class);


        startActivity(LogIntent);
    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(AdminActivity.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }

}
