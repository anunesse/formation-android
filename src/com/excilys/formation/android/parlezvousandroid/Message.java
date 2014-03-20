package com.excilys.formation.android.parlezvousandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Message {
	public String author;
	public String message;
	
	
	
	public Message(String author, String message) {
		super();
		this.author = author;
		this.message = message;
	}



	@Override
	public String toString() {
		return "Message [author=" + author + ", message=" + message + "]";
	}



	public static List<Message> convert(InputStream is) {
		List<Message> myList = new ArrayList<Message>();
    	String line = "";
    	StringBuilder builder = new StringBuilder();
    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));	 
    	try {
	    	while ((line = rd.readLine()) != null) {
	    		builder.append(line);
	    	}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	    String[] myStrings = builder.toString().split(";");
	    for(String str : myStrings){
	    	Message myMessage = new Message(str.substring(0, str.indexOf(":")), str.substring(str.indexOf(":")+1));
	    	myList.add(myMessage);
	    	//System.out.println(myMessage);
	    }
    	return myList;
	}
	
}
