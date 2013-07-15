package com.myapp.qrims;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SettingsOpenHelper  extends SQLiteOpenHelper{
	
    private static final String TABLE_NAME = "settings";
    private static final String TABLE_CREATE ="CREATE TABLE " + TABLE_NAME + "(id  INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR NOT NULL,address VARCHAR NOT NULL ,phone_number VARCHAR NOT NULL,server_ip VARCHAR NOT NULL)";
   
	public SettingsOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d("creating table: ", "creating ..");
		db.execSQL(TABLE_CREATE);
		Log.d("Insert: ", "Inserting ..");
	//	intialize(db);
	}

/*	private void intialize(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		values.put("name", "Name"); 
		values.put("address", "Address"); 
		values.put("phone_number", "9876543210"); 
		values.put("server_ip","192.168.1.1");
		db.insert("settings", null, values);
		Log.d("Inserted: ", "Inserted ..");
	}
*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
