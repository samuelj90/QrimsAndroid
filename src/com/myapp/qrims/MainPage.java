package com.myapp.qrims;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.TextView;

public class MainPage {
	MainActivity ma;
	/** Page component declaration**/
	Button loginButton=null;
	ImageView bGImage=null;
	DigitalClock dClock=null;
	TextView helloTV=null,coppyTV=null,dateTV=null;
	
	public MainPage(MainActivity mainActivity) {
		// TODO Auto-generated constructor stub
		this.ma=mainActivity;
	}
	
	public void set(){
		ma.setContentView(R.layout.main_page);
		/** Page component initialization**/
		dClock=(DigitalClock)ma.findViewById(R.id.digitalClock);
        loginButton=(Button) ma.findViewById(R.id.loginB);
        // loginButton.setCol
    	loginButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View arg0) {
    			//Checking internet connectivity
    				//if (ma.isOnline()) 
    					ma.login.set();
    				//else
    					//Stay on this page
    				//	ma.printToast("A Working Internet Connectivity is Required Plz correct it and try again later!!");
    		}
    	});
    //	helloTV=(TextView)ma.findViewById(R.id.helloTV);
    //	helloTV.setText("Hello Guest \n We Welcomes You!");
    	coppyTV=(TextView)ma.findViewById(R.id.copyrightTV);
    	dateTV=(TextView)ma.findViewById(R.id.dateTV);
    	dateTV.setText(ma.formattedDateToday);
		ma.flag=ma.MAINPAGE;
	}

	public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();	
		menu.add(Menu.NONE, 1, Menu.NONE, "Exit");	
		menu.add(Menu.NONE, 2, Menu.NONE, "Settings");		
	}
	
	public boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			ma.finish();
			return (true);		
		case 2:
			//ma.settings.set();//server.set();//
			Intent settings =new Intent(this.ma,Settings.class);
			ma.startActivity(settings);
			
			return (true);
		default:
			ma.pln("Feature Not Active");
			return true;

		}
	}
}
