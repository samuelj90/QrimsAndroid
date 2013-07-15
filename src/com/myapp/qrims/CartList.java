package com.myapp.qrims;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.os.Bundle;
//import android.widget.Adapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CartList  
	implements AdapterView.OnItemSelectedListener {
    
	MainActivity ma=null;
	TextView selection;
	/*Called when the activity is first created.*/
    //@Override
    ArrayList<String> list=new ArrayList<String>();
    Spinner spin=null;
    ArrayList<String> spinnerList=null;
    public CartList(MainActivity ma){
    	this.ma=ma;
    }
    public void set(){//ArrayList<String> list) {
        this.list=ma.bList;
        spinnerList = new ArrayList<String>();
        for (int k = 7; k < list.size(); k+=6) {
			spinnerList.add(list.get(k));
		}
        ma.setContentView(R.layout.spinner);
        selection=(TextView)ma.findViewById(R.id.selection);
        spin=(Spinner)ma.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        
        ArrayAdapter<String> aa =new ArrayAdapter<String>(ma,android.R.layout.simple_spinner_item,spinnerList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        ma.flag=ma.BILLLIST;
    }

    public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();
		menu.add(Menu.NONE, 1, Menu.NONE, "Delete");
		menu.add(Menu.NONE, 2, Menu.NONE, "Back");
		
	}
	public boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			//if(spin.getSelectedItemPosition()>=1){
		//	for( int k=1;k<=5;k++){
		//	if(spin.){
				ma.pln(spin.getSelectedItemPosition()+"spin.position");
				int pos=6+(6*spin.getSelectedItemPosition());
				for(int i=0;i<6;i++)
					ma.bList.remove(pos);
			/*	ma.pln(pos+"");ma.pln(ma.bList.toString());
				List<String> sublist = ma.bList.subList(pos, pos+5);
				ma.pln("Sublist"+sublist.toString());//remove(pos,pos+5);*/
				//ma.bList.removeAll(ma.bList.subList(pos, pos+5));
				//ma.bList.removeAll(sublist);
			//}
			//}
				//ma.billHome.set();
		//	}
				set();
			return (true);
		case 2:
			ma.cartHome.set();
			return (true);
		default:
			ma.pln("Feature Not Active");
			return true;
		}
	}
	//@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {
		// TODO Auto-generated method stub
		selection.setText(spinnerList.get(position));
		
	}

	//@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		selection.setText("");
	}
}