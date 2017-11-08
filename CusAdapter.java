package com.example.ashwin.sqlitetry2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Ashwin on 11/8/2017.
 */

public class CusAdapter {

    Helper help;

    public CusAdapter(Context context) {
        help = new Helper(context);
    }

    public long insertdata(String name, String pass){
        SQLiteDatabase db = help.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(help.NAME, name);
        cv.put(help.PASSWORD, pass);
        long id =db.insert(help.TABLENAME, null, cv);
        return id;
    }

    public String getdata(){
        SQLiteDatabase db = help.getWritableDatabase();
        String[] columns = {help.UID, help.NAME, help.PASSWORD};
        Cursor cursor =db.query(help.TABLENAME, columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(help.UID));
            String name =cursor.getString(cursor.getColumnIndex(help.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(help.PASSWORD));
            buffer.append(cid+ "   " + name + "   " + password +" \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = help.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(help.TABLENAME ,help.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = help.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(help.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(help.TABLENAME,contentValues, help.NAME+" = ?",whereArgs );
        return count;
    }
}

class Helper extends SQLiteOpenHelper
{
    public static final String DBNAME = "Lavbase";
    public static final String TABLENAME = "LavTab";
    public static final int DBVersion = 1;
    public static final String UID = "id";
    public static final String NAME = "Name";
    public static final String PASSWORD = "Password";
    private static final String CREATETABLE = "CREATE TABLE "+TABLENAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ PASSWORD+" VARCHAR(225));";
    private static final String DROPTABLE ="DROP TABLE IF EXISTS "+TABLENAME;
    private Context context;


    public Helper(Context context) {
        super(context, DBNAME, null, DBVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATETABLE);
        } catch (Exception e) {
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context,"OnUpgrade");
            db.execSQL(DROPTABLE);
            onCreate(db);
        }catch (Exception e) {
            Message.message(context,""+e);
        }
    }
}

class Message{
    public static void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}