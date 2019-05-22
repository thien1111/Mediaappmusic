package com.example.mediaappmusic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    //ten data, ten table, ten cot
    public static final String DATABASE_NAME = "User_Info.db";
    public static final String TABLE_NAME = "User_Info_table";
    public static final String COL_1 = "User_Name";
    public static final String COL_2 = "User_Pass";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table_name( col1 text prik, col2 text)
        db.execSQL("create table " + TABLE_NAME + " ("+COL_1+" text primary key, "+COL_2+" text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //update table
        //drop table if exists
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public  Boolean addData(String nameacc,String passacc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean resulf = true;

        ContentValues cv = new ContentValues();
        cv.put(COL_1,nameacc);
        cv.put(COL_2,passacc);
        long r = db.insert(TABLE_NAME,null,cv);
        if(r==-1)
        {
            resulf = false;
        }

        return  resulf;
    }

    //Dang nhap tai khoan
    public Boolean FindData(String namefind, String passfind)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String SQLStr =  "SELECT * FROM " +TABLE_NAME;
        Cursor cs = db.rawQuery(SQLStr,null);
        if(cs.moveToFirst())
        {
            do{
                String ktname = cs.getString(cs.getColumnIndex(COL_1));
                if(namefind.equals(ktname))
                {
                    String ktpass = cs.getString(cs.getColumnIndex(COL_2));
                    if(passfind.equals(ktpass))
                        return true;
                    else
                        return false;
                }
            }while (cs.moveToNext());
        }
        return false;
    }
}
