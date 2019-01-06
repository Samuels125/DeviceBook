package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.devicebook.devicebook.database.AdminDatabase;
import com.devicebook.devicebook.R;

public class AdminLogIn extends AppCompatActivity {
    SQLiteDatabase mDatabase;
    private AdminDatabase dbHelper;
    EditText adminusername;
    EditText adminpassword;
    CheckBox checkAdmin;
    static String PREFSNAME = "mypref";
    public static String PREF_USERNAME="";
    public static String PREF_PASSWORD="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new AdminDatabase(AdminLogIn.this);
       mDatabase = dbHelper.getReadableDatabase();

        adminusername = findViewById( R.id.adminusername);
        adminpassword = findViewById(R.id.adminpassword);
        checkAdmin  = findViewById(R.id.chckadmin);

        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        boolean isRemember = myPrefs.getBoolean("REMEMBER", false);
        if(!isRemember){
checkAdmin.setChecked(false);
        }
else
        {
            String user = myPrefs.getString("LOGIN_USER", "");
            String pswd = myPrefs.getString("LOGIN_PSWD", "");
        checkAdmin.setChecked(true);
            adminusername.setText(user);
            adminpassword.setText(pswd);

        }
    }
    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(AdminLogIn.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }

    public boolean adminloginbutton(View view) {

        String musername  = adminusername.getText().toString();
        String mpassword  = adminpassword.getText().toString();

       boolean adminchecker = dbHelper.checkAdmin(musername, mpassword);

       if(adminchecker == true) {

            SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = myPrefs.edit();
            prefsEditor.putString("LOGIN_USER", adminusername.getText().toString());
            prefsEditor.putString("LOGIN_PSWD", adminpassword.getText().toString());
            prefsEditor.putBoolean("REMEMBER", checkAdmin.isChecked());
            prefsEditor.apply();

            Intent LogIntent = new Intent(AdminLogIn.this, AdminActivity.class);

            emptynametext();
            startActivity(LogIntent);
        }
        else if(musername.isEmpty() || mpassword.isEmpty()){
            Toast.makeText(AdminLogIn.this,"Field is empty" , Toast.LENGTH_SHORT).show();
            return false;
        }
        
        return adminchecker;
    }

    private void emptynametext() {
        adminusername.setText(null);
        adminpassword.setText(null);
        dbHelper = new AdminDatabase(AdminLogIn.this);


    }

}
