package com.myapp.qrims;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SettingsDBAdapter {

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "qrbs";
	private SettingsOpenHelper dbOpenHelper;
	private SQLiteDatabase db;

	/*
	 * public SettingsDBAdapter(Context context) { super(context, DATABASE_NAME,
	 * null, DATABASE_VERSION); }
	 */

	public SettingsDBAdapter(Context context) {
		// super(context, name, factory, version);
		dbOpenHelper = new SettingsOpenHelper(context, DATABASE_NAME, null,	DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	public void open() throws SQLException {
		// open database in reading/writing mode
		db = dbOpenHelper.getWritableDatabase();
	}

	public void close() {
		if (db != null)
			db.close();
	}

	public Cursor getAll() {
		// TODO Auto-generated method stub

		// Cursor cursor=
		return db.query("settings", new String[] { "id", "name", "address","phone_number", "server_ip" }, null, null, null, null, null);
		// return cursor;
	}

	public long insert(String name, String address, String phno, String ip) {

		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("address", address);
		values.put("phone_number", phno);
		values.put("server_ip", ip);

		// db = db.getApplicationContext().openOrCreateDatabase(DATABASE_PATH,
		// SQLiteDatabase.OPEN_READWRITE, null);
		// Inserting Row
		open();
		long i = db.insert("settings", "id", values);
		close();
		return i;
	}
	public boolean update(long rowID,String name ,String address,String phno,String ip)
	{ 
		ContentValues values = new ContentValues();
		values.put("name",  name); 
		values.put("address", address);
		values.put("phone_number", phno);
		values.put("server_ip", ip);
		return db.update ("settings",values,"id="+rowID,null)>0;
	
	}

}
