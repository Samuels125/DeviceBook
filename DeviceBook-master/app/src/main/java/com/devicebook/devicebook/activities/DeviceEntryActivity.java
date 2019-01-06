package com.devicebook.devicebook.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devicebook.devicebook.R;

import java.util.ArrayList;
import java.util.Collections;

public class DeviceEntryActivity extends AppCompatActivity {
    private Spinner deviceType;
    private Spinner deviceBrand;
    private Spinner deviceModel;
    private Spinner faultSpinner;
    private Spinner mycolorSpinner;
    private RadioGroup mycollectionOptions;
    private Button continueButton;
    String collectionradio;
    String colorchoice;
    RadioButton postOption;
    RadioButton pickupOption;

    // this is used to call the arrays in the string list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_avtivity);
        //this bellow adds the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        deviceType = findViewById(R.id.deviceType);
        faultSpinner = findViewById(R.id.faultSpinner);
        deviceBrand = findViewById(R.id.deviceBrand);
        deviceModel = findViewById(R.id.deviceModel);
        mycolorSpinner = findViewById(R.id.mycolorSpinner);
        mycollectionOptions = findViewById(R.id.mycollectionOptions);
        continueButton = findViewById(R.id.continueButton);

        postOption = findViewById(R.id.postOption);
        pickupOption = findViewById(R.id.pickUpOption);





        ArrayList<String> deviceArray = new ArrayList<>();
        Collections.addAll(deviceArray,"Select Device","Phone","Tablet");

        ArrayList<String> emptybrandarray = new ArrayList<>();
        emptybrandarray.add("Brand");

        final ArrayAdapter<String>emptybrandadapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,emptybrandarray);
        emptybrandadapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        ArrayList<String> emptymodelarray = new ArrayList<>();
        emptymodelarray.add("Model");



        final ArrayAdapter<String>emptymodeladapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,emptymodelarray);
        emptymodeladapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);



        ArrayList<String>phonebrandArray = new ArrayList<String>();
        Collections.addAll(phonebrandArray,  "Phone Brand","Samsung", "Apple",  "Sony");

        ArrayList<String> faultArray = new ArrayList<>();
        Collections.addAll(faultArray, "Fault Description", "Speaker Problem", "Will not turn on", "Broken Screen", "Water Damage", "Overheating", "Charging Problem", "Other");


        ArrayList<String> defaultlist = new ArrayList<>();
        defaultlist.add("");

        ArrayList<String> samsungArray = new ArrayList<>();
        Collections.addAll(samsungArray,  "Samsung Model","Samsung Galaxy S8", "Samsung Galaxy S7", "Samsung Galaxy S6", "Samsung Galaxy S5", "Samsung Galaxy A4", "Samsung Galaxy J3");

        ArrayList<String> appleArray = new ArrayList<>();
        Collections.addAll(appleArray,  "Iphone Model","Iphone X", "Iphone 8", "Iphone 7", "Iphone 6", "Iphone 5", "Iphone SE");



        ArrayList<String> sonyArray = new ArrayList<>();
        Collections.addAll(sonyArray,  "Sony Model","Sony XY", "Sony XZ", "Sony Z4", "Sony Z3", "Sony Z2", "Sony Z");

        ArrayList<String> colorArray = new ArrayList<>();
        Collections.addAll(colorArray, "Color","Device Color", "Red", "Black", "White", "Grey", "Rose Gold");



        ArrayAdapter<String>deviceAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,deviceArray);
        deviceAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);


        final ArrayAdapter<String> phoneBrandAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, phonebrandArray);

        phoneBrandAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);


        final ArrayAdapter<String> samsungAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, samsungArray);

        samsungAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        final ArrayAdapter<String> appleAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, appleArray);

        appleAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);





        final ArrayAdapter<String> sonyAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, sonyArray);

        sonyAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        final ArrayAdapter<String> defaultadapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, defaultlist);

        ArrayAdapter<String> faultAdapter= new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,faultArray);

        faultAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);



        ArrayAdapter<String> colorAdapter= new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,  colorArray);

        colorAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);









        ArrayList<String> tabletbrandarray = new ArrayList<>();
        Collections.addAll(tabletbrandarray,"Tablet Type","Samsung Tablet","Apple Tablet");

        ArrayList<String> tabletOsArray = new ArrayList<>();
        Collections.addAll(tabletOsArray,"Samsung tablet type","Samsung Tab A ","Samsung Tab A","Samsung Tab B");

        ArrayList<String> appleOsarray = new ArrayList<>();
        Collections.addAll(appleOsarray ,"Apple tablet type ","Ipad A","Ipad B","Ipad C","IOS Ipad D");


        final ArrayAdapter<String> tabletbrandAdapter= new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tabletbrandarray);
        tabletbrandAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);



        final ArrayAdapter<String> tabletosAdapter= new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tabletOsArray);
        tabletosAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);



        final ArrayAdapter<String> appleosAdapter= new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, appleOsarray);
        appleosAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        deviceType.setAdapter(deviceAdapter);
        faultSpinner.setAdapter(faultAdapter);
        mycolorSpinner.setAdapter(colorAdapter);

        faultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView faultview = (TextView) faultSpinner.getSelectedView();

                if(faultview.getText().toString().equals("Fault Description")){
                    faultview.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mycolorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView colorview = (TextView) mycolorSpinner.getSelectedView();

                if(colorview.getText().toString().equals("Color")){
                    colorview.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        deviceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

boolean notempty = true;
                String selectedDevice = deviceType.getSelectedItem().toString();
                TextView devicetypeview = (TextView) deviceType.getSelectedView();

                if(devicetypeview.getText().toString().equals("Select Device")){
                    devicetypeview.setTextColor(Color.GRAY);
                }
                if(selectedDevice.contentEquals("Select Device")){

                    deviceBrand.setAdapter(emptybrandadapter);
                }

                else if(selectedDevice.contentEquals("Phone")){
                    deviceBrand.setAdapter(phoneBrandAdapter);
                }
                else if(selectedDevice.contentEquals("Tablet")){
                    deviceBrand.setAdapter(tabletbrandAdapter);
                }

                else
                    deviceBrand.setAdapter(defaultadapter);

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        deviceBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String selectedphone = deviceBrand.getSelectedItem().toString();


                TextView brandview = (TextView) deviceBrand.getSelectedView();

                if (brandview.getText().toString().equals("Brand")) {
                    brandview.setTextColor(Color.GRAY);
                } else if (brandview.getText().toString().equals("Phone Brand")) {
                    brandview.setTextColor(Color.GRAY);
                }

                if (selectedphone.contentEquals("Brand")) {
                    deviceModel.setAdapter(emptymodeladapter);
                } else if (selectedphone.contentEquals("Phone Brand")) {
                    deviceModel.setAdapter(emptymodeladapter);
                } else if (selectedphone.contentEquals("Samsung")) {

                    deviceModel.setAdapter(samsungAdapter);
                } else if (selectedphone.contentEquals("Apple")) {
                    deviceModel.setAdapter(appleAdapter);
                }  else if (selectedphone.contentEquals("Sony")) {
                    deviceModel.setAdapter(sonyAdapter);
                }

                else {
                    deviceModel.setAdapter(defaultadapter);

                }






                String selectedtablet = deviceBrand.getSelectedItem().toString();
                 if(selectedtablet.contentEquals("Tablet Type")){
                    deviceModel.setAdapter(emptymodeladapter);
                }
                else if(selectedtablet.contentEquals("Apple Tablet")){
                    deviceModel.setAdapter(appleosAdapter);
                }
                else if(selectedtablet.contentEquals("Samsung Tablet")){
                    deviceModel.setAdapter(tabletosAdapter);
                }
                else if(selectedtablet.contentEquals("Lg Tablet")){
                    deviceModel.setAdapter(tabletosAdapter);
                }
                else if(selectedtablet.contentEquals("Toshiba Tablet")){
                    deviceModel.setAdapter(tabletosAdapter);
                }



            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        deviceModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView modelview = (TextView) deviceModel.getSelectedView();

                if(modelview.getText().toString().equals("Model") || (modelview.getText().toString().equals("Samsung Model"))
                    || (modelview.getText().toString().equals("Iphone Model")) || (modelview.getText().toString().equals("Sony Model"))
                        || (modelview.getText().toString().equals("Samsung tablet type")) || (modelview.getText().toString().equals("Apple tablet type")))
                {
                    modelview.setTextColor(Color.GRAY);
                }

                else if(modelview.getText().toString().equals("Tablet Type")){
                    modelview.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent menuintent = new Intent(DeviceEntryActivity.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }

    public void onContinueButtonPressed(View view) {





        if(faultSpinner.getSelectedItem().toString().contentEquals("Fault Description") ){
            Toast.makeText(DeviceEntryActivity.this,"Please Select a valid fault " ,Toast.LENGTH_SHORT).show();
            return;
        }
        else if(mycolorSpinner.getSelectedItem().toString().contentEquals("Color") ){
            Toast.makeText(DeviceEntryActivity.this,"Please Select device color " ,Toast.LENGTH_SHORT).show();
            return;
        }
        else if(deviceType.getSelectedItem().toString().contentEquals("Select Device")){
            Toast.makeText(DeviceEntryActivity.this,"Please Select device type " ,Toast.LENGTH_SHORT).show();
            return;
        }
        else if(deviceBrand.getSelectedItem().toString().contentEquals("Brand")){
            Toast.makeText(DeviceEntryActivity.this,"Please Select device brand " ,Toast.LENGTH_SHORT).show();
            return;
        }
        else if(deviceModel.getSelectedItem().toString().contentEquals("Model") || deviceModel.getSelectedItem().toString().contentEquals("Samsung Model")||
        (deviceModel.getSelectedItem().toString().contentEquals("Sony Model") || deviceModel.getSelectedItem().toString().contentEquals("Iphone Model"))||
        (deviceModel.getSelectedItem().toString().contentEquals("Samsung tablet type") || deviceModel.getSelectedItem().toString().contentEquals("Apple tablet type"))){
            Toast.makeText(DeviceEntryActivity.this,"Please Select device model " ,Toast.LENGTH_SHORT).show();
            return;
        }
        else if(mycollectionOptions.getCheckedRadioButtonId() == -1){
            Toast.makeText(DeviceEntryActivity.this,"Please Select a collection option " ,Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            Intent bookingdetailsintent = new Intent(getApplicationContext(),CustomerEntryActivity.class);
            bookingdetailsintent.putExtra("csdeviceType",deviceType.getSelectedItem().toString());
            bookingdetailsintent.putExtra("csbrand",deviceBrand.getSelectedItem().toString());
            bookingdetailsintent.putExtra("csmodel",deviceModel.getSelectedItem().toString());

            bookingdetailsintent.putExtra("csfault",faultSpinner.getSelectedItem().toString());
            bookingdetailsintent.putExtra("cscolor",mycolorSpinner.getSelectedItem().toString());
            bookingdetailsintent.putExtra("cscollection",collectionradio = ((RadioButton) findViewById(mycollectionOptions.getCheckedRadioButtonId())).getText().toString());





            startActivity(bookingdetailsintent);

        }

    }


}
