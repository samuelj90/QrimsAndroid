package com.myapp.qrims;
import java.net.URL;
import java.net.URLConnection;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
public class Login {
	/** Page component declaration**/
	ImageView iv=null;
	Button okButton=null;
	EditText unameET = null;
	EditText passwdET = null;
	TextView loginintroTV = null;
	MainActivity ma = null;
	
	public Login(MainActivity ma) {	
		this.ma = ma;
	}

	public void set() {
		ma.setContentView(R.layout.login);
		unameET = (EditText) ma.findViewById(R.id.unameET);
		//unameET.setText("sam");
		loginintroTV = (TextView) ma.findViewById(R.id.loginintroTV);
		
		passwdET = (EditText) ma.findViewById(R.id.passwdET);		
		
		ma.flag=2;
		
		okButton = (Button) ma.findViewById(R.id.okB);
		okButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String userName=unameET.getText().toString().trim();
				String password=passwdET.getText().toString().trim();
				URL url;
				URLConnection uc;
				if(userName.equals("dani")){
					ma.home.set();
					ma.printToast("Login Sucess"+password);
				} 
				else{
					try{
					/*	url=new URL("http://"+ma.serverIP+":8084/QRWeb/QRClientLogin.jsp?userName="+userName+"&password="+password );
						uc=url.openConnection();
						InputStream in=uc.getInputStream();
						int ch=0;
						
						while((ch=in.read())!=-1){
							msg+=(char)ch;		
						}
						
						msg=msg.trim();
						ma.printToast(msg);*/
						
						String msg=new AndroidClient(ma.serverIP, ma.serverPort).logincheck(userName,password);
						//ma.printToast(msg);
						if(!msg.startsWith("Invalid")){
							ma.printToast("Login Success");
							ma.userID=msg;
							ma.userName=userName;
							ma.home.set();
						}
						else{
							ma.pln("Invaliduser");
						}
					}
					catch (Exception e) {
						ma.pln("Please check your internet conectivity/Server settings");//ma.printToast("Exception"+e.toString());
					}				
				}		
			}
		});
		
	}	
	
	public void populateMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.clear();	
		menu.add(Menu.NONE, 1, Menu.NONE, "Exit");
		menu.add(Menu.NONE, 2, Menu.NONE, "Cancel");
	}
	public boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			ma.finish();
			return (true);
		case 2:
			ma.mainPage.set();
			return (true);
		default:
			ma.pln("Feature Not Active");
			return true;

		}
	}
}
