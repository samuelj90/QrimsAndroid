package com.myapp.qrims;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.util.ByteArrayBuffer;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

	/** Menu Flags**/
	public static final int MAINPAGE=1;
	public static final int LOGIN=2;
	public static final int HOME=3;
	public static final int CHANGEPASSWORD=4;
	public static final int GENERATEQR=5;
	public static final int GENERATEDQR=6;
	public static final int BILLHOME=7;
	public static final int BILLLIST=8;
	public static final int SETTINGS=9;

	/** Pages Declaration  **/
	MainPage mainPage=null;
	Login login=null;
	Home home=null;
	ChangePassword changePassword=null;
	CartHome cartHome=null;
	CartList cartList=null;
	Settings settings=null;
	Enterqty eQty=null;
	final Context context = this;
	
	/** Variable declarations **/
	String value;
	String inp=null;
	Calendar calendar=null;
	SimpleDateFormat simpleDateFormat=null;
	String formattedDateToday=null;
	String userName=null,userID="1";
	String apppath=Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"qr"+ File.separator ;
	String serverIP="192.168.1.12";
	String mykey = "This_is_my_Key";
	String contents="";
	String superMarketName="QRBS";
	String superMarketAddress="123052/Street,City\nTown";
	String superMarketPh="944858585858";
	String data="";
	int serverPort=1234;
	int flag = 0;
	public boolean qrflag=false;
	ArrayList<String> bList;
	private SettingsDBAdapter ob;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/** Pages initialization **/
		mainPage=new MainPage(this);
		login=new Login(this);
		home=new Home(this);
		changePassword=new ChangePassword(this);
		cartHome=new CartHome(this);
		cartList=new CartList(this);
		eQty=new Enterqty(this);
		bList=new ArrayList<String>();
		calendar=Calendar.getInstance();
		simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
		formattedDateToday=simpleDateFormat.format(calendar.getTime());
		userName="Guest";
		ob=new SettingsDBAdapter(this);
		try
		{
			super.onStart();
			ob.open();
			Cursor cursor=ob.getAll();
			if (cursor!=null){
				if(cursor.moveToFirst()){
					serverIP=cursor.getString(cursor.getColumnIndex("server_ip"));
					superMarketName=cursor.getString(cursor.getColumnIndex("name"));
					superMarketAddress=cursor.getString(cursor.getColumnIndex("address"));
					superMarketPh=cursor.getString(cursor.getColumnIndex("phone_number"));	
				}
				else{
					ob.insert("Name", "Address", "987654321", "192.168.1.1");
				}
			}
			else{
				ob.insert("Name", "Address", "987654321", "192.168.1.1");
			}
			ob.close();
		}
		catch(Exception ex)
		{
			pln("Error In Phone "+ex);
			ex.printStackTrace();
		}
		mainPage.set();
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
	}

	//@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

	public boolean onPrepareOptionsMenu(Menu menu){
		menu.clear();	
		if (flag == MAINPAGE)
			mainPage.populateMenu(menu);
		else if (flag == LOGIN)
			login.populateMenu(menu);
		else if (flag == HOME)
			home.populateMenu(menu);
		else if (flag ==CHANGEPASSWORD)
			changePassword.populateMenu(menu);
		else if (flag == BILLHOME)
			cartHome.populateMenu(menu);
		else if (flag == BILLLIST)
			cartList.populateMenu(menu);
		else if (flag == SETTINGS)
			settings.populateMenu(menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (flag == MAINPAGE)
			return (mainPage.applyMenuChoice(item));		
		else if (flag == LOGIN)
			return (login.applyMenuChoice(item));
		else if (flag == HOME)
			return (home.applyMenuChoice(item));
		else if (flag==CHANGEPASSWORD)
			return (changePassword.applyMenuChoice(item)); 
		else if (flag == BILLHOME)
			return (cartHome.applyMenuChoice(item));
		else if (flag == BILLLIST)
			return (cartList.applyMenuChoice(item));
		else if (flag == SETTINGS)
			return (settings.applyMenuChoice(item));
		return true;
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

	public void printToast(String text){
		Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
	}
	
	public boolean isOnline() {
		ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	public void decQR(){
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				// Handle successful scan
				String cleartext="";
				String crypto= intent.getStringExtra("SCAN_RESULT");
				try {
					cleartext = SimpleCrypto.decrypt(mykey, crypto);
					printToast(contents);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contents=cleartext;
				String str[]=contents.split(":");
				if(str.length>=4){
				for (int k = 0; k < 5;k++){
					bList.add(str[k]);
					
				}
				pln("You Have "+str[5]+" as compliments");
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				eQty.set();
				}else{
					pln("InvalidQR");
					cartHome.set();
				}
			} 
			else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

}
