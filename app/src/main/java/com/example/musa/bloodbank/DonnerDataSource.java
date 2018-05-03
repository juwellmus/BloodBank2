package com.example.musa.bloodbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Musa on 4/28/2018.
 */

public class DonnerDataSource {

    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public DonnerDataSource(Context context){
        helper = new DataBaseHelper(context);
    }

    public void openConnection(){
        db = helper.getWritableDatabase();
    }

    public void closeConnection(){
        db.close();
    }

    public boolean insertDonner(Donor donner){
        this.openConnection();
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TBL_BLOOD_COL_NAME,donner.getDonorName());
        values.put(DataBaseHelper.TBL_BLOOD_COL_EMAIL,donner.getDonorEmail());
        values.put(DataBaseHelper.TBL_BLOOD_COL_CONTACT,donner.getDonorContact());
        values.put(DataBaseHelper.TBL_BLOOD_COL_DOB,donner.getDonorAge());
        values.put(DataBaseHelper.TBL_BLOOD_COL_CITY,donner.getDonorCity());
        values.put(DataBaseHelper.TBL_BLOOD_COL_USER,donner.getDonorUserName());
        values.put(DataBaseHelper.TBL_BLOOD_COL_PASS,donner.getDonorUserPass());
        values.put(DataBaseHelper.TBL_BLOOD_COL_BLOOD_GROUP,donner.getDonorGroup());

        long insertedRow = db.insert(DataBaseHelper.TABLE_BLOOD,null,values);
        this.closeConnection();
        if(insertedRow > 0){
            return true;
        }else{
            return false;
        }
    }


    public String searchPassword(String userName){
        this.openConnection();
       String user,password;
       password = "Don't Match";
        Cursor cursor = db.query(DataBaseHelper.TABLE_BLOOD,null,null,null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                user = cursor.getString(6);
                if (user.equals(userName)) {
                    password = cursor.getString(7);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.closeConnection();
        return password;
    }

    public List<Donor> searchBlood(String bloodGroup)
    {
        this.openConnection();
        List<Donor> donors = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT "+ DataBaseHelper.TBL_BLOOD_COL_NAME +" as NAME, "+DataBaseHelper.TBL_BLOOD_COL_CONTACT+" as PHONE, "+DataBaseHelper.TBL_BLOOD_COL_CITY+ " as CITY FROM " + DataBaseHelper.TABLE_BLOOD +" WHERE " + DataBaseHelper.TBL_BLOOD_COL_BLOOD_GROUP + " = '"+ bloodGroup + "';",null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String  name = cursor.getString(cursor.getColumnIndex("NAME"));
                String  contact = cursor.getString(cursor.getColumnIndex("PHONE"));
                String  city = cursor.getString(cursor.getColumnIndex("CITY"));

                Donor donor = new Donor(name,contact,city);
                donors.add(donor);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.closeConnection();
        return donors;
    }


    public List<Donor> getAllDonor(){
        this.openConnection();
        List<Donor> donors = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *  FROM " + DataBaseHelper.TABLE_BLOOD +";",null);
        //Cursor cursor = db.query(DataBaseHelper.TABLE_BLOOD,null,null,null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_NAME));
                String mail = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_EMAIL));
                String contact = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_CONTACT));
                String age = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_DOB));
                String city = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_CITY));
                String group = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_BLOOD_GROUP));

                Donor donor = new Donor(id,name,mail,contact,age,city,group);
                donors.add(donor);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.closeConnection();
        return donors;
    }

    public List<Donor> personalDetails(String userName){
        this.openConnection();
        List<Donor> donors = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_BLOOD +" WHERE " + DataBaseHelper.TBL_BLOOD_COL_USER + " = '"+ userName + "';",null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_NAME));
                String mail = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_EMAIL));
                String contact = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_CONTACT));
                String age = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_DOB));
                String city = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_CITY));
                String group = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_BLOOD_GROUP));
                String user = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_USER));
                String pass = cursor.getString(cursor.getColumnIndex(DataBaseHelper.TBL_BLOOD_COL_PASS));

                Donor donor = new Donor(id,name,mail,contact,age,city,user,pass,group);
                donors.add(donor);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.closeConnection();
        return donors;


    }

    public boolean updateDonner(int id,String name,String email, String contact, String city, String userName,String pass){
        this.openConnection();
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TBL_BLOOD_COL_NAME,name);
        values.put(DataBaseHelper.TBL_BLOOD_COL_EMAIL,email);
        values.put(DataBaseHelper.TBL_BLOOD_COL_CONTACT,contact);
        values.put(DataBaseHelper.TBL_BLOOD_COL_CITY,city);
        values.put(DataBaseHelper.TBL_BLOOD_COL_USER,userName);
        values.put(DataBaseHelper.TBL_BLOOD_COL_PASS,pass);

        String whereClause = DataBaseHelper.TBL_BLOOD_COL_ID + "=?";
        String[] arg = {String.valueOf(id)};


         int updatedRow = db.update(DataBaseHelper.TABLE_BLOOD, values, whereClause,arg );

        this.closeConnection();
        if(updatedRow > 0){
            return true;
        }else{
            return false;
        }

    }
    public boolean deleteDonner(int id){
        this.openConnection();

        String whereClause = DataBaseHelper.TBL_BLOOD_COL_ID + "=?";
        String[] arg = {String.valueOf(id)};


        int deletedRow = db.delete(DataBaseHelper.TABLE_BLOOD,whereClause,arg);

        this.closeConnection();
        if(deletedRow > 0){
            return true;
        }else{
            return false;
        }

    }



}
