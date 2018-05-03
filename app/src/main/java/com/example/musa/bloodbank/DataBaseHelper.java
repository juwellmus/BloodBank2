package com.example.musa.bloodbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Musa on 4/27/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "blood_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_BLOOD = "tbl_blood";
    public static final String TBL_BLOOD_COL_ID = "dnr_id";
    public static final String TBL_BLOOD_COL_NAME = "dnr_name";
    public static final String TBL_BLOOD_COL_EMAIL = "dnr_email";
    public static final String TBL_BLOOD_COL_DOB = "dnr_dob";
    public static final String TBL_BLOOD_COL_CONTACT = "dnr_contact";
    public static final String TBL_BLOOD_COL_CITY = "dnr_city";
    public static final String TBL_BLOOD_COL_USER = "dnr_user";
    public static final String TBL_BLOOD_COL_PASS = "dnr_pass";
    public static final String TBL_BLOOD_COL_BLOOD_GROUP = "dnr_group";

    public static final String CREATE_TABLE_BLOOD = "create table "+TABLE_BLOOD+"("+
            TBL_BLOOD_COL_ID+" integer primary key, "+
            TBL_BLOOD_COL_NAME+" text, "+
            TBL_BLOOD_COL_EMAIL+" text, "+
            TBL_BLOOD_COL_CONTACT+" text, "+
            TBL_BLOOD_COL_DOB+" text, "+
            TBL_BLOOD_COL_CITY+" text, "+
            TBL_BLOOD_COL_USER+" text, "+
            TBL_BLOOD_COL_PASS+" text, "+
            TBL_BLOOD_COL_BLOOD_GROUP+" text);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BLOOD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_BLOOD);
    }
}
