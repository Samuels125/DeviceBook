package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.devicebook.devicebook.adapter_class.DisplayEntryAdapter;
import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.objects.DisplayEntryObject;
import com.devicebook.devicebook.R;

import java.util.ArrayList;

public class DisplayEntryActivity extends AppCompatActivity {

    ListView customerListView;

    MyDatabase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    //this bellow adds the back button




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button csConfirmButton = findViewById(R.id.csConfirmButton);
        Button editbutton1 = findViewById(R.id.csEditButton1);
        Button editbutton2 = findViewById(R.id.csEditButton2);
        Button csDeleteButton1 = findViewById(R.id.csDeleteButton1);
        Button csDeleteButton2 = findViewById(R.id.csDeleteButton1);

    Intent csdetailsintent = getIntent();



       String csid = "id";

        String  csusername = csdetailsintent.getStringExtra("csusername");
        String csfullname = csdetailsintent.getStringExtra("csfullname");
        String csemail = csdetailsintent.getStringExtra("csemail");
        String csaddress = csdetailsintent.getStringExtra("csaddress");
        String cscountry = csdetailsintent.getStringExtra("cscountry");



        String csdeviceType = csdetailsintent.getStringExtra("csdeviceintent2");
        String csbrand = csdetailsintent.getStringExtra("csbrandintent2");
        String csmodel = csdetailsintent.getStringExtra("csmodelintent2");
        String csfault = csdetailsintent.getStringExtra("csfaultintent2");
        String cscolor = csdetailsintent.getStringExtra("cscolorintent2");
        String cscollection = csdetailsintent.getStringExtra("cscollectionintent2");








        DisplayEntryObject csObject = new DisplayEntryObject(csid,csusername,csfullname,csemail,csaddress,cscountry,editbutton1,csDeleteButton1,csdeviceType,csbrand,csmodel,csfault,cscolor,cscollection,editbutton2,csDeleteButton2,csConfirmButton);


        ArrayList<DisplayEntryObject> customerArray = new ArrayList<>();
customerArray.add(csObject);




        customerListView = findViewById(R.id.customerListView);

        DisplayEntryAdapter csadapter = new DisplayEntryAdapter(this, R.layout.customerdisplay_adapter, customerArray);
        customerListView.setAdapter(csadapter);
    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(DisplayEntryActivity.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }





}
