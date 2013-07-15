package com.myapp.qrims;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends Activity{
	SettingsDBAdapter ob;
	Button ok, cancel;
	EditText serverIPET = null,addressET= null,superMarketNameET= null,phoneNumberET= null;
	public void onCreate(Bundle b) {
		super.onCreate(b);
		ob = new SettingsDBAdapter(this);//new SettingsDBAdapter(this,"qrbs",null,33);
		setContentView(R.layout.settings);
		serverIPET= (EditText) findViewById(R.id.serveripET);
		superMarketNameET=(EditText)findViewById(R.id.superMarketNameET);
		addressET=(EditText)findViewById(R.id.addressET);
		phoneNumberET=(EditText) findViewById(R.id.phoneNumberET);
		ok=(Button)findViewById(R.id.okB);
		cancel=(Button)findViewById(R.id.cancelB);
		try
		{
			super.onStart();
			ob.open();
			Cursor cursor=ob.getAll();
			if (cursor.moveToFirst()){
				serverIPET.setText(cursor.getString(cursor.getColumnIndex("server_ip")));
				superMarketNameET.setText(cursor.getString(cursor.getColumnIndex("name")));
				addressET.setText(cursor.getString(cursor.getColumnIndex("address")));
				phoneNumberET.setText(cursor.getString(cursor.getColumnIndex("phone_number")));
			}
			else{
				//ob.create();
				pln("No db");
			}
			ob.close();
			
		}
		catch(Exception ex)
		{
			CatchError(ex.toString());
			ex.printStackTrace();
		}
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				try
				{
					ob.open();
					boolean update = ob.update(1,superMarketNameET.getText().toString(),addressET.getText().toString(),phoneNumberET.getText().toString(),serverIPET.getText().toString());
					if (update) {
						pln("Database Updated Sucessfully restart the app to take the effect");
						ob.close();
						
					
					} else {
						ob.close();
						pln("oops! Some Error Occured try again later");
					}
				}
				catch(Exception ex)
				{
					CatchError(ex.toString());
				}
				
			}
		});//never close cursor
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		
	}
	public boolean applyMenuChoice(MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	void CatchError(String Exception)
	{
		Dialog diag=new Dialog(this);
		diag.setTitle("QRBS");
		TextView txt=new TextView(this);
		txt.setText(Exception);
		diag.setContentView(txt);
		diag.show();
	}
	public void pln(String message){
		new AlertDialog.Builder(this)
		.setTitle("QRBS Says")
		.setMessage(message)
		.setNeutralButton("Close", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dlg, int sumthin) {
			}
		})
		.show();
	}
	
}
