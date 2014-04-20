package com.example.helloworld3;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//public class ArrivalsActivity extends ListActivity {
public class ArrivalsActivity extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.arrivals);
        
        /*
        Button btn = (Button) findViewById(R.id.addBtn);

		btn.setOnClickListener(new View.OnClickListener() {

		    public void onClick(View v) {
		    	//myClick(v); 
		    	
		        
		    }
		});
        */
        
        adapter=new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            listItems);
        setListAdapter(adapter);
    }
    
    /**
     * ReadJson now calls to an Async process, keeps the main thread free!
     * We listen for the reply to our request through the onResponseReceived callback
     * When the asynctask is done this will be called, and we will see the results on screen!
     * @param url --Target url where our json will be coming from
     */
    public void retrieveAndShowJson(String url) {
        if(url != null)
        {
            RetrieveJson rt = new RetrieveJson()
            {
                @Override
                public void onResponseReceived(String result)
                {
                    //textView.setText(result);
                    //TextView lbResult = (TextView) findViewById(R.id.lbSearch);
                    //lbResult.setText(result);
                    try {
        				JSONObject jsonObj = new JSONObject(result);
        				
        				//String arriv = jsonObj[0];
        			} catch (JSONException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
                    
                	listItems.add("Arrival : "+ result);
                    adapter.notifyDataSetChanged();
                	
                    //button.setText("Get your Data");
                }
            };
            //button.setText("working...");
            rt.execute(url);
        }
    }

    
    public void myClick(View v) {
		
    	EditText editText = (EditText) findViewById(R.id.stop_id_2);
        if(editText.getText() != null)
        {
        
            String ourURL = editText.getText().toString();
        
            retrieveAndShowJson(ourURL);
        }
        
        
	}

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
    	
    	EditText editText = (EditText) findViewById(R.id.stop_id_2);
        if(editText.getText() != null)
        {
        
            //String ourURL = editText.getText().toString();
            String ourURL = "http://www.corvallis-bus.appspot.com/arrivals?stops=14413";

            
            retrieveAndShowJson(ourURL);
        }
        
    	
        //listItems.add("Clicked : "+clickCounter++);
        //adapter.notifyDataSetChanged();
    }

}