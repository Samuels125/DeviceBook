package com.devicebook.devicebook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.devicebook.devicebook.objects.AdminLayoutObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.sun.mail.imap.Rights.Right.INSERT;

/**
 * Created by Sam on 25/04/2018.
 */

public class AdminDatabase  extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3  ;
    private static final String DATABASE_NAME = "admin.db";
    private static final String TABLE_ADMIN = "adminTable";
    public static final String AdminId = "adminid";
    public static final String AdminUsername = "adminusername";
    public static final String Adminpassword = "adminpassword";
    private Context cscontext;

    public AdminDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        cscontext = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +
                TABLE_ADMIN + "("
                + AdminId + " INTEGER PRIMARY KEY, " +
                AdminUsername + " TEXT COLLATE NOCASE, " +
                Adminpassword + " TEXT COLLATE NOCASE " +
                ")";


        db.execSQL(query);
        //ContentValues contentValues = new ContentValues();
        //contentValues.put(AdminUsername, "admin");
        //contentValues.put(Adminpassword, "Password");
        //db.insert(TABLE_ADMIN, null, contentValues);

        db.execSQL("insert into " + TABLE_ADMIN + "(" + AdminId + ","+ AdminUsername + ","
                + Adminpassword + ") values(101,'admin','Password')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);
    }

    public Boolean checkAdmin(String Adminname, String Adminpword) {
        List<AdminLayoutObject> adminfile = new ArrayList<AdminLayoutObject>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(TABLE_ADMIN,
                null,
                "AdminUsername=? AND Adminpassword=?",
                new String[]{Adminname,Adminpword},
                null,null,null
        );
        if(c.getCount() <= 0) {
            c.close();
            db.close();
            Toast.makeText(cscontext, "User doesnt exist ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            c.close();
            db.close();

            return true;
        }


    }

    public Boolean checkPassword(String checkpassword) {
        List<AdminLayoutObject> adminfile = new ArrayList<AdminLayoutObject>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Adminpassword};

        String selection = Adminpassword + " = ?";
        String[] selectionArgs = {checkpassword};

        Cursor cursor = db.query(TABLE_ADMIN, //Table to query
                columns,                    //columns to return
                (selection),                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);

        if (cursor.moveToFirst()) {
            do {
                AdminLayoutObject admin = new AdminLayoutObject();
                admin.setPassword(cursor.getString(cursor.getColumnIndex(Adminpassword)));

                adminfile.add(admin);
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

}
