package com.excilys.formation.android.parlezvousandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectedActivity extends Activity implements OnClickListener{

	private final String TAG = "PONEY "+MainActivity.class.getSimpleName();
	
	Button button_list;
	Button button_send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connected);
		Intent myIntent = this.getIntent();
		((TextView)findViewById(R.id.say_hello)).setText("Hello "+myIntent.getStringExtra("name"));
		
		button_list = (Button) findViewById(R.id.list);
		button_list.setOnClickListener(this);
        button_send = (Button) findViewById(R.id.send);
        button_send.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connected, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
			switch(v.getId()){
			case R.id.list:
				Log.i(TAG, "listing!");
				Intent myIntent = new Intent(this, ListActivity.class);
				this.startActivity(myIntent);
				break;
			case R.id.send:
				Log.i(TAG, "sending!");
				break;
		}
	}
}
