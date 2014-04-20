package com.example.helloworld3;

//import com.uphouseworks.android_http_example.R;
//import com.uphouseworks.android_http_example.RetrieveJson;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

//public class MainActivity extends ActionBarActivity {
public class MainActivity extends ActionBarActivity  {
	//
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button) findViewById(R.id.search_btn);

		btn.setOnClickListener(new View.OnClickListener() {

		    public void onClick(View v) {
		    	myClick2(v); /* my method to call new intent or activity */
		    	//TextView txt = (TextView) findViewById(R.id.lbSearch);
		    	//txt.setText("searching...");
		    	
		    	/*
		    	
		        */
		        
		    }
		});
	
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
                    TextView lbResult = (TextView) findViewById(R.id.lbSearch);
                    lbResult.setText(result);
                	
                    //button.setText("Get your Data");
                }
            };
            //button.setText("working...");
            rt.execute(url);
        }
    }
	
	public void myClick2(View v) {
		
		Intent intent = new Intent(this, ArrivalsActivity.class);
	    startActivity(intent);
	    
	    TextView txt = (TextView) findViewById(R.id.lbSearch);
    	txt.setText("searching...");
    	
		/*
    	EditText editText = (EditText) findViewById(R.id.stopId);
        if(editText.getText() != null)
        {
        
            String ourURL = editText.getText().toString();
        
            retrieveAndShowJson(ourURL);
        }
        */
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	
/*
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
*/
}
