package com.myapp.qrims;



import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePassword {
	MainActivity ma=null;
	Button update;
	EditText oldpasswdET = null, newpasswdET = null, confirmpasswdET = null;
	TextView oldpasswdTV = null, newpasswdTV = null, confirmpasswdTV = null,changePasswordIntroTV=null;

	public ChangePassword(MainActivity ma) {
		// TODO Auto-generated constructor stub
		this.ma=ma;
	}
	public void set() {
		ma.setContentView(R.layout.changepassword);	
		update = (Button) ma.findViewById(R.id.update);
		update.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if(newpasswdET.getText().toString().equals(confirmpasswdET.getText().toString()))
				{	 
				String msg=new AndroidClient(ma.serverIP, ma.serverPort).changePassword(ma.userID,newpasswdET.getText()+"");
				if(msg.startsWith("success")){
					ma.pln("Password Changed"+msg);
					ma.home.set();
				}
				else
					ma.pln(""+msg+"Error occured");
				}
				else
					ma.pln("New Passwords doesnt match");
				
			}
		});
		oldpasswdET = (EditText) ma.findViewById(R.id.currentPasswordET);

		newpasswdET = (EditText) ma.findViewById(R.id.newpasswordET);

		confirmpasswdET = (EditText) ma.findViewById(R.id.confirmNewpasswordET);
		
		changePasswordIntroTV=(TextView)ma.findViewById(R.id.changePasswordIntroTV);
		ma.flag = ma.CHANGEPASSWORD;
		
	}
	public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();
		menu.add(Menu.NONE, 1, Menu.NONE, "Home");
		menu.add(Menu.NONE, 2, Menu.NONE, "Logout");
		menu.add(Menu.NONE, 3, Menu.NONE, "Exit");
		
	}
	public boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			ma.home.set();
			return true;
		case 2:
			ma.login.set();
			return (true);
		case 3:
			ma.finish();
			return (true);

		}

		 return(false);
	}
}
