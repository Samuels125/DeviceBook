package com.devicebook.devicebook.adapter_class;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devicebook.devicebook.activities.DisplayGridActivity;
import com.devicebook.devicebook.database.MyDatabase;
import com.devicebook.devicebook.activities.LogInActivity;
import com.devicebook.devicebook.objects.DisplayEntryObject;
import com.devicebook.devicebook.objects.DisplayOrderObject;
import com.devicebook.devicebook.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sam on 07/02/2018.
 */

public class DisplayEntryAdapter extends ArrayAdapter<DisplayEntryObject> {
    private static final String tag = "csdetailadapter";
    private Context cscontext;
    int csresource;
    LayoutInflater inflater;
    int custId;
    int custNumber;
    MyDatabase dbHelper;

    public DisplayEntryAdapter(Context context, int resource, ArrayList<DisplayEntryObject> objects) {
        super(context, resource, objects);
        cscontext = context;
        csresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String csid = getItem(position).getCsid();
        String username = getItem(position).getUsername();
        String fullname = getItem(position).getFullname();
        String email = getItem(position).getEmail();
        String addressline1 = getItem(position).getAddressline1();
        String country = getItem(position).getCountry();

        Button editbutton1 = getItem(position).getEditbutton1();
        Button csDeleteButton1 = getItem(position).getCsDeleteButton1();
        String csdevice = getItem(position).getCsdeviceType();
        final String csdeviceModel = getItem(position).getCsdeviceModel();
        String csdeviceBrand = getItem(position).getCsdeviceBrand();
        String csfaultspinner = getItem(position).getCsfaultspinner();
        String cscolorspinner = getItem(position).getCscolorspinner();
        String csCollectionOptions = getItem(position).getCsCollectionOptions();

        Button edibutton2 = getItem(position).getEditbutton2();
        Button csDeleteButton2 = getItem(position).getCsDeleteButton2();
        Button csConfirmButton = getItem(position).getCsConfirmButton();


        inflater = LayoutInflater.from(cscontext);
        convertView = inflater.inflate(csresource, parent, false);


        final TextView csId = convertView.findViewById(R.id.csId);
        final EditText tvusername = convertView.findViewById(R.id.csusername);
        final EditText tvfullname = convertView.findViewById(R.id.csfullname);
        final EditText tvemail = convertView.findViewById(R.id.csEmail);
        final EditText tvaddress = convertView.findViewById(R.id.csAddress);
        final EditText tvcountry = convertView.findViewById(R.id.csCountry);
        final Button btn1 = convertView.findViewById(R.id.csEditButton1);
        final Button csDeletebtn1 = convertView.findViewById(R.id.csDeleteButton1);


        final EditText tvdevice = convertView.findViewById(R.id.csdeviceType);
        final EditText tvmodel = convertView.findViewById(R.id.csModel);
        final EditText tvbrand = convertView.findViewById(R.id.csBrand);
        final EditText tvfault = convertView.findViewById(R.id.csFault);
        final EditText tvcolor = convertView.findViewById(R.id.deviceColor);
        final EditText tvcollectionOptions = convertView.findViewById(R.id.deviceRecieveMethod);
        final Button btn2 = convertView.findViewById(R.id.csEditButton2);
        final Button csDeletebtn2 = convertView.findViewById(R.id.csDeleteButton2);
        final Button csConfirmbtn = convertView.findViewById(R.id.csConfirmButton);


        tvusername.setText(username);
        tvfullname.setText(fullname);
        tvemail.setText(email);
        tvaddress.setText(addressline1);

        tvcountry.setText(country);


        tvdevice.setText(csdevice);
        tvmodel.setText(csdeviceModel);
        tvbrand.setText(csdeviceBrand);
        tvfault.setText(csfaultspinner);
        tvcolor.setText(cscolorspinner);
        tvcollectionOptions.setText(csCollectionOptions);
        btn1.setText("Edit List");
        btn2.setText("Edit List");
        csConfirmbtn.setText("Save and Continue");


        tvusername.setSelection(tvusername.getText().length());
        tvemail.setSelection(tvemail.getText().length());
        tvfullname.setSelection(tvfullname.getText().length());

        tvaddress.setSelection(tvaddress.getText().length());
        tvcountry.setSelection(tvcountry.getText().length());

        tvdevice.setSelection(tvdevice.getText().length());
        tvmodel.setSelection(tvmodel.getText().length());
        tvbrand.setSelection(tvbrand.getText().length());
        tvcolor.setSelection(tvcolor.getText().length());
        tvfault.setSelection(tvfault.getText().length());
        tvcollectionOptions.setSelection(tvcollectionOptions.getText().length());


/////////////////////////////////////btn to edit what is in keyboard and bringing up softkeyboard///////////////////////////////////////////////////////////////
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvusername.setFocusableInTouchMode(true);
                tvusername.requestFocus();
                InputMethodManager nameInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                nameInput.showSoftInput(tvusername, 0);


                InputMethodManager numberInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                numberInput.showSoftInput(tvfullname, 0);
                tvfullname.setFocusableInTouchMode(true);


                InputMethodManager emailInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                emailInput.showSoftInput(tvemail, 0);
                tvemail.setFocusableInTouchMode(true);


                InputMethodManager addressInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                addressInput.showSoftInput(tvaddress, 0);
                tvaddress.setFocusableInTouchMode(true);


                InputMethodManager countryInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                countryInput.showSoftInput(tvcountry, 0);
                tvcountry.setFocusableInTouchMode(true);


            }
        });
///////////////////////////////  to hide soft keyboard. /////////////////////////////////////////////////////
        tvcountry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tvcountry.clearFocus();
                    tvcountry.setFocusableInTouchMode(false);
                    tvusername.setFocusableInTouchMode(false);
                    tvfullname.setFocusableInTouchMode(false);
                    tvemail.setFocusableInTouchMode(false);
                    tvaddress.setFocusableInTouchMode(false);

                    InputMethodManager myinput = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    myinput.hideSoftInputFromWindow(tvcountry.getWindowToken(), 0);

                }
                return false;
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tvdevice.setFocusableInTouchMode(true);
                tvdevice.requestFocus();
                InputMethodManager devicetypeInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                devicetypeInput.showSoftInput(tvdevice, 0);


                InputMethodManager brandInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                brandInput.showSoftInput(tvbrand, 0);
                tvbrand.setFocusableInTouchMode(true);

                InputMethodManager modelInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                modelInput.showSoftInput(tvmodel, 0);
                tvmodel.setFocusableInTouchMode(true);


                InputMethodManager faultInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                faultInput.showSoftInput(tvfault, 0);
                tvfault.setFocusableInTouchMode(true);


                InputMethodManager colorInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                colorInput.showSoftInput(tvcolor, 0);
                tvcolor.setFocusableInTouchMode(true);


                InputMethodManager collectInput = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                collectInput.showSoftInput(tvcollectionOptions, 0);
                tvcollectionOptions.setFocusableInTouchMode(true);


            }
        });


        tvcollectionOptions.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tvcollectionOptions.clearFocus();
                    tvcollectionOptions.setFocusableInTouchMode(false);
                    tvdevice.setFocusableInTouchMode(false);
                    tvmodel.setFocusableInTouchMode(false);
                    tvbrand.setFocusableInTouchMode(false);
                    tvcolor.setFocusableInTouchMode(false);
                    tvfault.setFocusableInTouchMode(false);
                    InputMethodManager myinput = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    myinput.hideSoftInputFromWindow(tvcollectionOptions.getWindowToken(), 0);


                }
                return false;
            }
        });


        csConfirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String lettersValid = "[a-zA-Z ]+";
                if (tvusername.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "First Name is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvfullname.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Last Name is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvemail.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Email is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!tvemail.getText().toString().matches(emailPattern)) {
                    Toast.makeText(view.getContext(), "Email is Invalid", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvaddress.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Address is empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvcountry.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Country is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvdevice.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Device type is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvmodel.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Model is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvbrand.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Brand is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvcolor.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Color is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvfault.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "Fault is Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (tvcollectionOptions.getText().toString().contentEquals("")) {
                    Toast.makeText(view.getContext(), "No Collection option", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    Intent CustomerIntent = new Intent(view.getContext().getApplicationContext(), DisplayGridActivity.class);


                    // Toast.makeText(view.getContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                    dbHelper = new MyDatabase(view.getContext());

                    dbHelper.getWritableDatabase();

                    DisplayOrderObject customer = new DisplayOrderObject(csId.getText().toString(), tvusername.getText().toString(), tvfullname.getText().toString(), tvemail.getText().toString(), tvaddress.getText().toString(), tvcountry.getText().toString(), tvdevice.getText().toString(), tvbrand.getText().toString(), tvmodel.getText().toString(), tvfault.getText().toString(), tvcolor.getText().toString(), tvcollectionOptions.getText().toString());
                    dbHelper.addcustomer(customer);


                    view.getContext().startActivity(CustomerIntent);


                }

            }


        });

        //////////////////////////////Delete Section /////////////////////////////////////////////////////

        csDeletebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<EditText> entry1 = new ArrayList<>();

                Collections.addAll(entry1, tvusername, tvfullname, tvemail, tvaddress, tvcountry);

                entry1.removeAll(entry1);


            }
        });

        csDeletebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
///////////////////
        ///////////////
        ////////////////
        //////////////////
        /////////////////////

return convertView;
    }

}

