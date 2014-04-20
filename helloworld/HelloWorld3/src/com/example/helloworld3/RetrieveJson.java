package com.example.helloworld3;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class RetrieveJson extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... voids) {
        if(voids[0] == null)
             return "No url provided.";
        String url = voids[0];
        StringBuilder builder = new StringBuilder();
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            Log.e("HTTP CLIENT:", "Sth passed!");
            
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(MainActivity.class.toString(), "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            System.out.println("-->Client Protocol Exception in RetrieveJson");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("-->IOException Exception in RetrieveJson");
            e.printStackTrace();
        } catch (IllegalStateException ise) {
            System.out.println("-->IllegalState Exception in RetrieveJson");
            ise.printStackTrace();
        } catch (IllegalArgumentException iae) {
            System.out.println("-->IllegalArgument Exception in RetrieveJson");
            iae.printStackTrace();
        }
        catch (Exception e)
        {
           //The task failed
           Log.e("HTTP CLIENT: e=", e.getMessage());           
        }
        return (!builder.toString().equals(""))?builder.toString():"Nothing found, is your url correct?";
    }

    @Override
    protected void onPostExecute(String aVoid)
    {
        super.onPostExecute(aVoid);

        //This is the key here,
        //onPostExecute is called on completion, we can have this push
        //our result onto our callback 'onResponseReceived'
        onResponseReceived(aVoid);
    }

    //our callback, note the 'abstract' mark in the method name,
    //this indicates that this method Must be overridden by the caller
    public abstract void onResponseReceived(String result);
}