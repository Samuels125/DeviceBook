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
import android.widget.EditText;
import android.widget.Toast;

import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.R;

public class LogInActivity extends AppCompatActivity {

    EditText nameEntry;
    private MyDatabase dbHelper;
    Button nameEntryButton;
    SQLiteDatabase mDatabase;
    public Boolean namereturn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);
//this bellow adds the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new MyDatabase(LogInActivity.this);
        mDatabase = dbHelper.getReadableDatabase();

        nameEntryButton = findViewById(R.id.nameEntryButton);
        nameEntry = findViewById(R.id.nameEntry);

        // final ArrayList<DisplayOrderObject> customerObject = dbHelper.findCustomer();

    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(LogInActivity.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }


    public Boolean entrycheck(View view) {

        String namechecker = nameEntry.getText().toString();
         if(dbHelper.checkcustomer(namechecker)) {
            Intent LogIntent = new Intent(LogInActivity.this, DisplayOrderActivity.class);

            LogIntent.putExtra("Name", nameEntry.getText().toString().trim());
            emptynametext();
            startActivity(LogIntent);
        }
        else if(nameEntry.getText().toString().isEmpty()){
             Toast.makeText(LogInActivity.this,"Field is empty" , Toast.LENGTH_SHORT).show();
             return false;
        }
        else{
            Toast.makeText(LogInActivity.this,"Username not recognised " , Toast.LENGTH_SHORT).show();
                     return false;
         }
        return true;
    }

    private void emptynametext() {
        nameEntry.setText(null);
        dbHelper = new MyDatabase(LogInActivity.this);
        String empty = "";


        //if (nameEntry.getText().toString().contentEquals(csname)) {


    }

}