package com.myapp.qrims;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.itextpdf.text.pdf.codec.Base64.InputStream;

public class CartHome extends Application {
	 /* @override*/
	public void onCreate() {
	    super.onCreate();
	    // TODO Put your application initialization code here.
	}
	
	MainActivity ma =null;
    TextView selection;
    GridView gridView;
    Intent intnet;
    
	public CartHome(MainActivity ma) {
		// TODO Auto-generated constructor stub
		this.ma = ma;	
	}
	
	public void set(){
		
		ma.setContentView(R.layout.grid);
        Log.i("QRIMS_Staff_App bill hmt", "billlist"+ma.cartList);
        selection= (TextView)ma.findViewById(R.id.selection);
        gridView=(GridView)ma.findViewById(R.id.grid);
        gridView.setAdapter(new ArrayAdapter(ma,android.R.layout.simple_list_item_1,ma.bList));
        System.out.print(ma.bList.toString());
        ma.flag=ma.BILLHOME;
    }
	public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();
		menu.add(Menu.NONE,1,Menu.NONE,"Add Items");
		menu.add(Menu.NONE,2,Menu.NONE,"Edit Items");
		menu.add(Menu.NONE,3,Menu.NONE,"Total");
		menu.add(Menu.NONE,4,Menu.NONE,"Checkout");
		menu.add(Menu.NONE,5,Menu.NONE,"Clear Cart");
		
	}
	public boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			ma.decQR();
		/*	String contents="1:colgate:50:2:2";
			//String qty=ma.popup();//"1";//ma.popUpInput();
        	String str[]=contents.split(":");
			for (int k = 0; k < str.length; k++) {
				ma.bList.add(str[k]);
			}
			//ma.bList.add(qty);*/
			System.out.print(ma.bList.toString());
			
			return (true);

		case 2:
			ma.cartList.set();	
			return (true);
		case 3:
			ma.pln("Total ="+calc(ma.bList));
			return true;
		case 4:
			checkout();
			return (true);
		case 5:
			ma.bList=new ArrayList<String>();
			ma.pln("Purchase Cancelled ");
			ma.home.set();
			return (true);
		
		default:
			ma.pln("Feature Not Active");
			return true;

		}
	}
	 private void checkout() {
		// TODO Auto-generated method stub
		 String reply=new AndroidClient(ma.serverIP, ma.serverPort).saveCart(ma.bList,ma.userID);
		 ma.pln(reply);
		 ma.home.set();
	}

	public String convertStreamToString(InputStream inputStream) throws IOException {
		if (inputStream != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				inputStream.close();
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	 public double calc(ArrayList<String> list){
		double tot = 0.0;
		for (int k = 6; k < list.size(); k += 6) {
			tot += ((Double.parseDouble(list.get(k + 2)) - Double.parseDouble(list.get(k + 3))) + Double.parseDouble(list.get(k + 4)))* Double.parseDouble(list.get(k + 5));
		}
		return tot;
	    }
	    
}
