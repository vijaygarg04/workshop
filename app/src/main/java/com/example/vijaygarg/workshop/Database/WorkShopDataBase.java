package com.example.vijaygarg.workshop.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vijaygarg.workshop.WorkShopModel;

import java.util.ArrayList;

/**
 * Created by vijaygarg on 13/03/18.
 */

public class WorkShopDataBase extends SQLiteOpenHelper{
    public static final String DATABASENAME="workshops.db";
    public static final String TABLENAME="workshop";
    public static final String COLUMN0="Id";
    public static final String COLUMN1="CompanyName";
    public static final String COLUMN2="Profile";
    public static final String COLUMN3="Description";
    public static final String COLUMN4="Date";
    public static final String COLUMN5="InternshipDescription";

    public WorkShopDataBase(Context context) {
        super(context, DATABASENAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE "+TABLENAME+" ("+COLUMN0+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN1+" TEXT,"+COLUMN2+" TEXT,"+COLUMN3+" TEXT,"+COLUMN4+" TEXT,"+COLUMN5+" TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        onCreate(sqLiteDatabase);
    }


    public ArrayList<WorkShopModel> getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ TABLENAME,null);
        ArrayList<WorkShopModel> arr=new ArrayList<>();
        while(cursor.moveToNext()){
            String companyname=cursor.getString(1);
            String profile=cursor.getString(2);
            String description=cursor.getString(3);
            String date=cursor.getString(4);
            String details=cursor.getString(5);
            WorkShopModel wsm=new WorkShopModel(companyname,profile,description,date,details);
            arr.add(wsm);
        }
        return arr;

    }
    public boolean insertdata(String companyname,String profile,String description,String date,String details){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN1,companyname);
        contentValues.put(COLUMN2,profile);
        contentValues.put(COLUMN3,description);
        contentValues.put(COLUMN4,date);
        contentValues.put(COLUMN5,details);

        long result=db.insert(TABLENAME,null,contentValues);
        if(result==-1){
            return false;}
        else{
            return true;
        }
    }

    public void delete(String companyname) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLENAME,"CompanyName = ?",new String[]{companyname});
    }
}
