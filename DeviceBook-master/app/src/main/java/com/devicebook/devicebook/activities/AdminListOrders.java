package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.devicebook.devicebook.R;
import com.devicebook.devicebook.adapter_class.AdminAdapter;
import com.devicebook.devicebook.database.MyDatabase;

import java.util.ArrayList;

public class AdminListOrders extends AppCompatActivity {
    MyDatabase dbhelper;
    SQLiteDatabase mDatabase;

    ArrayList listorders = new ArrayList<>();
    private AdminAdapter totalOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlistviewlayout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView total_order_listview = findViewById(R.id.adminlistview);







        dbhelper = new MyDatabase(AdminListOrders.this);
        mDatabase = dbhelper.getWritableDatabase();


        listorders.clear();
        listorders.addAll(dbhelper.totalOrder());



        totalOrderAdapter = new  AdminAdapter(this, R.layout.admin_list_orders, listorders);
        total_order_listview.setAdapter(totalOrderAdapter);


    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);

       // MenuItem searchitem = menu.findItem(R.id.search);
        //SearchView mysearchview =(SearchView) MenuItemCompat.getActionView(searchitem);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(AdminListOrders.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }

    //@Override
    //public boolean onQueryTextSubmit(String query) {
    //    return false;
    //}

    //@Override
    //public boolean onQueryTextChange(String newText) {
    //ArrayList<AdminObject> orderlist = new ArrayList<>();
    //for(AdminObject orders : listorders){

    // }
    // return false;
}

