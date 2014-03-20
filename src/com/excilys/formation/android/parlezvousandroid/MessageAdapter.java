package com.excilys.formation.android.parlezvousandroid;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter{
	
	private List<Message> myMessages = Collections.emptyList();

	private final Context context;
	
	public MessageAdapter(Context context) {
		this.context = context;
	}
	
	public void updateMessages(List<Message> Messages) {
        this.myMessages = Messages;
        notifyDataSetChanged();
    }

	@Override
	public int getCount() {
		return myMessages.size();
	}

	@Override
	public Message getItem(int arg0) {
		return myMessages.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
	        convertView = LayoutInflater.from(context)
	          .inflate(R.layout.activity_list, parent, false);
	    }

	    TextView authorView = (TextView) convertView.findViewById(R.id.button1);
	    TextView messageView = (TextView) convertView.findViewById(R.id.button2);

	    Message message = getItem(position);
	    authorView.setText(message.author);
	    messageView.setText(message.message);

	    return convertView;
	}

}
