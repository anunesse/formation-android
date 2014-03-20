package com.excilys.formation.android.parlezvousandroid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;


public class ListActivity extends android.app.ListActivity {

	RetrieveListTask myTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		myTask = new RetrieveListTask();
		myTask.execute();
		ListView listView = (ListView)findViewById(R.id.list);
		// Using Implemented BaseAdapter
		//	    BaseAdapter adapter = new MessageAdapter(this);
		//	    setListAdapter(adapter);
		
		//Using ArrayAdapter
		List<String> values = Arrays.asList("Poney", "Ninja", "Hello girls");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.id.list, values);
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void onResume(){
		
	}
	
	private class RetrieveListTask extends AsyncTask<String, Void, List<Message>>{
    	@Override
    	protected void onPreExecute(){
    		ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressbar);
    		progressbar.setVisibility(View.VISIBLE);
    	}

    	@Override
    	protected List<Message> doInBackground(String... params) {
    		InputStream content = null;
    		try {
	    			DefaultHttpClient client = new DefaultHttpClient();
	    			HttpGet request = new HttpGet("http://parlezvous.herokuapp.com/messages/alex/alex");
	    			HttpResponse response = client.execute(request); 
	    			content = response.getEntity().getContent();
    			} catch (ClientProtocolException e) {
    				e.printStackTrace();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		return Message.convert(content);
    		//return Boolean.valueOf(InputStreamToString.convert(content));
    	}
    	@Override
    	protected void onPostExecute(List<Message> myMessages){
    		ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressbar);
    		progressbar.setVisibility(View.GONE);
    	}

    }
	
	
	//rodolphe:test;rodolphe:test2;
	public class MessagesAdapter extends BaseAdapter {

		private List<Message> myMessages = Collections.emptyList();

	    private final Context context = ListActivity.this.getApplicationContext();
		
		@Override
		public int getCount() {
			return myMessages.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			return null;
		}
		
	}
}
