package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.devicebook.devicebook.adapter_class.DisplayOrderAdapter;
import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.R;

import java.util.ArrayList;

public class DisplayOrderActivity extends AppCompatActivity {

    Button deleteButton;
    MyDatabase dbHelper;
    SQLiteDatabase mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_order_view);
        //this bellow adds the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView display_order_listview;
        deleteButton = findViewById(R.id.deleteOrder);
//add to database
dbHelper = new MyDatabase(DisplayOrderActivity.this);
        //display data in listview
        Intent LogIntent = getIntent();
String findName = LogIntent.getStringExtra("Name");
         mDatabase = dbHelper.getWritableDatabase();
       ArrayList listCustomers = new ArrayList<>();
       listCustomers.clear();
       listCustomers.addAll(dbHelper.findCustomer(findName));


        display_order_listview = findViewById(R.id.display_order_listview);
        DisplayOrderAdapter orderAdapter = new DisplayOrderAdapter(this, R.layout.display_order, listCustomers);
        display_order_listview.setAdapter(orderAdapter);




    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(DisplayOrderActivity.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }

    public void delete(View view){
TextView tvdeleteuser = findViewById(R.id.customerId);

String deleteuser = tvdeleteuser.getText().toString();

             try {
                 dbHelper.deletecustomer(deleteuser);
                 Toast.makeText(DisplayOrderActivity.this, "Order removed ", Toast.LENGTH_SHORT).show();
                 Intent returntodatabase = new Intent(DisplayOrderActivity.this,LogInActivity.class);
                 startActivity(returntodatabase);
             }catch (Exception e){
                 Toast.makeText(DisplayOrderActivity.this, "Unable to delete table ", Toast.LENGTH_SHORT).show();

             }
        //}


        //else {
           // Toast.makeText(DisplayOrderActivity.this, "Unable to delete table ", Toast.LENGTH_SHORT).show();


       // }
        //Intent gotoalter = new Intent(DisplayOrderActivity.this,DeleteActivity.class);
        //startActivity(gotoalter);
    }


    }








