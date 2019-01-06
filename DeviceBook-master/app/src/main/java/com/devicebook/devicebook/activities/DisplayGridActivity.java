package com.devicebook.devicebook.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.devicebook.devicebook.objects.ProductGridObject;
import com.devicebook.devicebook.R;

public class DisplayGridActivity extends AppCompatActivity {

    GridView gridView;

String listcaption;
    String phonecaptionlists[] = {"Device Repair", "Store Finder", "Booking Details", "Administrator"};


    int deviceImages[] = {R.drawable.phonerepair2, R.drawable.storefinder, R.drawable.bookingdetails, R.drawable.admin};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        gridView = findViewById(R.id.productView);


        final ProductGridObject adapter = new ProductGridObject(DisplayGridActivity.this, deviceImages, phonecaptionlists);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        String deviceselection = adapterView.getItemAtPosition(0).toString();
        String otherservices = adapterView.getItemAtPosition(1).toString();
        String orderDisplay = adapterView.getItemAtPosition(2).toString();
        String Administrator = adapterView.getItemAtPosition(3).toString();


                if(phonecaptionlists[i]  == deviceselection){
                    startActivity(new Intent(DisplayGridActivity.this,DeviceEntryActivity.class));
                }

                if(phonecaptionlists[i] == otherservices){
                    startActivity(new Intent(DisplayGridActivity.this,MapsActivity.class));
                }
                if(phonecaptionlists[i] == orderDisplay){
                    startActivity(new Intent (DisplayGridActivity.this,LogInActivity.class));
                }
                if(phonecaptionlists[i] == Administrator){
                    startActivity(new Intent(DisplayGridActivity.this,AdminLogIn.class));
                }

            }
        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }

    public void productselected(View view) {


    }
}
