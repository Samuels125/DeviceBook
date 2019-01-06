package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.R;

import java.util.Locale;


public class CustomerEntryActivity extends AppCompatActivity {


    private EditText username;
    private EditText fullname;
    private EditText address1;
    private EditText country;
    private EditText email;
    SQLiteDatabase mDatabase;
    private MyDatabase dbHelper = new MyDatabase(CustomerEntryActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productanduserdetails);
//this bellow adds the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.username);
       fullname = findViewById(R.id.fullname);
        address1 = findViewById(R.id.address1);
        country = findViewById(R.id.country);
        email = findViewById(R.id.email);

        Locale[] myLocation = Locale.getAvailableLocales();

        //ArrayList<String> myCountry = new ArrayList<>();
       // myCountry.add("Select Country");
       // for (Locale locale : myLocation){

           // String country = locale.getDisplayCountry();
           // if(country.trim().length()>0 && !myCountry.contains(country)){
               // myCountry.add(country);
          //  }
       // }

       // Collections.sort(myCountry);
       // countrySpinner.setPrompt("Select Country");


        //ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myCountry);
        //countryAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);


        //countrySpinner.setAdapter(countryAdapter);










    }
    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(CustomerEntryActivity.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
        case android.R.id.home:
        onBackPressed();
            return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }
    public void padbuttonpressed (View view){

        String Username = username.getText().toString();
        dbHelper.getWritableDatabase();
        dbHelper.findCustomer(Username);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(username.getText().toString().contentEquals("")){
           Toast.makeText(CustomerEntryActivity.this,"Please Enter your Full Name " , Toast.LENGTH_SHORT).show();
            return;
        }



        if(dbHelper.checkusername(Username)) {
            Toast.makeText(CustomerEntryActivity.this,"User already exists" +
                    " " , Toast.LENGTH_SHORT).show();
            emptynametext();
            return;
        }
        else if(fullname.getText().toString().contentEquals("")){
            Toast.makeText(CustomerEntryActivity.this,"Please Enter your fullname " , Toast.LENGTH_SHORT).show();
            return;
        }

        else if(email.getText().toString().contentEquals("")){
            Toast.makeText(CustomerEntryActivity.this,"Please Enter your Email address " , Toast.LENGTH_SHORT).show();
            return;
        }

        else if(!email.getText().toString().matches(emailPattern)){
            Toast.makeText(CustomerEntryActivity.this,"Please Enter a valid email address " , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(address1.getText().toString().contentEquals("")){
            Toast.makeText(CustomerEntryActivity.this,"Please Enter address prefix " , Toast.LENGTH_SHORT).show();
            return;
        }


        else if(country.getText().toString().contentEquals("")){
            Toast.makeText(CustomerEntryActivity.this,"Please Enter your country " , Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            Intent bookingdetailsintent = getIntent();

            String csdeviceType = bookingdetailsintent.getStringExtra("csdeviceType");
            String csmodel = bookingdetailsintent.getStringExtra("csmodel");
            String csbrand = bookingdetailsintent.getStringExtra("csbrand");
            String csfault = bookingdetailsintent.getStringExtra("csfault");
            String cscolor = bookingdetailsintent.getStringExtra("cscolor");
            String cscollection = bookingdetailsintent.getStringExtra("cscollection");




            Intent csdetailsintent = new Intent(getApplicationContext(),DisplayEntryActivity.class);

            csdetailsintent.putExtra("csusername",username.getText().toString());
            csdetailsintent.putExtra("csfullname",fullname.getText().toString());
            csdetailsintent.putExtra("csemail",email.getText().toString());
            csdetailsintent.putExtra("csaddress",address1.getText().toString());
            csdetailsintent.putExtra("cscountry",country.getText().toString());
            csdetailsintent.putExtra("csdeviceintent2",csdeviceType);
            csdetailsintent.putExtra("csmodelintent2",csmodel);
            csdetailsintent.putExtra("csbrandintent2",csbrand);
            csdetailsintent.putExtra("csfaultintent2",csfault);
            csdetailsintent.putExtra("cscolorintent2",cscolor);
            csdetailsintent.putExtra("cscollectionintent2",cscollection);




            startActivity(csdetailsintent);

        }
        return;
    }
    private void emptynametext() {
        username.setText(null);
        dbHelper = new MyDatabase(CustomerEntryActivity.this);
        String empty = "";


        //if (nameEntry.getText().toString().contentEquals(csname)) {


    }
}
