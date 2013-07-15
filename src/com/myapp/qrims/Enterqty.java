package com.myapp.qrims;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Enterqty {
	MainActivity ma;
	EditText eQtyET;
	TextView eqtyTV;
	Button eQtyB;
	public Enterqty(MainActivity ma) {
		// TODO Auto-generated constructor stub
		this.ma=ma; 
	}
	public void set() {
		// TODO Auto-generated method stub
		ma.setContentView(R.layout.prompt);
		eQtyET=(EditText)ma.findViewById(R.id.eQtyET);
		eqtyTV=(TextView)ma.findViewById(R.id.eQtyTV);
		eQtyB=(Button)ma.findViewById(R.id.eQtyB);
		eQtyB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				ma.bList.add(eQtyET.getText().toString());
				ma.cartHome.set();
			}
		});
	}

}
