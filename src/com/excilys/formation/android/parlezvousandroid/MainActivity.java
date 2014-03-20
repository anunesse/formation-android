package com.excilys.formation.android.parlezvousandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private final String username = "admin";
	private final String password = "admin";
	private final String TAG = "PONEY "+MainActivity.class.getSimpleName();

	ConnectTask myTask;
	
	private Button button1;
	private Button button2;

	@Override
	public void onClick(View myView) {
		switch(myView.getId()){
			case R.id.button1:
				((Button)findViewById(R.id.button1)).setEnabled(false);
				if(myTask == null)
					myTask = new ConnectTask();
				myTask.execute();
				break;
			case R.id.button2:
				((EditText)findViewById(R.id.usr)).setText("");
				((EditText)findViewById(R.id.pwd)).setText("");
				break;
		}
	}
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.i(TAG, "Creating!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Log.i(TAG, "Creating option menu!");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onDestroy(){
    	Log.i(TAG, "Destroying!");
    	super.onDestroy();
    }
    
    @Override
    public void onPause(){
    	Log.i(TAG, "Pausing!!");
    	if(myTask != null)
    		myTask.cancel(true);
    	super.onPause();
    }
    
    @Override
    public void onResume(){
    	Log.i(TAG, "Resuming!");
    	myTask = new ConnectTask();
    	super.onResume();
    }
    
    @Override
    public void onRestoreInstanceState(Bundle bundle){
    	Log.i(TAG, "Restoring instance!");
    	super.onRestoreInstanceState(bundle);
    }
    
    private class ConnectTask extends AsyncTask<String, Void, Boolean>{
    	@Override
    	protected void onPreExecute(){
    		ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressbar);
    		progressbar.setVisibility(View.VISIBLE);
    	}

    	@Override
    	protected Boolean doInBackground(String... params) {
    		InputStream content = null;
    		try {
	    			DefaultHttpClient client = new DefaultHttpClient();
	    			HttpGet request = new HttpGet("http://parlezvous.herokuapp.com/connect/alex/alex");
	    			HttpResponse response = client.execute(request); 
	    			content = response.getEntity().getContent();
    			} catch (ClientProtocolException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		return Boolean.valueOf(InputStreamToString.convert(content));
    	}
    	@Override
    	protected void onPostExecute(Boolean result){
    		ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressbar);
    		progressbar.setVisibility(View.GONE);
    		if(result){
    			Intent intent = new Intent(MainActivity.this, ConnectedActivity.class);
    			EditText editText = (EditText) findViewById(R.id.usr);
    			String message = editText.getText().toString();
    			intent.putExtra("name", message);
    			MainActivity.this.startActivity(intent);
    			MainActivity.this.finish();
    		}
    		else{
    			Toast.makeText(getApplicationContext(),getString(R.string.label_login_wrong),0).show();
    		}
    	}
    }
    
    public static class InputStreamToString {
    	public static String convert(InputStream is) {
	    	String line = "";
	    	StringBuilder builder = new StringBuilder();
	    	BufferedReader rd=new BufferedReader(new InputStreamReader(is));
		    	 
	    	try {
			    	while ((line = rd.readLine()) != null) {
			    		builder.append(line);
			    	}
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	} 
		    	return builder.toString();
	    	}
    	}
}




