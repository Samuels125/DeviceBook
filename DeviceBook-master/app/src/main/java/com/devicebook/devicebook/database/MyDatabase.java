package com.devicebook.devicebook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.devicebook.devicebook.objects.AdminObject;
import com.devicebook.devicebook.objects.DisplayOrderObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 22/02/2018.
 */

public class MyDatabase extends SQLiteOpenHelper {

    MyDatabase dbHelper;

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "customerOrderDetails.db";
    private static final String TABLE_CUSTOMER = "customerTable";
    public static final String CustomerId_column = "Id";
    public static final String Username_column = "username";
    public static final String Fullname_column = "fullname";
    public static final String CustomerEmail_column = "Email";
    public static final String CustomerAddress_column = "Address";
    public static final String CustomerCountry_column = "Country";
    public static final String customerDevice_column = "Device";
    public static final String customerBrand_column = "Brand";
    public static final String customerModel_column = "Model";
    public static final String customerFault_column = "Fault";
    public static final String customerColor_column = "Color";
    public static final String customerRecieve_column = "Recieve";
    private Context cscontext;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        cscontext = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +
                TABLE_CUSTOMER + "("
                + CustomerId_column + " INTEGER PRIMARY KEY, " +
                Username_column + " TEXT COLLATE NOCASE, " +
                Fullname_column + " TEXT, " +
                CustomerEmail_column + " TEXT, " +
                CustomerAddress_column + " TEXT, " +
                CustomerCountry_column + " TEXT, " +
                customerDevice_column + " TEXT, " +
                customerModel_column + " TEXT, " +
                customerBrand_column + " TEXT, " +
                customerFault_column + " TEXT, " +
                customerColor_column + " TEXT, " +
                customerRecieve_column + " TEXT " +
                ")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    public void addcustomer(DisplayOrderObject customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Username_column, customer.getMyCustomerUsername());
        values.put(Fullname_column, customer.getMyCustomerfullname());
        values.put(CustomerEmail_column, customer.getMyCustomerEmail());
        values.put(CustomerAddress_column, customer.getMyCustomerAddress());
        values.put(CustomerCountry_column, customer.getMyCustomerCountry());
        values.put(customerDevice_column, customer.getMycustomerDevice());
        values.put(customerBrand_column, customer.getMycustomerBrand());
        values.put(customerModel_column, customer.getMycustomerModel());
        values.put(customerFault_column, customer.getMycustomerFault());
        values.put(customerColor_column, customer.getMycustomerColor());
        values.put(customerRecieve_column, customer.getMycustomerRecieve());


        long cxInserted = db.insert(TABLE_CUSTOMER, null, values);

        if (cxInserted != -1) {
            Toast.makeText(cscontext, "Saved to database ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(cscontext, "Data not saved ", Toast.LENGTH_SHORT).show();
        }
        db.close();

        return;
    }


    public List<DisplayOrderObject> findCustomer(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = Username_column + "   ASC ";


        String selection = Username_column + " = ?";

        String[] selectionArgs = {name};
        String[] columns = {
                CustomerId_column,
                Username_column,
                Fullname_column,
                CustomerEmail_column,
                CustomerAddress_column,
                CustomerCountry_column,
                customerDevice_column,
                customerBrand_column,
                customerModel_column,
                customerFault_column,
                customerColor_column,
                customerRecieve_column
        };
        // sorting orders


        Cursor cursor = db.query(TABLE_CUSTOMER, columns, selection, selectionArgs, null, null, sortOrder);

        List<DisplayOrderObject> customerList = new ArrayList<DisplayOrderObject>();

        if (cursor.moveToFirst()) {

            do {
                DisplayOrderObject customer = new DisplayOrderObject();
                customer.setMyCustomerId(cursor.getString(cursor.getColumnIndex(CustomerId_column)));
                customer.setMyCustomerUsername(cursor.getString(cursor.getColumnIndex(Username_column)));
                customer.setMyCustomerfullname(cursor.getString(cursor.getColumnIndex(Fullname_column)));
                customer.setMyCustomerEmail(cursor.getString(cursor.getColumnIndex(CustomerEmail_column)));
                customer.setMyCustomerAddress(cursor.getString(cursor.getColumnIndex(CustomerAddress_column)));
                customer.setMyCustomerCountry(cursor.getString(cursor.getColumnIndex(CustomerCountry_column)));
                customer.setMycustomerDevice(cursor.getString(cursor.getColumnIndex(customerDevice_column)));
                customer.setMycustomerBrand(cursor.getString(cursor.getColumnIndex(customerBrand_column)));
                customer.setMycustomerModel(cursor.getString(cursor.getColumnIndex(customerModel_column)));
                customer.setMycustomerFault(cursor.getString(cursor.getColumnIndex(customerFault_column)));
                customer.setMycustomerColor(cursor.getString(cursor.getColumnIndex(customerColor_column)));
                customer.setMycustomerRecieve(cursor.getString(cursor.getColumnIndex(customerRecieve_column)));


                // Adding user record to list
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return customerList;

    }






    public List<AdminObject> totalOrder() {

        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = Username_column + "   ASC ";



        String[] columns = {

                Username_column,
                customerDevice_column,
                customerModel_column,
                customerFault_column,

        };
        // sorting orders


        Cursor cursor = db.query(TABLE_CUSTOMER, columns, null, null, null, null, sortOrder);

        List<AdminObject> OrderList = new ArrayList<AdminObject>();

        if (cursor.moveToFirst()) {

            do {
                AdminObject order = new AdminObject();
                order.setUserName1(cursor.getString(cursor.getColumnIndex(Username_column)));
                order.setDeviceType1(cursor.getString(cursor.getColumnIndex(customerDevice_column)));
                order.setDeviceModel1(cursor.getString(cursor.getColumnIndex(customerModel_column)));
                order.setDeviceFault1(cursor.getString(cursor.getColumnIndex(customerFault_column)));
                // Adding user record to list
                OrderList.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return OrderList;

    }




    public Boolean checkcustomer(String name) {
        List<DisplayOrderObject> checklist = new ArrayList<DisplayOrderObject>();
        String[] columns = {Username_column};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = Username_column + " = ?";

        String[] selectionArgs = {name};

        Cursor cursor = db.query(TABLE_CUSTOMER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);

        if (cursor.moveToFirst()) {
            do {
                DisplayOrderObject customer = new DisplayOrderObject();
                customer.setMyCustomerUsername(cursor.getString(cursor.getColumnIndex(Username_column)));

                checklist.add(customer);
            } while (cursor.moveToNext());
        }
        //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount < 1) {

            return false;

        } else {

        }


        return true;
    }




    public int mobilecount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT (*) FROM " + TABLE_CUSTOMER + " WHERE " + customerDevice_column + "='Phone'",null);
        int count = 0;
        if (null != cursor)
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();


    db.close();
    return count;
}

    public int tabletcount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT (*) FROM " + TABLE_CUSTOMER + " WHERE " + customerDevice_column + "='Tablet'",null);
        int count = 0;
        if (null != cursor)
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();


        db.close();
        return count;
    }


    public int totalrepaircount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT (*) FROM " + TABLE_CUSTOMER ,null);
        int count = 0;
        if (null != cursor)
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
        cursor.close();


        db.close();
        return count;
    }

    public Boolean checkusername(String name){
        List<DisplayOrderObject> checklist = new ArrayList<DisplayOrderObject>();
        String[] columns = {Username_column};
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = Username_column + " = ?";

        String[] selectionArgs = {name};

        Cursor cursor = db.query(TABLE_CUSTOMER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);

        if (cursor.moveToFirst()) {
            do {
                DisplayOrderObject customer = new DisplayOrderObject();
                customer.setMyCustomerUsername(cursor.getString(cursor.getColumnIndex(Username_column)));

                checklist.add(customer);
            } while (cursor.moveToNext());
        }
        //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();


        if (cursorCount < 1) {

            return false;

        }
        else{

        }

        return true;
    }



    public String deletecustomer(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        
        db.delete(TABLE_CUSTOMER, CustomerId_column + "=?", new String[]{id});

        db.close();

        return id;
    }




}
