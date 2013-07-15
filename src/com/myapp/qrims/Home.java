package com.myapp.qrims;

import java.util.ArrayList;


import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends Activity  {
	MainActivity ma=null;
	ImageView bGImage=null;
	DigitalClock digitalClock=null;
	TextView helloTV=null,dateTV=null;

	public Home(MainActivity ma) {
		this.ma=ma;
	}
	
	public void set(){
		ma.setContentView(R.layout.home);
		digitalClock=(DigitalClock)ma.findViewById(R.id.digitalClock);
		helloTV=(TextView)ma.findViewById(R.id.helloTV);
    	helloTV.setText("Hello "+ma.userName+"!");
    	dateTV=(TextView)ma.findViewById(R.id.dateTV);
    	dateTV.setText(ma.formattedDateToday);
		ma.flag=ma.HOME;
	}
	
	public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();	
		menu.add(Menu.NONE,1,Menu.NONE,"Create Cart");
		menu.add(Menu.NONE,2,Menu.NONE,"Change Password");
		menu.add(Menu.NONE,3,Menu.NONE,"Logout");
		menu.add(Menu.NONE,4,Menu.NONE,"Exit");
	}
	
	public boolean applyMenuChoice(MenuItem item) {
		// TODO Auto-generated method stub		
		switch (item.getItemId()) {
		case 1:
			addHeader();
			ma.cartHome.set();	
			return (true);
		case 2:
			ma.changePassword.set();
			return (true);
		case 3:
			ma.mainPage.set();
			return (true);
		case 4:
			ma.finish();
			return (true);
		default:
			ma.pln("Feature Not Active");
			return true;
		}
	}

	private void addHeader() {
		// TODO Auto-generated method stub
		ma.bList=new ArrayList<String>();
		ma.bList.add("ID");
		ma.bList.add("Name");
		ma.bList.add("Price");
		ma.bList.add("Discount");
		ma.bList.add("Tax");
		ma.bList.add("Qty");
	}

}